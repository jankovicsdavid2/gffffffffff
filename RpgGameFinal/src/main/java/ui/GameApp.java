package ui;

import character.Characters;
import character.Mage;
import character.Warrior;
import enemies.EMage;
import enemies.EWarrior;
import enemies.Enemy;
import inventory.Inventory;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Random;

public class GameApp extends Application {

    private Characters character;
    private Enemy enemy;
    private int encounterCount = 0;

    private TextArea logArea;
    private Label playerStats;
    private Label enemyStats;

    private final Random rand = new Random();

    @Override
    public void start(Stage stage) {
        stage.setTitle("RPG Game");

        Scene creationScene = createCharacterCreationScene(stage);
        stage.setScene(creationScene);
        stage.show();
    }

    // ========== SCENE 1: CHARACTER CREATION ==========

    private Scene createCharacterCreationScene(Stage stage) {
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        Label title = new Label("Welcome to character creation!");
        title.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");

        ToggleGroup group = new ToggleGroup();
        RadioButton mageBtn = new RadioButton("Mage");
        mageBtn.setToggleGroup(group);
        RadioButton warriorBtn = new RadioButton("Warrior");
        warriorBtn.setToggleGroup(group);

        HBox classBox = new HBox(10, mageBtn, warriorBtn);
        classBox.setAlignment(Pos.CENTER);

        TextField nameField = new TextField();
        nameField.setPromptText("Enter your character's name");

        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> {
            if (group.getSelectedToggle() == null || nameField.getText().isBlank()) {
                showAlert(Alert.AlertType.ERROR, "Missing data",
                        "Please choose a class and enter a name.");
                return;
            }

            String name = nameField.getText().trim();
            if (mageBtn.isSelected()) {
                character = new Mage(name);
            } else {
                character = new Warrior(name);
            }

            // Game scene
            stage.setScene(createGameScene(stage));
        });

        root.getChildren().addAll(title, classBox, nameField, startButton);

