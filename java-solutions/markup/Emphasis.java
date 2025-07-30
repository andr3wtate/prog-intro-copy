package markup;

import java.util.List;

public class Emphasis extends GeneralMarkup implements ParagraphElement {

    public Emphasis(List<ParagraphElement> x) {
        super(x);
    }

    @Override
    public void toDocBook(StringBuilder ans) {
        super.toDocBookTag(ans, "emphasis", "");
    }

    public void toMarkdown(StringBuilder ans) {
        super.toMarkdownTag(ans, "*");
    }

}
