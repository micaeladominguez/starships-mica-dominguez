package edu.austral.mica.application;

import edu.austral.ingsis.starships.ui.Collision;
import edu.austral.ingsis.starships.ui.EventListener;
import edu.austral.mica.game.Game;

public class CollisionListener  implements EventListener<Collision> {
    ObservableGame observableGame;

    public CollisionListener(ObservableGame observableGame) {
        this.observableGame = observableGame;
    }

    @Override
    public void handle(Collision collision) {
        if(observableGame != null){
            Game newGame = observableGame.getGame().handleCollision(collision);
            observableGame.setGame(newGame);
        }
    }
}
