package edu.austral.mica.game.action;

import edu.austral.mica.application.ObservableGame;
import edu.austral.mica.game.game.Game;

public class PauseGameAction implements Action {

    @Override
    public Game runAction(ObservableGame observableGame) {
        return observableGame.getGame();
    }
}
