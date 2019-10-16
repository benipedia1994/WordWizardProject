package edu.gatech.seclass.words6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import java.text.DecimalFormat;
import java.util.*;
import java.lang.*;

public class GameScore extends AppCompatActivity {
   private ArrayList<GameStat> gameStats = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game_score);

        gameStats.add(new GameStat(10,5));
        gameStats.add(new GameStat(20,3));

        TableLayout table = (TableLayout)findViewById(R.id.gameScoreTable);
        for(GameStat entry : gameStats)
        {
            table.addView(createRow(entry), new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }

    public TableRow createRow(GameStat g){

        TableRow row = new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);
        TableRow.LayoutParams tableRowParams= new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(tableRowParams);
        TableRow.LayoutParams entryParams = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT,1.0f);

//        android:layout_width="0dp"
//        android:layout_height="wrap_content"
//        android:layout_weight="1"
//        android:text="399"
//        android:textSize="30sp"/>

        TextView score = new TextView(this);
        score.setText(Integer.toString(g.getScore()));
        TextView turns = new TextView(this);
        turns.setText(Integer.toString(g.getTurns()));
        TextView scorePerTurn = new TextView(this);
        scorePerTurn.setText(Double.toString(g.getScorePerTurn()));


        score.setTextSize(30);
        score.setLayoutParams(entryParams);
        row.addView(score);

        turns.setTextSize(30);
        turns.setLayoutParams(entryParams);
        row.addView(turns);
        
        scorePerTurn.setTextSize(30);
        scorePerTurn.setLayoutParams(entryParams);
        row.addView(scorePerTurn);



        return row;

    }

    public void gameScoreToStat(View view){
        Intent myIntent = new Intent(GameScore.this, StatisticsAct.class);
        startActivity(myIntent);
    }

    public void addGameStat(int score, int turns, GameSettings settings) {
        GameStat temp = new GameStat(score, turns, settings);
        gameStats.add(temp);
        Collections.sort(gameStats, new Comparator<GameStat>() {
            public int compare(GameStat s1, GameStat s2) {
                return Integer.compare(s1.getScore(), s2.getScore());
            }
        });
    }


}


