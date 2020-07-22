package com.example.connect4.games;

import com.example.connect4.Exceptions.ColumnFullException;
import com.example.connect4.Exceptions.FullBoardEndGameException;
import com.example.connect4.FourBoard;

public class NormalGame extends Game {

    public NormalGame() {
        super();
    }

    public NormalGame(int[][] chips) {
        super(chips);
    }

    @Override
    public GameMove computerPlay() throws ColumnFullException, FullBoardEndGameException {
        if (board.isFull()) {
            throw new FullBoardEndGameException();
        } else {
            int i = firstMove(tryMoves());
            if (i == -1) {
//                System.out.println("something went wrong... no moves available but board is not full");
                throw new IllegalArgumentException();
            } else {
                return new GameMove(board.addChip(COMPUTER, i), i);
            }
        }
    }

    // EFFECTS: maps viable moves
    private int[] tryMoves() {
        int[] options = {-1, -1, -1};
        int i = 0;
        while (i < FourBoard.COLS && options[2] == -1) {
            FourBoard duplicate = new FourBoard();
            duplicate.setChips(board.getChips());
            try {
                duplicate.addChip(PERSON, i);
                if (duplicate.isConnectFour() != -1) {
                    options[2] = i;
                } else {
                    duplicate.addChip(PERSON, i);
                    if (duplicate.isConnectFour() != -1) {
                        options[1] = i;
                    } else {
                        if (options[0] == -1) {
                            options[0] = i;
                        } /*else {
                                if ((int) (Math.random() * 100) > 49) {
                                    options[0] = i;
                                }
                            }*/
                    }
                }
            } catch (ColumnFullException e) {
                e.printStackTrace();
                //do nothing & move to next move
            } finally {
                i++;
            }

        }
        return options;
    }


    // EFFECTS: selects the "best" move from given options
    //          if no moves are possible, throws EndGameException
    private int firstMove(int[] options) {
        int i = options.length - 1;
        while (i >= 0 && options[i] == -1) {
            i--;
        }
        return options[i];
    }
}
