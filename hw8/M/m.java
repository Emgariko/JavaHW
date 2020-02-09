package com.company;

import java.util.*;

public class m {
    public static void solve(Scanner scan) {
        Integer n;
        n = scan.nextInt();
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = scan.nextInt();
        }
        Integer res = 0;
        Map c = new HashMap<Integer, Integer>();
        c.put(a[n - 1], 1);
        for (int j = n - 2; j >= 1; j--) {
            for (int i = 0; i <= j - 1; i++) {
                if (c.containsKey(2 * a[j] - a[i])) {
                    res += (Integer) c.get(2 * a[j] - a[i]);
                }
            }
            if (c.containsKey(a[j])) {
                c.put(a[j], (Integer) c.get(a[j]) + 1);
            } else {
                c.put(a[j], 1);
            }
        }
        System.out.println(res);
    }

    public static void main(String[] args) {
        Integer t;
        Scanner scan = new Scanner(System.in);
        t = scan.nextInt();
        for (int i = 0; i < t; i++) {
            solve(scan);
        }
    }
}
