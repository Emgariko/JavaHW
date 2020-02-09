public class Sum{
    public static int get_Value(String s){
        int stringLength = s.length(), result = 0;
        for (int i = 0; i < stringLength; i++) {
            int left = i, right = i;
            if (Character.isWhitespace(s.charAt(i))) {
                continue;
            }
            if (s.charAt(i) == '-') { 
                right++;
            }
            while (right < stringLength && (Character.isDigit(s.charAt(right)))) {
                right++;
            }
            if (left != right) {
                result += Integer.parseInt(s.substring(left, right));
            }
            i = right;
            if (i < stringLength && s.charAt(i) == '-') { 
                i--;
            }
        }
        return result;
    }
    public static void main(String[] args) { 
        int x = args.length;
        int ans = 0;
        for (int i = 0; i < x; i++) {
            ans += get_Value(args[i]);
        }
        System.out.println(ans);
    }
}
