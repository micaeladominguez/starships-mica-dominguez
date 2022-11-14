package edu.austral.mica.ship;


import edu.austral.mica.collision.ShipCollider;
import edu.austral.mica.damage.Projectile;
import edu.austral.mica.damage.Weapon;
import edu.austral.mica.interfaces.Movable;
import edu.austral.mica.vector.Vector;

import java.util.ArrayList;

public class Ship implements Movable<Ship> {
    final private String id;
    final private Vector position;
    final private Vector direction;
    final private float speed;
    final private boolean accelerating;
    final private Weapon weapon;
    final private int lives;
    public Ship(String id, Vector position, Vector direction, float speed, boolean accelerating, Weapon weapon, int lives) {
        this.id = id;
        this.position = position;
        this.direction = direction;
        this.speed = speed;
        this.accelerating = accelerating;
        this.weapon = weapon;
        this.lives = lives;
    }

    @Override
    public Ship moveForward() {
        float acc = 0.0F;
        if(this.speed == 0 || ( !accelerating && ( this.speed - acc == 0 || this.speed - acc <= 0)))
            return new Ship(id, position.add(direction.multiply(speed)), direction, speed, accelerating, weapon, lives);
        if(accelerating){
            return new Ship(id, position.add(direction.multiply(speed)), direction,
                    speed + acc, accelerating, weapon, lives);
        }
        return new Ship(id, position.add(direction.multiply(speed)), direction,
                speed - acc, accelerating, weapon, lives);
    }

    @Override
    public Ship moveBackward() {
        float acc = 0.5F;
        if(this.speed == 0 || ( !accelerating && ( this.speed - acc == 0 || this.speed - acc <= 0)))
            return new Ship(id, position.subtract(direction.multiply(speed)), direction, speed, accelerating, weapon, lives);
        if(accelerating){
            return new Ship(id, position.subtract(direction.multiply(speed)), direction,
                    speed + acc, accelerating, weapon, lives);
        }
        return new Ship(id, position.subtract(direction.multiply(speed)), direction,
                speed - acc, accelerating, weapon, lives);
    }
    @Override
    public Vector getPosition() {
        return this.position;
    }
    @Override
    public Vector getDirection() {
        return this.direction;
    }
    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public Ship decreaseLives(int lives) {
        return new Ship(id, position, direction, speed, accelerating, weapon, this.lives - lives);
    }

    public Ship changeAcceleration() {
        return new Ship(id, position.subtract(direction.multiply(speed)), direction, speed, !accelerating, weapon, lives);
    }
    public String getId() {
        return id;
    }
    public ShipCollider getCollider(){
        return new ShipCollider(this);
    }
    public boolean isAccelerating() {
        return accelerating;
    }
    public Ship changeWeapon(Weapon weapon){
        return new Ship(id, position, direction, speed, accelerating, weapon, lives);
    }
    public ArrayList<Projectile> shoot(){
        return this.weapon.shoot(this.position, this.direction, this.getId());
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id='" + id + '\'' +
                ", position=" + position +
                ", direction=" + direction +
                ", speed=" + speed +
                ", accelerating=" + accelerating +
                ", weapon=" + weapon +
                ", lives=" + lives +
                '}';
    }
}
