package edu.gatech.seclass.words6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StatisticsAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
    }

    public void statToGameScore(View view){
        Intent myIntent = new Intent(StatisticsAct.this, GameScore.class);
        startActivity(myIntent);
    }
    public void statToLetter(View view){
        Intent myIntent = new Intent(StatisticsAct.this, LetterStatistics.class);
        startActivity(myIntent);
    }
    public void statToWord(View view){
        Intent myIntent = new Intent(StatisticsAct.this, WordStatistics.class);
        startActivity(myIntent);
    }
    public void statToMain(View view){
        Intent myIntent = new Intent(StatisticsAct.this, MainActivity.class);
        startActivity(myIntent);
    }
}
