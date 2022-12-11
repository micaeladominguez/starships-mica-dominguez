package edu.austral.mica.game.action;

import edu.austral.mica.application.ObservableGame;
import edu.austral.mica.game.game.Game;

public class AccelerateShipAction implements Action {
    private String shipId;
    public AccelerateShipAction(String shipId) {
        this.shipId = shipId;
    }

    @Override
    public Game runAction(ObservableGame observableGame) {
        return observableGame.getGame().accelerateShip(shipId);
    }
}
