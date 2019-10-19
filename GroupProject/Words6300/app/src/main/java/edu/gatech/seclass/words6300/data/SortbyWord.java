package edu.gatech.seclass.words6300.data;

import java.util.Comparator;

import edu.gatech.seclass.words6300.WordStat;

public class SortbyWord implements Comparator<WordStat>
{
    public int compare(WordStat a, WordStat b) {
        return b.getTimesUsed() - a.getTimesUsed();
    }
}
