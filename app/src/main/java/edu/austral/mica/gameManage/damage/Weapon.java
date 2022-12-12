package edu.austral.mica.gameManage.damage;


import edu.austral.mica.gameManage.vector.Vector;

import java.util.ArrayList;

public abstract class Weapon {
    private final String type;
    private final int q_bullet ;
    private final int q_damage;
    private final float speed;
    static int counter = 0;


    protected Weapon(String type, int qBullet, int qDamage, float speed) {
        this.type = type;
        q_bullet = qBullet;
        q_damage = qDamage;
        this.speed = speed;
    }


    public int getQ_bullet() {
        return this.q_bullet;
    }

    public int getQ_damage() {
        return this.q_damage;
    }

    public float getSpeed() {
        return this.speed;
    }

    public ArrayList<Projectile> shoot(Vector position, Vector direction, String id){
        ArrayList<Projectile> projectiles = new ArrayList<>();
        for (int i = 0; i < getQ_bullet(); i++) {
            addNewId();
            String id_proj = "proj" + id+ this.type+ counter;
            projectiles.add(new Projectile(id_proj, new Vector(position.getX() + 10*(i+1), position.getY() + 10*(i+1)), direction.changeAngle(90), id, getQ_damage(), getSpeed(), 1));
        }
        return projectiles;
    }

    private static void addNewId() {
        counter = counter + 1;
    }

    public String getType() {
        return type;
    }
}
