import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Inventory {

    private static Inventory inventory;

    private List<Product> products;

    private Inventory() {
        products = new LinkedList<Product>();
    }

    public static Inventory instance() {

        if (inventory == null) {
            inventory = new Inventory();
        }

        return inventory;
    }

    public Product findProduct(String name) {

        for (Product product : products) {

            if (product.getName().equals(name)) {
                return product;
            }
        }

        return null;
    }

    public Product findProductByID(String productID) {

        for (Product product : products) {

            if (product.getProductID().equals(productID)) {
                return product;
            }
        }

        return null;
    }

    public boolean insertProduct(Product product) {

        if (findProduct(product.getName()) != null) {
            return false;
        }

        products.add(product);
        return true;
    }

    public Iterator<Product> getProducts() {
        return products.iterator();
    }
}