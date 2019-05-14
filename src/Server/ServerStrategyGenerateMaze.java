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

            MyCompressorOutputStream compressor = new MyCompressorOutputStream(outToClient);
            ObjectOutputStream toClient = new ObjectOutputStream(compressor);

            int[] size = (int[])fromClient.readObject();

            AMazeGenerator mazeGenerator = new MyMazeGenerator();
            Maze maze = mazeGenerator.generate(size[0] , size[1]);

            toClient.writeObject(maze.toByteArray());

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
