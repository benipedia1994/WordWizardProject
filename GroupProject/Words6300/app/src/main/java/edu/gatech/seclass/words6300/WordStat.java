package edu.gatech.seclass.words6300;

public class WordStat {
    private String word;
    private int timesUsed;

    public WordStat(String word, int timesUsed){
        this.word=word;
        this.timesUsed=timesUsed;
    }

    public String getWord(){
        return word;
    }
    public int getTimesUsed(){
        return timesUsed;
    }
}
