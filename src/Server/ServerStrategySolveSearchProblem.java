package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.time.chrono.IsoChronology;
import java.util.HashMap;

public class ServerStrategySolveSearchProblem implements IServerStrategy{

    private HashMap<Maze, Integer> mazePool;
    private int nameCounter;
    private String mazeTempDirectoryPath;
    private String solTempDirectoryPath;

    public ServerStrategySolveSearchProblem() {
        this.mazePool = new HashMap<>();
        nameCounter = 0;

        String tempDirectoryPath = System.getProperty("java.io.tmpdir");
        new File(tempDirectoryPath + "\\Maze").mkdir();
        new File(tempDirectoryPath + "\\Sol").mkdir();
        mazeTempDirectoryPath = tempDirectoryPath + "\\Maze";
        solTempDirectoryPath = tempDirectoryPath + "\\Sol";
    }

    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient){

        try {
            // Receiving the problem from client
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            Maze mazeFromClient = (Maze) fromClient.readObject();
            ISearchable prob = new SearchableMaze(mazeFromClient);
            fromClient.close();

            Solution solution;

            // Sending to client the problem solution
            if(!mazePool.containsKey(mazeFromClient))
            {
                saveCompMaze(mazeFromClient);
                ASearchingAlgorithm algorithm = new BestFirstSearch();
                solution = algorithm.solve(prob);
                saveSol(solution);

                nameCounter++;
            }
            else
            {
                solution = retrieveMazeSol(mazeFromClient);
            }

            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            toClient.writeObject(solution);

            toClient.flush();
            toClient.close();
        }
        catch(IOException e)
        {
            System.out.println("Invalid parametes");
            e.printStackTrace();
        }
        catch(ClassNotFoundException | ClassCastException e)
        {
            System.out.println("The sent problem is invalid");
            e.printStackTrace();
        }
    }

    private void saveCompMaze(Maze maze)
    {
        try {
            File newMaze = new File(mazeTempDirectoryPath + "\\" + nameCounter);
            newMaze.createNewFile();

            FileOutputStream out = new FileOutputStream(mazeTempDirectoryPath + "\\" + nameCounter);
            MyCompressorOutputStream compressor = new MyCompressorOutputStream(out);
            compressor.write(maze.toByteArray());

            mazePool.put(maze, nameCounter);

            compressor.close();
        }
        catch(IOException e)
        {
            System.out.println("Invalid path");
            e.printStackTrace();
        }
    }

    private void saveSol(Solution sol)
    {
        try
        {
            File newSol = new File(solTempDirectoryPath + "\\" + nameCounter);
            newSol.createNewFile();

            FileOutputStream out = new FileOutputStream(solTempDirectoryPath + "\\" + nameCounter);
            ObjectOutputStream objectOut = new ObjectOutputStream(out);
            objectOut.writeObject(sol);

            objectOut.close();
        }
        catch(IOException e)
        {
            System.out.println("Invalid path");
            e.printStackTrace();
        }
    }

    private Solution retrieveMazeSol(Maze maze)
    {
        try
        {
            FileInputStream in = new FileInputStream(solTempDirectoryPath + "\\" + mazePool.get(maze));
            ObjectInputStream objectIn = new ObjectInputStream(in);
            Solution returnSol = (Solution) objectIn.readObject();
            
            objectIn.close();
            
            return returnSol;
        }
        
        catch(IOException | ClassNotFoundException | ClassCastException e)
        {
            System.out.println("Invalid path");
            e.printStackTrace();
            return null;
        }
    }

}
