package edu.gatech.seclass.words6300;

import java.text.DecimalFormat;

public class LetterStat {
    private char letter;
    private int played;
    private int drawn;
    private double playedPerDrawn;
    public LetterStat(char letter, int played, int drawn){
        this.letter=letter;
        this.played=played;
        this.drawn=drawn;
        DecimalFormat twoDForm  = new DecimalFormat("#.##");
        this.playedPerDrawn = Double.valueOf(twoDForm.format((double) played / drawn));
    }

    @Override
    public String toString() {
        return letter + "," + played + "," + drawn;
    }

    public char getLetter(){
        return letter;
    }
    public int getPlayed(){
        return played;
    }
    public int getDrawn(){
        return drawn;
    }
    public double getPlayedPerDrawn(){
        return playedPerDrawn;
    }
}
