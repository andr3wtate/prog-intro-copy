package markup;

import java.util.List;

public class Strong extends GeneralMarkup implements ParagraphElement {

    public Strong(List<ParagraphElement> x) {
        super(x);
    }

    @Override
    public void toDocBook(StringBuilder ans) {
        super.toDocBookTag(ans, "emphasis", "role='bold'");
    }

    @Override
    public void toMarkdown(StringBuilder ans) {
        super.toMarkdownTag(ans, "__");
    }

}
