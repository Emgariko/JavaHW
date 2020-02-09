import java.util.Scanner;
import java.util.Arrays;

public class ReverseSum {
    public static void main(String[] agrs) {
        Scanner stringScanner = new Scanner(System.in);
        int[][] ints = new int[1][];
        int[][] sum = new int[1][];
        int[] lineSum = new int[1];
        int[] columnSum = new int[1];
        int[] lengths = new int[1];
        int currentLineNumber = 0;
        while (stringScanner.hasNextLine()) {
            Scanner intScanner = new Scanner(stringScanner.nextLine()); 
            if (currentLineNumber == ints.length) {
                ints = Arrays.copyOf(ints, 2 * ints.length);
            }
            if (currentLineNumber == lineSum.length) {
                lineSum = Arrays.copyOf(lineSum, lineSum.length * 2);
            }
            if (lengths.length == currentLineNumber) {
                lengths = Arrays.copyOf(lengths, 2 * lengths.length);
            }
            int[] intss = new int[1];
            int last = 0;
            while (intScanner.hasNextInt()) {
                if (last == intss.length) {
                    intss = Arrays.copyOf(intss, 2 * intss.length);
                }
                int newValue = intScanner.nextInt();
                if (columnSum.length == last) {
                    columnSum = Arrays.copyOf(columnSum, 2 * columnSum.length);
                }
                columnSum[last] += newValue;
                lineSum[currentLineNumber] += newValue;
                intss[last++] = newValue;
            }
            lengths[currentLineNumber] = last;
            ints[currentLineNumber] = intss;
            currentLineNumber++;
            intScanner.close();
        }
        stringScanner.close();
        for (int i = 0; i < currentLineNumber; i++) {
            for (int j = 0; j < lengths[i]; j++) {
                System.out.print(lineSum[i] + columnSum[j] - ints[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}

