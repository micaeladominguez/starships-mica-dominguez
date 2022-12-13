package edu.austral.mica.game.game;

import edu.austral.ingsis.starships.ui.Collision;
import edu.austral.mica.game.game.generators.AsteroidGenerator;
import edu.austral.mica.game.game.generators.GameInitializer;
import edu.austral.mica.game.game.managers.ScoreManager;
import edu.austral.mica.game.game.managers.WeaponManager;
import edu.austral.mica.gameManage.asteroid.Asteroid;
import edu.austral.mica.gameManage.damage.Projectile;
import edu.austral.mica.gameManage.interfaces.Movable;
import edu.austral.mica.gameManage.ship.Ship;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LiveGame implements Game{
   private final Map<String, Movable> elements;
   private final ArrayList<Integer> scoresForShip;

    public LiveGame(Map<String, Movable> elements, int q_ships) {
        this.elements = elements;
        this.scoresForShip = new ArrayList<>();
        generateFirstScores(q_ships);
    }
    public LiveGame(Map<String, Movable> elements, ArrayList<Integer> scores) {
        this.elements = elements;
        this.scoresForShip = scores;
    }

    private void generateFirstScores(int qShips) {
        for (int i = 0; i < qShips; i++) {
            scoresForShip.add(0);
        }
    }


    public LiveGame changePositions(int width, int height){
        Map<String, Movable> elements = new HashMap<>();
         this.elements.forEach((k,v) ->{
            if(CheckType.checkIfAsteroid(k)){
                moveAsteroid(width, height, elements, v);
            }else if(CheckType.checkIfProjectile(k)){
                moveProjectile(elements, v);
            }else if(CheckType.checkIfShip(k)){
                moveShip(elements, v);
            }
         });
         return new LiveGame(elements,scoresForShip);
    }

    private static void moveShip(Map<String, Movable> elements, Movable v) {
        Movable movable = v.moveForward();
        elements.put(movable.getId(), movable);
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


    public LiveGame createNewGame(){
        return getInitializeGame();
    }

    @NotNull
    private LiveGame getInitializeGame() {
        Map<String, Movable> defined_elements =  GameInitializer.initialize();
        return new LiveGame(defined_elements,scoresForShip);
    }


    public Map<String, Movable> getElements() {
        return elements;
    }

    public LiveGame handleCollision(Collision collision) {
        Movable movable = elements.get(collision.getElement1Id());
        Movable movable1 = elements.get(collision.getElement2Id());
        ArrayList<Integer> possible_scores = ScoreManager.checkScoreForCollision(scoresForShip, movable, movable1);
        if(movable1 != null && movable != null){
             Movable movable2= movable1.getCollider().handleCollisionWith(movable.getCollider());
             Movable movable3= movable.getCollider().handleCollisionWith(movable1.getCollider());
             elements.put(movable2.getId(), movable2);
             elements.put(movable3.getId(), movable3);
        }
        return checkAllLives(possible_scores);
    }

    private LiveGame checkAllLives(ArrayList<Integer> possible_scores) {
        Map<String, Movable> elements = new HashMap<>();
        this.elements.forEach((k,v) ->{
            if(!v.isDead()){
                elements.put(k,v);
            }
        });
        return new LiveGame(elements, possible_scores);
    }

    private Ship getShip(String id) {
        Movable movable =  elements.get(id);
        if(!CheckType.checkIfAsteroid(movable.getId())) return (Ship) movable;
        else return null;
    }

    public LiveGame handleShoot(String idShip) {
        Ship movable = getShip(idShip);
        if(movable != null){
            ArrayList<Projectile> projectiles = movable.shoot();
            for (Projectile projectile : projectiles) {
                elements.put(projectile.getId(), projectile);
            }
            return new LiveGame(elements,scoresForShip);
        }
        return this;
    }

    public LiveGame accelerateShip(String shipId) {
        Ship movable = getShip(shipId);
        if(movable != null){
            Ship newShip = movable.changeAcceleration();
            elements.put(newShip.getId(), newShip);
        }
        return new LiveGame(elements,scoresForShip);
    }

    public LiveGame stopShip(String shipId) {
        Ship movable = getShip(shipId);
        if(movable != null){
            Ship newShip = movable.stop();
            elements.put(newShip.getId(), newShip);
        }
        return new LiveGame(elements,scoresForShip);
    }

    public LiveGame rotateShip(int i, String shipId) {
        Ship movable = getShip(shipId);
        if(movable != null){
            Ship newShip = movable.changeDirection(i);
            elements.put(newShip.getId(), newShip);
        }
        return new LiveGame(elements,scoresForShip);
    }

    public LiveGame changeWeapon(String shipId) {
        Ship movable = getShip(shipId);
        if(movable != null){
            Ship newShip = movable.changeWeapon(WeaponManager.decideWeapon(movable));
            elements.put(newShip.getId(), newShip);
        }
        return new LiveGame(elements,scoresForShip);
    }


    public LiveGame addAsteroid() {
        Asteroid asteroid = AsteroidGenerator.createAsteroid();
        elements.put(asteroid.getId(), asteroid);
        return new LiveGame(elements,scoresForShip);
    }

    public ArrayList<Integer> getScoresForShip() {
        return scoresForShip;
    }

    @Override
    public Game stopGame() {
        return new StopGame(elements, scoresForShip);
    }

    @Override
    public Game unstopGame() {
        return this;
    }
}
