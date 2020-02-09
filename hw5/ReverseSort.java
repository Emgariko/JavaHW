import java.util.*;
import java.io.*;

public class ReverseSort {
    public static Line parseArray(myScanner intScanner, int number) throws IOException{
        int[] ints = new int[1];
        long sum = 0l;
        int lastElementIndex = 0;
        while (intScanner.hasNextInt()) {
            if (lastElementIndex == ints.length) {
                ints = Arrays.copyOf(ints, 2 * ints.length);
            }
            ints[lastElementIndex++] = intScanner.nextInt();
            sum += ints[lastElementIndex - 1];
        }
        ints = Arrays.copyOf(ints, lastElementIndex);
        return new Line(sum, number, ints);
    }
    public static void main(String[] agrs) {
        try {
            int number = 0;
            myScanner stringScanner = new myScanner(System.in);
            List lines = new ArrayList<Line>();
            while (stringScanner.hasNextLine()) {
                String str = stringScanner.nextLine();
                myScanner intScanner = new myScanner(str);
                lines.add(parseArray(intScanner, number));
                intScanner.close();
                number++;
            }
            stringScanner.close();
            Collections.sort(lines);
            for (int i = 0; i < lines.size(); i++) {
                Line x = (Line) lines.get(i);
                x.sort();
                for (int j = x.line.length - 1; j >= 0; j--) {
                    System.out.print(x.line[j]);
                    System.out.print(" ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("I/O error: ");
            System.out.println(e.getMessage());
        }
    }
}
