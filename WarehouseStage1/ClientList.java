import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ClientList {

    private static ClientList clientList;

    private List<Client> clients;

    private ClientList() {
        clients = new LinkedList<Client>();
    }

    public static ClientList instance() {

        if (clientList == null) {
            clientList = new ClientList();
        }

        return clientList;
    }

    public Client getClient(String clientID) {

        for (Client client : clients) {

            if (client.getID().equals(clientID)) {
                return client;
            }
        }

        return null;
    }

    public Client findClient(String name) {

        for (Client client : clients) {

            if (client.getName().equals(name)) {
                return client;
            }
        }

        return null;
    }

    public boolean insertClient(Client client) {

        if (getClient(client.getID()) != null) {
            return false;
        }

        clients.add(client);
        return true;
    }

    public Iterator<Client> getClients() {
        return clients.iterator();
    }
}