package main.asteroide;

import main.interfaces.Movable;
import main.interfaces.ScreenWrapper;
import main.vector.Vector;

public class Asteroid implements Movable<Asteroid>, ScreenWrapper<Asteroid> {
    private final Vector position;
    private final Vector direction;
    private final float speed;

    public Asteroid(Vector position, Vector direction, float speed) {
        this.position = position;
        this.direction = direction;
        this.speed = speed;
    }

    @Override
    public Asteroid moveForward() {
        return new Asteroid(getPosition().add(getDirection().multiply(speed)), getDirection(),getSpeed());
    }

    @Override
    public Asteroid moveBackward() {
        return new Asteroid(getPosition().subtract(getDirection().multiply(speed)), getDirection(),getSpeed());
    }
    @Override
    public Vector getPosition() { return position; }
    @Override
    public Vector getDirection() { return direction; }

    @Override
    public Asteroid screenWrap(int width, int height){
        Vector current = position;
        if( current.getX()>width){
            return new Asteroid(new Vector(0,current.getY()),direction,speed);
        }
        else if(current.getX()<0){
            return new Asteroid(new Vector(width,current.getY()),direction,speed);
        }
        else if(current.getY()>height){
            return new Asteroid(new Vector(current.getX(),0),direction,speed);
        }
        else if(current.getY()<0){
            return new Asteroid(new Vector(current.getX(),height),direction,speed);
        }
        return this;
    }
    @Override
    public float getSpeed() {
        return this.speed;
    }


}
