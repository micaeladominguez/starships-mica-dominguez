package edu.austral.mica.game.action;

import edu.austral.mica.application.ObservableGame;
import edu.austral.mica.game.game.Game;

public interface Action {
     Game runAction(ObservableGame observableGame);
}
