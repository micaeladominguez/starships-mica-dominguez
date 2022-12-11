package edu.austral.mica.application;

import edu.austral.mica.game.game.Game;

public interface GameObserver {
    void gameChanged(Game game);
}
