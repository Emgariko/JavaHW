package ticTacToe;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */

public class ServerBoard implements Board {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.',
            Cell.L, 'L',
            Cell.T, 'T'
    );

    private final Cell[] nextOf;
    private final Cell[][] cells;
    private final boolean[][] isValid;
    private final Map<Cell, Integer> num = Map.of(
            Cell.X, 0, Cell.O, 1, Cell.L, 2, Cell.T, 3
    );
    private Cell turn;
    private int n, m, k, cellsCount, playersCount, mode, mxCellsCountRh;

    public ServerBoard(int h, int w, int cnt, int players, String modeString) {
        n = h;
        m = w;
        k = cnt;
        cellsCount = 0;
        mxCellsCountRh = n * m;
        playersCount = players;
        nextOf = new Cell[players];
        if (modeString.equals("Rh")) {
            mode = 1;
        } else {
            mode = 0;
        }
        if (players == 2) {
            nextOf[0] = Cell.O;
            nextOf[1] = Cell.X;
        } else if (players == 3) {
            nextOf[0] = Cell.O;
            nextOf[1] = Cell.L;
            nextOf[2] = Cell.X;
        } else if (players == 4) {
            nextOf[0] = Cell.O;
            nextOf[1] = Cell.L;
            nextOf[2] = Cell.T;
            nextOf[3] = Cell.X;
        }
        this.cells = new Cell[n][m];

        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
        isValid = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                isValid[i][j] = true;
            }
        }
        if (mode == 1) { // RHombus init
            if (n % 2 != 0) {
                int k = n / 2, iHateJava = -1;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < k; j++) {
                        isValid[i][j] = false;
                        isValid[i][m - j - 1] = false;
                        mxCellsCountRh -= 2;
                    }
                    k += iHateJava;
                    if (k == 0) {
                        iHateJava = 1;
                    }
                }
            } else {
                int k = n / 2 - 1, iHateJava = -1;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < k; j++) {
                        isValid[i][j] = false;
                        isValid[i][m - j - 1] = false;
                        mxCellsCountRh -= 2;
                    }
                    k += iHateJava;
                    if (k == -1) {
                        k++;
                        iHateJava = 1;
                    }
                }
            }
        }
    }

    @Override
    public Position getPosition() {
        return new NmkBoard(this);
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    private boolean inField(int x, int y) {
        return 0 <= y && y <= n - 1 && 0 <= x && x <= m - 1;
    }

    private boolean checkWinning(int dy, int dx, int y, int x) {
        int count = 0;
        Cell[] buffer = new Cell[3 * k];
        Arrays.fill(buffer, Cell.E);
        int id = 0;
        int ny = y - k * dy, nx = x - k * dx;
        for (int i = 0; i < 2 * k - 1; i++) {
            ny += dy;
            nx += dx;
            if (!inField(nx, ny) || !isValid[ny][nx]) {
                id++;
                continue;
            }
            buffer[id++] = cells[ny][nx];
        }
        return (checkBuffer(buffer, id));
    }

    private boolean checkBuffer(Cell[] buffer, int id) {
        int cnt = 0;
        for (int i = 0; i < id; i++) {
            if (buffer[i] == turn) {
                cnt++;
            } else {
                if (cnt >= k) { return true; } else { cnt = 0; }
            }
        }
        return cnt >= k;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        int y = move.getRow();
        int x = move.getColumn();
        cells[y][x] = move.getValue();
        cellsCount++;
        if (checkWinning(1, 1, y, x) || checkWinning(1, 0, y, x)       ||
                checkWinning(1, -1, x, y) || checkWinning(0, 1, y, x)  ||
                checkWinning(0, -1, y, x) || checkWinning(-1, 1, y, x) ||
                checkWinning(-1, 0, y, x) || checkWinning(-1, -1, y, x)
        ) {
            return Result.WIN;
        }
        if (cellsCount == n * m && mode == 0 || mode == 1 && cellsCount == mxCellsCountRh) {
            return Result.DRAW;
        }
        turn = nextOf[num.get(turn)];
        return Result.UNKNOWN;
    }

    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < n
                && 0 <= move.getColumn() && move.getColumn() < m
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell() && isValid[move.getRow()][move.getColumn()];
    }

    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" ");
        for (int i = 0; i < m; i++) {
            sb.append(Integer.toString(i));
        }
        for (int r = 0; r < n; r++) {
            sb.append("\n");
            sb.append(r);
            for (int c = 0; c < m; c++) {
                if (!isValid[r][c]) {
                    sb.append(" ");
                } else {
                    sb.append(SYMBOLS.get(cells[r][c]));
                }
            }
        }
        return sb.toString();
    }
}
