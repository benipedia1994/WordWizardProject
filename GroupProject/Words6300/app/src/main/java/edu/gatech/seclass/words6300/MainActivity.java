package edu.gatech.seclass.words6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;



import android.view.View;
import android.view.Window;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

    }


    public void MainToGame(View view){
        Intent myIntent = new Intent(MainActivity.this, Game.class);
        startActivity(myIntent);
    }
    public void MainToSettings(View view){
        Intent myIntent = new Intent(MainActivity.this, SettingsAct.class);
        startActivity(myIntent);
    }
    public void MainToStatistics(View view){
        Intent myIntent = new Intent(MainActivity.this, StatisticsAct.class);
        startActivity(myIntent);
    }
    public void MainToInstructions(View view){
        Intent myIntent = new Intent(MainActivity.this, Instructions.class);
        startActivity(myIntent);
    }
    public void ExitApp(View view){
        finish();
        System.exit(0);
    }

}
