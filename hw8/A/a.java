import java.util.*;


public class a{ 
    public static void main(String[] args){
        Integer a, b, n;
        Scanner scan = new Scanner(System.in);
        a = scan.nextInt();
        b = scan.nextInt();
        n = scan.nextInt();
        System.out.println(2 * ((n - b + b - a - 1) / (b - a)) + 1);
    }
}
