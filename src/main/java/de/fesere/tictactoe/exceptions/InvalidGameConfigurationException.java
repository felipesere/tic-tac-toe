package de.fesere.tictactoe.exceptions;

public class InvalidGameConfigurationException extends RuntimeException {

    public InvalidGameConfigurationException(String message) {
        super(message);
    }
}
