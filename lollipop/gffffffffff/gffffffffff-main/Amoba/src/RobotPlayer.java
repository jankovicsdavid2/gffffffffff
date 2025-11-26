import java.util.Random;

public class RobotPlayer extends Player {

    private Random rand = new Random();

    public RobotPlayer(String name, char symbol) {
        super(name, symbol);
    }

    @Override
    public int[] getMove(Board board) {
        int size = board.getSize();

        int x, y;
        do {
            x = rand.nextInt(size);
            y = rand.nextInt(size);
        } while (!board.isValidMove(x, y));

        System.out.println(name + " (" + symbol + ") played at: " + (x+1) + ", " + (y+1));

        return new int[]{x, y};
    }
}
