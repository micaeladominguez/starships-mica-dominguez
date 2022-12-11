package edu.austral.mica.application.adapter;

import edu.austral.ingsis.starships.ui.ElementColliderType;
import edu.austral.ingsis.starships.ui.ElementModel;
import edu.austral.ingsis.starships.ui.ImageRef;
import edu.austral.mica.gameManage.interfaces.Movable;

import java.util.Map;

public class Adapter {
    static double heightAsteroid = 30.0;
    static double heightProjectile = 10.0;
    static double widthProjectile = 10.0;
    static double widthAsteroid = 30.0;
    static ImageRef starshipRef = new ImageRef("starship", 70.0, 70.0);
    static ImageRef asteroidRef = new ImageRef("asteroid", heightAsteroid, widthAsteroid);
    static ImageRef laser = new ImageRef("explosive_bullet", heightAsteroid, widthAsteroid);
    public static void adaptElement(Map<String, ElementModel> facade, Map<String, Movable> elements){
        facade.clear();
        elements.forEach((k, v) -> {
            if(k != null && v != null){
                if(k.startsWith("ast")){
                    facade.put(k, new ElementModel(k,
                            v.getPosition().getX(), v.getPosition().getY(), heightAsteroid, widthAsteroid, v.getDirection().getAngle(),
                            ElementColliderType.Elliptical, asteroidRef));
                }else if(k.startsWith("ship")){
                    facade.put(k, new ElementModel(k,
                            v.getPosition().getX(), v.getPosition().getY(), heightAsteroid, widthAsteroid, v.getDirection().getAngle(),
                            ElementColliderType.Triangular, starshipRef));
                }else if(k.startsWith("proj")){
                    facade.put(k, new ElementModel(k,
                            v.getPosition().getX(), v.getPosition().getY(), heightProjectile, widthProjectile, v.getDirection().getAngle(),
                            ElementColliderType.Elliptical, laser));
                }
            }
        });
    }
}
