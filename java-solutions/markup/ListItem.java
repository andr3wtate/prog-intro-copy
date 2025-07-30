package markup;

import java.util.List;

public class ListItem {

    final private List<ListItemElement> x;

    public ListItem(List<ListItemElement> x) {
        this.x = x;
    }

    public void toDocBook(StringBuilder ans) {
        ans.append("<listitem>");
        for (ListItemElement i : x) {
            i.toDocBook(ans);
        }
        ans.append("</listitem>");
    }
}
