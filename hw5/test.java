import java.io.*;
import java.util.*;

public class test{
    public static void main(String[] args) {
        try (myScanner reader = new myScanner(System.in)) {
            int n = reader.nextInt();
            String[] d = new String[100];
            for (int i = 0; i < n; i++) { 
                d[i] = reader.nextWord();
            }
            System.out.println(Arrays.toString(d));
        } catch (IOException e) {
        
        }
    }
}
