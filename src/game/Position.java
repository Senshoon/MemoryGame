package game;

public class Position {
    private int col;
    private int row;

    public Position(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
    //checking if row and col point to this position
    public boolean compare(int col, int row){
        boolean isMatching = col == this.col && row == this.row;
        return isMatching;
    }

}


