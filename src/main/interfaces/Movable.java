package main.interfaces;

import main.vector.Vector;

public interface Movable<T extends Movable<T>> {
     T moveForward();
     T moveBackward();

     Vector getPosition();
     Vector getDirection();
     float getSpeed();
     T decreaseLives(int lives);
     String getId();

}
