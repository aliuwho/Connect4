package com.example.connect4;

import com.example.connect4.Exceptions.ColumnFullException;

import java.util.Arrays;

/**
 * represents a Connect4 board and status
 */
public class FourBoard {
    // note: can use x's and filled circles
    // empty spaces are represented using 0
    // filled spaces are represented using one of:
    //      - 1 (representing a color)
    //      - 2 (representing a color different from 2)

    private int[][] chips;
    public static final int ROWS = 6;
    public static final int COLS = 7;
    private static final int EMPTY_CHIP = 0;
    public static final int[] TYPES = {1, 2};

    // EFFECTS: creates a new, empty board
    public FourBoard() {
        chips = new int[ROWS][COLS];
        for (int[] chip : chips) {
            Arrays.fill(chip, EMPTY_CHIP);
        }
    }

    // MODIFIES: this
    // EFFECTS: if canAddChip, adds a chip of chipType to lowest position on board at column and returns chip row;
    //          otherwise, throws a ColumnFullException
    public int addChip(int chip, int column) throws ColumnFullException {
        if (column < 0 || column > 6) {
            throw new IllegalArgumentException();
        } else if (canAddChip(column)) {
            int i = 0;
            while (i < ROWS && chips[i][column] != EMPTY_CHIP) {
                i++;
            }
            chips[i][column] = chip;
            return i;
        } else {
            throw new ColumnFullException();
        }
    }

    // EFFECTS: returns ChipType at position board[row][col]
    public int getChipType(int row, int col) {
        return chips[row][col];
    }

    // EFFECTS: returns type of chips if there are four identical, non-empty chips in a row;
    //          and -1 otherwise
    public boolean isFourAcross(int r, int c, int type) {
        int count = 0;
        for (int i = c; i < COLS; i++) {
            if (chips[r][i] == type) {
                count++;
            } else {
                count = 0;
            }
            if (count == 4) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns type of chip if four identical, non-empty chips in a column in a row; -1 otherwise
    public boolean isFourUpDown(int r, int c, int type) {
        int count = 0;
        for (int i = r; i < ROWS; i++) {
            if (chips[i][c] == type) {
                count++;
            } else {
                count = 0;
            }
            if (count == 4) {
                return true;
            }
        }

        return false;
    }

    // EFFECTS: returns Color of chips if four identical, non-empty chips are in a diagonal;
    // -1 otherwise
    public int isConnectFour() {
        for (int type : TYPES) {
            for (int r = 0; r < ROWS; r++) {
                for (int c = 0; c < COLS; c++) {
                    if (chips[r][c] == type && (isRightDiagonal(r, c, type) || isLeftDiagonal(r, c, type) ||
                            isFourAcross(r, c, type) || isFourUpDown(r, c, type))) {
                        return type;
                    }
                }
            }
        }
        return -1;
    }

    // EFFECTS: if the column is full, return false;
    //          otherwise return true
    public boolean canAddChip(int col) {
        for (int i = 0; i < ROWS; i++) {
            if (chips[i][col] == EMPTY_CHIP) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns true if there is a right 4 diagonal from given row, col; false otherwise
    public boolean isRightDiagonal(int row, int col, int type) {
        int r = row;
        int c = col;
        int count = 0;
        while (r < ROWS && c < COLS) {
            if (chips[r][c] == type) {
                count++;
            } else {
                count = 0;
            }
            if (count == 4) {
                return true;
            }
            r++;
            c++;
        }
        return false;
    }

    // EFFECTS: returns true if there is a left 4 diagonal from given row, col; false otherwise
    public boolean isLeftDiagonal(int row, int col, int type) {
        int r = row;
        int c = col;
        int count = 0;
        while (r < ROWS && c >= 0) {
            if (chips[r][c] == type) {
                count++;
            } else {
                count = 0;
            }
            if (count == 4) {
                return true;
            }
            r++;
            c--;
        }
        return false;
    }

    //EFFECTS: returns chips in board
    public int[][] getChips() {
        return chips;
    }

    // MODIFIES: this
    // EFFECTS: sets chips to copy of initial chips layout
    public void setChips(int[][] initial) {
        int[][] copy = new int[initial.length][initial[0].length];
        for (int r = 0; r < initial.length; r++) {
            System.arraycopy(initial[r], 0, copy[r], 0, initial[r].length);
        }
        this.chips = copy;
    }



    public boolean isFull() {
        for (int i = 0; i < FourBoard.COLS; i++) {
            if (canAddChip(i)) {
                return false;
            }
        }
        return true;
    }

}
