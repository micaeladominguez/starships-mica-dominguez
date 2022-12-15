package edu.austral.mica.application.adapter.objects;

import edu.austral.ingsis.starships.ui.ImageRef;

public class AdapterConstants {
    public static double heightAsteroid = 50.0;
    public static double widthAsteroid = 50.0;

    public static double heightAsteroid2 = 100.0;
    public static double widthAsteroid2 = 100.0;
    public static ImageRef asteroidRef = new ImageRef("asteroid2", heightAsteroid, widthAsteroid);
    public static ImageRef asteroid2Ref = new ImageRef("asteroid", heightAsteroid2, widthAsteroid2);
    public static double heightProjectile = 20.0;
    public static double widthProjectile = 20.0;
    public static ImageRef explosiveBullet = new ImageRef("explosive_bullet", heightProjectile, widthProjectile);
    public static ImageRef laser = new ImageRef("bullet", heightProjectile, widthProjectile);
    public static double heightShip = 80.0;
    public static double widthShip = 80.0;
    public static ImageRef defaultShipRef = new ImageRef("starship", heightShip, widthShip);

    public static ImageRef ship1RED = new ImageRef("spaceship", heightShip, widthShip);
    public static ImageRef ship2BLUE = new ImageRef("spaceship2", heightShip, widthShip);
}
