import java.io.*;
import java.util.*;

public class Line implements Comparable<Line>{
    public long sum;
    public int number;
    public int[] line;
    
    public Line() {
        sum = 0l;
        number = -1;
    }
    public Line(long s, int n, int[] l) {
        sum = s;
        number = n;
        line = l;
    }
    public int compareTo(Line a) {
        if (this.sum != a.sum){
            if (this.sum > a.sum) { return -1; }
            else { return 1; }
        } else {
            if (this.number < a.number) { return 1; }
            else { return -1; }
        } 
    }
    public void sort() {
        Arrays.sort(line);
    }
}
