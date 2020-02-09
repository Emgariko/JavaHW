package markup;

import java.util.List;

public class Emphasis extends AbstractHtmlable implements Htmlable {
    public Emphasis(List<Htmlable> t) {
        super(t);
    }
    public void toMarkdown(StringBuilder s) {
        super.toMarkdown(s, "*");
    }

    @Override
    public void toHtml(StringBuilder s) {
        s.append("<em>");
        super.toHtml(s);
        s.append("</em>");
    }
}
