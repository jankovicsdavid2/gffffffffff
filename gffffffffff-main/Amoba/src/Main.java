import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== AMOEBA GAME ===");

        System.out.print("Board size: ");
        int size = sc.nextInt();

        System.out.print("Win length: ");
        int winLength = sc.nextInt();

        Board board = new Board(size);

        Player p1 = new RobotPlayer("robotoo", 'X');
        Player p2 = new RobotPlayer("Robot", 'O');

        Player current = p1;
        int moves = 0;

        while (true) {
            board.print();

            int[] move = current.getMove(board);
            int x = move[0];
            int y = move[1];

            if (!board.isValidMove(x, y)) {
                System.out.println("Invalid move! Try again.");
                continue;
            }

            board.place(x, y, current.getSymbol());
            moves++;

            if (board.checkWin(x, y, current.getSymbol(), winLength)) {
                board.print();
                System.out.println(current.getName() + " WINS!");
                break;
            }

            if (moves == size * size) {
                board.print();
                System.out.println("Draw!");
                break;
            }

            current = (current == p1) ? p2 : p1;
        }

        sc.close();
    }
}
