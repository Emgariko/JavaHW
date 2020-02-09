package markup;

import java.util.List;

public class OrderedList extends AbstractList {
    public OrderedList(List<ListItem> t) {
        super(t);
    }
    @Override
    public void toHtml(StringBuilder s) {
        super.toHtml(s, "ol");
    }
}
