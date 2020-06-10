package com.example.connect4.games;

import android.util.Pair;

import com.example.connect4.Exceptions.ColumnFullException;
import com.example.connect4.Exceptions.EndGameException;
import com.example.connect4.Exceptions.FullBoardEndGameException;
import com.example.connect4.FourBoard;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnbeatableGame extends Game {

    public UnbeatableGame() {
        super();
    }

    public UnbeatableGame(int[][] chips) {
        super(chips);
    }

    @Override
    public Pair computerPlay() throws EndGameException, ColumnFullException {
        if (board.isFull()) {
            throw new FullBoardEndGameException();
        } else {
            FourBoard duplicate = new FourBoard();
            duplicate.setChips(board.getChips());
            int ret = fnForBoard(duplicate, new ArrayList<Integer>(), new HashMap<Integer, Integer>());
            return new Pair(board.addChip(COMPUTER, ret), ret);
        }
    }

    private int fnForBoard(FourBoard board, List<Integer> path, HashMap<Integer, Integer> wle) throws FullBoardEndGameException {
        for (int i = 0; i < FourBoard.COLS; i++) {
            try {
                FourBoard duplicate = new FourBoard();
                duplicate.setChips(board.getChips());
                duplicate.addChip(COMPUTER, i);
                if (duplicate.isGameOver() != -1) {
                    //add to work list
                    wle.put(path.get(0), path.size());
                } else {
                    path.add(i);
                    return fnForBoards(getNextMoves(duplicate), path, wle);
                }
            } catch (ColumnFullException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    private List<FourBoard> getNextMoves(FourBoard board) {
        List<FourBoard> nextMoves = new ArrayList<>();
        for (int i = 0; i < FourBoard.COLS; i++) {
            try {
                FourBoard duplicate = new FourBoard();
                duplicate.setChips(board.getChips());
                duplicate.addChip(PERSON, i);
                nextMoves.add(duplicate);
            } catch (ColumnFullException e) {
                //do nothing
            }
        }
        return nextMoves;
    }


    private int fnForBoards(List<FourBoard> boards, List<Integer> path, HashMap<Integer, Integer> wle) throws FullBoardEndGameException {
        if (boards.isEmpty()) {
            int minIndex = 0;
            int minLen = Integer.MAX_VALUE;
            for (int i = 0; i < FourBoard.COLS; i++) {
                try {
                    int len = wle.get(i);
                    if (len < minLen) {
                        minIndex = i;
                        minLen = len;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return minIndex;

        } else {
            for (int i = 0; i < boards.size(); i++) {
                FourBoard board = boards.get(i);
                fnForBoard(board, path, wle);
            }
            return -1;
        }
    }
}
