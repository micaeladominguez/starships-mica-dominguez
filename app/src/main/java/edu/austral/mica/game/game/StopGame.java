package edu.austral.mica.game.game;

import edu.austral.ingsis.starships.ui.Collision;
import edu.austral.mica.gameManage.interfaces.Movable;
import edu.austral.mica.gameManage.ship.Ship;

import java.util.Map;

public class StopGame implements Game{
    private final Map<String, Movable> elements;
    private final Map<String, Integer> scoresForShip;
    private final Map<String,Integer> livesForShip;
    public StopGame(Map<String, Movable> elements, Map<String, Integer> scoresForShip, Map<String,Integer> livesForShip) {
        this.elements = elements;
        this.scoresForShip = scoresForShip;
        this.livesForShip = livesForShip;
    }


    @Override
    public Game changePositions(int width, int height) {
        return this;
    }

    @Override
    public Game createNewGame() {
        return this;
    }

    @Override
    public Map<String, Movable> getElements() {
        return this.elements;
    }

    @Override
    public Game handleCollision(Collision collision) {
        return this;
    }

    @Override
    public Game handleShoot(String idShip) {
        return this;
    }

    @Override
    public Game accelerateShip(String shipId) {
        return this;
    }

    @Override
    public Game stopShip(String shipId) {
        return this;
    }

    @Override
    public Game rotateShip(int i, String shipId) {
        return this;
    }

    @Override
    public Game changeWeapon(String shipId) {
        return this;
    }

    @Override
    public Game addAsteroid() {
        return this;
    }

    @Override
    public Map<String, Integer> getScoresForShip() {
        return this.scoresForShip;
    }

    @Override
    public Map<String, Integer> getLivesForShip() {
        return this.livesForShip;
    }

    @Override
    public Game stopGame() {
        return this;
    }

    @Override
    public Game unstopGame() {
        return new LiveGame(elements, scoresForShip,livesForShip);
    }

    @Override
    public Ship checkForFinishedGame() {
        return null;
    }

    @Override
    public Game saveGame() {
        SaveGame.saveGame(elements,scoresForShip);
        return this;
    }
}
