package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    //variable definition
    private int port;
    private int listeningInterval;
    private IServerStrategy serverStrategy;
    private volatile boolean stop;
    private ExecutorService executorService;

    /**
     * constructor
     * @param port
     * @param listeningInterval
     * @param serverStrategy
     */
    public Server(int port, int listeningInterval, IServerStrategy serverStrategy) {
        this.port = port;
        this.listeningInterval = listeningInterval;
        this.serverStrategy = serverStrategy;

    }

    /**
     * Starts the new server
     */
    public void start() {
        new Thread(() -> {
            runServer();
        }).start();
    }

    /**
     * runs the server
     */
    private void runServer() {
        try {
            this.executorService = Executors.newFixedThreadPool(Configurations.getThreadPoolSize());
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningInterval);

            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept(); // blocking call
                    executorService.execute(() -> handleClient(clientSocket));
                } catch (SocketTimeoutException e) {
                    //System.out.println("waiting for clients");
                }
            }
            serverSocket.close();
            executorService.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function execute the server strategy for the client
     * @param clientSocket
     */
    private void handleClient(Socket clientSocket) {
        try {
            System.out.println(String.format("Handling client with socket: %s", clientSocket.toString()));
            serverStrategy.serverStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * stop the server
     */
    public void stop() {
        stop = true;
    }
}
