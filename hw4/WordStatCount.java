import java.util.*;
import java.io.*;

public class WordStatCount {
    public static Comparator<MyData> compare= new Comparator <MyData>() {
        public int compare(MyData a, MyData b) {
            if ((a.count == b.count && a.index < b.index) || 
            (a.count < b.count)) {
                return -1;
            } else {
                return 1;
            }
        }
    };
    public static void main(String[] args){        
        try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(args[0]), "utf8"));
                    BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(args[1]), "utf8"))
            ){
            Map <String, Pair> d = new HashMap<>();
            int number = 0;
            while (true) {
                StringBuilder in = new StringBuilder("");
                char buf = 0;
                boolean doBreak = false;
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
                    while (r < n && WordStatInput.isValidCharacter(s.charAt(r))) {
                        r = r + 1;
                    }
                    if (i == r) {
                        continue;
                    }
                    String substring = s.substring(i, r);
                    if (d.containsKey(substring)) {
                        Pair prev = d.get(substring);
                        prev.count++;
                        d.put(substring, prev); 
                    } else {
                        d.put(substring, new Pair(number, 1));
                    }
                    number++;
                    i = r;
                }
            }
            ArrayList <MyData> result = new ArrayList<>();
            d.forEach((k, v) -> {
                result.add(new MyData(v.index, v.count, k));
            });
            Collections.sort(result, compare);
            for (MyData i : result){
                writer.write(i.value);
                writer.write(" ");
                writer.write(Integer.toString(i.count));
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
