package algorithms.mazeGenerators;


import sun.security.ssl.Debug;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Maze {

    protected int[][] maze;
    private int mRows;
    private int mColumns;
    private Position startPos;
    private Position endPos;

    @SuppressWarnings("unused")
    private final int WALL_VALUE = 1;

    private final int PASS_VALUE = 0;

    public Maze(int rows, int columns)
    {
        try {
            if(rows <= 0 || columns <= 0 || rows > 25000 || columns > 25000)
                throw new IOException("Not a valid maze size");
            maze = new int[rows][columns];
            mRows = rows;
            mColumns = columns;
        }
        catch(IOException e){
            System.out.println(e.getMessage());
            mRows = 30;
            mColumns = 30;
            maze = new int[mRows][mColumns];
        }
        for(int i=0; i<mRows; i++)
            for(int j=0; j<mColumns; j++)
                maze[i][j] = 0;

        startPos = new Position(0,0);
        endPos = new Position(rows-1, columns-2);
    }

    public Maze( byte [] ba ){

        loadMazeData( ba );
        maze = new int[mRows][mColumns];
        bytesToMaze( ba );

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
     */
    void breakWall(Position position){
        if(position.getRowIndex() < 0 || position.getRowIndex() >= this.mRows)
            return;
        if(position.getColumnIndex() < 0 || position.getColumnIndex() >= this.mColumns)
            return;
        this.maze[position.getRowIndex()][position.getColumnIndex()] = PASS_VALUE;
    }

    /**
     * Sets the starting position of the maze
     * @param start Represents the desired starting position
     */
    void setStartPos(Position start){
        if(start != null && start.getRowIndex()<mRows && start.getColumnIndex()<mColumns){
            this.startPos = new Position(start);
        }
    }

    /**
     * Sets the goal position of the maze
     * @param end Represents the desired goal position
     */
    void setEndPos(Position end){
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
                else if (i == this.endPos.getRowIndex() && j == this.endPos.getColumnIndex())
                    System.out.print("E");
                else
                    System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * This function checks is a curtain position is a pass
     * @param row the row number of a certain position
     * @param col the row number of a certain position
     * @return True - if the value of the position is 0 <br> False - otherwise
     */
    public boolean isAPass(int row , int col){

        if(row >= 0 && row < mRows && col >= 0 &&  col < mColumns)
            return maze[row][col] == PASS_VALUE;

        return false;
    }


    public Object getPosition(int row , int col){

        return new Position(row, col);

    }

    private final int[] byteArray_rowsNumPosition = { 0 , 1 };
    private final int[] byteArray_colsNumPosition = { 2 , 3 };
    private final int[] byteArray_startPosition = { 4 , 5 , 6 , 7 };
    private final int[] byteArray_EndPosition = { 8 , 9 , 10 , 11 };
    private final int SHIFT = 256;
    private final int NUM_OF_DATA_CELLS = 12;

    public byte[] toByteArray() {

        byte[] mazeData = new byte[ NUM_OF_DATA_CELLS + 1 + (mRows * mColumns)/8 ];

        saveMazeData( mazeData );
        mazeToBytes( mazeData );

        return mazeData;
    }

    private void saveMazeData( byte[] ba ){

        ba[byteArray_rowsNumPosition[0]] = (byte)(mRows/SHIFT);
        ba[byteArray_rowsNumPosition[1]] = (byte)(mRows%SHIFT);

        ba[byteArray_colsNumPosition[0]] = (byte)(mColumns/SHIFT);
        ba[byteArray_colsNumPosition[1]] = (byte)(mColumns%SHIFT);

        ba[byteArray_startPosition[0]] = (byte)(getStartPosition().getRowIndex()/SHIFT);
        ba[byteArray_startPosition[1]] = (byte)(getStartPosition().getRowIndex()%SHIFT);

        ba[byteArray_startPosition[2]] = (byte)(getStartPosition().getColumnIndex()/SHIFT);
        ba[byteArray_startPosition[3]] = (byte)(getStartPosition().getColumnIndex()%SHIFT);

        ba[byteArray_EndPosition[0]] = (byte)(getGoalPosition().getRowIndex()/SHIFT);
        ba[byteArray_EndPosition[1]] = (byte)(getGoalPosition().getRowIndex()%SHIFT);

        ba[byteArray_EndPosition[2]] = (byte)(getGoalPosition().getColumnIndex()/SHIFT);
        ba[byteArray_EndPosition[3]] = (byte)(getGoalPosition().getColumnIndex()%SHIFT);

    }

    private void loadMazeData( byte[] ba ){

        mRows = transformToInt(ba , byteArray_rowsNumPosition , 0 , 1);
        mColumns = transformToInt(ba , byteArray_colsNumPosition , 0 , 1);

        startPos = new Position(transformToInt(ba , byteArray_startPosition , 0 , 1)
                               , transformToInt(ba , byteArray_startPosition , 2 , 3));

        endPos = new Position(transformToInt(ba , byteArray_EndPosition , 0 , 1)
                                , transformToInt(ba , byteArray_EndPosition , 2 , 3));

    }

    private int transformToInt(byte[] ba , int[] position , int i , int j){
        return ((ba[position[i]] & 0xFF) * SHIFT) + (ba[position[j]] & 0xFF);
    }

    private final int[]  BIT_VALUE = { 1 , 2 , 4 , 8 , 16 , 32 , 64 , 128 };

    private void mazeToBytes( byte[] byte_array ){

        int index = NUM_OF_DATA_CELLS;
        int counter = 7;
        int value = 0;

        for(int i = 0 ; i < mRows ; i++)
            for (int j = 0 ; j < mColumns ; j++) {

                if( maze[i][j] == 1 )
                    value += BIT_VALUE[counter];

                counter--;
                if ( counter < 0 ){
                    byte_array[index] = (byte)value;
                    index++;
                    value = 0;
                    counter = 7;
                }
            }
    }

    private void bytesToMaze( byte [] byte_array ){

        int index = NUM_OF_DATA_CELLS;
        int counter = 7;
        int value = byte_array[index];

        for (int  i = 0 ; i < mRows ; i++)
            for(int j = 0 ; j < mColumns ; j++){
                if (value - BIT_VALUE[counter] >= 0) {
                    maze[i][j] = 1;
                    value -= BIT_VALUE[counter];
                }
                else
                    maze[i][j] = 0;
                counter--;
                if (counter < 0){
                    counter = 7;
                    index++;
                    value = byte_array[index] & 0xFF;
                }
            }
    }


    @Override
    public boolean equals(Object obj) {
        boolean isEqual = true;
        for(int i = 0 ; i < mRows ; i++)
            for(int j = 0 ; j < mColumns ; j++)
                if (maze[i][j] != ((Maze)obj).maze[i][j]) {
                    System.out.println(i + " , " + j);
                    isEqual = false;
                }
        return isEqual;
    }

}
