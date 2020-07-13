package com.example.connect4.games;

/** representing a connect 4 move by row and column **/
public class GameMove {
    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public final int row;
    public final int column;

    public GameMove(int r, int c) {
        row = r;
        column = c;
    }
}
