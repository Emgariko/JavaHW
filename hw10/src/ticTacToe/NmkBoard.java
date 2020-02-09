package ticTacToe;

public class NmkBoard implements Position{
    private ServerBoard serverBoard;

    public NmkBoard(int n, int m, int k, int p, String mode) {
        serverBoard = new ServerBoard(n, m, k, p, mode);
    }

    /*public NmkBoard(ServerBoard server) {
        serverBoard = server;
    }*/

    @Override
    public boolean isValid(Move move) {
        return serverBoard.isValid(move);
    }

    @Override
    public Cell getCell(int r, int c) {
        return serverBoard.getCell(r, c);
    }
    public int play(Player[] players, boolean log) {
        Game game = new Game(log, players);
        return game.play(serverBoard);
    }

    @Override
    public String toString() {
        return serverBoard.toString();
    }
}
