package main.starShip;

import main.interfaces.Movable;
import main.vector.Vector;

public class StarShip implements Movable<StarShip> {
    final Vector position;
    final Vector direction;
    final float speed;
    final boolean accelerating;

    public StarShip(Vector position, Vector direction,float speed, boolean accelerating) {
        this.position = position;
        this.direction = direction;
        this.speed = speed;
        this.accelerating = accelerating;
    }

    @Override
    public StarShip moveForward() {
        float acc = 0.5F;
        if(this.speed == 0 || ( !accelerating && ( this.speed - acc == 0 || this.speed - acc <= 0)))
            return new StarShip(position.add(direction.multiply(speed)), direction, speed, accelerating);
        if(accelerating){
            return new StarShip(position.add(direction.multiply(speed)), direction,
                    speed + acc, accelerating);
        }
        return new StarShip(position.add(direction.multiply(speed)), direction,
                speed - acc, accelerating);
    }

    @Override
    public StarShip moveBackward() {
        float acc = 0.5F;
        if(this.speed == 0 || ( !accelerating && ( this.speed - acc == 0 || this.speed - acc <= 0)))
            return new StarShip(position.subtract(direction.multiply(speed)), direction, speed, accelerating);
        if(accelerating){
            return new StarShip(position.subtract(direction.multiply(speed)), direction,
                    speed + acc, accelerating);
        }
        return new StarShip(position.subtract(direction.multiply(speed)), direction,
                speed - acc, accelerating);
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

    public StarShip changeAcceleration() {
        return new StarShip(position.subtract(direction.multiply(speed)), direction, speed, !accelerating);
    }
}
