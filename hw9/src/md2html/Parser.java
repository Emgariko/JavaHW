package md2html;

import java.io.*;
import java.util.*;

public class Parser implements AutoCloseable{
    private BufferedReader scanner;
    public BufferedWriter writer;
    private List<Integer> parInfo;
    private String cur;
    private final Map<String, Integer> id = Map.of(
            "emphasis", 0, "strikeout", 1, "strong", 2, "code", 3
    );
    private final Map<Integer, String> type = Map.of(
            0, "emphasis", 1, "strikeout", 2, "strong", 3, "code"
    );
    private final Map<Integer, String> htmlPref = Map.of(
            0, "em", 1, "s", 2, "strong", 3, "code"
    );
    int[] left = new int[]{-1, -1, -1, -1, -1};
    boolean[] findElement = new boolean[5];
    int[] add = new int[]{1, 2, 2, 1, 1};
    private List <Set<Character>> chars = new ArrayList<Set<Character>>();
    private final Set<Character> emphasisChars = Set.of('*', '_');
    private final Set<Character> strikeoutChars = Set.of('-');
    private final Set<Character> specialChars = Set.of('<', '>', '&');
    private final Set<Character> codeChars = Set.of('`');
    private final Map<Character, String> symbol = Map.of(
            '<', "&lt;", '>', "&gt;", '&', "&amp;");
    private int last;
    private List<Integer> hasPair = new ArrayList<Integer>();

    public Parser(String inp, String out, String charsetName) throws IOException {
        scanner = new BufferedReader(new InputStreamReader(new FileInputStream(inp), charsetName));
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out), charsetName));
        parInfo = new ArrayList<Integer>();
        chars.add(emphasisChars);
        chars.add(strikeoutChars);
        chars.add(emphasisChars); // Strong;
        chars.add(codeChars);
        chars.add(specialChars);
    }
    private boolean hasNextPar() throws IOException {
        return scanner.ready();
    }

    public void parse() throws IOException {
        int index = 0;
        while (hasNextPar()) {
            parInfo.add(-1);
            String s = parsePar(index);
            writer.write(s);
            if (!s.equals("")) { writer.newLine(); }
            index++;
        }
    }

    private String readAllInPar(int index) throws IOException {
        StringBuilder res = new StringBuilder();
        int number = 0;
        boolean isHeader = false;
        while (scanner.ready()) {
            StringBuilder s = new StringBuilder(scanner.readLine());
            if (s.length() == 0) {
                break;
            }
            int count = 0;
            if (number == 0) {
                if (s.charAt(0) == '#') {
                    count = 0;
                    for (int i = 0; i < s.length(); i++) {
                        if (s.charAt(i) != '#') { break; }
                        count++;
                    }
                }
                if (count > 0 && count < s.length() && Character.isWhitespace(s.charAt(count))) {
                    s.delete(0, count + 1); // + 1 is to delete Space
                    parInfo.set(index, count);
                }
            }
            res.append(s);
            res.append("\n");
            number++;
        }
        if (res.length() > 0 && res.charAt(res.length() - 1) == '\n') { res.delete(res.length() - 1, res.length()); }
        return res.toString();
    }

    private boolean isHeader(int index) {
        return !parInfo.get(index).equals(-1);
    }

    private String parsePar(int index) throws IOException {
        String str = readAllInPar(index);
        if (str.equals("")) { return ""; }
        StringBuilder result = new StringBuilder();
        if (isHeader(index)) {
            result.append("<h");
            result.append(parInfo.get(index).toString());
            result.append(">");
        } else {
            result.append("<p>");
        }
        cur = str;
        preProcessImg(cur);
        result.append(parseText(0));
        if (isHeader(index)) {
            result.append("</h");
            result.append(parInfo.get(index).toString());
            result.append(">");
        }
        else {
            result.append("</p>");
        }
        return result.toString();
    }

    private int parseImage(String paragraph, int pos, StringBuilder result) {
        int left = pos + 2;
        int right = (hasPair.get(pos + 1)) - 1;
        String name = paragraph.substring(left, right + 1);
        int id = right + 3;
        int beg = id;
        while (paragraph.charAt(id) != ')') {
            id++;
        }
        String link = paragraph.substring(beg, id);
        result.append("<img alt='");
        result.append(name);
        result.append("' src='");
        result.append(link);
        result.append("'>");
        return id;
    }

    private void preProcessImg(String s) {
        hasPair.clear();
        //hasPair.addAll(new List(new int[cur.length()]));
        for (int i = 0; i < cur.length(); i++) {
            hasPair.add(-1);
        }

        int len = s.length(), last = -1;
        for (int i = len - 1; i >= 0; i--) {
            if (s.charAt(i) == '[') {
                hasPair.set(i, last);
                last = -1;
            }
            if (s.charAt(i) == ']') {
                last = i;
            }
        }
    }

    private boolean checkType(int index, int t) {
        int len = cur.length();
        return (chars.get(t).contains(cur.charAt(index))) && ((t == 0)  &&
                ((index + 1 < len && !chars.get(t).contains(cur.charAt(index + 1))) || (index + 1 >= len))
                || (0 < t && t < 3) && (index + 1 < len && chars.get(t).contains(cur.charAt(index + 1)))
                || (t > 2) && (chars.get(t)).contains(cur.charAt(index)));
    }

    private boolean typeElementCompare(int index, int t) {
        return cur.charAt(left[t]) == cur.charAt(index);
    }

    private int getType(int i) {
        for (int j = 0; j < chars.size(); j++) {
            if (checkType(i, j)) {
                return j;
            }
        }
        return -1;
    }

    private boolean isMarkable(int index) {
        return getType(index) != -1 && getType(index) != 4;
    }

    private String parseText(int l) { // l - inclusive, r - inclusive
        StringBuilder result = new StringBuilder();
        for (int i = l; i < cur.length(); i++) {
            last = i;
            if (i + 1 < cur.length() && cur.charAt(i) == '!'
                    && cur.charAt(i + 1) == '[' && !hasPair.get(i + 1).equals(-1)) { // image processing
                i = parseImage(cur, i, result);
                continue;
            }
            if (cur.charAt(i) == '\\' && i < cur.length() && isMarkable(i + 1)) { // shielding
                result.append(cur.charAt(i + 1));
                i++;
                continue;
            }
            int t = getType(i);
            if (isMarkable(i)) {
                if (left[t] != -1 && typeElementCompare(i, t)) {
                    StringBuilder pref = new StringBuilder("<");
                    pref.append(htmlPref.get(t));
                    pref.append(">");
                    result.insert(0, pref);
                    pref.insert(1, '/');
                    result.append(pref);
                    findElement[t] = true;
                    return result.toString();
                } else {
                    left[t] = i;
                    StringBuilder pref = new StringBuilder(parseText(i + add[t]));
                    if (findElement[t]) {
                        findElement[t] = false;
                        left[t] = -1;
                    } else { result.append(cur.charAt(i)); }
                    result.append(pref);
                    i = last + 1;
                    if (t == 3 || t == 0) { i--; }
                }
            } else {
                if (t == 4) { // Special symbol
                    result.append(symbol.get(cur.charAt(i)));
                }
                else {
                    result.append(cur.charAt(i));
                }
            }
        }
        return result.toString();
    }

    @Override
    public void close() throws IOException {
        scanner.close();
        writer.close();
    }
}
