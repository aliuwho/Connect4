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
    public int computerPlay() throws EndGameException {
        if (board.isFull()) {
            throw new FullBoardEndGameException();
        } else {
            int ret;
            try {
                ret = board.addChip(COMPUTER, (int) (Math.random() * 7));
                return ret;
            } catch (ColumnFullException e) {
                return computerPlay();
            }
        }
    }
}
