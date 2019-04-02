package alogrithms.mazeGenerators;

public class MyMazeGenerator extends AMazeGenerator{

    @Override
    public Maze generate(int rows, int columns) {
        Maze myMaze = new Maze(rows, columns);
        generateMaze(myMaze, 0, 0, rows, columns);
        return myMaze;
    }

    private void generateMaze(Maze maze, int startRow, int startColumn, int endRow, int endColumn){
       // if()
    }
}
