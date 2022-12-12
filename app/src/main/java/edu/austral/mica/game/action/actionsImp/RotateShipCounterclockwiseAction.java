package edu.austral.mica.game.action.actionsImp;

import edu.austral.mica.application.ObservableGame;
import edu.austral.mica.game.action.Action;
import edu.austral.mica.game.game.Game;

public class RotateShipCounterclockwiseAction implements Action {
    private String shipId;
    public RotateShipCounterclockwiseAction(String shipId) {
        this.shipId = shipId;
    }

    @Override
    public Game runAction(ObservableGame observableGame) {
        return observableGame.getGame().rotateShip(-25, shipId);
    }
}
