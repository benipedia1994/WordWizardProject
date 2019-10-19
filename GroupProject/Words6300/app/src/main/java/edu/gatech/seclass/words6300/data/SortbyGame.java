package edu.gatech.seclass.words6300.data;

import java.util.Comparator;

import edu.gatech.seclass.words6300.GameStat;

public class SortbyGame implements Comparator<GameStat> {
    @Override
    public int compare(GameStat o1, GameStat o2) {
        return o2.getScore() - o1.getScore();
    }
}
