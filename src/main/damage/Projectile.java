package main.damage;

import main.collision.ProjectileCollider;
import main.interfaces.Movable;
import main.vector.Vector;

public class Projectile implements Movable<Projectile> {
    private final String id;
    public final Vector position;
    public final Vector direction;
    public final String idShip;
    public final int damage;
    public final float speed;
    public final int lives;

    public Projectile(String id1, Vector position, Vector direction, String id, int damage, float speed, int lives) {
        this.id = id1;
        this.position = position;
        this.direction = direction;
        this.idShip = id;
        this.damage = damage;
        this.speed = speed;
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }

    public String getIdShip() {
        return idShip;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public Projectile moveForward() {
        return new Projectile(id, getPosition().add(getDirection().multiply(speed)), getDirection(), idShip, damage, getSpeed(), lives);
    }

    @Override
    public Projectile moveBackward() {
        return new Projectile(id, getPosition().subtract(getDirection().multiply(speed)), getDirection(), idShip, damage, getSpeed(), lives);
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
    public Projectile decreaseLives(int lives) {
        return new Projectile(id, position, direction, idShip, damage, speed, 0);
    }

    @Override
    public String getId() {
        return null;
    }

    public ProjectileCollider getCollider(){
        return new ProjectileCollider(this);
    }

}
