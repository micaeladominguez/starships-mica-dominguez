package edu.austral.mica.persistence;

import edu.austral.mica.gameManage.damage.DefaultWeapon;
import edu.austral.mica.gameManage.damage.OtherWeapon1;
import edu.austral.mica.gameManage.damage.Weapon;
import edu.austral.mica.gameManage.vector.Vector;


public class Constants {
    public static final String KEYBINDINGS_FILE_PATH = "/home/mica/faculty/DS/starships-base/app/src/main/java/edu/austral/mica/persistence/keybindings.json";
    public static final String def = "DEFAULT";
    public static final String other1 = "OTHER1";
    public static final Vector DEFAULT_STARSHIP_POSITION = new Vector(400, 300) ;
    public static final Vector DEFAULT_STARSHIP_DIRECTION = new Vector(0, -1 );

    public static final Vector TOP_POSITION = new Vector(0,0);
    public static final Vector BOTTOM_POSITION = new Vector(790,790);
    public static final Vector RIGHT_POSITION = new Vector(790,400);
    public static final Vector LEFT_POSITION = new Vector(0,400);

    public static final Vector TOP_DIRECTION = new Vector(1, 1);
    public static final Vector BOTTOM_DIRECTION = new Vector(-1, -1);
    public static final Vector RIGHT_DIRECTION = new Vector(-1, 0);
    public static final Vector LEFT_DIRECTION = new Vector(+1, 0);
    public static final float DEFAULT_ASTEROID_SPEED = 0.1F;
    public static final int DEFAULT_ASTEROID_LIVES = 2;

    private static final int q_bullet_def = 2 ;
    private static final int q_damage_def = 1;
    private static final float speed_proj = 0.1F;
    private static final int q_bullet_oth1 = 4 ;
    private static final int q_damage_oth1 = 2;
    public static final Weapon defaultWeapon = new DefaultWeapon(q_bullet_def,q_damage_def,speed_proj);
    public static final Weapon differentWeapon = new OtherWeapon1(q_bullet_oth1,q_damage_oth1,speed_proj);
    public static final String INITIAL_CONFIG_FILE_PATH = "/home/mica/faculty/DS/starships-base/app/src/main/java/edu/austral/mica/persistence/initial_config_file.json";

    public static final int DIFFERENCE_BETWEEN_SHIP = 50;
    public static final double ASTEROID_NEW_RATE = 0.001;
}
