package markup;

public interface ParagraphElement {
    void toMarkdown(StringBuilder ans);
    void toDocBook(StringBuilder ans);
}
