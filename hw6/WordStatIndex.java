import java.util.*;
import java.io.*;

public class WordStatIndex {    
    public static void main(String[] args){        
        try (myScanner reader = new myScanner(new File(args[0]), "utf8");
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(args[1]), "utf8"))
        ){ 
            Map<String, IntList> d = new LinkedHashMap<>();
            int index = 1;
            while (reader.hasNextWord()) {
                String s = reader.nextWord().toLowerCase();
                if (d.containsKey(s)) {
                    d.get(s).add(index);
                } else {
                    d.put(s, new IntList(new int[]{index}));
                }
                index++;
            }
            for (Map.Entry <String, IntList> i : d.entrySet()) {
                writer.write(i.getKey());
                writer.write(" ");
                writer.write(Integer.toString(i.getValue().size()));
                writer.write(" ");
                writer.write(i.getValue().toString());
                writer.write("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Input/Output Error : ");
            System.out.println(e.getMessage());
        }
    }
}
