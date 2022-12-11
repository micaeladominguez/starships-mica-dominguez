package edu.austral.mica.game.game;

import edu.austral.ingsis.starships.ui.Collision;
import edu.austral.mica.gameManage.asteroid.Asteroid;
import edu.austral.mica.gameManage.damage.Projectile;
import edu.austral.mica.gameManage.damage.Weapon;
import edu.austral.mica.gameManage.interfaces.Movable;
import edu.austral.mica.gameManage.ship.Ship;
import edu.austral.mica.gameManage.vector.Vector;
import edu.austral.mica.persistence.Constants;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game {
   private final Map<String, Movable> elements;

    public Game(Map<String, Movable> elements) {
        this.elements = elements;
    }


    public Game changePositions(int width, int height){
        Map<String, Movable> elements = new HashMap<>();
         this.elements.forEach((k,v) ->{
            if(checkIfAsteroid(k)){
                Asteroid asteroid = (Asteroid) v.moveForward();
                Asteroid asteroidWrapper = asteroid.screenWrap(width, height);
                elements.put(asteroidWrapper.getId(), asteroidWrapper);
            }else if(checkIfProjectile(k)){
                Movable movable = v.moveForward();
                elements.put(movable.getId(), movable);
            }else{
                Movable movable = v.moveForward();
                elements.put(movable.getId(), movable);
            }
         });
         return new Game(elements);
    }

    private boolean checkIfProjectile(String k) {
        if(k != null){
            if(k.length() > 4){
                return k.startsWith("proj");
            }
            return false;
        }
        return false;
    }

    public Game defineFirstMap(int width, int height){
        return getInitializeGame();
    }

    @NotNull
    private static Game getInitializeGame() {
        Map<String, Movable> elements = new HashMap<>();
        // elements.put("ast1", new Asteroid(new Vector(0,10), new Vector(100,0), 0.01F,5,"ast1" ));
        //  elements.put("ast2", new Asteroid(new Vector(0,100), new Vector(100,0), 0.01F,5,"ast2" ));
        elements.put("ast3", new Asteroid(new Vector(0,300), new Vector(100,0), 0.01F,5,"ast3" ));
        //  elements.put("ast4", new Asteroid(new Vector(0,700), new Vector(100,0), 0.01F,5,"ast4" ));
        elements.put("ship1", new Ship("ship1", new Vector(400,300), new Vector(0,-1), 1F, 0, Constants.defaultWeapon,5 ));
        /*elements.put("ship2", new Ship("ship2", new Vector(400,500), new Vector(1,0), 1F, true, new
                Weapon(4,1,100F),5 ));*/
        return new Game(elements);
    }

    private boolean checkIfAsteroid(String k) {
        if(k != null){
            if(k.length() > 3){
                return k.startsWith("ast");
            }
            return false;
        }
        return false;
    }

    public Map<String, Movable> getElements() {
        return elements;
    }

    public Game handleCollision(Collision collision) {
        System.out.println("Collision 1");
        Movable movable = elements.get(collision.getElement1Id());
        Movable movable1 = elements.get(collision.getElement2Id());
        if(movable1 != null && movable != null){
             Movable movable2= movable1.getCollider().handleCollisionWith(movable.getCollider());
             Movable movable3= movable.getCollider().handleCollisionWith(movable1.getCollider());
             elements.put(movable2.getId(), movable2);
             elements.put(movable3.getId(), movable3);

        }
        return checkAllLives();
    }

    private Game checkAllLives() {
        Map<String, Movable> elements = new HashMap<>();
        this.elements.forEach((k,v) ->{
            if(!v.isDead()){
                elements.put(k,v);
            }
        });
        return new Game(elements);
    }

    private Ship getShip(String id) {
        Movable movable =  elements.get(id);
        if(!checkIfAsteroid(movable.getId())) return (Ship) movable;
        else return null;
    }

    public Game handleShoot(String idShip) {
        Ship movable = getShip(idShip);
        if(movable != null){
            ArrayList<Projectile> projectiles = movable.shoot();
            for (Projectile projectile : projectiles) {
                elements.put(projectile.getId(), projectile);
            }
            return new Game(elements);
        }
        return this;
    }

    public Game accelerateShip(String shipId) {
        Ship movable = getShip(shipId);
        if(movable != null){
            Ship newShip = movable.changeAcceleration();
            elements.put(newShip.getId(), newShip);
        }
        return new Game(elements);
    }

    public Game stopShip(String shipId) {
        Ship movable = getShip(shipId);
        if(movable != null){
            Ship newShip = movable.stop();
            elements.put(newShip.getId(), newShip);
        }
        return new Game(elements);
    }

    public Game rotateShip(int i, String shipId) {
        Ship movable = getShip(shipId);
        if(movable != null){
            Ship newShip = movable.changeDirection(i);
            elements.put(newShip.getId(), newShip);
        }
        return new Game(elements);
    }

    public Game changeWeapon(String shipId) {
        Ship movable = getShip(shipId);
        if(movable != null){
            Ship newShip = movable.changeWeapon(decideWeapon(movable));
            elements.put(newShip.getId(), newShip);
        }
        return new Game(elements);
    }

    private Weapon decideWeapon(Ship movable) {
        if(movable.getWeapon().getType().equals(Constants.def)){
            return Constants.differentWeapon;
        }
        return Constants.defaultWeapon;
    }
}
