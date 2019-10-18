package edu.gatech.seclass.words6300.exceptions;

public class PoolException extends Exception {
    public PoolException() {
        super("Insufficient pool resources to fulfill request");
    }
}
