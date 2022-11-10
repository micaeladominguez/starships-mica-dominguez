package main.game;

import main.asteroid.Asteroid;
import main.damage.Weapon;
import main.interfaces.Movable;
import main.ship.Ship;
import main.vector.Vector;

import javax.swing.text.Position;
import java.util.HashMap;
import java.util.Map;

public class Game {
    Map<String, Movable> elements;

    public void changePositions(int width, int height){
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
         this.elements = elements;
    }

    public void defineFirstMap(int width, int height){
        Map<String, Movable> elements = new HashMap<>();
        elements.put("ast1", new Asteroid(new Vector(0,1), new Vector(1,0), 0.5F,5,"ast1" ));
        elements.put("ast2", new Asteroid(new Vector(0,5), new Vector(1,0), 0.5F,5,"ast2" ));
        elements.put("ast3", new Asteroid(new Vector(0,9), new Vector(1,0), 0.5F,5,"ast3" ));
        elements.put("ast4", new Asteroid(new Vector(0,14), new Vector(1,0), 0.5F,5,"ast4" ));
        elements.put("ship1", new Ship("ship1", new Vector(6,6), new Vector(1,0), 0.5F, true, new
                Weapon(4,1,0.5F),5 ));
        elements.put("ship2", new Ship("ship2", new Vector(8,8), new Vector(1,0), 0.5F, true, new
                Weapon(4,1,0.5F),5 ));
    }

    private boolean checkIfAsteroid(String k) {
        if(k.length() > 3){
            return k.startsWith("ast");
        }
        return false;
    }
}
