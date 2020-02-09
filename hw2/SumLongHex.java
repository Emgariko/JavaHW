public class SumLongHex{
    public static Long getValue(String s){
        int stringLength = s.length();
        long result = 0;
        for (int i = 0; i < stringLength; i++) {
            int left = i, right = i;
            if (Character.isWhitespace(s.charAt(i))) {
                continue;
            }
            boolean isHex = false;
            if ((s.charAt(left) == '0') 
               && (left + 1 < stringLength) 
               && ((s.charAt(left + 1) == 'x') || (s.charAt(left + 1) == 'X'))) {
                left += 2;
                isHex = true;
            }
            while (right < stringLength && !Character.isWhitespace(s.charAt(right))) {
                right++;
            }
            if (isHex) {
                result += Long.parseUnsignedLong(s.substring(left, right), 16);
            } else {
                result += Long.parseLong(s.substring(left, right));
            }
            i = right;
        }
        return result;
    }
    public static void main(String[] args) { 
        int x = args.length;
        long ans = 0;
        for (int i = 0; i < x; i++) {
            ans += getValue(args[i]);

        }
        System.out.println(ans);
    }
}
