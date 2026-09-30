import java.util.Iterator;
import java.util.Scanner;

public class UserInterface {

    private Scanner scanner;
    private WarehouseBusiness warehouseBusiness;

    public UserInterface() {

        scanner = new Scanner(System.in);

        warehouseBusiness =
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
            warehouseBusiness.addClient(request);

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
            warehouseBusiness.getClient(id);

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
            warehouseBusiness.getOperator(id);

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
                "4. View Clients"
            );

            System.out.println(
                "5. View Client Wishlist"
            );

            System.out.println(
                "6. Add Product To Client Wishlist"
            );

            // System.out.println(
            //     "7. Remove Product From Client Wishlist"
            // );

            System.out.println(
                "0. Logout"
            );

            System.out.print(
                "Choice: "
            );

            choice =
                scanner.nextInt();

            scanner.nextLine();
            int caseChoice;

            switch (choice) {
                case 1:
                    do {
                        addProduct();
                    
                        System.out.print("\n1. Add Another Product\n0. Exit\nEnter choice: ");
                        caseChoice = scanner.nextInt();

                        if (scanner.hasNextLine()) {
                            scanner.nextLine(); 
                        }

                    } while (caseChoice  != 0);
                    break;

                case 2:
                    warehouseBusiness.displayProducts();
                    break;

                case 3:
                    do {
                        addClient();
                    
                        System.out.print("\n1. Add Another Client\n0. Exit\nEnter choice: ");
                        caseChoice = scanner.nextInt();

                        if (scanner.hasNextLine()) {
                            scanner.nextLine(); 
                        }

                    } while (caseChoice  != 0);
                    break;

                case 4:
                    warehouseBusiness.displayClients();
                    break;

                case 5:
                    String clientID = requestClientID();
                    warehouseBusiness.displayWishlist(clientID);
                    break;

                case 6:
                    String currentClientID = requestClientID();

                    do {
                        warehouseBusiness.addProductWishlist(currentClientID, requestProductID(), requestQuantity());

                        System.out.print("\n1. Add Another Product\n0. Exit\nEnter choice: ");
                        caseChoice = scanner.nextInt();

                        if (scanner.hasNextLine()) {
                            scanner.nextLine(); 
                        }

                    } while (caseChoice  != 0);
                    break;

                // case 7:
                //     String currentClientID = requestClientID();
                //     String currentProductID = requestProductID();
                //     warehouseBusiness.removeProductWishlist(currentClientID, currentProductID);
                //     break;

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
            warehouseBusiness.addClient(request);

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
            warehouseBusiness.addProduct(request);

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
            warehouseBusiness
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
    // REQUEST CLIENT ID
    // =========================

    private String requestClientID() {
        System.out.print(
            "Client ID: "
        );

        String currentClientID =
            scanner.nextLine();

        return currentClientID;
    }

    // =========================
    // REQUEST PRODUCT ID
    // =========================

    private String requestProductID() {
        System.out.print(
            "Product ID: "
        );

        String currentProductID =
            scanner.nextLine();

        return currentProductID;
    }

    // =========================
    // REQUEST QUANTITY
    // =========================

    private int requestQuantity() {
        System.out.print(
            "Quantity: "
        );

        int quantity =
            scanner.nextInt();

        return quantity;
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
            warehouseBusiness.addProductWishlist(
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
            warehouseBusiness.getClient(clientID);

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