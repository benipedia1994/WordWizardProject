package edu.gatech.seclass.words6300.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import java.util.*;
import java.lang.*;

import edu.gatech.seclass.words6300.GameSettings;
import edu.gatech.seclass.words6300.GameStat;
import edu.gatech.seclass.words6300.R;
import edu.gatech.seclass.words6300.data.Statistics;

public class GameScoreActivity extends AppCompatActivity {

    private Statistics gameStats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game_score);
        try {
            gameStats = new Statistics(getApplicationContext());
        } catch (Exception e){
            System.out.println(e);
        }
//        gameStats.add(new GameStat(10,5));
//        gameStats.add(new GameStat(20,3));
//        gameStats.add(new GameStat(49,4));
//        gameStats.add(new GameStat(49,4));
//        gameStats.add(new GameStat(49,4));
//        gameStats.add(new GameStat(49,4));
//        gameStats.add(new GameStat(49,4));
//        gameStats.add(new GameStat(49,4));
//        gameStats.add(new GameStat(49,4));
//        gameStats.add(new GameStat(49,4));
//        gameStats.add(new GameStat(49,4));
//        gameStats.add(new GameStat(49,4));
//        gameStats.add(new GameStat(43,12));


        TableLayout table = (TableLayout)findViewById(R.id.gameScoreTable);

        for(GameStat entry : gameStats.getGameList())
        {
            table.addView(createRow(entry), new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }


    }

    public TableRow createRow(GameStat g){

        TableRow row = new TableRow(this);
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
        Button viewGame = new Button(this);
        viewGame.setText("View Game");


        score.setTextSize(30);
        score.setLayoutParams(entryParams);
        row.addView(score);

        turns.setTextSize(30);
        turns.setLayoutParams(entryParams);
        row.addView(turns);

        scorePerTurn.setTextSize(30);
        scorePerTurn.setLayoutParams(entryParams);
        row.addView(scorePerTurn);

        viewGame.setLayoutParams(entryParams);
        viewGame.setBackgroundResource(R.drawable.rounded_border_green);
        viewGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(GameScoreActivity.this, GameSettingStatActivity.class);
                startActivity(myIntent);
            }
        });
        row.addView(viewGame);


        return row;

    }

    public void gameScoreToStat(View view){
        Intent myIntent = new Intent(GameScoreActivity.this, StatisticsActivity.class);
        startActivity(myIntent);
    }

//    public void addGameStat(int score, int turns, GameSettings settings) {
//        GameStat temp = new GameStat(score, turns, settings);
//        gameStats.add(temp);
//        Collections.sort(gameStats, new Comparator<GameStat>() {
//            public int compare(GameStat s1, GameStat s2) {
//                return Integer.compare(s1.getScore(), s2.getScore());
//            }
//        });
//    }


}


