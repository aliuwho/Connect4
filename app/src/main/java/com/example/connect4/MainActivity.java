package com.example.connect4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        finish();
        System.exit(0);
    }
}