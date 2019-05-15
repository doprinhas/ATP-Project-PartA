package Client;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Every Clients Strategy
 */
public interface IClientStrategy {
    void clientStrategy(InputStream inFromServer, OutputStream outToServer);
}
