public class Client extends Person {

    private double balance;
    private WishList wishlist;

    public Client(String name, String address) {
        super(name, address);

        balance = 0.0;
        wishlist = new WishList();
    }

    public double getBalance() {
        return balance;
    }

    public WishList getWishlist() {
        return wishlist;
    }
}