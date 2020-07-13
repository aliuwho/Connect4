package com.example.connect4;

import com.example.connect4.Exceptions.ColumnFullException;
import com.example.connect4.Exceptions.EndGameException;
import com.example.connect4.games.GameMove;
import com.example.connect4.games.UnbeatableGame;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

public class UnbeatableGameTest {
    private int[][] chips1 = {{1, 1, 2, 2, 0, 0, 0},
            {1, 0, 2, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}};

    private int[][] chips2 = {{1, 1, 2, 2, 0, 0, 2},
            {1, 2, 2, 0, 0, 0, 2},
            {1, 2, 0, 0, 0, 0, 2},
            {2, 1, 0, 0, 0, 0, 0},
            {1, 2, 0, 0, 0, 0, 0},
            {2, 1, 0, 0, 0, 0, 0}};

    private int[][] chipsFilled = {{1, 1, 2, 2, 1, 1, 2},
            {1, 2, 2, 4, 3, 5, 4},
            {1, 2, 2, 4, 3, 5, 4},
            {1, 2, 2, 4, 3, 5, 4},
            {1, 2, 2, 4, 3, 5, 4},
            {1, 2, 2, 4, 3, 5, 4}};

    private int[][] chipsAlmostFull1 = {{1, 1, 2, 2, 1, 1, 2},
            {1, 2, 2, 4, 3, 5, 4},
            {1, 2, 2, 4, 3, 5, 4},
            {1, 2, 2, 4, 3, 5, 4},
            {1, 2, 2, 4, 3, 5, 4},
            {0, 2, 2, 4, 3, 5, 4}};

    private int[][] chipsAlmostFull2 = {{1, 1, 2, 2, 1, 1, 2},
            {1, 2, 2, 4, 3, 5, 4},
            {1, 2, 2, 4, 3, 5, 4},
            {1, 2, 2, 4, 3, 5, 4},
            {1, 2, 2, 4, 3, 5, 4},
            {5, 2, 2, 4, 3, 5, 0}};
    private UnbeatableGame game;

    @Before
    public void runBefore() {
        game = new UnbeatableGame();
    }

    @Test
    public void testNonDefaultConstructor() {
        UnbeatableGame game = new UnbeatableGame(chipsFilled);
        UnbeatableGame newGame = new UnbeatableGame();
        for (int i = 0; i < game.getBoard().getChips().length; i++) {
            for (int j = 0; j < game.getBoard().getChips()[0].length; j++) {
                assertNotEquals(game.getBoard().getChips()[i][j], newGame.getBoard().getChips()[i][j]);
            }
        }

    }

    @Test
    public void testOne() {
        //block player move
        game = new UnbeatableGame(chips1);
        try {
            GameMove actual = game.computerPlay();
            assertEquals(3, actual.getRow());
            assertEquals(0, actual.getColumn());
        } catch (EndGameException e) {
            fail();
        } catch (ColumnFullException e) {
            fail();
        }

//        //play winning move
//        game = new UnbeatableGame(chips2);
//        try {
//            GameMove actual = game.computerPlay();
//            assertEquals(3, actual.getRow());
//            assertEquals(6, actual.getColumn());
//        } catch (EndGameException e) {
//            fail();
//        } catch (ColumnFullException e) {
//            fail();
//        }
    }

    @Test
    public void testFnForBoardVersion1() {
        game = new UnbeatableGame(chips1);
        assertEquals(0, game.fnForBoardVersion1(game.getBoard()));
        game = new UnbeatableGame(chips2);
        assertEquals(2, game.fnForBoardVersion1(game.getBoard()));
        game = new UnbeatableGame(chipsFilled);
        assertEquals(-1, game.fnForBoardVersion1(game.getBoard()));
        //        /* only one move (board full)*/
        game = new UnbeatableGame(chipsAlmostFull1);
        assertEquals(0, game.fnForBoardVersion1(game.getBoard()));
        game = new UnbeatableGame(chipsAlmostFull2);
        assertEquals(6, game.fnForBoardVersion1(game.getBoard()));
    }

}
