package fr.epsi.civicscope.exception;

public class ElectionInvalideException extends RuntimeException {
    public ElectionInvalideException(String message) {
        super(message);
    }
}