package edu.gatech.seclass.words6300.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import edu.gatech.seclass.words6300.LetterStat;
import edu.gatech.seclass.words6300.R;

public class LetterStatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_letter_statistics);

        ArrayList<LetterStat> letterList = new ArrayList<LetterStat>();
        letterList.add(new LetterStat('a', 3, 5));
        letterList.add(new LetterStat('b', 3, 5));
        letterList.add(new LetterStat('c', 3, 5));
        letterList.add(new LetterStat('d', 3, 5));
        letterList.add(new LetterStat('e', 3, 5));
        letterList.add(new LetterStat('f', 3, 5));
        letterList.add(new LetterStat('g', 3, 5));
        letterList.add(new LetterStat('h', 3, 5));
        letterList.add(new LetterStat('i', 3, 5));
        letterList.add(new LetterStat('j', 3, 5));
        letterList.add(new LetterStat('e', 5, 6));

        TableLayout letterTable = (TableLayout) findViewById(R.id.letterTable);
        for (LetterStat entry : letterList) {
            letterTable.addView(createRow(entry), new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }
    public TableRow createRow(LetterStat letter){

        TableRow letterRow = new TableRow(this);
        TableRow.LayoutParams tableRowParams= new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        letterRow.setLayoutParams(tableRowParams);

        TableRow.LayoutParams entryParams = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT,1.0f);

        TextView letterView = new TextView(this);
        letterView.setTextColor(Color.WHITE);
        letterView.setTextSize(30);
        letterView.setBackgroundResource(R.drawable.rounded_border_darkpurple);
        letterView.setLayoutParams(entryParams);
        letterView.setText(Character.toString(letter.getLetter()));
        Log.i("info",Character.toString(letter.getLetter()));
        letterRow.addView(letterView);


        TextView playedView = new TextView(this);
        playedView.setTextSize(30);
        playedView.setLayoutParams(entryParams);
        playedView.setText(Integer.toString(letter.getPlayed()));
        letterRow.addView(playedView);

        TextView drawnView = new TextView(this);
        drawnView.setTextSize(30);
        drawnView.setLayoutParams(entryParams);
        drawnView.setText(Integer.toString(letter.getDrawn()));
        letterRow.addView(drawnView);

        TextView playedPerDrawn = new TextView(this);
        playedPerDrawn.setTextSize(30);
        playedPerDrawn.setLayoutParams(entryParams);
        playedPerDrawn.setText(String.valueOf(letter.getPlayedPerDrawn()));
        letterRow.addView(playedPerDrawn);



        return letterRow;

    }
    public void letterToStat(View view){
        Intent myIntent = new Intent(LetterStatisticsActivity.this, StatisticsActivity.class);
        startActivity(myIntent);
    }





}
