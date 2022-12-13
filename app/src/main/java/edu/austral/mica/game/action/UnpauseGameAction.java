package edu.austral.mica.game.action;

import edu.austral.mica.application.ObservableGame;
import edu.austral.mica.game.game.Game;

public class UnpauseGameAction implements Action {
    @Override
    public Game runAction(ObservableGame observableGame) {
        return observableGame.getGame().unstopGame();
    }
}
