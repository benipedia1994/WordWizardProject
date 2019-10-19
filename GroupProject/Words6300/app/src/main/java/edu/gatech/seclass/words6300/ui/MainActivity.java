package edu.gatech.seclass.words6300.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;



import android.view.View;
import android.view.Window;

import edu.gatech.seclass.words6300.R;
import edu.gatech.seclass.words6300.data.Statistics;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

    }


    public void MainToGame(View view){
        Intent myIntent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(myIntent);
    }
    public void MainToSettings(View view){
        Intent myIntent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(myIntent);
    }
    public void MainToStatistics(View view){
        Intent myIntent = new Intent(MainActivity.this, StatisticsActivity.class);
        startActivity(myIntent);
    }
    public void MainToInstructions(View view){
        Intent myIntent = new Intent(MainActivity.this, InstructionsActivity.class);
        startActivity(myIntent);
    }
    public void ExitApp(View view){
        this.finishAffinity();
        System.exit(0);
    }

}
