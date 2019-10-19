package edu.gatech.seclass.words6300.data;

import java.util.Comparator;

import edu.gatech.seclass.words6300.LetterStat;

public class SortbyLetter implements Comparator<LetterStat> {

    public int compare( LetterStat a, LetterStat b) {
        return b.getPlayed() - a.getPlayed();
    }

}
