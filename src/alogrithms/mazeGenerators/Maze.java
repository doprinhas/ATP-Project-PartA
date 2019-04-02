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
}
