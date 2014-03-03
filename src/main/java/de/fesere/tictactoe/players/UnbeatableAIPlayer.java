package de.fesere.tictactoe.players;

import de.fesere.tictactoe.Board;
import de.fesere.tictactoe.Marker;
import de.fesere.tictactoe.Move;
import de.fesere.tictactoe.Player;

import java.util.List;
import java.util.TreeSet;

public class UnbeatableAIPlayer implements Player {

    private final Marker marker;

    public UnbeatableAIPlayer(Marker marker) {
        this.marker = marker;
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
            return scoredMoves.first().move;
        }
        return scoredMoves.last().move;
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
        if(isOpponent(marker)) {
            return -10 - iteration;
        }
        else{
            return 10 -iteration;
        }
    }

    private boolean isOpponent(Marker marker) {
        return marker != this.marker;
    }

    private int scoreOfOppenentsOptimalMove(Board updatedBoard, Marker opponent, int iteration) {


        int nextIteration = iteration+1;

        Move move = findOptimalMove(updatedBoard, opponent,nextIteration );

        return calculateScoreOfMove(move, updatedBoard, opponent, nextIteration);
    }


    private class ScoredMove implements Comparable<ScoredMove>{
        private final Move move;
        private final Integer score;

        public ScoredMove(Move move, int score) {
            this.move = move;
            this.score = score;
        }

        @Override
        public int compareTo(ScoredMove o) {
            if(score.compareTo(o.score) == 0) {
                return -1;
            }
            else {
               return score.compareTo(o.score);
            }
        }
    }
}
