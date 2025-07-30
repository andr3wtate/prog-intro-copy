package markup;

import java.util.List;

public class OrderedList extends GeneralList implements ListItemElement {

    public OrderedList(List<ListItem> x){
        super(x);
    }

    public void toDocBook(StringBuilder ans) {
        super.toDocBookTag(ans, "orderedlist");
    }
}
