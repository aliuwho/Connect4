package com.example.connect4.games;

import com.example.connect4.Exceptions.ColumnFullException;
import com.example.connect4.Exceptions.EndGameException;
import com.example.connect4.Exceptions.FullBoardEndGameException;

public class EasyGame extends Game {
    public EasyGame() {
        super();
    }

    public EasyGame(int[][] chips) {
        super(chips);
    }

    @Override
    public GameMove computerPlay() throws EndGameException {
        if (board.isFull()) {
            throw new FullBoardEndGameException();
        } else {
            try {
                int col = (int) (Math.random() * 7);
                int row = board.addChip(COMPUTER, col);
                if (isGameOver()) {
                    throw new EndGameException();
                } else {
                    return new GameMove(row, col);
                }
            } catch (ColumnFullException e) {
                return computerPlay();
            }
        }
    }
}
