package main.damage;

import main.vector.Vector;

import java.util.ArrayList;

public class Weapon {
    private final int q_bullet;
    private final int q_damage;
    private final float speed;


    public Weapon(int q_bullet, int q_damage, float speed) {
        this.q_bullet = q_bullet;
        this.q_damage = q_damage;
        this.speed = speed;
    }

    public int getQ_bullet() {
        return q_bullet;
    }

    public int getQ_damage() {
        return q_damage;
    }

    public float getSpeed() {
        return speed;
    }

    public ArrayList<Projectile> shoot(Vector position, Vector direction, String id){
        ArrayList<Projectile> projectiles = new ArrayList<>();
        for (int i = 0; i < getQ_bullet(); i++) {
            projectiles.add(new Projectile(position, direction, id, getQ_damage(), getSpeed(), 1));
        }
        return projectiles;
    }
}
