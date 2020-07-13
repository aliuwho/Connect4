package com.example.connect4.GameActivities;

import android.os.Bundle;
import android.view.View;

import com.example.connect4.R;
import com.example.connect4.games.EasyGame;

public class EasyGameActivity extends GameActivity {
//    private GameManager gameManager;
//    private int[] ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_game);
        setGame(new EasyGame());
    }

    @Override
    public void howToPlay(View view) {
        super.howToPlay(view);
    }

    @Override
    public void returnToMenu(View view) {
        super.returnToMenu(view);
    }

    @Override
    public void columnPress(View view) {
        super.columnPress(view);
    }

}