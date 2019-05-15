package algorithms.mazeGenerators;

import java.io.Serializable;

public class Position implements Serializable {

    private int row;
    private int column;

    public Position(int row, int column)
    {
        if(row >= 0 && column >=0) {
            this.row = row;
            this.column = column;
        }
    }

    /**
     * Copy constructor
     * @param other Position to copy
     */
    public Position(Position other){
        if(other != null && other.row >= 0 && other.column >=0){
            this.row = other.row;
            this.column = other.column;
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

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Position))
            return false;
        if(((Position)obj).row == this.row && ((Position)obj).column == this.column)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "{" + row + "," + column + "}";
    }
}
