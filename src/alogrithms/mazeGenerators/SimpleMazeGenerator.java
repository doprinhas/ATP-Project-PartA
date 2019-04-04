package alogrithms.mazeGenerators;

import javafx.geometry.Pos;

public class SimpleMazeGenerator extends AMazeGenerator {

    public Maze generate(int rows, int columns)
    {
        Maze simpleMaze = new Maze(rows, columns);
        for(int i=0; i<rows; i++)
            for(int j=0; j<columns; j++) {
                simpleMaze.maze[i][j] = 1;
            }
        findPath(simpleMaze);
        randomWalls(simpleMaze);
        return simpleMaze;
    }

    void findPath(Maze maze){
        Position position = new Position(0,0);
        maze.setValue(position, 0);
        while(position.getRowIndex() != maze.getRows()-1 || position.getColumnIndex() != maze.getColumns()-1){
            int random = (int)((Math.random()*2)+1);
            if(random == 1) {
                if (position.getRowIndex() + 1 < maze.getRows()) {
                    position.setRow(position.getRowIndex() + 1);
                    maze.setValue(position, 0);
                }
            }
            else
                if(position.getColumnIndex() + 1 < maze.getColumns()) {
                    position.setColumn(position.getColumnIndex() + 1);
                    maze.setValue(position, 0);
                }
        }
    }

    void randomWalls(Maze maze){
        for(int i=0; i<maze.getRows(); i++)
            for(int j=0; j<maze.getColumns(); j++) {
                if(maze.maze[i][j] == 1){
                    if(Math.random() < 0.3)
                        maze.maze[i][j] = 0;
                    else
                        maze.maze[i][j] = 1;

                }
            }
    }
}
