package edu.austral.mica.game.game;

import edu.austral.ingsis.starships.ui.Collision;

import edu.austral.mica.gameManage.interfaces.Movable;
import edu.austral.mica.gameManage.ship.Ship;

import java.util.Map;

public interface Game {

    Game changePositions(int width, int height);
    Game createNewGame();
    Map<String, Movable> getElements();

    Game handleCollision(Collision collision);

    Game handleShoot(String idShip);

    Game accelerateShip(String shipId);

    Game stopShip(String shipId);

    Game rotateShip(int i, String shipId);

    Game changeWeapon(String shipId);

    Game addAsteroid();

    Map<String, Integer> getScoresForShip();
    Map<String, Integer> getLivesForShip();



    Game stopGame();
    Game unstopGame();

    Ship checkForFinishedGame();

    Game saveGame();
    Game loadGame();
}
