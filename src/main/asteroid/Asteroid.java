package main.asteroid;

import main.collision.AsteroidCollider;
import main.interfaces.Movable;
import main.interfaces.ScreenWrapper;
import main.vector.Vector;

public class Asteroid implements Movable<Asteroid>, ScreenWrapper<Asteroid> {
    private final Vector position;
    private final Vector direction;
    private final float speed;
    private final int lives;

    public Asteroid(Vector position, Vector direction, float speed, int lives) {
        this.position = position;
        this.direction = direction;
        this.speed = speed;
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }

    @Override
    public Asteroid moveForward() {
        return new Asteroid(getPosition().add(getDirection().multiply(speed)), getDirection(),getSpeed(), getLives());
    }

    @Override
    public Asteroid moveBackward() {
        return new Asteroid(getPosition().subtract(getDirection().multiply(speed)), getDirection(),getSpeed(), getLives());
    }
    @Override
    public Vector getPosition() { return position; }
    @Override
    public Vector getDirection() { return direction; }

    @Override
    public Asteroid screenWrap(int width, int height){
        Vector current = position;
        if( current.getX()>width){
            return new Asteroid(new Vector(0,current.getY()),direction,speed, lives);
        }
        else if(current.getX()<0){
            return new Asteroid(new Vector(width,current.getY()),direction,speed, lives);
        }
        else if(current.getY()>height){
            return new Asteroid(new Vector(current.getX(),0),direction,speed, lives);
        }
        else if(current.getY()<0){
            return new Asteroid(new Vector(current.getX(),height),direction,speed, lives);
        }
        return this;
    }
    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public Asteroid decreaseLives(int lives) {
        return new Asteroid(position, direction, speed, this.lives - lives);
    }

    public AsteroidCollider getCollider(){
        return new AsteroidCollider(this);
    }

}
