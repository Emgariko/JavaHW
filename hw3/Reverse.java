import java.util.Scanner;
import java.util.Arrays;

public class Reverse {
    public static int[] parseReversedArray(Scanner intScanner) {
        int[] ints = new int[1];
        int lastElementIndex = 0;
        while (intScanner.hasNextInt()) {
            if (lastElementIndex == ints.length) {
                ints = Arrays.copyOf(ints, 2 * ints.length);
            }
            ints[lastElementIndex++] = intScanner.nextInt();
        }
        ints = Arrays.copyOf(ints, lastElementIndex);
        return ints;
    }
    public static void main(String[] agrs) {
        Scanner stringScanner = new Scanner(System.in);
        int[][] ints = new int[1][];
        int lastElementIndex = 0;
        while (stringScanner.hasNextLine()) {
            Scanner intScanner = new Scanner(stringScanner.nextLine());
            if (lastElementIndex == ints.length) {
                ints = Arrays.copyOf(ints, 2 * ints.length);
            }
            ints[lastElementIndex++] = parseReversedArray(intScanner);
            intScanner.close();
        }
        stringScanner.close();
        for (int i = lastElementIndex - 1; i >= 0; i--) {
            for (int j = ints[i].length - 1; j >= 0; j--) {
                System.out.print(ints[i][j] + " ");
            }
            System.out.println();
        }
    }
}
