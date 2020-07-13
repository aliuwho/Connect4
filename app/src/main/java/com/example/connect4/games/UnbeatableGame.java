package com.example.connect4.games;

import com.example.connect4.Exceptions.ColumnFullException;
import com.example.connect4.Exceptions.EndGameException;
import com.example.connect4.Exceptions.FullBoardEndGameException;
import com.example.connect4.FourBoard;

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
    public GameMove computerPlay() throws EndGameException, ColumnFullException {
        if (board.isFull()) {
            throw new FullBoardEndGameException();
        } else {
            FourBoard duplicate = new FourBoard();
            duplicate.setChips(board.getChips());
            int ret = fnForBoard(duplicate, 0);
            return new GameMove(board.addChip(COMPUTER, ret), ret);
        }
    }

    // given a board, will return first move or -1
    public int fnForBoardVersion1(FourBoard board) {
        for (int i = 0; i < FourBoard.COLS; i++) {

            FourBoard duplicate = new FourBoard();
            duplicate.setChips(board.getChips());
            if (duplicate.canAddChip(i)) {
                //add to work list
                return i;
            } /*else {
                    fnForBoards(getNextMoves(duplicate));
                }*/

        }
        return -1;
    }

    //given a board, will return first available move or -1 if none available
    public int fnForBoard(FourBoard board, int count) {
        for (int i = 0; i < FourBoard.COLS; i++) {
            FourBoard duplicate = new FourBoard();
            duplicate.setChips(board.getChips());
            if (duplicate.canAddChip(i)) {
                //add to work list
                try {
                    duplicate.addChip(COMPUTER, i);
                    if (duplicate.isGameOver() > -1) {
                        /* board is "solved" **/
                        return i;
                    }
                } catch (ColumnFullException e) {
                    e.printStackTrace();
                    //should never be thrown
                } catch (FullBoardEndGameException e) {
//                    e.printStackTrace();
                    /* board is "solved" **/
                    return i;
                }
                return fnForBoards(getNextMoves(duplicate), count++);
            }

        }
        return -1;
    }

    public List<FourBoard> getNextMoves(FourBoard board) {
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

    //given a list of boards, returns winning move or -1 if no move available
    private int fnForBoards(List<FourBoard> boards, int count) {
        if (boards.isEmpty()) {
            return -1;
        } else {
            for (int i = 0; i < boards.size(); i++) {
                FourBoard board = boards.get(i);
                int col = fnForBoardVersion1(board);
                if (col != -1) {
                    return col;
                }
            }
        }
        return -1;
    }
}
