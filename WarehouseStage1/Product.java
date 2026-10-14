import java.util.LinkedList;
import java.util.Queue;

public class Product {

    private static int idCounter = 1;

    private String productID;
    private String name;
    private int quantity;
    private double salePrice;
    private record Waitlisted(Client client, int quantity) {}
    Queue<Waitlisted> waitlist = new LinkedList<>();

    public Product(String name, int quantity, double salePrice) {

        this.productID = "PR" + idCounter++;
        this.name = name;
        this.quantity = quantity;
        this.salePrice = salePrice;
    }

    public String getProductID() {
        return productID;
    }

    public void viewWaitlist() {
        StringBuilder productsString = new StringBuilder();
        productsString.append("\n===== WAITLIST =====\n");
        productsString.append("Product: ").append(name).append("\n");
        for (Waitlisted waitlisted : waitlist) {
            productsString.append("ClientID: ").append(waitlisted.client.getID());
            productsString.append("Quantity: ").append(waitlisted.quantity).append("\n----------\n");
        }
        System.out.println(productsString.toString());
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void addToWaitlist(Client client, int quantity) {
        Waitlisted waitlisted = new Waitlisted(client, quantity);
        waitlist.add(waitlisted);
    }
}