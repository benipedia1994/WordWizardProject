package edu.gatech.seclass.words6300;

import java.util.Objects;

public class LetterSettings {
    private char letter;
    private int value;
    private int count;

    public LetterSettings(char letter, int value, int count) {
        this.letter = letter;
        this.value = value;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LetterSettings that = (LetterSettings) o;
        return letter == that.letter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter);
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return letter + ":" + value + ":" + count;
    }
}
