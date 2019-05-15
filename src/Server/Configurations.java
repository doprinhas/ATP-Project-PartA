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

    private static final String FILE_NAME = "resources/config.properties";
    public static final String THREAD_POOL_SIZE = "ThreadPool Size";
    private static final String SOLVER_ALGORITHM = "Solver Algorithm";
    private static final String MAZE_GENERATOR = "Maze Generator";

    private static OutputStream getConfigurationsFile(){
        try {
            OutputStream output = new FileOutputStream(FILE_NAME);
            return output;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Properties loadConfigurationsFile(){
        try {
            InputStream input = new FileInputStream(FILE_NAME);
            Properties prop = new Properties();
            prop.load(input);

            return prop;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    private static void setConfiguration(String conName , String conValue){
        try {
            OutputStream out = getConfigurationsFile();
            Properties prop = loadConfigurationsFile();
            prop.setProperty(conName , conValue);
            prop.store(out , null);
        } catch (IOException e){
            System.out.println("File Not Found");
            e.printStackTrace();
        }
    }

    private static String getConfiguration(String conName){
        Properties prop = loadConfigurationsFile();
        return prop.getProperty(conName);
    }


    //*********************** - Set Functions - *************************//

    public static void setDefaultConfigurations(){

        OutputStream out = getConfigurationsFile();
        Properties prop = new Properties();

        prop.setProperty(THREAD_POOL_SIZE , "5");
        prop.setProperty(SOLVER_ALGORITHM , "Best First Search");
        prop.setProperty(MAZE_GENERATOR , "MyMaze");

        try {
            prop.store(out , null);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setThreadPoolSize(int num){
        if (num < 0)
            num = 5;

        setConfiguration(THREAD_POOL_SIZE , "" + num);
    }

    public static void setSolveAlgorithm(String algorithm){

        if (!algorithm.equals("Best first search") && !algorithm.equals("Breadth first search") &&
                !algorithm.equals("Depth first search"))
           algorithm = "Best first search";

        setConfiguration(SOLVER_ALGORITHM , algorithm);
    }

    public static void setMazeGenerator(String generator){

        if ( !generator.equals("MyMaze") && !generator.equals("SimpleMaze") && !generator.equals("EmptyMaze"))
            generator = "MyMaze";

        setConfiguration(MAZE_GENERATOR , generator);
    }

    //*********************** - Get Functions - *************************//

    public static int getThreadPoolSize(){

        return Integer.valueOf(getConfiguration(THREAD_POOL_SIZE));
    }

    public static ASearchingAlgorithm getSolverAlgorithm(){

        String algorithm = getConfiguration(SOLVER_ALGORITHM);

        if (algorithm.equals("Best first search"))
            return new BestFirstSearch();

        else if (algorithm.equals("Breadth first search"))
            return new BreadthFirstSearch();

        else
            return new DepthFirstSearch();
    }

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
