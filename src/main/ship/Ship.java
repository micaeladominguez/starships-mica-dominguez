package main.ship;

import main.collision.ShipCollider;
import main.damage.Projectile;
import main.damage.Weapon;
import main.interfaces.Movable;
import main.vector.Vector;

import java.util.ArrayList;

public class Ship implements Movable<Ship> {
    final private String id;
    final private Vector position;
    final private Vector direction;
    final private float speed;
    final private boolean accelerating;
    final private Weapon weapon;

    public Ship(String id, Vector position, Vector direction, float speed, boolean accelerating, Weapon weapon) {
        this.id = id;
        this.position = position;
        this.direction = direction;
        this.speed = speed;
        this.accelerating = accelerating;
        this.weapon = weapon;
    }

    @Override
    public Ship moveForward() {
        float acc = 0.5F;
        if(this.speed == 0 || ( !accelerating && ( this.speed - acc == 0 || this.speed - acc <= 0)))
            return new Ship(id, position.add(direction.multiply(speed)), direction, speed, accelerating, weapon);
        if(accelerating){
            return new Ship(id, position.add(direction.multiply(speed)), direction,
                    speed + acc, accelerating, weapon);
        }
        return new Ship(id, position.add(direction.multiply(speed)), direction,
                speed - acc, accelerating, weapon);
    }

    @Override
    public Ship moveBackward() {
        float acc = 0.5F;
        if(this.speed == 0 || ( !accelerating && ( this.speed - acc == 0 || this.speed - acc <= 0)))
            return new Ship(id, position.subtract(direction.multiply(speed)), direction, speed, accelerating, weapon);
        if(accelerating){
            return new Ship(id, position.subtract(direction.multiply(speed)), direction,
                    speed + acc, accelerating, weapon);
        }
        return new Ship(id, position.subtract(direction.multiply(speed)), direction,
                speed - acc, accelerating, weapon);
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
    public Ship changeAcceleration() {
        return new Ship(id, position.subtract(direction.multiply(speed)), direction, speed, !accelerating, weapon);
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
        return new Ship(id, position, direction, speed, accelerating, weapon);
    }
    public ArrayList<Projectile> shoot(){
        return this.weapon.shoot(this.position, this.direction, this.getId());
    }
}
