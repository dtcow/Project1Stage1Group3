import java.util.Iterator;
import java.util.Scanner;

public class UserInterface {

    private Scanner scanner;
    private WarehouseBusiness warehouse;

    public UserInterface() {

        scanner = new Scanner(System.in);

        warehouse =
            WarehouseBusiness.instance();
    }


    // =========================
    // MAIN MENU
    // =========================

    public void process() {

        int choice;

        do {

            System.out.println(
                "\n===== WAREHOUSE SYSTEM ====="
            );

            System.out.println("1. Client");
            System.out.println("2. Operator");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    clientStart();
                    break;

                case 2:
                    operatorLogin();
                    break;

                case 0:
                    System.out.println("Goodbye.");
                    break;

                default:
                    System.out.println(
                        "Invalid choice."
                    );
            }

        } while (choice != 0);
    }


    // =========================
    // CLIENT START
    // =========================

    private void clientStart() {

        System.out.println(
            "\n===== CLIENT ====="
        );

        System.out.println(
            "1. Create Account"
        );

        System.out.println(
            "2. Enter Client ID"
        );

        System.out.println(
            "0. Back"
        );

        System.out.print("Choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            createClientAccount();
        }

        else if (choice == 2) {
            clientLogin();
        }
    }


    // =========================
    // CREATE CLIENT
    // =========================

    private void createClientAccount() {

        System.out.print(
            "Client name: "
        );

        String name =
            scanner.nextLine();

        System.out.print(
            "Address: "
        );

        String address =
            scanner.nextLine();

        Request request =
            Request.instance();

        request.setClientName(name);
        request.setAddress(address);

        Result result =
            warehouse.addClient(request);

        if (result.getResultCode()
                == Result.OPERATION_COMPLETED) {

            System.out.println(
                "\nAccount created!"
            );

            System.out.println(
                "Your ID is: "
                + result.getClientID()
            );

            System.out.println(
                "You are now logged in."
            );

            clientMenu(
                result.getClientID()
            );

        } else {

            System.out.println(
                "Unable to create account."
            );
        }
    }


    // =========================
    // CLIENT LOGIN
    // =========================

    private void clientLogin() {

        System.out.print(
            "Enter Client ID: "
        );

        String id =
            scanner.nextLine();

        Client client =
            warehouse.getClient(id);

        if (client == null) {

            System.out.println(
                "Client not found."
            );

            return;
        }

        System.out.println(
            "Welcome "
            + client.getName()
        );

        clientMenu(id);
    }


    // =========================
    // CLIENT MENU
    // =========================

    private void clientMenu(
            String clientID) {

        int choice;

        do {

            System.out.println(
                "\n===== CLIENT MENU ====="
            );

            System.out.println(
                "1. View Products"
            );

            System.out.println(
                "2. View Wishlist"
            );

            System.out.println(
                "3. Add Product to Wishlist"
            );

            System.out.println(
                "0. Logout"
            );

            System.out.print(
                "Choice: "
            );

            choice =
                scanner.nextInt();

            scanner.nextLine();

            switch (choice) {

                case 1:
                    displayProducts();
                    break;

                case 2:
                    displayWishlist(
                        clientID
                    );
                    break;

                case 3:
                    addProductToWishlist(
                        clientID
                    );
                    break;

                case 0:
                    System.out.println(
                        "Logged out."
                    );
                    break;

                default:
                    System.out.println(
                        "Invalid choice."
                    );
            }

        } while (choice != 0);
    }


    // =========================
    // OPERATOR LOGIN
    // =========================

    private void operatorLogin() {

        System.out.print(
            "Enter Operator ID: "
        );

        String id =
            scanner.nextLine();

        Operator operator =
            warehouse.getOperator(id);

        if (operator == null) {

            System.out.println(
                "Operator not found."
            );

            return;
        }

        System.out.println(
            "Welcome "
            + operator.getName()
        );

        operatorMenu();
    }


    // =========================
    // OPERATOR MENU
    // =========================

    private void operatorMenu() {

        int choice;

        do {

            System.out.println(
                "\n===== OPERATOR MENU ====="
            );

            System.out.println(
                "1. Add Product"
            );

            System.out.println(
                "2. View Products"
            );

            System.out.println(
                "3. Add Client"
            );

            System.out.println(
                "0. Logout"
            );

            System.out.print(
                "Choice: "
            );

            choice =
                scanner.nextInt();

            scanner.nextLine();

            switch (choice) {

                case 1:
                    addProduct();
                    break;

                case 2:
                    displayProducts();
                    break;

                case 3:
                    addClient();
                    break;

                case 0:
                    System.out.println(
                        "Logged out."
                    );
                    break;

                default:
                    System.out.println(
                        "Invalid choice."
                    );
            }

        } while (choice != 0);
    }


    // =========================
    // ADD CLIENT
    // =========================

    private void addClient() {

        System.out.print(
            "Client name: "
        );

        String name =
            scanner.nextLine();

        System.out.print(
            "Address: "
        );

        String address =
            scanner.nextLine();

        Request request =
            Request.instance();

        request.setClientName(name);
        request.setAddress(address);

        Result result =
            warehouse.addClient(request);

        if (result.getResultCode()
                == Result.OPERATION_COMPLETED) {

            System.out.println(
                "Client added."
            );

            System.out.println(
                "Client ID: "
                + result.getClientID()
            );

        } else {

            System.out.println(
                "Unable to add client."
            );
        }
    }


    // =========================
    // ADD PRODUCT
    // =========================

    private void addProduct() {

        System.out.print(
            "Product name: "
        );

        String name =
            scanner.nextLine();

        System.out.print(
            "Quantity: "
        );

        int quantity =
            scanner.nextInt();

        System.out.print(
            "Sale price: "
        );

        double price =
            scanner.nextDouble();

        scanner.nextLine();

        Request request =
            Request.instance();

        request.setProductName(name);
        request.setQuantity(quantity);
        request.setSalePrice(price);

        Result result =
            warehouse.addProduct(request);

        if (result.getResultCode()
                == Result.OPERATION_COMPLETED) {

            System.out.println(
                "Product added."
            );

            System.out.println(
                "Product ID: "
                + result.getProductID()
            );

        } else if (
            result.getResultCode()
                == Result.DUPLICATE_PRODUCT) {

            System.out.println(
                "Product already exists."
            );

        } else {

            System.out.println(
                "Unable to add product."
            );
        }
    }


    // =========================
    // DISPLAY PRODUCTS
    // =========================

    private void displayProducts() {

        Iterator<Product> products =
            warehouse
                .getInventory()
                .getProducts();

        System.out.println(
            "\n===== PRODUCTS ====="
        );

        while (products.hasNext()) {

            Product product =
                products.next();

            System.out.println(
                "ID: "
                + product.getProductID()
            );

            System.out.println(
                "Name: "
                + product.getName()
            );

            System.out.println(
                "Quantity: "
                + product.getQuantity()
            );

            System.out.println(
                "Price: $"
                + product.getSalePrice()
            );

            System.out.println(
                "--------------------"
            );
        }
    }


    // =========================
    // ADD TO WISHLIST
    // =========================

    private void addProductToWishlist(
            String clientID) {

        System.out.print(
            "Product ID: "
        );

        String productID =
            scanner.nextLine();

        System.out.print(
            "Quantity wanted: "
        );

        int quantity =
            scanner.nextInt();

        scanner.nextLine();

        Request request =
            Request.instance();

        request.setClientID(clientID);
        request.setProductID(productID);
        request.setQuantity(quantity);

        Result result =
            warehouse.addProductWishlist(
                request
            );

        if (result.getResultCode()
                == Result.OPERATION_COMPLETED) {

            System.out.println(
                "Product added to wishlist."
            );

        } else if (
            result.getResultCode()
                == Result.PRODUCT_NOT_FOUND) {

            System.out.println(
                "Product not found."
            );

        } else {

            System.out.println(
                "Unable to add product."
            );
        }
    }


    // =========================
    // DISPLAY WISHLIST
    // =========================

    private void displayWishlist(
            String clientID) {

        Client client =
            warehouse.getClient(clientID);

        if (client == null) {

            System.out.println(
                "Client not found."
            );

            return;
        }

        Iterator<WishListItem> items =
            client
                .getWishlist()
                .getWishlistItems();

        System.out.println(
            "\n===== WISHLIST ====="
        );

        while (items.hasNext()) {

            WishListItem item =
                items.next();

            Product product =
                item.getProduct();

            System.out.println(
                "Product ID: "
                + product.getProductID()
            );

            System.out.println(
                "Name: "
                + product.getName()
            );

            System.out.println(
                "Wanted: "
                + item.getQuantity()
            );

            System.out.println(
                "Price: $"
                + product.getSalePrice()
            );

            System.out.println(
                "--------------------"
            );
        }
    }


    // =========================
    // MAIN
    // =========================

    public static void main(String[] args) {

        UserInterface ui =
            new UserInterface();

        ui.process();
    }
}