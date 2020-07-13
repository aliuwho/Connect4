//package com.example.connect4.games;
//
//import com.example.connect4.Exceptions.ColumnFullException;
//import com.example.connect4.Exceptions.FullBoardEndGameException;
//import com.example.connect4.FourBoard;
//
//public class NormalGame extends Game {
//
//    public NormalGame() {
//        super();
//    }
//
//    public NormalGame(int[][] chips) {
//        super(chips);
//    }
//
//    @Override
//    public GameMove computerPlay() throws ColumnFullException, FullBoardEndGameException {
//        if (board.isFull()) {
//            throw new FullBoardEndGameException();
//        } else {
//            int i = firstMove(tryMoves());
//            if (i == -1) {
//                System.out.println("something went wrong... no moves available but board is not full");
//                throw new IllegalArgumentException();
//            } else {
//                return new GameMove(board.addChip(COMPUTER, i), i);
//            }
//        }
//    }
//
//    // EFFECTS: maps viable moves
//    private int[] tryMoves() throws FullBoardEndGameException {
//        int[] options = {-1, -1};
//        for (int i = 0; i < FourBoard.COLS; i++) {
//            FourBoard duplicate = new FourBoard();
//            duplicate.setChips(board.getChips());
//            try {
//                duplicate.addChip(PERSON, i);
//                if (isGameOver() != -1 && options[1] == -1) {
//                    options[1] = i;
//                } else {
//                    if (options[0] == -1) {
//                        options[0] = i;
//                    } else {
//                        if ((int) (Math.random() * 2) == 1) {
//                            options[0] = i;
//                        }
//                    }
//                }
//            } catch (ColumnFullException e) {
//                //do nothing
//            } catch (FullBoardEndGameException e) {
//                duplicate.setChips(board.getChips());
//                if (duplicate.canAddChip(i)) {
//                    options[1] = i;
//                }
//            }
//        }
//        return options;
//    }
//
//    // EFFECTS: selects the "best" move from given options
//    //          if no moves are possible, throws EndGameException
//    private int firstMove(int[] options) {
//        if (options[1] != -1) {
//            return options[1];
//        } else {
//            return options[0];
//        }
//    }
//}
