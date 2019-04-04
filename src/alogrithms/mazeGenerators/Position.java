package alogrithms.mazeGenerators;

import javafx.geometry.Pos;

public class Position {

    private int row;
    private int column;

    public Position(int row, int column)
    {
        if(row >= 0 && column >=0) {
            this.row = row;
            this.column = column;
        }
    }

    public Position(Position other){
        if(other != null && other.row >= 0 && other.column >=0){
            this.row = other.row;
            this.column = other.column;
        }

    }

    public int getColumnIndex() {
        return column;
    }

    public int getRowIndex() {
        return row;
    }

    public void setRow(int row){
        if(row >= 0)
            this.row = row;
    }

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
