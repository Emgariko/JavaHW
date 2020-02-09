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
        try (myScanner reader = new myScanner(new File(args[0]), "utf8");
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(args[1]), "utf8"))
        ){ 
            Map <String, Pair> d = new HashMap<>();
            int number = 0;
            while (reader.hasNextWord()) {
                String s = reader.nextWord().toLowerCase();    
                if (d.containsKey(s)) {
                    Pair prev = d.get(s);
                    prev.count++;
                    d.put(s, prev); 
                } else {
                    d.put(s, new Pair(number, 1));
                }
                number++;
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
