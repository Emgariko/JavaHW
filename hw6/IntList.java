import java.util.*;

public class IntList {
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
