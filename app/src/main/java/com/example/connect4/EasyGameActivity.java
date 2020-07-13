package com.example.connect4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.connect4.Exceptions.ColumnFullException;
import com.example.connect4.Exceptions.EndGameException;
import com.example.connect4.Exceptions.FullBoardEndGameException;
import com.example.connect4.games.EasyGame;
import com.example.connect4.games.Game;
import com.example.connect4.games.GameMove;

public class EasyGameActivity extends AppCompatActivity {
    private Game game;
//    private int[] ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = new EasyGame();
//        if (savedInstanceState == null) {
//            setContentView(R.layout.activity_easy_game);
//            // TODO: set player to player selection color in game select screen
////            ids = new int[]{R.id.chip00, R.id.chip01, R.id.chip02, R.id.chip03, R.id.chip04, R.id.chip05, R.id.chip06,
////                    R.id.chip10, R.id.chip11, R.id.chip12, R.id.chip13, R.id.chip14, R.id.chip15, R.id.chip16,
////                    R.id.chip20, R.id.chip21, R.id.chip22, R.id.chip23, R.id.chip24, R.id.chip25, R.id.chip26,
////                    R.id.chip30, R.id.chip31, R.id.chip32, R.id.chip33, R.id.chip34, R.id.chip35, R.id.chip36,
////                    R.id.chip40, R.id.chip41, R.id.chip42, R.id.chip43, R.id.chip44, R.id.chip45, R.id.chip46,
////                    R.id.chip50, R.id.chip51, R.id.chip52, R.id.chip53, R.id.chip54, R.id.chip55, R.id.chip56};
//
//        } else {
//            game.getBoard().setChips((int[][]) savedInstanceState.get("chips"));
////            ids = (int[]) savedInstanceState.get("ids");
//        }
//        // TODO: figure out saved instance states
//        // TODO: add a new game function (which resets activity completely) & persistence (which continues activity)
//        // TODO: game over functionality
        setContentView(R.layout.activity_easy_game);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putSerializable("ids", game.getIds());
        outState.putSerializable("chips", game.getBoard().getChips());
    }

    /**
     * runs player move on button press (aka when a column is tapped)
     */
    public void columnPress(View view) {
        // because this is the player tap button
        try {
            int col = findColumn(view.getId());
            int row = game.personPlay(col);
            int id = game.getChipId(row, col);
//            animate(Game.PERSON, R.id.chip44);
            updatePieceGraphics(Game.PERSON, id);

//            updatePieceGraphics(Game.getPERSON(), view.getId());
//            updatePieceGraphics(Game.getCOMPUTER(), game.computerPlay());
//            placePiece(player, view.getId());
        } catch (ColumnFullException e) {
//            TODO:   warn that column is full
        } catch (EndGameException e) {
            // TODO: end game stuff
            if (e.getClass() == FullBoardEndGameException.class) {
                noWin();
            } else {
                gameOver();
            }
//            e.printStackTrace();
        }
        try {
            GameMove computer = game.computerPlay();
            int id2 = game.getChipId(computer.getRow(), computer.getColumn());
            updatePieceGraphics(Game.COMPUTER, id2);

        } catch (ColumnFullException e) {
            e.printStackTrace();
        } catch (FullBoardEndGameException e) {
            noWin();
        } catch (EndGameException e) {
            gameOver();
        }
    }

    public void gameOver() {
        Intent intent = new Intent(this, NoWinActivity.class);
        startActivity(intent);

    }

    public void noWin() {
        PopupWindow window = new PopupWindow();
    }

    /**
     * finds column corresponding to button press ID
     *
     * @param id representing button press ID
     * @return column number
     */
    private int findColumn(int id) {
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


    // EFFECTS: displays piece with given id and color
    private void updatePieceGraphics(int color, int id) {
        ImageView img = findViewById(id);
        img.setVisibility(View.VISIBLE);
        if (color == 1) {
            img.setImageResource(R.drawable.blue_square_custom);
        } else {
            img.setImageResource(R.drawable.red_square_custom);
        }
    }

    // EFFECTS: displays piece with given id and color
    private void animate(int color, int id) {
        ImageView img = findViewById(id);
        img.setVisibility(View.VISIBLE);
        if (color == 1) {
            img.setImageResource(R.drawable.blue_square_custom);
        } else {
            img.setImageResource(R.drawable.red_square_custom);
        }
        img.setVisibility(View.INVISIBLE);
    }

    /**
     * Called when user taps the How To Play button
     */
    public void howToPlay(View view) {
        Intent intent = new Intent(this, HowToPlayActivity.class);
        startActivity(intent);
    }

    /**
     * Called when user taps the Return To Menu button
     */
    public void returnToMenu(View view) {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
        finish();

    }


}