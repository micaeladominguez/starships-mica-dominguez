package edu.austral.mica.game.action;

import edu.austral.mica.application.ObservableGame;
import edu.austral.mica.game.game.Game;

public class SaveGameStateAction implements Action {
    @Override
    public Game runAction(ObservableGame observableGame) {
        //TODO
        return observableGame.getGame();
    }
}
