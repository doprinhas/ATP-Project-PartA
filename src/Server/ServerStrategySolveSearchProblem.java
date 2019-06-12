package Server;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class ServerStrategySolveSearchProblem implements IServerStrategy{

    private int nameCounter;
    final private String mazeTempDirectoryPath;
    final private String solTempDirectoryPath;
    final private String counterTempFilePath;

    /**
     * This function builds the folder for the mazes solution
     */
    public ServerStrategySolveSearchProblem() {

        String tempDirectoryPath = System.getProperty("java.io.tmpdir");
        mazeTempDirectoryPath = tempDirectoryPath + "\\Maze";
        solTempDirectoryPath = tempDirectoryPath + "\\Sol";
        counterTempFilePath = tempDirectoryPath + "\\Counter.txt";

        try // Reading the next new maze index
        {
            FileInputStream inCounter = new FileInputStream(counterTempFilePath);
            Scanner sc = new Scanner(inCounter);
            nameCounter = sc.nextInt();
        }
        catch (IOException e) // If Counter file dont exist
        {
            try
            {
                new File(counterTempFilePath).createNewFile();
                Writer outCounter = new FileWriter(counterTempFilePath);
                outCounter.write("" + 0);
                nameCounter = 0;
                outCounter.close();
            }
            catch(IOException e2)
            {
                e.printStackTrace();
            }
        }

        File mazeDir = new File(mazeTempDirectoryPath);
        File solDir = new File(solTempDirectoryPath);
        if(!mazeDir.exists() || !solDir.exists())
        {
            try
            {
                if (!mazeDir.mkdir() || !solDir.mkdir())
                    throw new Exception("Cant create folder");
            }
            catch(Exception e)
            {
                System.out.println("Cant create folder");
                e.printStackTrace();
            }
        }

    }

    /**
     * This function gets a maze and return the solution for that maze
     * @param inFromClient
     * @param outToClient
     */
    @Override
    public synchronized void serverStrategy(InputStream inFromClient, OutputStream outToClient){

       try {
            // Receiving the problem from client
            MyCompressorOutputStream compressor = new MyCompressorOutputStream(outToClient);
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(compressor);
            Maze mazeFromClient = (Maze) fromClient.readObject();
            ISearchable prob = new SearchableMaze(mazeFromClient);

            byte[] byteClientMaze = mazeFromClient.toByteArray();
            Solution solution;
            int mazeIndex = isMazeExists(byteClientMaze);
            // Sending to client the problem solution
            if(mazeIndex == -1)
            {
                saveCompMaze(byteClientMaze);
                ASearchingAlgorithm algorithm = Configurations.getSolverAlgorithm();
                solution = algorithm.solve(prob);
                saveSol(solution);

                addOneToCounter();
            }
            else
            {
                solution = retrieveMazeSol(mazeIndex);
            }

            toClient.writeObject(solution);

            toClient.flush();
            toClient.close();
            fromClient.close();
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

    private void addOneToCounter()
    {
        try
        {
            nameCounter++;
            Writer counterWriter = new FileWriter(counterTempFilePath, false);
            counterWriter.write("" + nameCounter);
            counterWriter.close();
        }
        catch(IOException e)
        {
            System.out.println("Invalid path");
            e.printStackTrace();
        }
    }

    private int isMazeExists(byte[] clientMaze)
    {
        for(int i=0; i<nameCounter; i++)
        {
            try
            {
                FileInputStream inStreamMaze = new FileInputStream(mazeTempDirectoryPath + "\\" + i);
                MyDecompressorInputStream decompressor = new MyDecompressorInputStream(inStreamMaze);
                byte[] inMaze = new byte[clientMaze.length];
                int mazeLength = decompressor.read(inMaze);
                if(clientMaze.length == mazeLength && Arrays.equals(inMaze, clientMaze))
                {
                    return i;
                }
            }
            catch(IOException e)
            {
                System.out.println("Invalid path");
                e.printStackTrace();
            }
        }
        return -1;
    }

    private void saveCompMaze(byte[] maze)
    {
        try {
            File newMaze = new File(mazeTempDirectoryPath + "\\" + nameCounter);
            newMaze.createNewFile();

            FileOutputStream out = new FileOutputStream(mazeTempDirectoryPath + "\\" + nameCounter);
            MyCompressorOutputStream compressor = new MyCompressorOutputStream(out);
            compressor.write(maze);

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

    private Solution retrieveMazeSol(int solNumber)
    {
        try
        {
            FileInputStream in = new FileInputStream(solTempDirectoryPath + "\\" + solNumber);
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
