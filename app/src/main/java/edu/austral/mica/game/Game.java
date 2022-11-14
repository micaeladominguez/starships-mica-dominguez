package edu.austral.mica.game;

import edu.austral.ingsis.starships.ui.Collision;
import edu.austral.mica.asteroid.Asteroid;
import edu.austral.mica.damage.Weapon;
import edu.austral.mica.interfaces.Movable;
import edu.austral.mica.ship.Ship;
import edu.austral.mica.vector.Vector;

import java.util.HashMap;
import java.util.Map;

public class Game {
   private final Map<String, Movable> elements;

    public Game(Map<String, Movable> elements) {
        this.elements = elements;
    }


    public Game changePositions(int width, int height){
        System.out.println("Entre a change Positions ");
        Map<String, Movable> elements = new HashMap<>();
         this.elements.forEach((k,v) ->{
            if(checkIfAsteroid(k)){
                Asteroid asteroid = (Asteroid) v.moveForward();
                Asteroid asteroidWrapper = asteroid.screenWrap(width, height);
                elements.put(asteroidWrapper.getId(), asteroidWrapper);
            }else{
                Movable movable = v.moveForward();
                elements.put(movable.getId(), movable);
            }
         });
        elements.forEach((k,v) ->{
            System.out.println("V  : " + v.toString());
        });

         return new Game(elements);
    }
    public Game defineFirstMap(int width, int height){
        Map<String, Movable> elements = new HashMap<>();
        //elements.put("ast1", new Asteroid(new Vector(0,10), new Vector(100,0), 100F,5,"ast1" ));
        //elements.put("ast2", new Asteroid(new Vector(0,100), new Vector(100,0), 100F,5,"ast2" ));
        //elements.put("ast3", new Asteroid(new Vector(0,300), new Vector(100,0), 100F,5,"ast3" ));
        //elements.put("ast4", new Asteroid(new Vector(0,700), new Vector(100,0), 100F,5,"ast4" ));
        elements.put("ship1", new Ship("ship1", new Vector(400,300), new Vector(1,0), 1F, true, new
               Weapon(4,1,0.5F),5 ));
        elements.put("ship2", new Ship("ship2", new Vector(400,500), new Vector(1,0), 1F, true, new
                Weapon(4,1,100F),5 ));
        return new Game(elements);
    }

    private boolean checkIfAsteroid(String k) {
        if(k.length() > 3){
            return k.startsWith("ast");
        }
        return false;
    }

    public Map<String, Movable> getElements() {
        return elements;
    }

    public Game handleCollision(Collision collision) {
        return this;
    }
}
