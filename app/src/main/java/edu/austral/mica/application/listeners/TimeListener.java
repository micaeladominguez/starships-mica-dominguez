package edu.austral.mica.application.listeners;

import edu.austral.ingsis.starships.ui.EventListener;
import edu.austral.ingsis.starships.ui.TimePassed;
import edu.austral.mica.application.ObservableGame;
import edu.austral.mica.game.game.Game;
import edu.austral.mica.persistence.Constants;

public class TimeListener implements EventListener<TimePassed> {
    ObservableGame observableGame;
    int width;
    int height;


    public TimeListener(ObservableGame game, int width, int height) {
        this.observableGame = game;
        this.width = width;
        this.height = height;
    }

    @Override
    public void handle(TimePassed timePassed) {
        if(observableGame.getGame() != null){
            if(possibleNewAsteroid()){
                Game game = observableGame.getGame().addAsteroid();
                game.changePositions(width, height);
            }
            Game new_game = observableGame.getGame().changePositions(width, height);
            observableGame.setGame(new_game);
        }
    }

    private boolean possibleNewAsteroid() {
        System.out.println(Math.random() +  "   NUMBER");
        System.out.println(Math.random() <= Constants.ASTEROID_NEW_RATE);
        return Math.random() <= Constants.ASTEROID_NEW_RATE;
    }
}
