package com.example.connect4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;

import com.example.connect4.Exceptions.ColumnFullException;

import java.lang.reflect.Field;
import java.util.HashMap;

public class EasyGameActivity extends AppCompatActivity {
    private FourBoard easyBoard;
    private int playerPiece;
    private HashMap<String, Integer> chipNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        easyBoard = new FourBoard();
        setContentView(R.layout.activity_easy_game);
        // TODO: set playerpiece to player selection
        playerPiece = R.drawable.blue_square_custom;

//        putRows(chipNames);
    }

//    public void putRows(HashMap map) {
//        for (int r = 0; r < FourBoard.ROWS; r++) {
//            putCols(map, r);
//        }
//    }
//
//    public void putCols(HashMap map, int row) {
//        switch (row) {
//            case 0:
//                col0(map);
//                break;
//            case 1:
//                tableRow = findViewById(R.id.row1);
//                break;
//            case 2:
//                tableRow = findViewById(R.id.row2);
//                break;
//            case 3:
//                tableRow = findViewById(R.id.row3);
//                break;
//            case 4:
//                tableRow = findViewById(R.id.row4);
//                break;
//            case 5:
//                tableRow = findViewById(R.id.row5);
//                break;
//            default:
//                tableRow = null;
//                break;
//
//        }
//    }
//
//    private void col0(HashMap map) {
//        map.put("chip00", R.id.chip00);
//        map.put("chip01", R.id.chip01);
//    }


    /**
     * TODO: lmao
     */
    public void columnSelect(View view) {
//        Button button = findViewById(R.id.col1);
//        button.setVisibility(View.VISIBLE);
//        view.setVisibility(View.INVISIBLE);
//        view.getId();
        // because this is the player tap button
        int color = 1;
        try {
            placePiece(color, view.getId());
        } catch (ColumnFullException e) {
            e.printStackTrace();
        }
//        view.setAlpha(1);
    }

    public int placeChip(int id) throws ColumnFullException {
//        findViewById(id).setAlpha(1);
        switch (id) {
            case R.id.col1:
                return 0;
            case R.id.col2:
                return 1;
            case R.id.col3:
                return 2;
            case R.id.col4:
                return 3;
            case R.id.col5:
                return 4;
            case R.id.col6:
                return 5;
            case R.id.col7:
                return 6;
            default:
                return -1;
        }
    }

    // MODIFIES: this
    // EFFECTS: changes graphics of lowest empty chip in given column to match color
    private void placePiece(int color, int id) throws ColumnFullException {
        int col = placeChip(id);
        int row = easyBoard.addChip(color, col);
//        String name = "chip" + col + row;
//        String name = "chip00";
//        int resourceId = this.getResources().getIdentifier(name, "layout", this.getPackageName());
        TableRow tb = findViewById(R.id.row1);
        int resourceId = R.id.chip00;
        ImageView img = findViewById(resourceId);
        img.setImageResource(R.drawable.blue_square_custom);
    }


    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int getId(String resourceName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resourceName);
            return idField.getInt(idField);
        } catch (Exception e) {
            throw new RuntimeException("No resource ID found for: "
                    + resourceName + " / " + c, e);
        }
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