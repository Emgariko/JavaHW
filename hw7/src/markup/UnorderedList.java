package markup;

import java.util.List;

public class UnorderedList extends AbstractList {
    public UnorderedList(List<ListItem> t) {
        super(t);
    }

    @Override
    public void toHtml(StringBuilder s) {
        super.toHtml(s, "ul");
    }
}
