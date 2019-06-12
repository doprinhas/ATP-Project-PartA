package algorithms.mazeGenerators;


import sun.security.ssl.Debug;

import javax.naming.NameNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Maze implements java.io.Serializable {

    protected int[][][] maze;
    private int mRows;
    private int mColumns;
    private int mFloors;
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
            maze = new int[1][rows][columns];
            mRows = rows;
            mColumns = columns;
            mFloors = 1;
        }
        catch(IOException e){
            System.out.println(e.getMessage());
            mRows = 30;
            mColumns = 30;
            mFloors = 1;
            maze = new int[1][mRows][mColumns];
        }
        for(int i=0; i<mRows; i++)
            for(int j=0; j<mColumns; j++)
                maze[1][i][j] = 0;

        startPos = new Position(0,0);
        endPos = new Position(rows-1, columns-2);
    }

    public Maze(int floors, int rows, int columns)
    {
        try {
            if(rows <= 0 || columns <= 0 || floors <= 0 || floors > 5 || rows > 25000 || columns > 25000)
                throw new IOException("Not a valid maze size");
            mRows = rows;
            mColumns = columns;
            mFloors = floors*2 - 1;
            maze = new int[mFloors][mRows][mColumns];

        }
        catch(IOException e){
            System.out.println(e.getMessage());
            mRows = 30;
            mColumns = 30;
            mFloors = 1;
            maze = new int[1][mRows][mColumns];
        }

        for(int i=0; i<mFloors; i++)
            for(int j=0; j<mRows; j++)
                for(int k=0; k<mColumns; k++) {
                    if(i % 2 == 0)
                        maze[i][j][k] = 0;
                    else
                        maze[i][j][k] = 1;
                }

        startPos = new Position(0,0,0);
        endPos = new Position(floors-1, rows-1, columns-2);
    }

    public Maze( byte [] ba ){

        loadMazeData( ba );
        maze = new int[mFloors][mRows][mColumns];
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

    public int getFloors() {
        return mFloors;
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
        if(position.getFloorIndex() < 0 || position.getFloorIndex() >= this.mFloors)
            return;
        this.maze[position.getFloorIndex()][position.getRowIndex()][position.getColumnIndex()] = PASS_VALUE;
    }

    /**
     * Sets the starting position of the maze
     * @param start Represents the desired starting position
     */
    void setStartPos(Position start){
        if(start != null && start.getRowIndex()<mRows && start.getColumnIndex()<mColumns && start.getFloorIndex() < mFloors){
            this.startPos = new Position(start);
        }
    }

    /**
     * Sets the goal position of the maze
     * @param end Represents the desired goal position
     */
    void setEndPos(Position end){
        if(end != null && end.getRowIndex()<mRows && end.getColumnIndex()<mColumns && end.getFloorIndex() < mFloors){
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
        if(pos.getRowIndex() < mRows && pos.getColumnIndex() < mColumns && pos.getFloorIndex() < mFloors){
            return maze[pos.getFloorIndex()][pos.getRowIndex()][pos.getColumnIndex()];
        }
        return -2;
    }

    /**
     * Prints the maze
     */
    public void print(){
        for(int i=mFloors-1; i>=0; i=i-2) {
            for (int j = 0; j < this.mRows; j++) {
                for(int k = 0; k < this.mColumns; k++) {
                    if (i == this.startPos.getColumnIndex() && j == this.startPos.getRowIndex() && k == this.startPos.getColumnIndex())
                        System.out.print("S");
                    else if (i == this.endPos.getFloorIndex() && j == this.endPos.getRowIndex() && k == this.endPos.getColumnIndex())
                        System.out.print("E");
                    else if (i % 2 == 0 && i != mFloors-1 && maze[i+1][j][k] == 0)
                        System.out.print("U");
                    else if (i % 2 == 0 && i != 0 && maze[i-1][j][k] == 0)
                        System.out.print("D");
                    else if(i % 2 == 0)
                        System.out.print(maze[i][j][k]);
                }
                System.out.println();
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
            return maze[0][row][col] == PASS_VALUE;

        return false;
    }

    public boolean isAPass(int row , int col, int floor){

        if(row >= 0 && row < mRows && col >= 0 &&  col < mColumns && floor >= 0 && floor < mFloors)
            return maze[floor][row][col] == PASS_VALUE;

        return false;
    }


    public Object getPosition(int row , int col){

        return new Position(row, col);

    }

    public Object getPosition(int floor, int row, int col)
    {
        return new Position(floor, row, col);
    }

    private final int[] byteArray_rowsNumPosition = { 0 , 1 };
    private final int[] byteArray_colsNumPosition = { 2 , 3 };
    private final int[] byteArray_floorsNumPosition = { 4, 5 };
    private final int[] byteArray_startPosition = { 6 , 7 , 8 , 9 , 10 , 11 };
    private final int[] byteArray_EndPosition = { 12 , 13 , 14 , 15 , 16 , 17 };
    private final int SHIFT = 256;
    private final int NUM_OF_DATA_CELLS = 18;

    public byte[] toByteArray() {

        byte[] mazeData = new byte[ NUM_OF_DATA_CELLS + 1 + (mFloors * mRows * mColumns)/8 ];

        saveMazeData( mazeData );
        mazeToBytes( mazeData );

        return mazeData;
    }

    public int[][][] toIntArray()
    {

        int[][][] res = new int[mFloors][mRows][mColumns];

        for(int i=0; i<mFloors; i++)
            for(int j=0; j<mRows; j++)
                for(int k=0; k<mColumns; k++)
                    res[i][j][k] = maze[i][j][k];

        return res;
    }

    private void saveMazeData( byte[] ba ){

        ba[byteArray_rowsNumPosition[0]] = (byte)(mRows/SHIFT);
        ba[byteArray_rowsNumPosition[1]] = (byte)(mRows%SHIFT);

        ba[byteArray_colsNumPosition[0]] = (byte)(mColumns/SHIFT);
        ba[byteArray_colsNumPosition[1]] = (byte)(mColumns%SHIFT);

        ba[byteArray_floorsNumPosition[0]] = (byte)(mFloors/SHIFT);
        ba[byteArray_floorsNumPosition[1]] = (byte)(mFloors%SHIFT);

        ba[byteArray_startPosition[0]] = (byte)(getStartPosition().getRowIndex()/SHIFT);
        ba[byteArray_startPosition[1]] = (byte)(getStartPosition().getRowIndex()%SHIFT);

        ba[byteArray_startPosition[2]] = (byte)(getStartPosition().getColumnIndex()/SHIFT);
        ba[byteArray_startPosition[3]] = (byte)(getStartPosition().getColumnIndex()%SHIFT);

        ba[byteArray_startPosition[4]] = (byte)(getStartPosition().getFloorIndex()/SHIFT);
        ba[byteArray_startPosition[5]] = (byte)(getStartPosition().getFloorIndex()%SHIFT);

        ba[byteArray_EndPosition[0]] = (byte)(getGoalPosition().getRowIndex()/SHIFT);
        ba[byteArray_EndPosition[1]] = (byte)(getGoalPosition().getRowIndex()%SHIFT);

        ba[byteArray_EndPosition[2]] = (byte)(getGoalPosition().getColumnIndex()/SHIFT);
        ba[byteArray_EndPosition[3]] = (byte)(getGoalPosition().getColumnIndex()%SHIFT);

        ba[byteArray_EndPosition[4]] = (byte)(getGoalPosition().getFloorIndex()/SHIFT);
        ba[byteArray_EndPosition[5]] = (byte)(getGoalPosition().getFloorIndex()%SHIFT);

    }

    private void loadMazeData( byte[] ba ){

        mRows = transformToInt(ba , byteArray_rowsNumPosition , 0 , 1);
        mColumns = transformToInt(ba , byteArray_colsNumPosition , 0 , 1);
        mFloors = transformToInt(ba, byteArray_floorsNumPosition, 0, 1);

        startPos = new Position(transformToInt(ba , byteArray_startPosition , 0 , 1)
                               , transformToInt(ba , byteArray_startPosition , 2 , 3)
                                , transformToInt(ba, byteArray_startPosition, 4, 5));

        endPos = new Position(transformToInt(ba , byteArray_EndPosition , 0 , 1)
                                , transformToInt(ba , byteArray_EndPosition , 2 , 3)
                                , transformToInt(ba, byteArray_EndPosition, 4, 5));

    }

    private int transformToInt(byte[] ba , int[] position , int i , int j){
        return ((ba[position[i]] & 0xFF) * SHIFT) + (ba[position[j]] & 0xFF);
    }

    private final int[]  BIT_VALUE = { 1 , 2 , 4 , 8 , 16 , 32 , 64 , 128 };

    private void mazeToBytes( byte[] byte_array ){

        int index = NUM_OF_DATA_CELLS;
        int counter = 7;
        int value = 0;

        for(int i = 0 ; i < mFloors ; i++)
            for (int j = 0 ; j < mRows ; j++)
                for(int k=0 ; k< mColumns ; k++) {

                if( maze[i][j][k] == 1 )
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

        for (int  i = 0 ; i < mFloors ; i++)
            for(int j = 0 ; j < mRows ; j++)
                for(int k=0 ; k < mColumns ; k++) {
                if (value - BIT_VALUE[counter] >= 0) {
                    maze[i][j][k] = 1;
                    value -= BIT_VALUE[counter];
                }
                else
                    maze[i][j][k] = 0;
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

        if(!obj.getClass().equals(this.getClass()))
            return false;

        if (((Maze)obj).mRows != this.mRows || ((Maze)obj).mColumns != this.mColumns || ((Maze)obj).mFloors != this.mFloors)
            return false;

        boolean isEqual = true;

        for(int i = 0 ; i < mFloors ; i++)
            for(int j = 0 ; j < mRows ; j++)
                for(int k=0 ; k < mColumns ; k++)
                    if (maze[i][j] != ((Maze)obj).maze[i][j]) {
                        System.out.println(i + " , " + j);
                        isEqual = false;
                    }

        return isEqual;
    }

    public byte[] toBAS(){
        byte[] b = new byte[ NUM_OF_DATA_CELLS + (mFloors*mRows*mColumns)];
        int index = NUM_OF_DATA_CELLS;

        for (int i = 0 ; i < mFloors ; i++)
            for (int j = 0 ; j < mRows ; j++)
                for(int k = 0 ; k < mColumns ; k++){
                b[index] = (byte)maze[i][j][k];
                index++;
            }
        return b;

    }

}
