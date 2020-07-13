package com.example.connect4;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.connect4.games.EasyGame;

public class EasyGameActivity extends AppCompatActivity {
    private GameActivity gameActivity;
//    private int[] ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameActivity = new GameActivity(new EasyGame(), this);
        // TODO: set player to player selection color in game select screen
        // TODO: figure out saved instance states
        // TODO: add a new game function (which resets activity completely) & persistence (which continues activity)
        // TODO: game over functionality
        setContentView(R.layout.activity_easy_game);


    }

//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
////        outState.putSerializable("ids", game.getIds());
//        outState.putSerializable("chips", game.getBoard().getChips());
//    }

    /**
     * runs the next stage of game on column tap
     */
    public void columnPress(View view) {
        gameActivity.columnPress(view);
    }

    /**
     * Called when user taps the Return To Menu button
     */
    public void returnToMenu(View view) {
        gameActivity.returnToMenu(view);
    }

    /**
     * Called when user taps the How To Play button
     */
    public void howToPlay(View view) {
        gameActivity.howToPlay(view);
    }


}