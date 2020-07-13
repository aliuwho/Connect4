package com.example.connect4;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.connect4.games.NormalGame;

public class NormalGameActivity extends AppCompatActivity {
    private GameManager gameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_game);
        gameManager = new GameManager(new NormalGame(), this);
    }

//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
////        outState.putSerializable("ids", game.getIds());
//        outState.putSerializable("chips", game.getBoard().getChips());
//    }

    /**
     * runs player move on button press (aka when a column is tapped)
     */
    public void columnPress(View view) {
        gameManager.columnPress(view);
    }

    /**
     * Called when user taps the How To Play button
     */
    public void howToPlay(View view) {
        gameManager.howToPlay(view);
    }

    /**
     * Called when user taps the Return To Menu button
     */
    public void returnToMenu(View view) {
        gameManager.returnToMenu(view);
    }


}