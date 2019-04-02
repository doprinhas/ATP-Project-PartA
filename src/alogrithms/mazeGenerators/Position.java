package alogrithms.mazeGenerators;

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
}
