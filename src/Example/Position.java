package Example;

import java.io.Serializable;

public class Position implements Serializable {
    private int col, row;

    public Position(int col, int row) {
        setCol(col);
        setRow(row);
    }

    public Position(String str) {
        String legalDelimiters = "[ ,;]";
        String[] values = str.split(legalDelimiters);
        try{
            setCol(Integer.parseInt(values[0]));
            setRow(Integer.parseInt(values[1]));
        } catch (Exception e){
            System.out.println("Error in position constructor -> x,y = 0,0");
            setCol(0);
            setRow(0);
        }
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        if (col >= 0 && col < 3) {
            this.col = col;
        }
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        if (row >= 0 && row < 3) {
            this.row = row;
        }
    }

    @Override
    public String toString() {
        return "Position{" +
                "col=" + col +
                ", row=" + row +
                '}';
    }
}
