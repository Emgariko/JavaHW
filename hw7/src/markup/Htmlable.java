package markup;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface Htmlable extends AbstractElement{
    public void toMarkdown(StringBuilder s);
    public void toHtml(StringBuilder s);
}
