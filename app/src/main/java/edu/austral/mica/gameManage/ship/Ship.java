package edu.austral.mica.gameManage.ship;


import edu.austral.mica.gameManage.collision.ShipCollider;
import edu.austral.mica.gameManage.damage.Projectile;
import edu.austral.mica.gameManage.damage.Weapon;
import edu.austral.mica.gameManage.vector.Vector;
import edu.austral.mica.gameManage.interfaces.Movable;

import java.util.ArrayList;

public class Ship implements Movable<Ship> {
    final private String id;
    final private Vector position;
    final private Vector direction;
    final private float speed;
    final private Weapon weapon;
    final private int lives;
    final private float acceleration;
    public Ship(String id, Vector position, Vector direction, float speed, float acceleration, Weapon weapon, int lives) {
        this.id = id;
        this.position = position;
        this.direction = direction;
        this.speed = speed;
        this.acceleration = acceleration;
        this.weapon = weapon;
        this.lives = lives;
    }

    @Override
    public Ship moveForward() {
        float newSpeed = changeSpeed();
        if(acceleration != 0F){
            return new Ship(id, position.add(direction.multiply(speed)), direction,
                    newSpeed, decreaseAcceleration(), weapon, lives);
        }else{
            return this;
        }
    }

    private float decreaseAcceleration() {
        if(acceleration >= 0.05F){
            return acceleration - 0.005F;
        }else{
            return 0F;
        }
    }

    private float changeSpeed() {
        if(acceleration != 0){
            return speed + (0.5F*acceleration);
        }
        return speed;
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
        return new Ship(id, position, direction, speed, acceleration, weapon, this.lives - lives);
    }

    public Ship changeAcceleration() {
        return new Ship(id, position.subtract(direction.multiply(speed)), direction, speed, acceleration + 0.05F, weapon, lives);
    }
    public Ship stop() {
        return new Ship(id, position, direction, 0F, 0F, weapon, lives);
    }
    public String getId() {
        return id;
    }
    public ShipCollider getCollider(){
        return new ShipCollider(this);
    }

    @Override
    public boolean isDead() {
        return lives <= 0;
    }

    @Override
    public Ship dead() {
        return new Ship(id, position, direction, speed, acceleration, weapon, 0);
    }

    public Ship changeWeapon(Weapon weapon){
        return new Ship(id, position, direction, speed, acceleration, weapon, lives);
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
                ", acceleration=" + acceleration +
                ", weapon=" + weapon +
                ", lives=" + lives +
                '}';
    }

    public Ship changeDirection(int grades) {
        return new Ship(id,position, direction.changeAngle(grades), speed, acceleration, weapon, lives);
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
