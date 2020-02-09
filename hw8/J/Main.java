package  com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[][] d = new int[n][n];
        for (int i = 0; i < n; i++) {
            String s;
            s = scan.next();
            for (int j = 0; j < n; j++) {
                d[i][j] = (int) s.charAt(j) - '0';
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (d[i][j] != 0) {
                    for (int k = 0; k < n; k++) {
                        d[i][k] = (d[i][k] - d[j][k] + 10) % 10;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(d[i][j]);
            }
            System.out.println();
        }
    }
}
