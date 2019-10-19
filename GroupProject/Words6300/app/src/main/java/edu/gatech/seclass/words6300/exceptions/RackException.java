package edu.gatech.seclass.words6300.exceptions;

import edu.gatech.seclass.words6300.Letter;

public class RackException extends Exception {
    public RackException(char missingLetter) {
        super("Letter: " + missingLetter + " not found on rack" );
    }

    public RackException() {
        super("Missing Letters from Rack");
    }
}
