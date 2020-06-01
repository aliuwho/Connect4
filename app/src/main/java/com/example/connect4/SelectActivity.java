package com.example.connect4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
    }

    /**
     * Called when user taps the Easy button
     */
    public void easyGame(View view) {
        Intent intent = new Intent(this, EasyGameActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        Button button = (Button) findViewById(R.id.playGame);
        startActivity(intent);

    }

    /**
     * Called when user taps the Normal button
     */
    public void normalGame(View view) {
        Intent intent = new Intent(this, NormalGameActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        Button button = (Button) findViewById(R.id.playGame);
        startActivity(intent);

    }

    /**
     * Called when user taps the Unbeatable button
     */
    public void unbeatableGame(View view) {
        Intent intent = new Intent(this, UnbeatableGameActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        Button button = (Button) findViewById(R.id.playGame);
        startActivity(intent);

    }
}