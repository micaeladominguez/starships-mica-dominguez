package edu.austral.mica.gameManage.interfaces;


import edu.austral.mica.gameManage.collision.GameCollider;
import edu.austral.mica.gameManage.vector.Vector;

public interface Movable<T extends Movable<T>> {
     T moveForward();
     Vector getPosition();
     Vector getDirection();
     float getSpeed();
     T decreaseLives(int lives);
     String getId();
     GameCollider getCollider();
     boolean isDead();
     T dead();
}
