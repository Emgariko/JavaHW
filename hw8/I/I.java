import java.util.*;


public class I{ 
    public static void main(String[] args){
        Integer n;
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        Integer[] x = new Integer[n];
        Integer[] y = new Integer[n];
        Integer[] h = new Integer[n];
        Integer res, xl, xr, yl, yr;
        xl = Integer.MAX_VALUE;
        xr = Integer.MIN_VALUE;
        yl = Integer.MAX_VALUE;
        yr = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            x[i] = scan.nextInt();
            y[i] = scan.nextInt();
            h[i] = scan.nextInt();
            xl = Integer.min(x[i] - h[i], xl);
            xr = Integer.max(x[i] + h[i], xr);
            yl = Integer.min(y[i] - h[i], yl);
            yr = Integer.max(y[i] + h[i], yr);
        }
        System.out.print((xl + xr) / 2);
        System.out.print(' ');
        System.out.print((yl + yr) / 2);
        System.out.print(' ');
        System.out.print((Integer.max(xr - xl, yr - yl) + 1) / 2);
    }
}
