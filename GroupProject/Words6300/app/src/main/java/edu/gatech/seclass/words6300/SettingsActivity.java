package edu.gatech.seclass.words6300;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    ArrayList<EditText> freqEditList = new ArrayList<>();
    ArrayList<EditText> valueEditList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_settings);

        GameSettings settings = new GameSettings(40);
        LinearLayout settingsLayout = findViewById(R.id.settingsLayout);

        for(LetterSettings letter : settings.getLetterSettings()){
            settingsLayout.addView(createLetter(letter));
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

    public void SettingsToMain(View view){
        Intent myIntent=new Intent(SettingsActivity.this,MainActivity.class);
        startActivity(myIntent);

    }
}
