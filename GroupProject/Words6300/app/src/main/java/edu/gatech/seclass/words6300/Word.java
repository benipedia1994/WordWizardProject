package edu.gatech.seclass.words6300;

import java.util.ArrayList;
import java.util.Random;

public class Word {
    private ArrayList<Letter> letters;

    public Word(ArrayList<Letter> letters) {
        this.letters = letters;
    }

    public int getScore(){
        int score = 0;
        for (Letter l : letters){
            score += l.getPoints();
        }
        return score;
    }

    public Letter getRandomLetter(){
        Random rand = new Random();
        int n = rand.nextInt(letters.size());
        return this.letters.get(n);
    }

    @Override
    public String toString() {
        String out = "";
        for (Letter l: letters){
            out += l.getLetter();
        }
        return out;
    }
}
