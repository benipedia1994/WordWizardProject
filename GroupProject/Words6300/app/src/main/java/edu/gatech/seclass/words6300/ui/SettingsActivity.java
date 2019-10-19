package edu.gatech.seclass.words6300.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

import edu.gatech.seclass.words6300.GameSettings;
import edu.gatech.seclass.words6300.GameStat;
import edu.gatech.seclass.words6300.LetterSettings;
import edu.gatech.seclass.words6300.R;

public class SettingsActivity extends AppCompatActivity {

    ArrayList<EditText> freqEditList = new ArrayList<>();
    ArrayList<EditText> valueEditList = new ArrayList<>();

    private EditText turn;

    private GameSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_settings);

        turn = findViewById(R.id.turn);

        try {
            File file = new File(getApplicationContext().getFilesDir(), "settings.txt");
            if (!file.exists()) {
                System.out.println("initialize file");
                file.createNewFile();
                settings = new GameSettings();
                saveSettings();
            } else {
                System.out.println("reading from file");
                Scanner scanner = new Scanner(file);
                final String DELIMITER = "<->";
                String[] tokens;
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    System.out.println(line);
                    tokens = line.split(DELIMITER);
                    int maxTurns = Integer.parseInt(tokens[0]);
                    String letterDistribution = tokens[1];
                    settings = new GameSettings(maxTurns, letterDistribution);
                }
                scanner.close();
            }
            LinearLayout settingsLayout = findViewById(R.id.settingsLayout);

            turn.setText(Integer.toString(settings.getMaxTurns()));

            for(LetterSettings letter : settings.getLetterSettings()){
                settingsLayout.addView(createLetter(letter));
            }
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    public LinearLayout createLetter(LetterSettings letter){

        LinearLayout letterView = new LinearLayout(this);
        letterView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        letterView.setOrientation(LinearLayout.HORIZONTAL);

        TextView letterText = new TextView(this);
        LinearLayout.LayoutParams params =new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT,.75f);
        params.setMargins(0,0,4,0);
        letterText.setLayoutParams(params);
        letterText.setTextColor(Color.WHITE);
        letterText.setText(Character.toString(letter.getLetter()));
        letterText.setBackgroundResource(R.drawable.rounded_border_darkpurple);
        letterView.addView(letterText);

        TextView freqView = new TextView(this);
        freqView.setLayoutParams(new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT,1f));
        freqView.setText("Frequency:");
        letterView.addView(freqView);

        EditText freqEdit = new EditText(this);
        freqEdit.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
        freqEdit.setText(String.valueOf(letter.getCount()));
        freqEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
        freqEdit.setGravity(Gravity.RIGHT);
        freqEditList.add(freqEdit);
        letterView.addView(freqEdit);

        TextView valueView = new TextView(this);
        valueView.setLayoutParams(new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT,.75f));
        valueView.setText("Value:");
        letterView.addView(valueView);

        EditText valueEdit = new EditText(this);
        valueEdit.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
        valueEdit.setText(String.valueOf(letter.getValue()));
        valueEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
        valueEdit.setGravity(Gravity.RIGHT);
        valueEditList.add(valueEdit);
        letterView.addView(valueEdit);

        return letterView;
    }

    public void onSave(View view){
        saveSettings();
    }

    public void resetDefault(View view){
        settings = new GameSettings();
        LinearLayout settingsLayout = findViewById(R.id.settingsLayout);

        turn.setText(Integer.toString(settings.getMaxTurns()));
        settingsLayout.removeAllViews();
        for(LetterSettings letter : settings.getLetterSettings()){
            settingsLayout.addView(createLetter(letter));
        }
        saveSettings();
    }

    public void saveSettings() {
        try {
            settings.setMaxTurns(Integer.parseInt(turn.getText().toString()));
            for (int i = 0; i < 26; i++) {
                LetterSettings ls = settings.getLetter(i);
                ls.setValue(Integer.parseInt(valueEditList.get(i).getText().toString()));
                ls.setCount(Integer.parseInt(freqEditList.get(i).getText().toString()));
                settings.setLetter(i, ls);
            }
            System.out.println("Settings Saved!");
            FileOutputStream fos = getApplicationContext().openFileOutput("settings.txt", Context.MODE_PRIVATE);
            OutputStreamWriter os = new OutputStreamWriter(fos);
            os.write(settings.toString()+"\n");
            os.close();
        } catch (Exception e){
            System.err.println(e);
        }
    }

    public void SettingsToMain(View view){
        Intent myIntent=new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(myIntent);

    }
}
