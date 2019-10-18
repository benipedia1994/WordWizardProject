package edu.gatech.seclass.words6300.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import edu.gatech.seclass.words6300.R;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
    }

    public void statToGameScore(View view){
        Intent myIntent = new Intent(StatisticsActivity.this, GameScoreActivity.class);
        startActivity(myIntent);
    }
    public void statToLetter(View view){
        Intent myIntent = new Intent(StatisticsActivity.this, LetterStatisticsActivity.class);
        startActivity(myIntent);
    }
    public void statToWord(View view){
        Intent myIntent = new Intent(StatisticsActivity.this, WordStatisticsActivity.class);
        startActivity(myIntent);
    }
    public void statToMain(View view){
        Intent myIntent = new Intent(StatisticsActivity.this, MainActivity.class);
        startActivity(myIntent);
    }
}
