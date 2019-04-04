package alogrithms.mazeGenerators;

import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * The class generates complicated mazes.
 */
public class MyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int rows, int columns) {
        if(rows < 2 || columns < 2){
            return null;
        }
        Maze myMaze = new Maze(rows, columns);
        gridMaze(myMaze); // Gridding the maze

        ArrayList<Wall> wallList = new ArrayList<>();

        Position startPos = new Position(0, 0);
        myMaze.setValue(startPos, 0);
        myMaze.setStartPos(startPos);

        addWallsToList(startPos, wallList, myMaze);

        while(!wallList.isEmpty()){
            Random rand = new Random();
            int randomWall = rand.nextInt(wallList.size());
            Wall wall = wallList.get(randomWall);
            if(myMaze.getValue(wall.adjCell1) == 0 && myMaze.getValue(wall.adjCell2) == -1){
                myMaze.setValue(wall.position, 0);
                myMaze.setValue(wall.adjCell2, 0);
                addWallsToList(wall.adjCell2, wallList, myMaze);
            }
            if(myMaze.getValue(wall.adjCell1) == -1 && myMaze.getValue(wall.adjCell2) == 0){
                myMaze.setValue(wall.position, 0);
                myMaze.setValue(wall.adjCell1, 0);
                addWallsToList(wall.adjCell1, wallList, myMaze);

            }
            if(wallList.size() == 1) // Making the last cell as the ending point
                myMaze.setEndPos(wall.adjCell2);
            wallList.remove(randomWall);
        }
        return myMaze;
    }

    /**
     * Recieves cell in the maze, and adds his surrounding walls to the wall list
     * @param pos The position of the maze cell
     * @param wallList List of walls. updated by the given maze cell
     * @param maze The currently used maze
     */
    private void addWallsToList(Position pos, ArrayList<Wall> wallList, Maze maze){
        if(pos.getColumnIndex() < maze.getColumns()) {
            if (pos.getRowIndex() < maze.getRows() - 1 && maze.maze[pos.getRowIndex() + 1][pos.getColumnIndex()] == 1) {
                wallList.add(new Wall(new Position(pos.getRowIndex() + 1, pos.getColumnIndex())));
            }
            if (pos.getRowIndex() > 0 && maze.maze[pos.getRowIndex() - 1][pos.getColumnIndex()] == 1) {
                wallList.add(new Wall(new Position(pos.getRowIndex() - 1, pos.getColumnIndex())));
            }
        }
        if(pos.getRowIndex() < maze.getRows()) {
            if (pos.getColumnIndex() < maze.getColumns() - 1 && maze.maze[pos.getRowIndex()][pos.getColumnIndex() + 1] == 1) {
                wallList.add(new Wall(new Position(pos.getRowIndex(), pos.getColumnIndex() + 1)));
            }
            if (pos.getColumnIndex() > 0 && maze.maze[pos.getRowIndex()][pos.getColumnIndex() - 1] == 1) {
                wallList.add(new Wall(new Position(pos.getRowIndex(), pos.getColumnIndex() - 1)));
            }
        }
    }

    /**
     * Deviding the maze to 1x1 cells
     * @param maze Maze to grid
     */
    private void gridMaze(Maze maze) {
        for (int i = 0; i < maze.getRows(); i++) {
            for (int j = 0; j < maze.getColumns(); j++) {
                if (i % 2 == 0 && i != maze.getRows() - 1) {
                    if (j % 2 != 0 && j != maze.getColumns()-1) {
                        maze.maze[i][j] = 1;
                    }
                    else if(j % 2 == 0)
                        maze.maze[i][j] = -1;
                    else // last column not a wall
                        maze.maze[i][j] = 0;
                }
                else if(i == maze.getRows() - 1){ // last row not a wall
                    if (j % 2 != 0 && j != maze.getColumns()-1) {
                        maze.maze[i][j] = 1;
                    }
                    else
                        maze.maze[i][j] = 0;
                }
                else
                    maze.maze[i][j] = 1;
            }
        }
    }

    /**
     * Class representing a wall in the maze
     * Position represents the wall position in the maze, and adjCell1/2 representing the two cells he is seperating
     */
    private class Wall{
        private Position position;
        private Position adjCell1;
        private Position adjCell2;

        public Wall(Position pos){
            this.position = pos;
            if (pos.getRowIndex()%2 != 0){
                this.adjCell1 = new Position(pos.getRowIndex()-1, pos.getColumnIndex());
                this.adjCell2 = new Position(pos.getRowIndex()+1, pos.getColumnIndex());
            }
            else{
                this.adjCell1 = new Position(pos.getRowIndex(), pos.getColumnIndex()-1);
                this.adjCell2 = new Position(pos.getRowIndex(), pos.getColumnIndex()+1);
            }
        }
    }
}