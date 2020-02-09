import java.util.*;
import java.io.*;
import java.lang.*;

public class WordStatInput {
    public static Comparator<MyData> compare= new Comparator <MyData>() {
        public int compare(MyData a, MyData b) {
            if (a.index < b.index) {
                return -1;
            } else {
                return 1;
            }
        }
    };
    public static Boolean isValidCharacter(Character c) {
        return Character.isLetter(c) || c == '\'' || Character.getType(c) == Character.DASH_PUNCTUATION;
    }
    public static void main(String[] args){        
        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(new FileInputStream(args[0]), "utf8"));
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(args[1]), "utf8"))
        ){ 
            Map <String, Integer> d = new LinkedHashMap<>();
            while (true) {
                StringBuilder in = new StringBuilder();
                boolean doBreak = false;
                char buf;
                while (true) {
                    int value = reader.read();
                    if (value == -1) {
                        doBreak = true;
                        break;
                    }
                    buf = (char) value;
                    if (buf == '\n') {
                        break;
                    }
                    in.append(buf);
                }
                if (doBreak) {
                    break;
                }
                String s = (in.toString()).toLowerCase();
                int l = 0, r = 0, n = s.length();
                for (int i = 0; i < n; i++) {
                    r = i;
                    while (r < n && isValidCharacter(s.charAt(r))) {
                        r = r + 1;
                    }
                    if (i == r) {
                        continue;
                    }
                    String substring = s.substring(i, r);
                    if (d.containsKey(substring)) {
                        d.put(substring, d.get(substring) + 1); 
                    } else {
                        d.put(substring, 1);
                    }
                    i = r;
                }
            }
            for (Map.Entry<String, Integer> entry : d.entrySet()) {
                writer.write(entry.getKey());
                writer.write(" ");
                writer.write(Integer.toString(entry.getValue()));
                writer.newLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Input/Output Error : ");
            System.out.println(e.getMessage());
        }
    }
}
