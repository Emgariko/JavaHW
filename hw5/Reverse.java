import java.util.Arrays;
import java.io.IOException;
import java.io.File;

public class Reverse {
    public static int[] parseReversedArray(myScanner intScanner) throws IOException{
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
        try {
            //System.out.println();
            myScanner stringScanner = new myScanner(System.in);
            //myScanner stringScanner = new myScanner(new File("a.txt"));
            int[][] ints = new int[1][];
            int lastElementIndex = 0;
            while (stringScanner.hasNextLine()) {
                String str = stringScanner.nextLine();
                myScanner intScanner = new myScanner(str);
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
        } catch (IOException e) {
            System.out.println("I/O error: ");
            System.out.println(e.getMessage());
        }
    }
}
