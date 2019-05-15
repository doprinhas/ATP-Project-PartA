package Client;

import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Aviadjo on 3/3/2017.
 */
public class Client {
    private InetAddress serverIP;
    private int serverPort;
    private IClientStrategy clientStrategy;

    /**
     * constructor
     * @param IP
     * @param port
     * @param clientStrategy
     */
    public Client(InetAddress IP, int port, IClientStrategy clientStrategy) {
        this.serverIP = IP;
        this.serverPort = port;
        this.clientStrategy = clientStrategy;
    }

    /**
     * This function starts the clients request for the server
     */
    public void start(){
        try {
            Socket theServer = new Socket(serverIP, serverPort);
            System.out.println("Client is connected to server!");
            clientStrategy.clientStrategy(theServer.getInputStream(),theServer.getOutputStream());
            theServer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This function starts the clients request for the server
     */
    public void communicateWithServer(){
        start();
    }
}
