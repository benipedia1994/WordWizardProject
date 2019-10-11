package edu.gatech.seclass.words6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameAct extends AppCompatActivity {
    private Game currentGame;

    private EditText attempt;
    private TextView currentTurn;
    private TextView currentScore;
    private TextView rackView;
    private TextView boardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game);
        currentGame = new Game(new GameSettings(50));
    /*
        currentTurn = findViewById(R.id.currentTurn);
        currentScore = findViewById(R.id.currentScore);
        rackView = findViewById(R.id.rackView);
        boardView = findViewById(R.id.boardView);
        attempt = findViewById(R.id.attempt);
        refreshScreen();
        
     */
    }
    private void refreshScreen() {
        //System.out.println(currentGame.getCurrentTurn());
        currentTurn.setText(Integer.toString(currentGame.getCurrentTurn()));
        currentScore.setText(Integer.toString(currentGame.getScore()));
        String rack = "";
        for (Letter l: currentGame.getRack()){
            rack = rack + l.getLetter() + ",";
        }
        rackView.setText(rack);
        String board = "";
        for (Letter l: currentGame.getBoard()){
            board = board + l.getLetter() + ",";
        }
        boardView.setText(board);
        attempt.setText("");
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