package edu.austral.mica.game.action;

import edu.austral.mica.application.ObservableGame;
import edu.austral.mica.game.game.Game;

public class StopShipAction implements Action {
    private String shipId;
    public StopShipAction(String shipId) {
       this.shipId = shipId;
    }

    @Override
    public Game runAction(ObservableGame observableGame) {
        return observableGame.getGame().stopShip(shipId);
    }
}
