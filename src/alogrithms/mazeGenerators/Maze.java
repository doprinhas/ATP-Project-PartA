package alogrithms.mazeGenerators;

import javafx.geometry.Pos;

import java.util.ArrayList;

public class Maze {

    protected int maze[][];
    private int mRows;
    private int mColumns;
    private Position startPos;
    private Position endPos;

    public Maze(int rows, int columns)
    {
        mRows = rows;
        mColumns = columns;
        maze = new int[rows][columns];
        for(int i=0; i<rows; i++)
            for(int j=0; j<columns; j++)
                maze[i][j] = 0;

        startPos = new Position(0,1);
        endPos = new Position(rows-1, columns-2);
    }

    /**
     * Returns the number of rows in the maze
     * @return Number of rows in the maze
     */
    public int getRows() {
        return mRows;
    }

    /**
     * Returns the number of columns in the maze
     * @return Number of columns in the maze
     */
    public int getColumns() {
        return mColumns;
    }

    /**
     * Changes the value of a single cell in the maze to a certain value
     * @param position Represents the cell to change
     * @param value Represents the value to change the cell to
     * @return If the value has changed - returns true <br> Other wise - return false
     */
    public boolean setValue(Position position, int value){
        if(position.getRowIndex() < 0 || position.getRowIndex() >= this.mRows)
            return false;
        if(position.getColumnIndex() < 0 || position.getColumnIndex() >= this.mColumns)
            return false;
        this.maze[position.getRowIndex()][position.getColumnIndex()] = value;
        return true;
    }

    /**
     * Sets the starting position of the maze
     * @param row The row number of the starting position
     * @param column The column number of the starting position
     */
    public void setStartPos(int row, int column){
        if(row >= 0 && column >=0 && row<mRows && column<mColumns){
            this.startPos.setRow(row);
            this.startPos.setColumn(column);
        }
    }

    /**
     * Sets the goal position of the maze
     * @param row The row number of the goal position
     * @param column The column number of the goal position
     */
    public void setEndPos(int row, int column){
        if(row >= 0 && column >=0 && row<mRows && column<mColumns){
            this.endPos.setRow(row);
            this.endPos.setColumn(column);
        }
    }

    /**
     * Sets the starting position of the maze
     * @param start Represents the desired starting position
     */
    public void setStartPos(Position start){
        if(start != null && start.getRowIndex()<mRows && start.getColumnIndex()<mColumns){
            this.startPos = new Position(start);
        }
    }

    /**
     * Sets the goal position of the maze
     * @param end Represents the desired goal position
     */
    public void setEndPos(Position end){
        if(end != null && end.getRowIndex()<mRows && end.getColumnIndex()<mColumns){
            this.endPos = new Position(end);
        }
    }

    /**
     * Returns the starting position of the maze
     * @return Starting position of the maze
     */
    public Position getStartPosition(){
        return startPos;
    }

    /**
     * Returns the goal position of the maze
     * @return Goal position of the maze
     */
    public Position getGoalPosition() {
        return endPos;
    }

    /**
     * Returns the value of the desired cell in the maze
     * @param pos The desired position to get the value from
     * @return The value in pos Position
     */
    public int getValue(Position pos){
        if(pos.getRowIndex() < mRows && pos.getColumnIndex() < mColumns){
            return maze[pos.getRowIndex()][pos.getColumnIndex()];
        }
        return -2;
    }

    /**
     * Prints the maze
     */
    public void print(){
        for(int i=0; i<this.mRows; i++) {
            for (int j = 0; j < this.mColumns; j++) {
                if (i == this.startPos.getRowIndex() && j == this.startPos.getColumnIndex()) {
                    System.out.print("S");
                }
                else if (i == this.endPos.getRowIndex() && j == this.endPos.getColumnIndex()) {
                    System.out.print("E");
                } else
                    System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * This function checks is a curtain position is a pass
     * @param row
     * @param col
     * @return
     */
    public boolean isAPass(int row , int col){

        if(row >= 0 && row < mRows && col >= 0 &&  col < mColumns)
            return maze[row][col] == 0;

        return false;
    }


    public Object getPosition(int row , int col){

        return new Position(row, col);

    }

}
