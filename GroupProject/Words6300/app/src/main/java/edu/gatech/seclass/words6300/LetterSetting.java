package edu.gatech.seclass.words6300;

public class LetterSetting {
    private char letter;
    int freq;
    int value;

    public LetterSetting(char letter, int freq, int value){
        this.letter=letter;
        this.freq=freq;
        this.value=value;
    }

    public char getLetter(){
        return letter;
    }
    public int getFreq(){
        return freq;
    }
    public int getValue(){
        return value;
    }



}
