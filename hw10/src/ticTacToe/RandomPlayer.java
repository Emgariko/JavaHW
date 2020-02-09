package ticTacToe;

import java.util.Random;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class RandomPlayer implements Player {
    private final Random random;
    private int n, m, k;

    public RandomPlayer(final Random random) {
        this.random = random;
    }

    public RandomPlayer(int row, int column, int cnt) {
        n = row;
        m = column;
        k = cnt;
        random = new Random();
    }
    public RandomPlayer() {
        this(new Random());
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            int r = random.nextInt(n);
            int c = random.nextInt(m);
            final Move move = new Move(r, c, cell);
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}
