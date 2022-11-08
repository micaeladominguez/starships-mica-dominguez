package main.damage;

import main.collision.ProjectileCollider;
import main.interfaces.Movable;
import main.vector.Vector;

public class Projectile implements Movable<Projectile> {

    public final Vector position;
    public final Vector direction;
    public final String idShip;
    public final int damage;
    public final float speed;

    public Projectile(Vector position, Vector direction, String id, int damage, float speed) {
        this.position = position;
        this.direction = direction;
        this.idShip = id;
        this.damage = damage;
        this.speed = speed;
    }

    public String getIdShip() {
        return idShip;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public Projectile moveForward() {
        return new Projectile(getPosition().add(getDirection().multiply(speed)), getDirection(), idShip, damage, getSpeed());
    }

    @Override
    public Projectile moveBackward() {
        return new Projectile(getPosition().subtract(getDirection().multiply(speed)), getDirection(), idShip, damage, getSpeed());
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

    public ProjectileCollider getCollider(){
        return new ProjectileCollider(this);
    }

}
