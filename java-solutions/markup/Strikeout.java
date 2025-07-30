package markup;

import java.util.List;

public class Strikeout extends GeneralMarkup implements ParagraphElement {

    public Strikeout(List<ParagraphElement> x) {
        super(x);
    }

    @Override
    public void toDocBook(StringBuilder ans) {
        super.toDocBookTag(ans, "emphasis", "role='strikeout'");
    }

    @Override
    public void toMarkdown(StringBuilder ans) {
        super.toMarkdownTag(ans, "~");
    }

}
