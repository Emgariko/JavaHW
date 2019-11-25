import java.io.*;
import java.util.*;

public class myScanner implements AutoCloseable{
    private Reader reader;
    private String val = "", buf = "";
    private int mode = 0; // 0 - token, 1 - word, 2 - line

    public  myScanner (String s) throws IOException {
        //this.reader = new InputStreamReader(new StringBufferInputStream(s));
        this.reader = new StringReader(s);
    }
    public myScanner (String s, String charSetName) throws IOException {
        //this.reader = new InputStreamReader(new StringBufferInputStream(s), charSetName);
        this.reader = new StringReader(s);
    }

    public myScanner (InputStream s) throws IOException {
        this.reader = new InputStreamReader(s);
    }
    public myScanner (InputStream s, String charSetName) throws IOException{
        this.reader = new InputStreamReader(s, charSetName);
    }

    public myScanner (File f, String charSetName) throws IOException {
        this.reader = new InputStreamReader(new FileInputStream(f), charSetName);
    }
    public myScanner (File f) throws IOException {
        this.reader = new InputStreamReader(new FileInputStream(f));
    }

    public void close() throws IOException {
        reader.close();
    }
    public boolean isValid(char c) {
        if (mode == 0 && !Character.isWhitespace(c) ||
                (mode == 1 && !Character.isWhitespace(c) && (Character.isLetter(c) || c == '\''  || Character.getType(c) == Character.DASH_PUNCTUATION)) ||
                (mode == 2 && c != '\n')){
            return true;
        } else {
            return false;
        }
    }
    
    private void readToken() throws IOException {
        if (val.length() != 0) {
            return;
        }
        StringBuilder s = new StringBuilder();
        int c = -1;
        char sym = 0;
        boolean ok = false;
        if (mode == 2) {
            ok = true;
        }
        while (true) {
            c = reader.read();
            if (c == -1) {
                break;
            }
            sym = (char) c;
            if (!isValid(sym) && ok) {
                break;
            } else {
                if (!isValid(sym)) {
                    continue;
                }
                s.append(sym);
                ok = true;
            }
        }
        if (s.length() == 0 && c != -1 && mode == 2) {
            s.append(sym);
        }
        val = s.toString();
    }

    public void nextToken() throws IOException {
        mode = 0;
        readToken();
    }
    public boolean hasNext() throws IOException {
        nextToken();
        return val.length() != 0;
    }

    public boolean hasNextInt() throws IOException {
        if (!hasNext()) {
            return false;
        }
        try {
            int value = Integer.parseInt(val);
                return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public Integer nextInt() throws IOException {
        if (hasNextInt()) {
            buf = val;
            val = "";
            return Integer.parseInt(buf);
        } else {
            throw new IOException ("There is no Int to read");
        }
    }
    public void readNextWord() throws IOException {
        mode = 1;
        readToken();
    }
    public String nextWord() throws IOException {
        if (hasNextWord()) {
            buf = val;
            val = "";
            return buf;
        } else {
            throw new IOException("There is no Word to read");
        }
    }
    public boolean hasNextWord() throws IOException {
        readNextWord();
        return val.length() != 0;
    }
    private void readNextLine() throws IOException {
        mode = 2;
        readToken();
    }
    public boolean hasNextLine() throws IOException {
        readNextLine();
        return val.length() != 0;
    }
    public String nextLine() throws IOException {
        if (hasNextLine()) {
            buf = val;
            val = "";
            return buf;
        } else {
            throw new IOException("There is no Line to read");
        }
    }
}
