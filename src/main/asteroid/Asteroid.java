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
    private final String id;

    public Asteroid(Vector position, Vector direction, float speed, int lives, String id) {
        this.position = position;
        this.direction = direction;
        this.speed = speed;
        this.lives = lives;
        this.id = id;
    }

    public int getLives() {
        return lives;
    }

    @Override
    public Asteroid moveForward() {
        return new Asteroid(getPosition().add(getDirection().multiply(speed)), getDirection(),getSpeed(), getLives(), id);
    }

    @Override
    public Asteroid moveBackward() {
        return new Asteroid(getPosition().subtract(getDirection().multiply(speed)), getDirection(),getSpeed(), getLives(), id);
    }
    @Override
    public Vector getPosition() { return position; }
    @Override
    public Vector getDirection() { return direction; }

    @Override
    public Asteroid screenWrap(int width, int height){
        Vector current = position;
        if( current.getX()>width){
            return new Asteroid(new Vector(0,current.getY()),direction,speed, lives, id);
        }
        else if(current.getX()<0){
            return new Asteroid(new Vector(width,current.getY()),direction,speed, lives, id);
        }
        else if(current.getY()>height){
            return new Asteroid(new Vector(current.getX(),0),direction,speed, lives, id);
        }
        else if(current.getY()<0){
            return new Asteroid(new Vector(current.getX(),height),direction,speed, lives, id);
        }
        return this;
    }
    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public Asteroid decreaseLives(int lives) {
        return new Asteroid(position, direction, speed, this.lives - lives, id);
    }

    public AsteroidCollider getCollider(){
        return new AsteroidCollider(this);
    }

    public String getId() {
        return id;
    }
}
