package edu.austral.mica.persistence;

import edu.austral.mica.gameManage.damage.Weapon;

public class Constants {
    public static final String KEYBINDINGS_FILE_PATH = "/home/mica/faculty/DS/starships-base/app/src/main/java/edu/austral/mica/persistence/keybindings.json";
    public static final String def = "DEFAULT";
    public static final String other1 = "OTHER1";
    public static final Weapon defaultWeapon = new Weapon(2,1,0.1F, def);
    public static final Weapon differentWeapon = new Weapon(4,2,0.1F, other1);
}
