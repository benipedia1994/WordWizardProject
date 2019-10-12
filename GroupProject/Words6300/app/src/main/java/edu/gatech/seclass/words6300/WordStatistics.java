package edu.gatech.seclass.words6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class WordStatistics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_word_statistics);

        ArrayList<WordStat> wordList = new ArrayList<>();
        wordList.add(new WordStat("platypus", 14));
        wordList.add(new WordStat("denim",3));
        wordList.add(new WordStat("zygote",49));
        wordList.add(new WordStat("fight", 2));
        wordList.add(new WordStat("wingman", 5));
        wordList.add(new WordStat("burglar",9));
        wordList.add(new WordStat("nirvana",5));
        wordList.add(new WordStat("time",50));
        wordList.add(new WordStat("rhymes",3));
        wordList.add(new WordStat("dimes",9));
        wordList.add(new WordStat("mimes", 9));
        wordList.add(new WordStat("crimes", 19));
        wordList.add(new WordStat("limes", 38));
        wordList.add(new WordStat("hind",7));

        TableLayout wordTable = findViewById(R.id.wordTable);
        for(WordStat word : wordList){
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
        Intent myIntent = new Intent(WordStatistics.this,MainActivity.class);
        startActivity(myIntent);
    }
}
