package edu.austral.mica.application.adapter.objects;

import edu.austral.ingsis.starships.ui.ImageRef;

public class AdapterConstants {
    public static double heightAsteroid = 30.0;
    public static double widthAsteroid = 30.0;
    public static ImageRef asteroidRef = new ImageRef("asteroid", heightAsteroid, widthAsteroid);
    public static double heightProjectile = 10.0;
    public static double widthProjectile = 10.0;
    public static ImageRef explosiveBullet = new ImageRef("explosive_bullet", heightProjectile, widthProjectile);
    public static ImageRef laser = new ImageRef("laser_bullet", heightProjectile, widthProjectile);
    public static double heightShip = 50.0;
    public static double widthShip = 50.0;
    public static ImageRef starshipRef = new ImageRef("starship", heightShip, widthShip);
}
