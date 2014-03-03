package de.fesere.tictactoe;

import de.fesere.tictactoe.board.ArrayBoard;
import de.fesere.tictactoe.players.HumanPlayer;
import de.fesere.tictactoe.players.UnbeatableAIPlayer;
import de.fesere.tictactoe.ui.ConsoleUI;

import static de.fesere.tictactoe.Marker.O;
import static de.fesere.tictactoe.Marker.X;

public class Game {

    public static void main(String [] args) {

        UI userInterface = new ConsoleUI();
        Board board = new ArrayBoard();
        Player[] players = {new HumanPlayer(X, userInterface), new UnbeatableAIPlayer(O) };
       // Player[] players = {new UnbeatableAIPlayer(X), new UnbeatableAIPlayer(O) };


        Player player= null;
        int round = 0;
        while(!isFinished(board)) {
            player = players[round % 2];
            board = player.performMove(board);
            round++;
        }

        if(board.hasWinner()) {
            userInterface.showWinner(player);
        }
        else {
            System.out.println("It was a draw...");
        }
        userInterface.displayBoard(board);
    }

    private static boolean isFinished(Board board) {
        return board.hasWinner() || board.hasDraw();
    }


}
