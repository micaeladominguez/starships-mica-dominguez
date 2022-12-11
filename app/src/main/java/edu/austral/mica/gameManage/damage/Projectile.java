package edu.austral.mica.gameManage.damage;


import edu.austral.mica.gameManage.collision.ProjectileCollider;
import edu.austral.mica.gameManage.interfaces.Movable;
import edu.austral.mica.gameManage.vector.Vector;

public class Projectile implements Movable<Projectile> {
    private final String id;
    public final Vector position;
    public final Vector direction;
    public final String idShip;
    public final int damage;
    public final float speed;
    public final int lives;

    public Projectile(String ownId, Vector position, Vector direction, String idShip, int damage, float speed, int lives) {
        this.id = ownId;
        this.position = position;
        this.direction = direction;
        this.idShip = idShip;
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
        return id;
    }

    public ProjectileCollider getCollider(){
        return new ProjectileCollider(this);
    }

    @Override
    public boolean isDead() {
        return lives <= 0;
    }

    @Override
    public Projectile dead() {
        return decreaseLives(0);
    }

}
