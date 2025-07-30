package markup;

import java.util.List;

public class UnorderedList extends GeneralList implements ListItemElement {

    public UnorderedList(final List<ListItem> x){
        super(x);
    }

    public void toDocBook(final StringBuilder ans) {
        toDocBookTag(ans, "itemizedlist");
    }
}
