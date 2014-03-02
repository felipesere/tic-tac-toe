package de.fesere.tictactoe;

public class InvalidMoveException extends RuntimeException {
    private final Move move;

    public InvalidMoveException(Move move, String message) {
        super(message);
        this.move = move;
    }
}
