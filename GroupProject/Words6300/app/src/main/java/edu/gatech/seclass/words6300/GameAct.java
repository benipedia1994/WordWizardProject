package edu.gatech.seclass.words6300;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;

import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameAct extends AppCompatActivity {

    private Game currentGame;
    private EditText attempt;
    private TextView currentTurn;
    private TextView currentScore;
    private LinearLayout rackView;
    private LinearLayout boardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game);

        currentGame = new Game(new GameSettings(50));
        currentTurn = findViewById(R.id.currentTurn);
        currentScore = findViewById(R.id.currentScore);
        rackView = findViewById(R.id.rackView);
        boardView = findViewById(R.id.boardView);
        attempt = findViewById(R.id.attempt);
        refreshScreen();


    }

    private void refreshScreen() {
        //System.out.println(currentGame.getCurrentTurn());
        currentTurn.setText(Integer.toString(currentGame.getCurrentTurn()));
        currentScore.setText(Integer.toString(currentGame.getScore()));

        if(rackView.getChildCount() > 0){
            rackView.removeAllViews();
        }if(boardView.getChildCount() > 0){
            boardView.removeAllViews();
        }


        for (Letter l: currentGame.getRack()){
            LinearLayout rackPiece = new LinearLayout(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.WRAP_CONTENT,1.0f);

            layoutParams.setMargins(0, 0, 10, 0);
            rackPiece.setLayoutParams(layoutParams);
            rackPiece.setBackgroundResource(R.drawable.rounded_border_darkpurple);
            rackPiece.setOrientation(LinearLayout.VERTICAL);
            rackView.addView(rackPiece);

            TextView rackLetter = new TextView(this);
            rackLetter.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            rackLetter.setTextSize(20);
            rackLetter.setTextColor(Color.WHITE);
            rackLetter.setText(Character.toString(l.getLetter()));
            rackPiece.addView(rackLetter);

            TextView rackNumber = new TextView(this);
            rackNumber.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            rackNumber.setTextColor(Color.WHITE);
            rackNumber.setText("" + l.getPoints());
            rackPiece.addView(rackNumber);


        }

        for (Letter l: currentGame.getBoard()){
            LinearLayout boardPiece = new LinearLayout(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.WRAP_CONTENT,1.0f);

            layoutParams.setMargins(0, 0, 50, 0);
            boardPiece.setLayoutParams(layoutParams);
            boardPiece.setBackgroundResource(R.drawable.rounded_border_brightpurple);
            boardPiece.setOrientation(LinearLayout.VERTICAL);
            boardView.addView(boardPiece);

            TextView boardLetter = new TextView(this);
            boardLetter.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            boardLetter.setTextSize(20);
            boardLetter.setTextColor(Color.WHITE);
            boardLetter.setText(Character.toString(l.getLetter()));
            boardPiece.addView(boardLetter);

            TextView boardNumber = new TextView(this);
            boardNumber.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            boardNumber.setTextColor(Color.WHITE);
            boardNumber.setText("" + l.getPoints());
            boardPiece.addView(boardNumber);

        }

    }

    public void onSwap(View view){
        try {
            currentGame.swapLetters(currentGame.getRack());
        } catch (Exception e){
            System.out.println(e);
        }
        refreshScreen();
    }

    public void onPlayWord(View view){
        try {
            currentGame.makeWord(attempt.getText().toString());
        } catch (Exception e){
            System.out.println(e);
        }
        refreshScreen();
    }

    public void GameToMain(View view) {
        Intent myIntent = new Intent(GameAct.this, MainActivity.class);
        startActivity(myIntent);
    }
}

