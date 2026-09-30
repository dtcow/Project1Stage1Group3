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

        if (existing != null) { //If product exists, sets quantity
            existing.setQuantity(quantity);
            return true;
        }

        if (product.getQuantity() < quantity) { //Fails if we request a quantity thats larger than whats in the Inventory
            System.out.println("Requested quantity exceeds Inventory quantity!");
            return false;
        }

        product.setQuantity(product.getQuantity() - quantity);

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

    public String viewWishlist() {
        return toString();
    }

    @Override
    public String toString() {
        StringBuilder productsString = new StringBuilder();
        productsString.append("\n===== WISHLIST =====\n");
        for (WishListItem item : items) {
            Product product = item.getProduct();
            productsString.append("Name: ").append(product.getName()).append("\n");
            productsString.append("ID: ").append(product.getProductID()).append("\n");
            productsString.append("SalePrice: ").append(product.getSalePrice()).append("\n");
            productsString.append("Quantity: ").append(item.getQuantity()).append("\n----------\n"); //Different Quantity than product Quantity.
        }
        return productsString.toString();
    }
}