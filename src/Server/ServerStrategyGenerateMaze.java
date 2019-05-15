package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {

    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            ByteArrayOutputStream outByteArray = new ByteArrayOutputStream();
            MyCompressorOutputStream compressor = new MyCompressorOutputStream(outByteArray);

            toClient.flush();

            int[] size = (int[])fromClient.readObject();

            AMazeGenerator mazeGenerator = Configurations.getMazeGenerator();
            Maze maze = mazeGenerator.generate(size[0] , size[1]);

            compressor.write(maze.toByteArray());
            toClient.writeObject(outByteArray.toByteArray());

            toClient.flush();
            toClient.close();

        } catch (IOException e) {
            System.out.println("The parameters aren't valid");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("The sent problem is not valid");
            e.printStackTrace();
        }


    }
}
