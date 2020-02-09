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
        try (myScanner reader = new myScanner(new File(args[0]), "utf8");
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(args[1]), "utf8"))
        ){ 
            Map <String, Integer> d = new LinkedHashMap<>();
            while (reader.hasNextWord()) {
                String s = reader.nextWord().toLowerCase();
                if (d.containsKey(s)) {
                    d.put(s, d.get(s) + 1); 
                } else {
                    d.put(s, 1);
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
