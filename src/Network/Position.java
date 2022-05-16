package Network;

public class Position {
    private int col, row;

    public Position(int col, int row) {
        setCol(col);
        setRow(row);
    }

    public Position(String str) {
        String legalDelimiters = "[ ,;]";
        String[] values = str.split(legalDelimiters);
        setCol(Integer.parseInt(values[0]));
        setRow(Integer.parseInt(values[1]));
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
