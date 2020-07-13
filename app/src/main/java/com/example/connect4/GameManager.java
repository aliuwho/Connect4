package com.example.connect4;

import android.view.View;

import com.example.connect4.Exceptions.ColumnFullException;
import com.example.connect4.Exceptions.FullBoardEndGameException;
import com.example.connect4.games.Game;
import com.example.connect4.games.GameMove;

public class GameManager {
    private final Game game;

    public GameManager(Game game) {
        this.game = game;
    }

//    /**
//     * runs the next stage of game on column tap
//     */
//    public void columnPress(View view) {
//        // because this is the player tap button
//        try {
//            playerMove(view);
//            computerMove();
//            checkGameOver();
//        } catch (ColumnFullException e) {
//            warnColumnFull();
//        } catch (FullBoardEndGameException e) {
//            noWin();
//        }
//    }

    /**
     * Returns winner id if game is over; else, returns -1
     * If board is full, throws FullBoardEndGameException
     */
    public int checkGameOver() throws FullBoardEndGameException {
        return game.isGameOver();

    }

    /**
     * Updates game based on computer input
     */
    public int computerMove() throws FullBoardEndGameException {
        try {
            GameMove computer = game.computerPlay();
            return game.getChipId(computer.getRow(), computer.getColumn());

        } catch (ColumnFullException e) {
            throw new IllegalStateException();
            //error occurred in computerPlay
        }
    }

    /**
     * Updates game based on player input
     *
     * @param view: the button pressed
     */
    public int playerMove(View view) throws ColumnFullException {

        int col = findColumn(view.getId());
        int row = game.personPlay(col);
        return game.getChipId(row, col);

    }

    /**
     * finds column corresponding to button press ID
     *
     * @param id representing button press ID
     * @return column number
     */
    private int findColumn(int id) {
        switch (id) {
            case R.id.col1:
                return 0;
            case R.id.col2:
                return 1;
            case R.id.col3:
                return 2;
            case R.id.col4:
                return 3;
            case R.id.col5:
                return 4;
            case R.id.col6:
                return 5;
            case R.id.col7:
                return 6;
            default:
                return -1;
        }
    }


}