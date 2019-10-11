package edu.gatech.seclass.words6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TableLayout;

import java.util.ArrayList;

public class LetterStatistics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_letter_statistics);
        ArrayList<LetterStat> letterList = new ArrayList<LetterStat>();
        letterList.add(new LetterStat('a',3,5));
        letterList.add(new LetterStat('b', 3, 5));
        letterList.add(new LetterStat('c', 3, 5));
        letterList.add(new LetterStat('d', 3, 5));
        letterList.add(new LetterStat('b', 3, 5));
        letterList.add(new LetterStat('b', 3, 5));
        letterList.add(new LetterStat('b', 3, 5));
        letterList.add(new LetterStat('b', 3, 5));
        letterList.add(new LetterStat('b', 3, 5));
        letterList.add(new LetterStat('b', 3, 5));
        TableLayout letterTable = findViewById(R.id.letterTable);
        for(int i = 0; i <letterList.size(); i++){

        }


    }
    public void letterToStat(View view){
        Intent myIntent = new Intent(LetterStatistics.this, StatisticsAct.class);
        startActivity(myIntent);
    }





}