        return new Scene(root, 400, 300);
    }

    // ========== SCENE 2: MAIN GAME SCREEN ==========

    private Scene createGameScene(Stage stage) {
        BorderPane root = new BorderPane();

        // Top: Status bar area
        HBox statusBox = new HBox(40);
        statusBox.setPadding(new Insets(10));
        statusBox.setAlignment(Pos.TOP_LEFT);

        playerStats = new Label();
        enemyStats = new Label();

        playerStats.setStyle("-fx-font-family: monospace;");
        enemyStats.setStyle("-fx-font-family: monospace;");

        statusBox.getChildren().addAll(playerStats, enemyStats);

        // Center: Log area
        logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setWrapText(true);

        // Bottom: Buttons for actions
        HBox actions = new HBox(10);
        actions.setPadding(new Insets(10));
        actions.setAlignment(Pos.CENTER);

        Button attackBtn = new Button("Attack");
        Button specialBtn = new Button("Special Ability");
        Button statsBtn = new Button("View Stats");
        Button inventoryBtn = new Button("Inventory");
        Button sneakBtn = new Button("Sneak Past");

        actions.getChildren().addAll(attackBtn, specialBtn, statsBtn, inventoryBtn, sneakBtn);

        // Hook button actions
        attackBtn.setOnAction(e -> handleAttack());
        specialBtn.setOnAction(e -> handleSpecial());
        statsBtn.setOnAction(e -> log(character.toString()));
        inventoryBtn.setOnAction(e -> handleInventory());
        sneakBtn.setOnAction(e -> handleSneak());

        root.setTop(statusBox);
        root.setCenter(logArea);
        root.setBottom(actions);

        // Initial message + first encounter
        log("Welcome to the game, " + character.getName() + "!");
        log("If you wish to stop playing, just close the window.");
        nextEncounter();

        return new Scene(root, 800, 600);
    }

    // ========== GAME FLOW / ENCOUNTERS ==========

    private void nextEncounter() {
        encounterCount++;

        int typeOfEnemy = rand.nextInt(10) + 1;

        int enemyLevel = character.getLevel();
        if (encounterCount < 5) {
            if (rand.nextInt(3) == 0 && enemyLevel > 1) {
                enemyLevel--;
            }
        } else if (encounterCount < 15 && rand.nextInt(5) == 0 && enemyLevel > 1) {
            enemyLevel++;
        } else if (encounterCount > 15) {
            enemyLevel++;
        }

        switch (typeOfEnemy % 2) {
            case 0 -> enemy = new EMage("Enemy Mage", enemyLevel);
            case 1 -> enemy = new EWarrior("Enemy Warrior", enemyLevel);
        }

        log("\n--- Encounter #" + encounterCount + " ---");
        log("You have encountered a " + enemy.getName() + "!");
        updateStats();
    }

    // ========== BUTTON HANDLERS ==========

    private void handleAttack() {
        if (enemy == null || character == null) return;

        character.attack(character, enemy);
        log(character.getName() + " attacks!");

        afterPlayerAction();
    }

    private void handleSpecial() {
        if (enemy == null || character == null) return;

        character.useSpecialAbility(character, enemy);
        log(character.getName() + " uses their special ability!");

        afterPlayerAction();
    }

    private void handleSneak() {
        if (enemy == null || character == null) return;

        log("You try to sneak past...");
        log("Escaped successfully!");
        enemy.setHealth(0);
        // Start a new encounter
        nextEncounter();
    }

    private void handleInventory() {
        // Your current inventory printing is console-based.
        // For now, just notify the player:
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Inventory");
        alert.setHeaderText("Inventory UI not wired yet");
        alert.setContentText("Your Inventory logic is there, but it prints to console.\n" +
                "We can refactor it to return text and show it in this window.");
        alert.showAndWait();
    }

    // ========== TURN RESOLUTION ==========

    private void afterPlayerAction() {
        // Check if enemy died
        if (enemy.getHealth() <= 0) {
            int xpGained = 50 + (enemy.getLevel() * 25);
            character.setXp(character.getXp() + xpGained);
            log("Victory! Gained " + xpGained + " XP!");

            if (character.getXp() >= character.getXpToNextLevel()) {
                character.levelUp();
                log("=== LEVEL UP! ===");
            }

            // Loot system currently uses Scanner, so we skip it in GUI for now.

            nextEncounter();
            return;
        }

        // Enemy still alive -> enemy attacks
        enemy.enemyAttack(enemy, character);
        log(enemy.getName() + " attacks!");

        // Check if player died
        if (character.getHealth() <= 0) {
            updateStats();
            showGameOver();
            return;
        }

        updateStats();
    }

    private void showGameOver() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("You lost!");
        alert.setContentText("You reached level " + character.getLevel() + ".\nPlay again?");

        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");
        alert.getButtonTypes().setAll(yes, no);

        var result = alert.showAndWait();
        if (result.isPresent() && result.get() == yes) {
            character.reset();
            encounterCount = 0;
            nextEncounter();
        } else {
            System.exit(0);
        }
    }

    // ========== HELPERS ==========

    private void updateStats() {
        if (character != null) {
            playerStats.setText(
                    "Player: " + character.getName() +
                            "\nLevel: " + character.getLevel() +
                            "\nHP: " + String.format("%.2f", character.getHealth()) +
                            "\nXP: " + character.getXp() + "/" + character.getXpToNextLevel()
            );
        }

        if (enemy != null) {
            enemyStats.setText(
                    "Enemy: " + enemy.getName() +
                            "\nLevel: " + enemy.getLevel() +
                            "\nHP: " + String.format("%.2f", enemy.getHealth())
            );
        } else {
            enemyStats.setText("No enemy");
        }
    }

    private void log(String text) {
        logArea.appendText(text + "\n");
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert a = new Alert(type);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(message);
        a.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
