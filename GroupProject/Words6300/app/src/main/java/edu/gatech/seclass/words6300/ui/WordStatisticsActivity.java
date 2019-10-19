package edu.gatech.seclass.words6300.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import edu.gatech.seclass.words6300.R;
import edu.gatech.seclass.words6300.WordStat;
import edu.gatech.seclass.words6300.data.Statistics;

public class WordStatisticsActivity extends AppCompatActivity {

    private Statistics gameStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_word_statistics);

        try {
            gameStats = new Statistics(getApplicationContext());
        } catch (Exception e){
            System.out.println(e);
        }

        TableLayout wordTable = findViewById(R.id.wordTable);

        for(WordStat word : gameStats.getWordBank()){
            wordTable.addView(createWordRow(word), new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }


    }

    public TableRow createWordRow(WordStat word){
        TableRow wordRow = new TableRow(this);
        TableRow.LayoutParams tableRowParams= new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        wordRow.setLayoutParams(tableRowParams);

        TableRow.LayoutParams entryParams = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT,1.0f);

        TextView wordView = new TextView(this);
        wordView.setTextSize(30);
        wordView.setLayoutParams(entryParams);
        wordView.setText(word.getWord());
        wordRow.addView(wordView);

        TextView timesView = new TextView(this);
        timesView.setTextSize(30);
        timesView.setLayoutParams(entryParams);
        timesView.setText(String.valueOf(word.getTimesUsed()));
        wordRow.addView(timesView);

        return wordRow;

    }





    public void wordToStat(View view){
        Intent myIntent = new Intent(WordStatisticsActivity.this, MainActivity.class);
        startActivity(myIntent);
    }
}
