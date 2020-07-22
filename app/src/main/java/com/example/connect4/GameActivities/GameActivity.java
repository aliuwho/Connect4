package com.example.connect4.GameActivities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.connect4.Exceptions.ColumnFullException;
import com.example.connect4.Exceptions.ComputerWinsEndGameException;
import com.example.connect4.Exceptions.EndGameException;
import com.example.connect4.Exceptions.FullBoardEndGameException;
import com.example.connect4.Exceptions.PersonWinsEndGameException;
import com.example.connect4.HowToPlayActivity;
import com.example.connect4.R;
import com.example.connect4.games.Game;
import com.example.connect4.games.GameMove;

/**
 * represents an activity which runs a game
 **/
public abstract class GameActivity extends AppCompatActivity {
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: set player to player selection color in game select screen
        // TODO: figure out saved instance states
        // TODO: add a new game function (which resets activity completely) & persistence (which continues activity)
        // TODO: game over functionality
    }

    protected void setGame(Game game) {
        this.game = game;
    }

    /**
     * Updates the next stage of game on column tap
     */
    protected void columnPress(View view) {
        // because this is the player tap button
        try {
//            gameManager.playerMove(view);
//            gameManager.computerMove();
            updateGame(Game.PERSON, playerMove(view));
            updateGame(Game.COMPUTER, computerMove());
//            updatePieceGraphics(Game.PERSON, playerMove(view));
//            findWinner();
//            updatePieceGraphics(Game.COMPUTER, computerMove());
//            findWinner();
        } catch (ColumnFullException e) {
            warnColumnFull();
        } catch (EndGameException e) {
            if (e.getClass().equals(FullBoardEndGameException.class)) {
                endGameDialog("It's a Tie", "Nobody won this round.");
            } else if (e.getClass().equals(PersonWinsEndGameException.class)) {
                endGameDialog("Congratulations!", "You beat the computer! Nice job.");

            } else {
                endGameDialog("Outsmarted", "The computer beat you. Better luck next time!");
            }
        } catch (Exception e) {
            endGameDialog("error unknown", e.getMessage());
        }
    }


    /**
     * Updates graphics based on player move & checks for endgame
     *
     * @param player representing the player move
     * @param id     representing the move piece
     */
    protected void updateGame(int player, int id) throws EndGameException {
        updatePieceGraphics(player, id);
        findWinner();
    }


    /**
     * Updates game based on player input
     *
     * @param view: the button pressed
     */
    protected int playerMove(View view) throws ColumnFullException {

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
    protected int findColumn(int id) {
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


    /**
     * Updates game based on computer input
     */
    protected int computerMove() throws EndGameException, ColumnFullException {
        GameMove computer = game.computerPlay();
        return game.getChipId(computer.getRow(), computer.getColumn());
    }


    /**
     * Updates the color of a given Connect 4 piece
     *
     * @param color representing the update color
     * @param id    representing the id of the piece to update
     */
    protected void updatePieceGraphics(int color, int id) {
        ImageView img = findViewById(id);
        img.setVisibility(View.VISIBLE);
        if (color == 1) {
            img.setImageResource(R.drawable.chip_blue);
        } else {
            img.setImageResource(R.drawable.chip_red);
        }
    }


    /**
     * Checks if the game is over; if so, displays corresponding dialog
     */
    protected void findWinner() throws EndGameException {
        if (game.isGameOver()) {
            int winner = game.getWinner();
            if (winner == Game.PERSON) {
                throw new PersonWinsEndGameException();
            } else if (winner == Game.COMPUTER) {
                throw new ComputerWinsEndGameException();
            }

        }
    }


    /**
     * Displays dialog message when user tries to play a full column
     */
    protected void warnColumnFull() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("No Space");
        builder.setMessage("The column is full! Try a different one.");
        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Called when user taps the Return To Menu button
     */
    protected void returnToMenu(View view) {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure you want to quit? Your game will not be saved.");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }


    /**
     * Called when user taps the How To Play button
     */
    protected void howToPlay(View view) {
        Intent intent = new Intent(this, HowToPlayActivity.class);
        startActivity(intent);
    }

    /**
     * displays dialog representing end of game
     *
     * @param title representing the dialog title
     * @param msg   representing the dialog message
     */
    protected void endGameDialog(String title, String msg) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                recreate();
            }
        });
        builder.setNegativeButton("Return to Menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }


//    // EFFECTS: displays piece with given id and color with animation
//    private void animate(int color, int id) {
//        ImageView img = findViewById(id);
//        img.setVisibility(View.VISIBLE);
//        if (color == 1) {
//            img.setImageResource(R.drawable.blue_square_custom);
//        } else {
//            img.setImageResource(R.drawable.red_square_custom);
//        }
//        img.setVisibility(View.INVISIBLE);

//    }

}