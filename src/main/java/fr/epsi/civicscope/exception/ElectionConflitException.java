package fr.epsi.civicscope.exception;

public class ElectionConflitException extends RuntimeException {
    public ElectionConflitException(String message) {
        super(message);
    }
}