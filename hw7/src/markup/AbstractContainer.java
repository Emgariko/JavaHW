package markup;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractContainer {
    List<AbstractElement> data;
    public AbstractContainer(List<?extends AbstractElement> t) {
        data = new ArrayList<AbstractElement>();
        if (t == null) return;
        for (var x : t) {
            data.add(x);
        }
    }
    public void toHtml(StringBuilder s) {
        for (AbstractElement x : data) {
            x.toHtml(s);
        }
    }
}
