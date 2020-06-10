package com.example.connect4.games;

import com.example.connect4.Exceptions.ColumnFullException;
import com.example.connect4.Exceptions.EndGameException;
import com.example.connect4.Exceptions.FullBoardEndGameException;
import com.example.connect4.FourBoard;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class UnbeatableGame extends Game {

    public UnbeatableGame() {
        super();
    }

    public UnbeatableGame(int[][] chips) {
        super(chips);
    }

    @Override
    public int computerPlay() throws EndGameException, ColumnFullException {
        if (board.isFull()) {
            throw new FullBoardEndGameException();
        } else {
            FourBoard duplicate = new FourBoard();
            duplicate.setChips(board.getChips());
            return tryMoves(getNextMoves(duplicate), 0, Integer.MAX_VALUE);
        }
    }

    private int tryMove(FourBoard board, int col, int len, int min) throws FullBoardEndGameException {
        try {
            board.addChip(COMPUTER, col);
            if (board.isGameOver() == -1) {
                return tryMoves(getNextMoves(board), len++, min);
            } else {
                return len;
            }
        } catch (ColumnFullException e) {
            e.printStackTrace();
        }
    }

    private List<FourBoard> getNextMoves(FourBoard board) {
        List<FourBoard> nextMoves = new ArrayList<>();
        List<Integer> cols = openCols(board);
        for (int i = 0; i < cols.size(); i++) {
            try {
                FourBoard duplicate = new FourBoard();
                duplicate.setChips(board.getChips());
                duplicate.addChip(PERSON, cols.get(i));
                nextMoves.add(duplicate);
            } catch (ColumnFullException e) {
                //do nothing
            }
        }
        return nextMoves;
    }

    private List<Integer> openCols(FourBoard board) {
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < FourBoard.ROWS; i++) {
            if (board.canAddChip(i)) {
                ret.add(i);
            }
        }
        return ret;
    }

    private int tryMoves(List<FourBoard> boards, int len, int min) throws FullBoardEndGameException {
        for (int i = 0; i < boards.size(); i++) {
            FourBoard board = boards.get(i);
            Lsit<Integer> =
            for (int j = 0; j < openCols(board).size(0; j++) {
                tryMove(board, openCols
            }
        }
    }
}
