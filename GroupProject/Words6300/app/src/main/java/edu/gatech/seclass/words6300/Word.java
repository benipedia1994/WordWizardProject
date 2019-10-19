package edu.gatech.seclass.words6300;

import java.util.ArrayList;
import java.util.Objects;
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

    public ArrayList<Letter> getLetters() {
        return letters;
    }

    @Override
    public String toString() {
        String out = "";
        for (Letter l: letters){
            out += l.getLetter();
        }
        return out;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return Objects.equals(letters, word.letters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(letters);
    }
}
