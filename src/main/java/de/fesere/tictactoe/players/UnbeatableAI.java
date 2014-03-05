package de.fesere.tictactoe.players;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.Marker;
import de.fesere.tictactoe.Move;

import java.util.List;
import java.util.TreeSet;

public class UnbeatableAI extends  BasePlayer {

    public UnbeatableAI(Marker marker) {
        super(marker);
    }

    @Override
    public Board performMove(Board board) {
        Move move = findOptimalMove(board, marker, 0);

        return board.mark(move, marker);
    }

    private Move findOptimalMove(Board board, Marker marker, int iteration) {
        List<Move> possibleMoves = board.getPossibleMoves();

        TreeSet<ScoredMove> scoredMoves = new TreeSet<>();

        for(Move move : possibleMoves ) {
            int score = calculateScoreOfMove(move, board, marker, iteration);
            scoredMoves.add(new ScoredMove(move, score));
        }

        return chooseMoveBasedOnPlayer(scoredMoves, marker);

    }

    private Move chooseMoveBasedOnPlayer(TreeSet<ScoredMove> scoredMoves, Marker marker) {
        if(isOpponent(marker)) {
            return scoredMoves.first().getMove();
        }
        return scoredMoves.last().getMove();
    }

    private int calculateScoreOfMove(Move move, Board board, Marker marker, int iteration) {
        Board updatedBoard = board.mark(move, marker);

        if(updatedBoard.hasWinner()) {
            return winnerScore(marker, iteration);
        }
        if(updatedBoard.hasDraw()) {
            return 0;
        }

        return scoreOfOppenentsOptimalMove(updatedBoard, marker.other(), iteration);
    }

    private int winnerScore(Marker marker, int iteration) {
        int score;
        if(isOpponent(marker)) {
            score = -10;
        }
        else{
            score =  10;
        }

        return score - iteration;
    }

    private boolean isOpponent(Marker marker) {
        return marker != this.marker;
    }

    private int scoreOfOppenentsOptimalMove(Board updatedBoard, Marker opponent, int iteration) {
        int nextIteration = iteration+1;

        Move move = findOptimalMove(updatedBoard, opponent, nextIteration);

        return calculateScoreOfMove(move, updatedBoard, opponent, nextIteration);
    }
}
