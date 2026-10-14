import java.util.LinkedList;
import java.util.List;

public class Client extends Person {

    private double balance;
    private WishList wishlist;
    private List<Transaction> transactions = new LinkedList<>();

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
        balance = Math.round(balance * 100.0d) / 100.0d;
    }

    public void credit(double credit) { //They pay off amount
        balance += credit;
        balance = Math.round(balance * 100.0d) / 100.0d;
        System.out.println("Paid $" + credit + " To " + getName() + "\nNew Balance: $" + balance);
    }

    public void addTransaction(Transaction currentTransaction) {
        transactions.add(currentTransaction);
    }

    public void viewTransactions() {
        for (Transaction transaction : transactions) {
            System.out.println(transaction.toString());
        }
    }
}