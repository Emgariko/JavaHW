package markup;

import java.util.List;

public class Strong extends AbstractHtmlable implements Htmlable {
    public Strong(List<Htmlable> t) {
        super(t);
    }
    @Override
    public void toMarkdown(StringBuilder s) {
        super.toMarkdown(s, "__");
    }
    public void toHtml(StringBuilder s) {
        s.append("<strong>");
        super.toHtml(s);
        s.append("</strong>");
    }
}
