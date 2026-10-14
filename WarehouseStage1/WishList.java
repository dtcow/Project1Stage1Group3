import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class WishList {

    private Scanner scanner;
    private List<WishListItem> items;
    private Client client;

    public WishList(Client client) {
        scanner = new Scanner(System.in);
        items = new LinkedList<WishListItem>();
        this.client = client;
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

        // product.setQuantity(product.getQuantity() - quantity); //This subtraction will be done when a Client purchases an item.

        WishListItem item =
            new WishListItem(product, quantity);

        items.add(item);

        return true;
    }

    public void purchase() {
        Transaction transaction = null;
        for (WishListItem wishListItem : items) { // Iterate through all products
            int chosenQuantity;
            do {
                System.out.println("\n===== PRODUCT =====");
                Product currentProduct = wishListItem.getProduct();
                System.out.println("Product: " + wishListItem.getName() + "\nID: " + wishListItem.getProductID() + "\nCurrent Quantity: " + wishListItem.getQuantity() + "\n----------\n");
                System.out.print("\nWhat is the quantity you wish to purchase: ");
                chosenQuantity = scanner.nextInt();

                if (scanner.hasNextLine()) scanner.nextLine();

                if (chosenQuantity < 0) {
                    System.out.println("Input Must Be Greater Than Zero");
                } 
                else if (chosenQuantity > currentProduct.getQuantity()) {
                    System.out.println("Quantity greater than Inventory Quantity of: " + currentProduct.getQuantity() + 
                    "\n\nWould you like to-" +
                    "\nRequest new quantity: 0" +
                    "\nAdd to Waitlist: 1");
                    System.out.print("\nAnswer: ");
                    int choice = scanner.nextInt();
                    if (scanner.hasNextLine()) scanner.nextLine();
                    
                    if (choice == 1) {//Add To Waitlist
                        currentProduct.addToWaitlist(client, chosenQuantity);
                    }
                    else {
                        chosenQuantity = -1; //Will Repeat last request
                    }
                } 
                else { // Set to quantity
                    if (transaction == null) transaction = new Transaction(client.getID());

                    double productSalePrice = currentProduct.getSalePrice();
                    transaction.AddProductToTransaction(productSalePrice, wishListItem.getProductID(), chosenQuantity);
                }

            } while (chosenQuantity < 0); // If negative ask again
        }
        //Bill Cost to account
        client.charge(transaction.getTotalCost());
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