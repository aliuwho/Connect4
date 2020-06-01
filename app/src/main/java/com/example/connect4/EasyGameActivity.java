package com.example.connect4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EasyGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_game);
    }

    /**
     * TODO: lmao
     */
    public void onClick(View view) {
//        Intent intent = new Intent(this, EasyGameActivity);
//        startActivity(intent);
    }

    /**
     * Called when user taps the How To Play button
     */
    public void howToPlay(View view) {
        Intent intent = new Intent(this, HowToPlayActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        Button button = (Button) findViewById(R.id.playGame);
        startActivity(intent);

    }

    /**
     * Called when user taps the Return To Menu button
     */
    public void returnToMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        Button button = (Button) findViewById(R.id.playGame);
        startActivity(intent);

    }


}