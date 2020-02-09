package ticTacToe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File("config.txt"))) {
            boolean correctConfig = false;
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int k = scanner.nextInt();
            int p = scanner.nextInt();
            if (n == 0 || m == 0 || p == 0 || k == 0 || p > 4) {
                throw new IOException();
            }
            scanner.nextLine();
            String s = scanner.nextLine();
            if (s.length() != p) {
                throw new IOException();
            }
            String mode = scanner.nextLine();
            if (mode.equals("Rh") && n != m) {
                throw new IOException();
            }
            Player[] players = new Player[p];
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == 'R') {
                    players[i] = new RandomPlayer(n, m, k);
                } else if (s.charAt(i) == 'H') {
                    players[i] = new HumanPlayer(n, m, k);
                } else if (s.charAt(i) == 'S') {
                    players[i] = new SequentialPlayer(n, m, k);
                } else {
                    throw new IOException();
                }
            }
            final Game game = new Game(true, players);
            int result;
            do {
                result = (new NmkBoard(n, m, k, p, mode).play(players, true));
                System.out.println("Game result: " + result);
                //result = 0;
            } while (result != 0);
        } catch (FileNotFoundException e) {
            System.out.println("Config file not found");
        } catch (Exception e) {
            System.out.println("Incorrect config file");
        }
    }
}
