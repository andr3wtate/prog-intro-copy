package markup;

public class Text implements ParagraphElement {

    final private StringBuilder s;

    public Text(String s) {
        this.s = new StringBuilder(s);
    }

    public void toMarkdown(StringBuilder ans) {
        ans.append(s);
    }

    public void toDocBook(StringBuilder ans) {
        this.toMarkdown(ans);
    }
}
