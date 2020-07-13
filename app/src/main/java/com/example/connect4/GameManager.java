package com.example.connect4;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.connect4.Exceptions.ColumnFullException;
import com.example.connect4.Exceptions.FullBoardEndGameException;
import com.example.connect4.games.Game;
import com.example.connect4.games.GameMove;

public class GameManager {
    private Game game;
    private AppCompatActivity activity;

    public GameManager(Game game, AppCompatActivity activity) {
        this.game = game;
        this.activity = activity;
    }

    /**
     * runs the next stage of game on column tap
     */
    public void columnPress(View view) {
        // because this is the player tap button
        try {
            playerMove(view);
            computerMove();
            checkGameOver();
        } catch (ColumnFullException e) {
            warnColumnFull();
        } /*catch (EndGameException e) {
            // TODO: end game stuff
            try {
                gameOver(game.getBoard().isGameOver());
            } catch (FullBoardEndGameException ex) {
                noWin();
                System.out.print("testing");
            }
//            e.printStackTrace();
        }*/ catch (FullBoardEndGameException e) {
            noWin();
        }
    }

    /**
     * Checks if the game is over & responds accordingly
     */
    public void checkGameOver() {
        try {
            int winner = game.isGameOver();
            if (winner != -1) {
                gameOver(winner);
            }
            //otherwise do nothing
        } catch (FullBoardEndGameException e) {
            noWin();
        }
    }

    /**
     * Updates game based on computer input
     */
    public void computerMove() throws FullBoardEndGameException {
        try {
            GameMove computer = game.computerPlay();
            int id2 = game.getChipId(computer.getRow(), computer.getColumn());
            updatePieceGraphics(Game.COMPUTER, id2);

        } catch (ColumnFullException e) {
            e.printStackTrace();
            //error occurred in computerPlay
        }
    }

    /**
     * Updates game based on player input
     *
     * @param view: the button pressed
     */
    public void playerMove(View view) throws ColumnFullException {

        int col = findColumn(view.getId());
        int row = game.personPlay(col);
        int id = game.getChipId(row, col);
//            animate(Game.PERSON, R.id.chip44);
        updatePieceGraphics(Game.PERSON, id);

//            updatePieceGraphics(Game.getPERSON(), view.getId());
//            updatePieceGraphics(Game.getCOMPUTER(), game.computerPlay());
//            placePiece(player, view.getId());

    }

    private void warnColumnFull() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Snug as a Bug");
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

    public void gameOver(int winner) {
        //yeet

    }

    public void noWin() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("It's a Tie");
        builder.setMessage("Nobody won this round.");
        builder.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                activity.recreate();
            }
        });
        builder.setNegativeButton("Return to Menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                activity.finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    /**
     * finds column corresponding to button press ID
     *
     * @param id representing button press ID
     * @return column number
     */
    private int findColumn(int id) {
//        findViewById(id).setAlpha(1);
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


    // EFFECTS: displays piece with given id and color
    private void updatePieceGraphics(int color, int id) {
        ImageView img = activity.findViewById(id);
        img.setVisibility(View.VISIBLE);
        if (color == 1) {
            img.setImageResource(R.drawable.blue_square_custom);
        } else {
            img.setImageResource(R.drawable.red_square_custom);
        }
    }

//    // EFFECTS: displays piece with given id and color
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

    /**
     * Called when user taps the How To Play button
     */
    public void howToPlay(View view) {
        Intent intent = new Intent(activity, HowToPlayActivity.class);
        activity.startActivity(intent);
    }

    /**
     * Called when user taps the Return To Menu button
     */
    public void returnToMenu(View view) {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure you want to quit? Your game will not be saved.");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                activity.finish();

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

}