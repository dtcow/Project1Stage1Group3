public class WarehouseBusiness {

    private static WarehouseBusiness warehouseBusiness;

    private ClientList clientList;
    private OperatorList operatorList;
    private Inventory inventory;

    private WarehouseBusiness() {

        clientList = ClientList.instance();
        operatorList = OperatorList.instance();
        inventory = Inventory.instance();

        // Create one default operator for our project.
        Operator operator =
            new Operator("Warehouse Operator", "Warehouse");

        operatorList.insertOperator(operator);

        System.out.println(
            "Default Operator ID: " + operator.getID()
        );
    }

    public static WarehouseBusiness instance() {

        if (warehouseBusiness == null) {
            warehouseBusiness =
                new WarehouseBusiness();
        }

        return warehouseBusiness;
    }


    // -------------------------
    // ADD CLIENT
    // -------------------------

    public Result addClient(Request request) {

        Result result = new Result();

        Client client = new Client(
            request.getClientName(),
            request.getAddress()
        );

        if (clientList.insertClient(client)) {

            result.setResultCode(
                Result.OPERATION_COMPLETED
            );

            result.setClientID(client.getID());
            result.setClientName(client.getName());

        } else {

            result.setResultCode(
                Result.OPERATION_FAILED
            );
        }

        return result;
    }


    // -------------------------
    // ADD PRODUCT
    // -------------------------

    public Result addProduct(Request request) {

        Result result = new Result();

        Product existing =
            inventory.findProduct(
                request.getProductName()
            );

        if (existing != null) {

            result.setResultCode(
                Result.DUPLICATE_PRODUCT
            );

            return result;
        }

        Product product = new Product(
            request.getProductName(),
            request.getQuantity(),
            request.getSalePrice()
        );

        if (inventory.insertProduct(product)) {

            result.setResultCode(
                Result.OPERATION_COMPLETED
            );

            result.setProductID(
                product.getProductID()
            );

            result.setProductName(
                product.getName()
            );

            result.setQuantity(
                product.getQuantity()
            );

            result.setSalePrice(
                product.getSalePrice()
            );

        } else {

            result.setResultCode(
                Result.OPERATION_FAILED
            );
        }

        return result;
    }


    // -------------------------
    // ADD PRODUCT TO WISHLIST
    // -------------------------

    public Result addProductWishlist(
            Request request) {

        Result result = new Result();

        Client client =
            clientList.getClient(
                request.getClientID()
            );

        if (client == null) {

            result.setResultCode(
                Result.CLIENT_NOT_FOUND
            );

            return result;
        }

        Product product =
            inventory.findProductByID(
                request.getProductID()
            );

        if (product == null) {

            result.setResultCode(
                Result.PRODUCT_NOT_FOUND
            );

            return result;
        }

        client.getWishlist().addProduct(
            product,
            request.getQuantity()
        );

        result.setResultCode(
            Result.OPERATION_COMPLETED
        );

        return result;
    }


    // -------------------------
    // FIND CLIENT
    // -------------------------

    public Client getClient(String clientID) {
        return clientList.getClient(clientID);
    }


    // -------------------------
    // FIND OPERATOR
    // -------------------------

    public Operator getOperator(String operatorID) {
        return operatorList.getOperator(operatorID);
    }


    // -------------------------
    // INVENTORY
    // -------------------------

    public Inventory getInventory() {
        return inventory;
    }
}