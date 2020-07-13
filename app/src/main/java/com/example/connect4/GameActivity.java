package com.example.connect4;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.connect4.Exceptions.ColumnFullException;
import com.example.connect4.Exceptions.FullBoardEndGameException;
import com.example.connect4.games.Game;

public abstract class GameActivity extends AppCompatActivity {
    protected GameManager gameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: set player to player selection color in game select screen
        // TODO: figure out saved instance states
        // TODO: add a new game function (which resets activity completely) & persistence (which continues activity)
        // TODO: game over functionality
    }

    protected abstract void setGameManager();

    /**
     * runs the next stage of game on column tap
     */
    public void columnPress(View view) {
        // because this is the player tap button
        try {
//            gameManager.playerMove(view);
//            gameManager.computerMove();
            updatePieceGraphics(Game.PERSON, gameManager.playerMove(view));
            updatePieceGraphics(Game.COMPUTER, gameManager.computerMove());
            gameManager.checkGameOver();
        } catch (ColumnFullException e) {
            warnColumnFull();
        } catch (FullBoardEndGameException e) {
            endGameDialog("It's a Tie","Nobody won this round.");
        }
    }

    /**
     * Called when user taps the Return To Menu button
     */
    public void returnToMenu(View view) {
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


    /**
     * Called when user taps the How To Play button
     */
    public void howToPlay(View view) {
        Intent intent = new Intent(this, HowToPlayActivity.class);
        startActivity(intent);
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

    // EFFECTS: displays piece with given id and color
    private void updatePieceGraphics(int color, int id) {
        ImageView img = findViewById(id);
        img.setVisibility(View.VISIBLE);
        if (color == 1) {
            img.setImageResource(R.drawable.blue_square_custom);
        } else {
            img.setImageResource(R.drawable.red_square_custom);
        }
    }

    public void endGameDialog(String title, String msg) {
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

}