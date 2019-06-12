package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator {

    public Maze generate(int floors, int rows, int columns)
    {
        Maze emptyMaze = new Maze(floors*2-1, rows, columns);
        for (int i=0; i<rows; i++)
            for (int j=0; j<columns; j++)
                for(int k=0; k<floors; k++)
                    emptyMaze.maze[i][j][k] = 0;
        return emptyMaze;
    }

    @Override
    public Maze generate(int rows, int columns) {
        return generate(1, rows, columns);
    }
}
