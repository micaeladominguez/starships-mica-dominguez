package edu.austral.mica.game.game;

import edu.austral.ingsis.starships.ui.Collision;
import edu.austral.mica.game.game.generators.AsteroidGenerator;
import edu.austral.mica.game.game.generators.GameInitializer;
import edu.austral.mica.game.game.generators.LoaderGame;
import edu.austral.mica.game.game.managers.ScoreManager;
import edu.austral.mica.game.game.managers.WeaponManager;
import edu.austral.mica.gameManage.asteroid.Asteroid;
import edu.austral.mica.gameManage.damage.Projectile;
import edu.austral.mica.gameManage.interfaces.Movable;
import edu.austral.mica.gameManage.ship.Ship;
import edu.austral.mica.gameManage.ship.ShipScore;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LiveGame implements Game{
   private final Map<String, Movable> elements;
   private final Map<String,Integer> scoresForShip;
   private final Map<String,Integer> livesForShip;


    public LiveGame(Map<String, Movable> elements, int q_ships) {
        this.elements = elements;
        this.scoresForShip = new HashMap<>();
        this.livesForShip = new HashMap<>();

    }
    public LiveGame(Map<String, Movable> elements, Map<String, Integer> scores, Map<String,Integer> livesForShip) {
        this.elements = elements;
        this.scoresForShip = scores;
        this.livesForShip = livesForShip;
    }


    public LiveGame changePositions(int width, int height){
        Map<String, Movable> elements = new HashMap<>();
         this.elements.forEach((k,v) ->{
            if(CheckType.checkIfAsteroid(k)){
                moveAsteroid(width, height, elements, v);
            }else if(CheckType.checkIfProjectile(k)){
                moveProjectile(elements, v);
            }else if(CheckType.checkIfShip(k)){
                moveShip(elements, v,  width, height);
                checkAllLives(this.scoresForShip);

            }
         });
         return new LiveGame(elements, scoresForShip, livesForShip);
    }

    private static void moveShip(Map<String, Movable> elements, Movable v, int width, int height) {
        Ship ship = (Ship) v.moveForward();
        Ship shipWrapper = ship.screenWrap(width, height);
        elements.put(shipWrapper.getId(), shipWrapper);
    }

    private static void moveProjectile(Map<String, Movable> elements, Movable v) {
        Movable movable = v.moveForward();
        elements.put(movable.getId(), movable);
    }

    private static void moveAsteroid(int width, int height, Map<String, Movable> elements, Movable v) {
        Asteroid asteroid = (Asteroid) v.moveForward();
        Asteroid asteroidWrapper = asteroid.screenWrap(width, height);
        elements.put(asteroidWrapper.getId(), asteroidWrapper);
    }


    public Game createNewGame(){
        return getInitializeGame();
    }

    @NotNull
    private Game getInitializeGame() {
        Map<String, Movable> definedElements =  GameInitializer.initialize();
        return defineScoresAndLives(definedElements);
    }

    private Game defineScoresAndLives(Map<String, Movable> definedElements) {
        HashMap<String, Integer> scoresForShip = new HashMap<>();
        HashMap<String, Integer> livesForShip = new HashMap<>();
        definedElements.forEach((k,v) ->{
            if(v instanceof Ship){
                scoresForShip.put(k,0);
                livesForShip.put(k,v.getLives());
            }
        });
        return new LiveGame(definedElements, scoresForShip, livesForShip);
    }


    public Map<String, Movable> getElements() {
        return elements;
    }

    public LiveGame handleCollision(Collision collision) {
        Movable movable = elements.get(collision.getElement1Id());
        Movable movable1 = elements.get(collision.getElement2Id());
        Map<String,Integer> possibleScores = ScoreManager.checkScoreForCollision(scoresForShip, movable, movable1);
        if(movable1 != null && movable != null){
             Movable movable2= movable1.getCollider().handleCollisionWith(movable.getCollider());
             Movable movable3= movable.getCollider().handleCollisionWith(movable1.getCollider());
             addMovables(movable2, movable3);
        }
        return checkAllLives(possibleScores);
    }

    private void addMovables(Movable movable2, Movable movable3) {
        elements.put(movable2.getId(), movable2);
        elements.put(movable3.getId(), movable3);
    }

    private LiveGame checkAllLives(Map<String,Integer> possibleScores) {
        Map<String, Movable> elements = new HashMap<>();
        this.elements.forEach((k,v) ->{
            if(!v.isDead()){
                elements.put(k,v);
                if(v instanceof Ship)
                    livesForShip.put(k, v.getLives());
            }
        });
        return new LiveGame(elements, possibleScores, livesForShip);
    }


    private Ship getShip(String id) {
        Movable movable =  elements.get(id);
        if(movable != null && !CheckType.checkIfAsteroid(movable.getId())) return (Ship) movable;
        else return null;
    }

    public LiveGame handleShoot(String idShip) {
        Ship movable = getShip(idShip);
        if(movable != null){
            ArrayList<Projectile> projectiles = movable.shoot();
            for (Projectile projectile : projectiles) {
                elements.put(projectile.getId(), projectile);
            }
            return new LiveGame(elements,scoresForShip, livesForShip);
        }
        return this;
    }

    public LiveGame accelerateShip(String shipId) {
        Ship movable = getShip(shipId);
        if(movable != null){
            Ship newShip = movable.changeAcceleration();
            elements.put(newShip.getId(), newShip);
        }
        return new LiveGame(elements,scoresForShip, livesForShip);
    }

    public LiveGame stopShip(String shipId) {
        Ship movable = getShip(shipId);
        if(movable != null){
            Ship newShip = movable.stop();
            elements.put(newShip.getId(), newShip);
        }
        return new LiveGame(elements,scoresForShip, livesForShip);
    }

    public LiveGame rotateShip(int i, String shipId) {
        Ship movable = getShip(shipId);
        if(movable != null){
            Ship newShip = movable.changeDirection(i);
            elements.put(newShip.getId(), newShip);
        }
        return new LiveGame(elements,scoresForShip, livesForShip);
    }

    public LiveGame changeWeapon(String shipId) {
        Ship movable = getShip(shipId);
        if(movable != null){
            Ship newShip = movable.changeWeapon(WeaponManager.decideWeapon(movable));
            elements.put(newShip.getId(), newShip);
        }
        return new LiveGame(elements,scoresForShip, livesForShip);
    }


    public LiveGame addAsteroid() {
        Asteroid asteroid = AsteroidGenerator.createAsteroid();
        elements.put(asteroid.getId(), asteroid);
        return new LiveGame(elements,scoresForShip, livesForShip);
    }

    public Map<String, Integer> getScoresForShip() {
        return scoresForShip;
    }

    @Override
    public Map<String, Integer> getLivesForShip() {
        return this.livesForShip;
    }

    @Override
    public Game stopGame() {
        return new StopGame(elements, scoresForShip, livesForShip);
    }

    @Override
    public Game unstopGame() {
        return this;
    }

    @Override
    public ShipScore checkForFinishedGame() {
        ArrayList<Ship> ships = new ArrayList<>();
        this.elements.forEach((k,v) ->{
            if(v instanceof Ship && v.getLives() > 0){
                ships.add((Ship) v);
            }
        });
        if(ships.size() == 1)
            return new ShipScore(ships.get(0), scoresForShip.get(ships.get(0).getId()));

        return null;
    }

    @Override
    public Game saveGame() {
        return this;
    }

    @Override
    public Game loadGame() {
        return LoaderGame.loadGame();
    }
}
