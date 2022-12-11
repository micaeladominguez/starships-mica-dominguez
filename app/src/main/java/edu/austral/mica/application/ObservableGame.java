package edu.austral.mica.application;

import edu.austral.mica.game.game.Game;

import java.util.ArrayList;
import java.util.HashMap;

public class ObservableGame {
    ArrayList<GameObserver> observers = new ArrayList<>();

    Game game = new Game(new HashMap<>());

    public void observe(GameObserver observer){
        observers.add(observer);
    }
    public Game getGame(){
        return game;
    }
    public void setGame(Game game){
        this.game = game;
        observers.stream().forEach((observer) -> {
            observer.gameChanged(game);
        });
    }
}
