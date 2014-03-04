package de.fesere.tictactoe.players;

import de.fesere.tictactoe.Marker;
import de.fesere.tictactoe.Player;

public abstract class BasePlayer implements Player {

    final Marker marker;

    public BasePlayer(Marker marker) {
        this.marker = marker;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public final Marker getMarker() {
        return marker;
    }
}
