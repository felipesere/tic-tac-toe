package de.fesere.tictactoe.players;

import de.fesere.tictactoe.Move;

class ScoredMove implements Comparable<ScoredMove>{
    private final Move move;
    private final Integer score;

    public ScoredMove(Move move, int score) {
        this.move = move;
        this.score = score;
    }

    @Override
    public int compareTo( ScoredMove o) {
        if(score.compareTo(o.score) == 0) {
            return -1;
        }
        else {
            return score.compareTo(o.score);
        }
    }

    public Move getMove() {
        return move;
    }
}
