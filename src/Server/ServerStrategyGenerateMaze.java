package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;

public class ServerStrategyGenerateMaze implements IServerStrategy {

    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        int[] size = getSize(inFromClient);

    }

    private int[] getSize (InputStream stream){
        try {
            ObjectInputStream fromClient = new ObjectInputStream(stream);
            int[] size = (int[]) fromClient.readObject();
            fromClient.close();
            return size;
        } catch (IOException e){
            System.out.println("The parameters aren't valid");
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            System.out.println("The sent problem is not valid");
            e.printStackTrace();
        }
        return null;
    }
}
