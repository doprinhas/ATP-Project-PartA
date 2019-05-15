package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Server strategies must implement this interface
 */
public interface IServerStrategy {
    void serverStrategy(InputStream inFromClient, OutputStream outToClient) throws IOException;
}
