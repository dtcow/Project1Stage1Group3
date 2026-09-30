public class Product {

    private static int idCounter = 1;

    private String productID;
    private String name;
    private int quantity;
    private double salePrice;

    public Product(String name, int quantity, double salePrice) {

        this.productID = "PR" + idCounter++;
        this.name = name;
        this.quantity = quantity;
        this.salePrice = salePrice;
    }

    public String getProductID() {
        return productID;
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
}