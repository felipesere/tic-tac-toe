package de.fesere.tictactoe;

import de.fesere.tictactoe.board.ArrayBoard;
import de.fesere.tictactoe.players.HumanPlayer;
import de.fesere.tictactoe.players.RandomAIPlayer;
import de.fesere.tictactoe.ui.ConsoleUI;

import static de.fesere.tictactoe.Marker.O;
import static de.fesere.tictactoe.Marker.X;

public class Game {

    public static void main(String [] args) {

        UI userInterface = new ConsoleUI();
        Board board = new ArrayBoard();
        Player[] players = {new HumanPlayer(X, userInterface), new RandomAIPlayer(O) };


        Player player= null;
        int round = 0;
        while(!board.hasWinner()) {
            player = players[round % 2];
            board = player.performMove(board);
            round++;
        }
        userInterface.showWinner(player);
        userInterface.displayBoard(board);
    }


}
