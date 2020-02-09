package markup;

import java.util.List;

public class Paragraph extends AbstractHtmlable implements ParAndList{
    public Paragraph(List<Htmlable> t) {
        super(t);
    }

    public void toMarkdown(StringBuilder s) {
        super.toMarkdown(s, "");
    }
    public void toHtml(StringBuilder s) {
        super.toHtml(s);
    }
}
