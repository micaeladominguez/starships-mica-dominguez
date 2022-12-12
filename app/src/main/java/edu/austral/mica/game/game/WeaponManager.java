package edu.austral.mica.game.game;

import edu.austral.mica.gameManage.damage.Weapon;
import edu.austral.mica.gameManage.ship.Ship;
import edu.austral.mica.persistence.Constants;

public class WeaponManager {
    public static Weapon decideWeapon(Ship movable) {
        if(movable.getWeapon().getType().equals(Constants.def)){
            return Constants.differentWeapon;
        }
        return Constants.defaultWeapon;
    }
}
