package com.example.connect4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    /**
     * Called when user taps the Play button
     */
    public void play(View view) {
        Intent intent = new Intent(this, SelectActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        Button button = (Button) findViewById(R.id.playGame);
        startActivity(intent);

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
     * Called when users tap the Quit button
     */
    public void quit(View view) {
        /* DON'T DO THIS BECAUSE ANDROID ACTIVITY CYCLE */
//        finish();
//        System.exit(0);

//        System.out.println("i wish i could quit");
        finish();
    }
}