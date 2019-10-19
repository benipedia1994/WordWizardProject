package edu.gatech.seclass.words6300;

import java.text.DecimalFormat;

public class LetterStat {
    private char letter;
    private int played;
    private int drawn;
    private int traded;
    private double playedPerDrawn;
    public LetterStat(char letter, int played, int traded, int drawn){
        this.letter=letter;
        this.played=played;
        this.traded=traded;
        this.drawn=drawn;
        DecimalFormat twoDForm  = new DecimalFormat("#.##");
        this.playedPerDrawn = Double.valueOf(twoDForm.format((double) played / drawn));
    }

    @Override
    public String toString() {
        return letter + "," + played + "," + traded + "," + drawn + "\n";
    }
    public void addPlayed(){
        this.played++;
    }
    public void addDrawn(){
        this.drawn++;
    }
    public void addTraded(){
        this.traded++;
    }
    public char getLetter(){
        return letter;
    }
    public int getPlayed(){
        return played;
    }
    public int getTraded(){
        return traded;
    }
    public double getPlayedPerDrawn(){
        return playedPerDrawn;
    }
}
