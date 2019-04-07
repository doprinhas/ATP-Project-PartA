package alogrithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator {

    public Maze generate(int rows, int columns)
    {
        Maze emptyMaze = new Maze(rows, columns);
        for (int i=0; i<rows; i++)
            for (int j=0; j<columns; j++)
                emptyMaze.maze[i][j] = 0;
        return emptyMaze;
    }
}
