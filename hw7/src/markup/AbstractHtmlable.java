package markup;

import java.util.List;

public abstract class AbstractHtmlable extends AbstractContainer{
    List <Htmlable> data;
    public AbstractHtmlable(List<Htmlable> t) {
        super(t);
        data = t;
    }
    public void toMarkdown(StringBuilder s, String pref) {
        s.append(pref);
        for (Htmlable x : data) {
            x.toMarkdown(s);
        }
        s.append(pref);
    }
    public void toHtml(StringBuilder s) {
        super.toHtml(s);
    }

}
