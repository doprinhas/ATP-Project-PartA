package test;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.*;

import java.io.*;
import java.util.Arrays;

/**
 * Created by Aviadjo on 3/26/2017.
 */
public class RunCompressDecompressMaze {
    public static void main(String[] args) {

/*        long lStartTime = System.nanoTime();
        AMazeGenerator mazeGenerator = new SimpleMazeGenerator();
        Maze maze = mazeGenerator.generate(1000, 1000);
//        maze.print();
        long lEndTime = System.nanoTime();
        System.out.println("Elapsed time in seconds: " + (double)(lEndTime-lStartTime) / 1000000000);

        lStartTime = System.nanoTime();
        maze.toByteArray();
        lEndTime = System.nanoTime();
        System.out.println("Elapsed time in seconds: " + (double)(lEndTime-lStartTime) / 1000000000);*/


        String mazeFileName = "savedMaze.maze";
        AMazeGenerator mazeGenerator = new SimpleMazeGenerator();
        Maze maze = mazeGenerator.generate(1000, 1000); //Generate new maze
        maze.print();
        try {
            // save maze to a file
            OutputStream out = new MyCompressorOutputStream(new FileOutputStream(mazeFileName));
            out.write(maze.toByteArray());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*byte savedMazeBytes[] = new byte[0];
        try {
            //read maze from file
            InputStream in = new MyDecompressorInputStream(new FileInputStream(mazeFileName));
            savedMazeBytes = new byte[maze.toByteArray().length];
            in.read(savedMazeBytes);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Maze loadedMaze = new Maze(savedMazeBytes);
        boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(),maze.toByteArray());
        System.out.println(String.format("Mazes equal: %s",areMazesEquals)); //maze should be equal to loadedMaze*/
    }
}