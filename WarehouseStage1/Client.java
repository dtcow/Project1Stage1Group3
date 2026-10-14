public class Client extends Person {

    private double balance;
    private WishList wishlist;

    public Client(String name, String address) {
        super(name, address);

        balance = 0.0;
        wishlist = new WishList(this);
    }

    public double getBalance() {
        return balance;
    }

    public WishList getWishlist() {
        return wishlist;
    }

    public void charge(double charge) { //They get charged amount
        balance -= charge;
    }

    public void credit(double credit) { //They pay off amount
        balance += credit;
    }
}