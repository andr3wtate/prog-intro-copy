package markup;

import java.util.List;

public class Paragraph implements ListItemElement {

    final private List<ParagraphElement> x;

    public Paragraph(List<ParagraphElement> x) {
        this.x = x;
    }

    public void toDocBook(StringBuilder ans) {
        // :NOTE: copy-paste
        ans.append("<para>");
        for (ParagraphElement i : x) {
            i.toDocBook(ans);
        }
        ans.append("</para>");
    }

    public void toMarkdown(StringBuilder ans) {
        // :NOTE: copy-paste
        for (ParagraphElement i : x) {
            i.toMarkdown(ans);
        }
    }
}

