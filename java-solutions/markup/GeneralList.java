package markup;

import java.util.List;

public abstract class GeneralList {
    // :NOTE: naming
    // :NOTE: access
    final private List<ListItem> items;

    public GeneralList(final List<ListItem> items){
        this.items = items;
    }

    public void toDocBookTag(final StringBuilder ans, final String Tag){
        ans.append("<").append(Tag).append(">");
        for (final ListItem i : items) {
            i.toDocBook(ans);
        }
        ans.append("</").append(Tag).append(">");
    }
}
