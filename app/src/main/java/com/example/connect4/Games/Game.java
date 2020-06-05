package com.example.connect4.Games;

import com.example.connect4.Exceptions.ColumnFullException;
import com.example.connect4.Exceptions.EndGameException;
import com.example.connect4.R;

// represents a connect 4 game
public abstract class Game {
    protected int[] ids = new int[]{R.id.chip00, R.id.chip01, R.id.chip02, R.id.chip03, R.id.chip04, R.id.chip05, R.id.chip06,
            R.id.chip10, R.id.chip11, R.id.chip12, R.id.chip13, R.id.chip14, R.id.chip15, R.id.chip16,
            R.id.chip20, R.id.chip21, R.id.chip22, R.id.chip23, R.id.chip24, R.id.chip25, R.id.chip26,
            R.id.chip30, R.id.chip31, R.id.chip32, R.id.chip33, R.id.chip34, R.id.chip35, R.id.chip36,
            R.id.chip40, R.id.chip41, R.id.chip42, R.id.chip43, R.id.chip44, R.id.chip45, R.id.chip46,
            R.id.chip50, R.id.chip51, R.id.chip52, R.id.chip53, R.id.chip54, R.id.chip55, R.id.chip56};


    public Game() {

    }

    // MODIFIES: this
    // EFFECTS: creates a move for the computer
    abstract protected void computerPlay() throws EndGameException;

    // MODIFIES: this
    // EFFECTS: runs player and computer moves
    protected void playMoves() throws ColumnFullException, EndGameException {
        board.addChip(new Chip(playerColor), column);
        placePiece(playerColor, column);
        computerPlay();
    }

    protected void updateBoard(int newCol) {
        if (board.isGameOver() != null) {
            winner = board.isGameOver();
            if (winner.equals(playerColor)) {
                JOptionPane.showMessageDialog(frame, "The player won!");
//            saveable.addPoints(20);
//                return true;
            } else {
                JOptionPane.showMessageDialog(frame, "The player lost....");
//            saveable.addPoints(1);
//                return false;
            }
            frame.dispose();
        }
        if (column != newCol) {
            components[0][newCol].setVisible(true);
            components[0][column].setVisible(false);
            column = newCol;
        }
    }
}
