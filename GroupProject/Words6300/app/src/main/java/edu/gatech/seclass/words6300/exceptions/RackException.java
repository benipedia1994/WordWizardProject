package edu.gatech.seclass.words6300.exceptions;

public class RackException extends Exception {
    public RackException() {
        super("Attempted to use letters not on Rack");
    }
}
