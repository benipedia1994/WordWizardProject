package edu.gatech.seclass.words6300;

import java.util.Comparator;

public class WordStat {
    private String word;
    private int timesUsed;

    public WordStat(String word, int timesUsed){
        this.word=word;
        this.timesUsed=timesUsed;
    }

    @Override
    public String toString() {
        return word + "," + timesUsed + "\n";
    }

    public String getWord(){
        return word;
    }
    public int getTimesUsed(){
        return timesUsed;
    }
    public void useWord(){
        timesUsed++;
    }
}
