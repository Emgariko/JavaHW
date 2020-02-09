package markup;

import java.util.List;

public class Strikeout extends AbstractHtmlable implements Htmlable {
    public Strikeout(List<Htmlable> t) {
        super(t);
    }
    @Override
    public void toMarkdown(StringBuilder s) {
        super.toMarkdown(s, "~");
    }

    @Override
    public void toHtml(StringBuilder s) {
        s.append("<s>");
        super.toHtml(s);
        s.append("</s>");
    }
}
