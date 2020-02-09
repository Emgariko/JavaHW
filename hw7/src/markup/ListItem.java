package markup;

import javax.swing.text.AbstractDocument;
import java.util.List;

public class ListItem extends AbstractContainer implements AbstractElement {
    private List <ParAndList> elements;
    public ListItem(List<ParAndList> t) {
        super(t);
    }
    public void toHtml(StringBuilder s) {
        s.append("<li>");
        super.toHtml(s);
        s.append("</li>");
    }
}
