package edu.gatech.seclass.words6300.exceptions;

public class GameOverException extends Exception {
    public GameOverException() {
        super("This game has ended");
    }
}
