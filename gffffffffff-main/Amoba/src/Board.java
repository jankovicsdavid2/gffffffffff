public class Board {
    private char[][] grid;
    private int size;

    public Board(int size) {
        this.size = size;
        grid = new char[size][size];

        for (int y = 0; y < size; y++)
            for (int x = 0; x < size; x++)
                grid[y][x] = '.';
    }

    public int getSize() {
        return size;
    }

    public boolean isValidMove(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size && grid[y][x] == '.';
    }

    public void place(int x, int y, char symbol) {
        grid[y][x] = symbol;
    }

    public void print() {
        System.out.println();
        System.out.print("   ");
        for (int x = 1; x <= size; x++)
            System.out.printf("%2d ", x);
        System.out.println();

        for (int y = 0; y < size; y++) {
            System.out.printf("%2d ", y + 1);
            for (int x = 0; x < size; x++) {
                System.out.print(" " + grid[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean checkWin(int x, int y, char symbol, int winLength) {
        return checkDir(x, y, symbol, 1, 0, winLength) ||
                checkDir(x, y, symbol, 0, 1, winLength) ||
                checkDir(x, y, symbol, 1, 1, winLength) ||
                checkDir(x, y, symbol, 1, -1, winLength);
    }

    private boolean checkDir(int x, int y, char sym, int dx, int dy, int winLength) {
        int count = 1;

        count += countOneSide(x, y, sym, dx, dy);
        count += countOneSide(x, y, sym, -dx, -dy);

        return count >= winLength;
    }

    private int countOneSide(int x, int y, char sym, int dx, int dy) {
        int count = 0;
        int nx = x + dx;
        int ny = y + dy;

        while (nx >= 0 && nx < size && ny >= 0 && ny < size &&
                grid[ny][nx] == sym) {
            count++;
            nx += dx;
            ny += dy;
        }
        return count;
    }
}

