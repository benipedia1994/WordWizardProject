package edu.gatech.seclass.words6300.ui;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.widget.EditText;
/*

 */
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import java.io.PrintWriter;

import edu.gatech.seclass.words6300.Game;
import edu.gatech.seclass.words6300.GameSettings;
import edu.gatech.seclass.words6300.Letter;
import edu.gatech.seclass.words6300.R;
import edu.gatech.seclass.words6300.Word;

public class GameActivity extends AppCompatActivity {

    private Game currentGame;
    private EditText playedLetters;
    private TextView currentTurn;
    private TextView currentScore;
    private TextView tilesRemaining;

    private LinearLayout boardLayout;
    private LinearLayout rackLayout;
    File inputFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game);
        inputFile = new File(this.getFilesDir(),"gameData.txt");
        loadGame();
        if(inputFile.length()>10) {
            currentGame = new Game(inputFile);
        }else {
            currentGame = new Game(new GameSettings(50));
        }

        currentTurn = findViewById(R.id.currentTurn);
        currentScore = findViewById(R.id.currentScore);
        playedLetters = findViewById(R.id.playedLetters);
        tilesRemaining = findViewById(R.id.tilesRemaining);
        boardLayout = findViewById(R.id.boardLayout);
        rackLayout = findViewById(R.id.rackLayout);

        refreshScreen();


    }

    @Override
    protected void onStop(){
        super.onStop();
        saveGame();
    }

    private void refreshScreen() {

        boardLayout.removeAllViews();
        rackLayout.removeAllViews();
        currentTurn.setText(Integer.toString(currentGame.getCurrentTurn()));
        currentScore.setText(Integer.toString(currentGame.getScore()));

        tilesRemaining.setText(Integer.toString(currentGame.remainingTileCount()));
        for (Letter l: currentGame.getRack()){
            rackLayout.addView(createLetterTile(l));

        }
        for (Letter l: currentGame.getBoard()){
            boardLayout.addView(createLetterTile(l));
        }
        playedLetters.setText("");
    }

    private LinearLayout createLetterTile(Letter l) {
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
        LayoutParams lParams = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        tile.setLayoutParams(lParams);
        tile.setOrientation(LinearLayout.VERTICAL);
        tile.setBackgroundResource(R.drawable.rounded_border_darkpurple);
        tile.addView(letter);
        tile.addView(value);
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

    public void saveGame(){
        try{


            FileOutputStream outputFile = this.getApplicationContext().openFileOutput("gameData.txt",
                Context.MODE_PRIVATE);

            PrintWriter writer = new PrintWriter(outputFile);
            writer.write("");
            writer.println(currentGame.getMaxTurns());
            writer.println(currentGame.getScore());
            writer.println(currentGame.getCurrentTurn());

            for(Letter rackLetter :currentGame.getRack()){
                writer.write(rackLetter.getLetter());
                writer.write(" ");
                writer.print(rackLetter.getPoints());
                writer.write(" ");
            }
            writer.println();
            for(Letter boardLetter: currentGame.getBoard()){
                writer.write(boardLetter.getLetter());
                writer.write(" ");
                writer.print(boardLetter.getPoints());
                writer.write(" ");
            }
            writer.println();
            for(Letter poolLetter: currentGame.getPool()){
                writer.write(poolLetter.getLetter());
                writer.write(" ");
                writer.print(poolLetter.getPoints());
                writer.write(" ");
            }
            /*
            writer.write("$");
            writer.write(" ");
            for(Word playedWord : currentGame.getPlayedWords()){
                for(Letter playedWordLetter: playedWord.getLetters()){
                    writer.write(playedWordLetter.getLetter());
                    writer.write(" ");
                    writer.print(playedWordLetter.getPoints());
                    writer.write(" ");
                    writer.write("%");
                    writer.write(" ");
                }
            }
             */
            writer.close();
            System.out.println("Game Saved");


        }catch(Exception e) {
            System.out.println("Invalid file");
            System.out.println(e.getClass().getSimpleName());
        }
    }
    public void loadGame(){
        try{
            StringBuilder text = new StringBuilder();
            inputFile = new File(this.getFilesDir(),"gameData.txt");
            FileReader fr = new FileReader(inputFile);
            BufferedReader reader = new BufferedReader(fr);
            String line;

            while ((line = reader.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            reader.close();
            System.out.println(text.toString());
        }
        catch (IOException|RuntimeException e) {
            System.out.println(e.getMessage());
        }

    }

}
