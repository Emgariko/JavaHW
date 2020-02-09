import java.util.*;
import java.io.*;

public class WordStatSortedLastIndex {    
    public static void main(String[] args){        
        try (myScanner reader = new myScanner(new File(args[0]), "utf8");
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(args[1]), "utf8"));
        ){ 
            Map<String, List<IntList>> d = new TreeMap<>();
            int index = 0;
            while (reader.hasNextLine()) {
                String s = reader.nextLine().toLowerCase();
                myScanner parser = new myScanner(s, "utf-8");
                int str_Index = 0;
                while (parser.hasNextWord()) {
                    String cur = parser.nextWord();
                    List<IntList> array = d.get(cur);
                    if (array == null) {
                        IntList t = new IntList(new int[]{index, str_Index});
                        List nwList = new ArrayList();
                        nwList.add(t);
                        d.put(cur, nwList);
                    } else {
                        if (index != array.get(array.size() - 1).get(0)) {
                            array.add(new IntList(new int[]{index}));
                        }
                        array.get(array.size() - 1).add(str_Index);
                    }
                    str_Index++;
                }
                index++;        
            }
            for (Map.Entry <String, List<IntList>> i : d.entrySet()) {
                int sum = 0;
                List <IntList> cur = i.getValue();
                for (int j = 0; j < cur.size(); j++) {
                    sum += cur.get(j).size() - 1;
                }
                writer.write(i.getKey());
                writer.write(" ");
                writer.write(Integer.toString(sum));
                writer.write(" ");
                for (int j = 0; j < cur.size(); j++) {
                    writer.write(Integer.toString(cur.get(j).get(cur.get(j).size() - 1) + 1));
                    if (j != cur.size() - 1) {
                        writer.write(" ");
                    }
                }
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
