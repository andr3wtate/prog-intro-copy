package markup;

import java.util.List;

public abstract class GeneralMarkup {

    final private List<ParagraphElement> x;

    public GeneralMarkup(List<ParagraphElement> x) {
        this.x = x;
    }

    public void toMarkdownTag(StringBuilder ans, String Tag) {
        ans.append(Tag);
        for (ParagraphElement i : x) {
            i.toMarkdown(ans);
        }
        ans.append(Tag);
    }

    public void toDocBookTag(StringBuilder ans, String Tag, String TagArg) {
        ans.append("<").append(Tag);
        if ((TagArg != "")) ans.append(" ");
        ans.append(TagArg).append(">");
        for (ParagraphElement i : x) {
            i.toDocBook(ans);
        }
        ans.append("</").append(Tag).append(">");
    }

}