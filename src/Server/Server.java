package Server;

/*import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;*/

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Server {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy serverStrategy;
    private volatile boolean stop;

    public Server(int port, int listeningIntervalMS, IServerStrategy serverStrategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.serverStrategy = serverStrategy;
    }

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);

            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept(); // Accepts client
                    try {
                        serverStrategy.serverStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
                        clientSocket.getInputStream().close();
                        clientSocket.getOutputStream().close();
                        clientSocket.close();
                    } catch (IOException e) {
                        //LOG.error("IOException - Error handing client!", e);
                        System.out.println(e.getStackTrace());
                    }
                } catch (SocketTimeoutException e) {
                    //LOG.debug("Socket Timeout - No clients are waiting!");
                    System.out.println(e.getStackTrace());
                }
            }
            serverSocket.close();
        } catch (IOException e) {
            //LOG.error("IOException:", e);
            System.out.println(e.getStackTrace());
        }
    }

    public void stop() {
        //LOG.info("Stopping server..");
        stop = true;
    }
}
