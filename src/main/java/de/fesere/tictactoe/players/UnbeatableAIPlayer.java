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
        Move move = findOptimalMove(board, marker);

        return board.mark(move, marker);
    }

    private Move findOptimalMove(Board board, Marker marker) {
        List<Move> possibleMoves = board.getPossibleMoves();

        TreeSet<ScoredMove> scoredMoves = new TreeSet<>();

        for(Move move : possibleMoves ) {
            int score = calculateScoreOfMove(move, board, marker);
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

    private int calculateScoreOfMove(Move move, Board board, Marker marker) {
        Board updatedBoard = board.mark(move, marker);

        if(updatedBoard.hasWinner()) {
            return winnerScore(marker);
        }
        if(updatedBoard.hasDraw()) {
            return 0;
        }

        return scoreOfOppenentsOptimalMove(updatedBoard, marker.other());
    }

    private int winnerScore(Marker marker) {
        if(isOpponent(marker)) {
            return -10;
        }
        else{
            return 10;
        }
    }

    private boolean isOpponent(Marker marker) {
        return marker != this.marker;
    }

    private int scoreOfOppenentsOptimalMove(Board updatedBoard, Marker opponent) {

        Move move = findOptimalMove(updatedBoard, opponent);

        return calculateScoreOfMove(move, updatedBoard, opponent);
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
