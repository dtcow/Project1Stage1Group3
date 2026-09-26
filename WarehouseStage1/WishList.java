import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class WishList {

    private List<WishListItem> items;

    public WishList() {
        items = new LinkedList<WishListItem>();
    }

    public boolean addProduct(Product product, int quantity) {

        WishListItem existing =
            findProduct(product.getProductID());

        if (existing != null) {
            existing.setQuantity(quantity);
            return true;
        }

        WishListItem item =
            new WishListItem(product, quantity);

        items.add(item);

        return true;
    }

    public WishListItem findProduct(String productID) {

        for (WishListItem item : items) {

            if (item.getProduct()
                    .getProductID()
                    .equals(productID)) {

                return item;
            }
        }

        return null;
    }

    public boolean updateQuantity(
            String productID,
            int quantity) {

        WishListItem item =
            findProduct(productID);

        if (item == null) {
            return false;
        }

        item.setQuantity(quantity);
        return true;
    }

    public Iterator<WishListItem> getWishlistItems() {
        return items.iterator();
    }
}