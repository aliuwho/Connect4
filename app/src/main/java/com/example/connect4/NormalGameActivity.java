package com.example.connect4;

import android.os.Bundle;

import com.example.connect4.games.NormalGame;

public class NormalGameActivity extends GameActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_game);
        setGame(new NormalGame());
    }

}