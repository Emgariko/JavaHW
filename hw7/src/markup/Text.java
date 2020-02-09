package markup;

public class Text implements Htmlable {
    private String text;
    public Text(String ss) {
        text = ss;
    }
    @Override
    public void toMarkdown(StringBuilder s) {
        s.append(text);
    }

    @Override
    public void toHtml(StringBuilder s) {
        s.append(text);
    }

}
