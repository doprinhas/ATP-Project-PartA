package algorithms.mazeGenerators;

import javafx.geometry.Pos;

import javax.sql.rowset.RowSetWarning;
import java.io.Serializable;

public class Position implements Serializable {

    private int row;
    private int column;
    private int floor;

    public Position(int row, int column)
    {
        if(row >= 0 && column >= 0) {
            this.row = row;
            this.column = column;
            this.floor = 0;
        }
    }

    public Position(int floor, int row, int column)
    {
        if(row >= 0 && column >= 0 && floor >= 0) {
            this.row = row;
            this.column = column;
            this.floor = floor;
        }
    }

    /**
     * Copy constructor
     * @param other Position to copy
     */
    public Position(Position other){
        if(other != null && other.row >= 0 && other.column >=0 && other.floor >= 0){
            this.row = other.row;
            this.column = other.column;
            this.floor = other.floor;
        }

    }

    /**
     * Returns the position's column index
     * @return position's column index
     */
    public int getColumnIndex() {
        return column;
    }

    /**
     * Returns the position's row index
     * @return position's row index
     */
    public int getRowIndex() {
        return row;
    }

    public int getFloorIndex() {
        return floor;
    }

    /**
     * Sets the row index to the desired row
     * @param row The desired row position
     */
    public void setRow(int row){
        if(row >= 0)
            this.row = row;
    }

    /**
     * Sets the column index to the desired column
     * @param column The desired column position
     */
    public void setColumn(int column){
        if(row >= 0)
            this.column = column;
    }

    public void setFloor(int floor){
        if(floor >= 0)
            this.floor = floor;
    }

    public void changeRowBy(int rowsToMove)
    {
        if(row + rowsToMove >= 0)
            row += rowsToMove;
    }

    public void changeColumnBy(int ColumnsToMove)
    {
        if(column + ColumnsToMove >= 0)
            column += ColumnsToMove;
    }

    public void changeFloorBy(int floorToMove)
    {
        if(floor + floorToMove >= 0)
            floor += floorToMove;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Position))
            return false;
        if(((Position)obj).row == this.row && ((Position)obj).column == this.column && ((Position)obj).floor == this.floor)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "{" + floor + "," + row + "," + column + "}";
    }
}
