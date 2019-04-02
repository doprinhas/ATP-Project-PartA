package alogrithms.mazeGenerators;

public class Maze {

    protected int maze[][];
    private int mRows;
    private int mColumns;

    public Maze(int rows, int columns)
    {
        mRows = rows;
        mColumns = columns;
        maze = new int[rows][columns];
    }

    public int getmRows() {
        return mRows;
    }

    public int getmColumns() {
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
}
