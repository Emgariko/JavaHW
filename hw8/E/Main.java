package com.company;

import java.util.*;

import java.io.*;
import java.util.*;

class myScanner implements AutoCloseable{
    private Reader reader;
    private String val = "", buf = "";
    private int mode = 0; // 0 - token, 1 - word, 2 - line

    public  myScanner (String s) throws IOException {
        //this.reader = new InputStreamReader(new StringBufferInputStream(s));
        this.reader = new StringReader(s);
    }
    public myScanner (String s, String charSetName) throws IOException {
        //this.reader = new InputStreamReader(new StringBufferInputStream(s), charSetName);
        this.reader = new StringReader(s);
    }

    public myScanner (InputStream s) throws IOException {
        this.reader = new InputStreamReader(s);
    }
    public myScanner (InputStream s, String charSetName) throws IOException{
        this.reader = new InputStreamReader(s, charSetName);
    }

    public myScanner (File f, String charSetName) throws IOException {
        this.reader = new InputStreamReader(new FileInputStream(f), charSetName);
    }
    public myScanner (File f) throws IOException {
        this.reader = new InputStreamReader(new FileInputStream(f));
    }

    public void close() throws IOException {
        reader.close();
    }
    public boolean isValid(char c) {
        if (mode == 0 && !Character.isWhitespace(c) ||
                (mode == 1 && !Character.isWhitespace(c) && (Character.isLetter(c) || c == '\''  || Character.getType(c) == Character.DASH_PUNCTUATION)) ||
                (mode == 2 && c != '\n')){
            return true;
        } else {
            return false;
        }
    }

    public void readToken() throws IOException {
        if (val.length() != 0) {
            return;
        }
        StringBuilder s = new StringBuilder();
        int c = -1;
        char sym = 0;
        boolean ok = false;
        if (mode == 2) {
            ok = true;
        }
        while (true) {
            c = reader.read();
            if (c == -1) {
                break;
            }
            sym = (char) c;
            if (!isValid(sym) && ok) {
                break;
            } else {
                if (!isValid(sym)) {
                    continue;
                }
                s.append(sym);
                ok = true;
            }
        }
        if (s.length() == 0 && c != -1 && mode == 2) {
            s.append(sym);
        }
        val = s.toString();
    }

    public void nextToken() throws IOException {
        mode = 0;
        readToken();
    }
    public boolean hasNext() throws IOException {
        nextToken();
        return val.length() != 0;
    }

    public boolean hasNextInt() throws IOException {
        if (!hasNext()) {
            return false;
        }
        try {
            int value = Integer.parseInt(val);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public Integer nextInt() throws IOException {
        if (hasNextInt()) {
            buf = val;
            val = "";
            return Integer.parseInt(buf);
        } else {
            throw new IOException ("There is no Int to read");
        }
    }
    public void readNextWord() throws IOException {
        mode = 1;
        readToken();
    }
    public String nextWord() throws IOException {
        if (hasNextWord()) {
            buf = val;
            val = "";
            return buf;
        } else {
            throw new IOException("There is no Word to read");
        }
    }
    public boolean hasNextWord() throws IOException {
        readNextWord();
        return val.length() != 0;
    }
    private void readNextLine() throws IOException {
        mode = 2;
        readToken();
    }
    public boolean hasNextLine() throws IOException {
        readNextLine();
        return val.length() != 0;
    }
    public String nextLine() throws IOException {
        if (hasNextLine()) {
            buf = val;
            val = "";
            return buf;
        } else {
            throw new IOException("There is no Line to read");
        }
    }
}


class IntList {
    private int[] data;
    private int sz;

    public IntList() {
        data = new int[1];
        sz = 0;
    }
    public IntList(int[] d) {
        data = d;
        sz = d.length;
    }
    public void put(int x, int i) {
        data[i] = x;
    }
    public int size() {
        return sz;
    }
    public int capacity() {
        return data.length;
    }
    public void add(int x) {
        if (data.length == sz) {
            data = Arrays.copyOf(data, 2 * sz);
        }
        data[sz++] = x;
    }
    public int top() {
        return data[sz - 1];
    }
    public int get(int index) {
        return data[index];
    }
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < sz; i++) {
            res.append(data[i]);
            if (i < sz - 1) { res.append(' '); }
        }
        return res.toString();
    }
}


public class Main {
    public static int[] c, h, par, path;
    public static IntList[] g;
    public static int n, m;

    public static void bfs(int u, int p) {
        int l = 0, r = 1;
        IntList q = new IntList();
        int n = g.length;
        for (int i = 0; i < n; i++) {
            h[i] = -1;
        }
        h[u] = 0;
        q.add(u);
        while (r - l > 0) {
            int cur = q.get(l);
            l++;
            for (int i = 0; i < g[cur].size(); i++) {
                int v = (int) g[cur].get(i);
                if (v == cur) { continue; }
                if (h[v] == -1) {
                    par[v] = cur;
                    h[v] = h[cur] + 1;
                    q.add(v);
                    r++;
                }

            }
        }
    }

    public static void main(String[] args) throws IOException {
        myScanner scan = new myScanner(System.in);
        n = scan.nextInt();
        m = scan.nextInt();
        c = new int[n];
        h = new int[n];
        g = new IntList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new IntList();
        }
        par = new int[n];
        path = new int[n];
        for (int i = 0; i < n - 1; i++) {
            int x, y;
            x = scan.nextInt();
            y = scan.nextInt();
            x--;
            y--;
            g[x].add(y);
            g[y].add(x);
        }
        for (int i = 0; i < m; i++) {
            c[i] = scan.nextInt();
            c[i]--;
        }
        h[c[0]] = 0;
        bfs(c[0], -1);
        int f = -1, mx = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            mx = Integer.max(mx, h[c[i]]);
            if (mx == h[c[i]]) {
                f = c[i];
            }
        }
        int x = f, id = 0;
        while (true) {
            if (x == c[0]) {
                path[id++] = x;
                break;
            }
            if (x == f) {
                path[id++] = x;
                x = par[x];
            } else {
                path[id++] = x;
                x = par[x];
            }
        }
        if (id % 2 == 0) {
            System.out.println("NO");
            return;
        }
        int u = path[id / 2];
        h[u] = 0;
        bfs(u,  -1);
        for (int i = 0; i < m; i++) {
            if (h[c[i]] != h[c[0]]) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
        System.out.print(u + 1);
    }
}
