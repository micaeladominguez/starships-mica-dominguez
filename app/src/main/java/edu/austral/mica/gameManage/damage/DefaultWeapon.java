package edu.austral.mica.gameManage.damage;

import edu.austral.mica.gameManage.vector.Vector;
import edu.austral.mica.persistence.Constants;

import java.util.ArrayList;

public class DefaultWeapon extends Weapon{
    private final String type = Constants.def;
    private final int q_bullet ;
    private final int q_damage;
    private final float speed;

    public DefaultWeapon(int q_bullet, int q_damage, float speed) {
        super(Constants.def, q_bullet, q_damage, speed);
        this.q_bullet = q_bullet;
        this.q_damage = q_damage;
        this.speed = speed;
    }




}
