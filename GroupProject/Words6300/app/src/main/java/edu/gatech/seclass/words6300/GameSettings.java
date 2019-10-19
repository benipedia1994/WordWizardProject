package edu.gatech.seclass.words6300;

import java.util.ArrayList;
import java.util.Arrays;

public class GameSettings {
    private LetterSettings[] letterSettings;
    private int maxTurns;

    public GameSettings(int maxTurns, String letterDistribution) {
        this.maxTurns = maxTurns;
        letterSettings = new LetterSettings[26];
        letterDistribution = letterDistribution.substring(1, letterDistribution.length()-1);
        String[] tokens = letterDistribution.split(",");
        for (int i = 0; i < tokens.length ; i++) {
            String[] params = tokens[i].split(":");
            letterSettings[i] = new LetterSettings(params[0].trim().charAt(0), Integer.parseInt(params[1]), Integer.parseInt(params[2]) );
        }
    }

    public GameSettings(int maxTurns) {
        this.maxTurns = maxTurns;
        letterSettings = new LetterSettings[26];
        letterSettings[0] = new LetterSettings('a', 1, 9);
        letterSettings[1] = new LetterSettings('b', 3, 2);
        letterSettings[2] = new LetterSettings('c', 3, 2);
        letterSettings[3] = new LetterSettings('d', 2, 4);
        letterSettings[4] = new LetterSettings('e', 1, 12);
        letterSettings[5] = new LetterSettings('f', 4, 2);
        letterSettings[6] = new LetterSettings('g', 2, 3);
        letterSettings[7] = new LetterSettings('h', 4, 2);
        letterSettings[8] = new LetterSettings('i', 1, 9);
        letterSettings[9] = new LetterSettings('j', 8, 1);
        letterSettings[10] = new LetterSettings('k', 5, 1);
        letterSettings[11] = new LetterSettings('l', 1, 4);
        letterSettings[12] = new LetterSettings('m', 3, 2);
        letterSettings[13] = new LetterSettings('n', 1, 6);
        letterSettings[14] = new LetterSettings('o', 1, 8);
        letterSettings[15] = new LetterSettings('p', 3, 2);
        letterSettings[16] = new LetterSettings('q', 10, 1);
        letterSettings[17] = new LetterSettings('r', 1, 6);
        letterSettings[18] = new LetterSettings('s', 1, 4);
        letterSettings[19] = new LetterSettings('t', 1, 6);
        letterSettings[20] = new LetterSettings('u', 1, 4);
        letterSettings[21] = new LetterSettings('v', 4, 2);
        letterSettings[22] = new LetterSettings('w', 4, 2);
        letterSettings[23] = new LetterSettings('x', 8, 1);
        letterSettings[24] = new LetterSettings('y', 4, 2);
        letterSettings[25] = new LetterSettings('z', 10, 1);
    }

    public int getMaxTurns() {
        return maxTurns;
    }

    public void setMaxTurns(int maxTurns) {
        this.maxTurns = maxTurns;
    }

    public void adjustLetter(LetterSettings letter){
        for (int i = 0; i < letterSettings.length ; i++) {
            if (letterSettings[i].equals(letter)){
                letterSettings[i] = letter;
            }
        }
    }

    public LetterSettings[] getLetterSettings() {
        return letterSettings;
    }

    public LetterSettings getLetter(char letter){
        LetterSettings l = new LetterSettings(letter, 0, 0);
        for (int i = 0; i < letterSettings.length ; i++) {
            if (letterSettings[i].equals(l)){
                l = letterSettings[i];
            }
        }
        return l;
    }

    @Override
    public String toString() {
        return maxTurns + "<->" + Arrays.toString(letterSettings);
    }
}
