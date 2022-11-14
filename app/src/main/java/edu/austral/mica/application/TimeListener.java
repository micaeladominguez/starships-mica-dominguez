package edu.austral.mica.application;

import edu.austral.ingsis.starships.ui.EventListener;
import edu.austral.ingsis.starships.ui.TimePassed;
import edu.austral.mica.game.Game;
import edu.austral.mica.interfaces.Movable;

import java.util.Map;

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
            Game new_game = observableGame.game.changePositions(width, height);
            observableGame.setGame(new_game);
        }
    }
}
