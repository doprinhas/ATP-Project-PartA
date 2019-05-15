package Server;

import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.ASearchingAlgorithm;
import algorithms.search.BestFirstSearch;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.DepthFirstSearch;

import java.io.*;
import java.util.Properties;

public class Configurations {

    // variable Definition
    private static final String FILE_NAME = "resources/config.properties";
    public static final String THREAD_POOL_SIZE = "ThreadPool Size";
    private static final String SOLVER_ALGORITHM = "Solver Algorithm";
    private static final String MAZE_GENERATOR = "Maze Generator";

    /**
     * This function returns an output stream of the configuration file
     * @return output stream of the configuration file
     */
    private static OutputStream getConfigurationsFile(){
        try {
            OutputStream output = new FileOutputStream(FILE_NAME);
            return output;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This function returns the current properties InputStream
     * @return current properties InputStream
     */
    private static Properties loadConfigurationsFile(){
        try {

            FileInputStream input = new FileInputStream(FILE_NAME);
            Properties prop = new Properties();
            prop.load(input);
            input.close();

            return prop;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This function set new configuration to the file
     * @param conName - configuration name
     * @param conValue - configuration value
     */
    private static void setConfiguration(String conName , String conValue){
        try {

            Properties prop = loadConfigurationsFile();
            prop.setProperty(conName , conValue);
            OutputStream out = getConfigurationsFile();
            prop.store(out , null);
            out.close();
        } catch (IOException e){
            System.out.println("File Not Found");
            e.printStackTrace();
        }
    }

    /**
     * This function returns the value of the sent configuration
     * @param conName - name of configuration
     * @return value of the sent configuration
     */
    private static String getConfiguration(String conName){
        Properties prop = loadConfigurationsFile();
        return prop.getProperty(conName);
    }


    //*********************** - Set Functions - *************************//

    /**
     * This function sets default configurations
     * Thread pool size - 5
     * Solver algorithm - "Best first search"
     * Maze Generator - My Maze
     */
    public static void setDefaultConfigurations(){

        OutputStream out = getConfigurationsFile();
        Properties prop = new Properties();

        prop.setProperty(THREAD_POOL_SIZE , "5");
        prop.setProperty(SOLVER_ALGORITHM , "Best First Search");
        prop.setProperty(MAZE_GENERATOR , "MyMaze");


        try {
            out.flush();
            prop.store(out , null);
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function set new size for the thread pool
     * Sets the default size if the input is invalid
     * @param num - positive int
     */
    public static void setThreadPoolSize(int num){
        if (num < 0)
            num = 5;

        setConfiguration(THREAD_POOL_SIZE , "" + num);
    }

    /**
     * This function sets the solver algorithm for the server
     * possible inputs:
     * "Best first search",
     * "Breadth first search",
     * "Depth first search".
     * @param algorithm
     */
    public static void setSolveAlgorithm(String algorithm){

        if (!algorithm.equals("Best first search") && !algorithm.equals("Breadth first search") &&
                !algorithm.equals("Depth first search"))
           algorithm = "Best first search";

        setConfiguration(SOLVER_ALGORITHM , algorithm);
    }

    /**
     * This function sets a maze generator for the server
     * possible inputs:
     * "MyMaze",
     * "SimpleMaze",
     * "EmptyMaze".
     * @param generator
     */
    public static void setMazeGenerator(String generator){

        if ( !generator.equals("MyMaze") && !generator.equals("SimpleMaze") && !generator.equals("EmptyMaze"))
            generator = "MyMaze";

        setConfiguration(MAZE_GENERATOR , generator);
    }

    //*********************** - Get Functions - *************************//

    /**
     * This function returns the size of the thread pool
     * @return the size of the thread pool
     */
    public static int getThreadPoolSize(){

        return Integer.valueOf(getConfiguration(THREAD_POOL_SIZE));
    }

    /**
     * This function returns the Searching algorithm
     * @return the Searching algorithm
     */
    public static ASearchingAlgorithm getSolverAlgorithm(){

        String algorithm = getConfiguration(SOLVER_ALGORITHM);

        if (algorithm.equals("Best first search"))
            return new BestFirstSearch();

        else if (algorithm.equals("Breadth first search"))
            return new BreadthFirstSearch();

        else
            return new DepthFirstSearch();
    }

    /**
     * This function returns the maze generator
     * @return the maze generator
     */
    public static AMazeGenerator getMazeGenerator(){

        String generator = getConfiguration(MAZE_GENERATOR);

        if ( generator.equals("MyMaze") )
            return new MyMazeGenerator();

        else if (generator.equals("SimpleMaze"))
            return new SimpleMazeGenerator();

        else
            return new EmptyMazeGenerator();
    }

}
