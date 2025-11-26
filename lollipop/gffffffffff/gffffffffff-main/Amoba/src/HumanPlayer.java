import java.util.Scanner;

public class HumanPlayer extends Player {

    private Scanner scanner = new Scanner(System.in);

    public HumanPlayer(String name, char symbol) {
        super(name, symbol);
    }

    @Override
    public int[] getMove(Board board) {
        int size = board.getSize();

        System.out.println(name + " (" + symbol + ")");

        System.out.print("X (1-" + size + "): ");
        int x = scanner.nextInt() - 1;

        System.out.print("Y (1-" + size + "): ");
        int y = scanner.nextInt() - 1;

        return new int[]{x, y};
    }
}
