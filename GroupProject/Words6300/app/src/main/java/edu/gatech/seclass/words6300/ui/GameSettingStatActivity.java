package edu.gatech.seclass.words6300.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import edu.gatech.seclass.words6300.GameSettings;
import edu.gatech.seclass.words6300.LetterSettings;
import edu.gatech.seclass.words6300.LetterStat;
import edu.gatech.seclass.words6300.R;

public class GameSettingStatActivity extends AppCompatActivity {
    GameSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setting_stat);

        settings = new GameSettings();


        TableLayout letterTable = (TableLayout) findViewById(R.id.letterSettingStats);
        for (LetterSettings letter : settings.getLetterSettings()) {

        letterTable.addView(createRow(letter), new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

    }
        TextView turnsView = (TextView)findViewById(R.id.turnsView);
        turnsView.setText(String.valueOf(settings.getMaxTurns()));
}



    public TableRow createRow(LetterSettings letter){

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

        letterRow.addView(letterView);


        TextView playedView = new TextView(this);
        playedView.setTextSize(30);
        playedView.setLayoutParams(entryParams);
        playedView.setText(Integer.toString(letter.getCount()));
        letterRow.addView(playedView);



        TextView playedPerDrawn = new TextView(this);
        playedPerDrawn.setTextSize(30);
        playedPerDrawn.setLayoutParams(entryParams);
        playedPerDrawn.setText(Integer.toString(letter.getValue()));
        letterRow.addView(playedPerDrawn);



        return letterRow;

    }

    public void gameSettingToGameScore(View view){
        Intent myIntent = new Intent(GameSettingStatActivity.this, GameScoreActivity.class);
        startActivity(myIntent);
    }

}
