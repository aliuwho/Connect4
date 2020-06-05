package com.example.connect4.Games;

import com.example.connect4.Exceptions.ColumnFullException;
import com.example.connect4.FourBoard;

import java.util.HashMap;

public class NormalGame {

    // EFFECTS: maps viable moves
    private HashMap<Integer, Integer> tryMoves() {
        HashMap<Integer, Integer> options = new HashMap<>();
        options.put(1, -1);
        options.put(2, -1);
        for (int i = 0; i < components[0].length; i++) {
            FourBoard next = new FourBoard();
//            Chip[][] temp = board.getChips().clone();
            Chip[][] temp = cloneChips(board.getChips());
            next.setChips(temp);
            try {
                next.addChip(new Chip(playerColor), i);
//                options[i] = next.isGameOver() != null;
                if (next.isGameOver() != null && options.get(2) == -1) {
                    options.put(2, i);
                } else {
                    if (options.get(1) == -1) {
                        options.put(1, i);
                    }
                }
            } catch (ColumnFullException e) {
                //do nothing
//                System.out.println("column full in tryMoves");
//                System.out.println("note: options[" + i + "] = " + options[i]);
//                e.printStackTrace();
//                options[i] = null;
            }
        }
        return options;
    }

    // EFFECTS: selects the "best" move from given options
    //          if no moves are possible, throws EndGameException
    private int firstMove(HashMap<Integer, Integer> options) {
        if (options.get(2) != -1) {
            return options.get(2);
        } else {
            return options.get(1);
        }
    }


}
