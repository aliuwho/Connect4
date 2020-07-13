package com.example.connect4;

import android.os.Bundle;

import com.example.connect4.games.EasyGame;

public class EasyGameActivity extends GameActivity {
//    private GameManager gameManager;
//    private int[] ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setGameManager();
        setContentView(R.layout.activity_easy_game);
    }

    @Override
    protected void setGameManager() {
        gameManager = new GameManager(new EasyGame());
    }

}