package md2html;

import md2html.Parser;

import java.io.IOException;

public class Md2Html {
    public static void main(String[] args) {
        //System.out.println(args[1]);
        try (Parser bestParserEver = new Parser(args[0], args[1], "UTF-8")){
            bestParserEver.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
