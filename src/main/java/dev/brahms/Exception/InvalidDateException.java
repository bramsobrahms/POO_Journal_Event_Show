package dev.brahms.Exception;

public class InvalidDateException extends RuntimeException {

    public InvalidDateException() {
        super();
    }

    public InvalidDateException(String message) {
        super(message);
    }

}
