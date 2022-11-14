package edu.austral.mica.application;

import edu.austral.mica.game.Game;

public interface GameObserver {
    void gameChanged(Game game);
}
