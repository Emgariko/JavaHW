package markup;

import java.util.List;

public abstract class AbstractList extends AbstractContainer implements ParAndList{
    public AbstractList(List<ListItem> t) {
        super(t);
    }
    public void toHtml(StringBuilder s, String pref) {
        s.append("<");
        s.append(pref);
        s.append(">");
        super.toHtml(s);
        s.append("</");
        s.append(pref);
        s.append(">");
    }
}
