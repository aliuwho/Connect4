package com.example.connect4.GameActivities;

import android.os.Bundle;
import android.view.View;

import com.example.connect4.R;
import com.example.connect4.games.NormalGame;

public class NormalGameActivity extends GameActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_game);
        setGame(new NormalGame());
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