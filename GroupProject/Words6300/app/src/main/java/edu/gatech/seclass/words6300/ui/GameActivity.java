package edu.gatech.seclass.words6300.ui;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;
import edu.gatech.seclass.words6300.Game;
import edu.gatech.seclass.words6300.GameSettings;
import edu.gatech.seclass.words6300.Letter;
import edu.gatech.seclass.words6300.R;

public class GameActivity extends AppCompatActivity {

    private Game currentGame;
    private EditText playedLetters;
    private TextView currentTurn;
    private TextView currentScore;
    private TextView tilesRemaining;

    private LinearLayout boardLayout;
    private LinearLayout rackLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game);

        currentGame = new Game(new GameSettings(50));

        currentTurn = findViewById(R.id.currentTurn);
        currentScore = findViewById(R.id.currentScore);
        playedLetters = findViewById(R.id.playedLetters);
        tilesRemaining = findViewById(R.id.tilesRemaining);
        boardLayout = findViewById(R.id.boardLayout);
        rackLayout = findViewById(R.id.rackLayout);

        refreshScreen();


    }

    private void refreshScreen() {

        boardLayout.removeAllViews();
        rackLayout.removeAllViews();
        currentTurn.setText(Integer.toString(currentGame.getCurrentTurn()));
        currentScore.setText(Integer.toString(currentGame.getScore()));

        tilesRemaining.setText(Integer.toString(currentGame.remainingTileCount()));
        for (Letter l: currentGame.getRack()){
            rackLayout.addView(createLetterTile(l,'r'));


        }
        for (Letter l: currentGame.getBoard()){
            boardLayout.addView(createLetterTile(l,'b'));
        }
        playedLetters.setText("");
    }

    private LinearLayout createLetterTile(Letter l, char type) {
        /*
        <LinearLayout
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_marginRight="10dp"
            android:layout_weight="1"
            android:background="@drawable/rounded_border_darkpurple"
            android:orientation="vertical">

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="a"
                android:textColor="@android:color/white"
                android:textSize="20sp" />

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="3"
                android:textColor="@android:color/white" />
         */
        TextView letter = new TextView(this);
        letter.setText(Character.toString(l.getLetter()));
        letter.setTextColor(Color.WHITE);
        letter.setTextSize(20);
        TextView value = new TextView(this);
        value.setText(Integer.toString(l.getPoints()));
        value.setTextColor(Color.WHITE);
        LinearLayout tile = new LinearLayout(this);
        tile.setOrientation(LinearLayout.VERTICAL);
        tile.setBackgroundResource(R.drawable.rounded_border_darkpurple);
        letter.setPadding(20,10,10,10);
        value.setPadding(20,10,20,10);
        tile.addView(letter);
        tile.addView(value);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT,1.0f);
        if (type == 'r')
            layoutParams.setMargins(10, 0, 10, 0);
        else layoutParams.setMargins(30, 10, 30, 0);
        tile.setLayoutParams(layoutParams);
        return tile;

    }

    public void onSwap(View view){
        try {
            currentGame.swapLetters(playedLetters.getText().toString());
        } catch (Exception e){
            playedLetters.setError(e.toString());
            System.out.println(e);
        }
        refreshScreen();
    }

    public void onPlayWord(View view){
        try {
            currentGame.makeWord(playedLetters.getText().toString());
        } catch (Exception e){
            playedLetters.setError(e.toString());
            System.out.println(e);
        }
        refreshScreen();
    }

    public void GameToMain(View view) {
        Intent myIntent = new Intent(GameActivity.this, MainActivity.class);
        startActivity(myIntent);
    }
}

