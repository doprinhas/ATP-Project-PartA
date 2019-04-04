package alogrithms.mazeGenerators;

import javafx.geometry.Pos;

public class Maze {

    protected int maze[][];
    private int mRows;
    private int mColumns;
    Position startPos;
    Position endPos;

    public Maze(int rows, int columns)
    {
        mRows = rows;
        mColumns = columns;
        maze = new int[rows][columns];
        for(int i=0; i<rows; i++)
            for(int j=0; j<rows; j++)
                maze[i][j] = 0;

        startPos = new Position(0,0);
        endPos = new Position(rows-1, columns-2);
    }

    public int getRows() {
        return mRows;
    }

    public int getColumns() {
        return mColumns;
    }

    public boolean setValue(Position position, int value){
        if(position.getRowIndex() < 0 || position.getRowIndex() >= this.mRows)
            return false;
        if(position.getColumnIndex() < 0 || position.getColumnIndex() >= this.mColumns)
            return false;
        this.maze[position.getRowIndex()][position.getColumnIndex()] = value;
        return true;
    }

    public void setStartPos(int row, int column){
        if(row >= 0 && column >=0 && row<mRows && column<mColumns){
            this.startPos.setRow(row);
            this.startPos.setColumn(column);
        }
    }

    public void setEndPos(int row, int column){
        if(row >= 0 && column >=0 && row<mRows && column<mColumns){
            this.endPos.setRow(row);
            this.endPos.setColumn(column);
        }
    }

    public void setStartPos(Position start){
        if(start != null && start.getRowIndex()<mRows && start.getColumnIndex()<mColumns){
            this.startPos = new Position(start);
        }
    }

    public void setEndPos(Position end){
        if(end != null && end.getRowIndex()<mRows && end.getColumnIndex()<mColumns){
            this.endPos = new Position(end);
        }
    }

    public Position getStartPosition(){
        return startPos;
    }

    public Position getGoalPosition() {
        return endPos;
    }

    public int getValue(Position pos){
        if(pos.getRowIndex() < mRows && pos.getColumnIndex() < mColumns){
            return maze[pos.getRowIndex()][pos.getColumnIndex()];
        }
        return -2;
    }

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
}
