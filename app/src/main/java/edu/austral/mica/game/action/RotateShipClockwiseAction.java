package edu.austral.mica.game.action;

import edu.austral.mica.application.ObservableGame;
import edu.austral.mica.game.game.Game;

public class RotateShipClockwiseAction implements Action {
    private String shipId;
    public RotateShipClockwiseAction(String shipId) {
        this.shipId = shipId;
    }

    @Override
    public Game runAction(ObservableGame observableGame) {
        return observableGame.getGame().rotateShip(90, shipId);
    }
}
