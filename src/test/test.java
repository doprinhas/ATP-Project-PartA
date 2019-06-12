package test;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

public class test {

    public static void main(String[] args) {
        {
            MyMazeGenerator a = new MyMazeGenerator();
            Maze maze = a.generate(10, 10);
            maze.print();
        }
    }
}