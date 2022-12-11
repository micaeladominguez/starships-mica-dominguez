package edu.austral.mica.gameManage.collision;


import edu.austral.mica.gameManage.asteroid.Asteroid;
import edu.austral.mica.gameManage.damage.Projectile;
import edu.austral.mica.gameManage.interfaces.Movable;
import edu.austral.mica.gameManage.ship.Ship;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class AsteroidCollider implements GameCollider{

    private Asteroid asteroid;
    public AsteroidCollider(Asteroid asteroid){
        this.asteroid = asteroid;
    }

    @Override
    public @NotNull Shape getShape() {
        return null;
    }

    @Override
    public Movable handleCollisionWith(@NotNull GameCollider collider) {
        return collider.handleCollisionWith(this);
    }

    @Override
    public Movable getModel() {
        return asteroid;
    }

    //SI CHOCA UN SHIP CONTRA MI
    @Override
    public Ship handleCollisionWith(ShipCollider shipCollider) {
        Movable model = shipCollider.getModel();
        return (Ship) model.decreaseLives(2);
    }

    //SI CHOCA UN ASTEROIDE CONTRA MI
    @Override
    public Asteroid handleCollisionWith(AsteroidCollider asteroidCollider) {
        Movable model = asteroidCollider.getModel();
        return (Asteroid) model;
    }

    //SI CHOCA UN PROJECTILE CONTRA MI
    @Override
    public Projectile handleCollisionWith(ProjectileCollider projectileCollider) {
        Movable model = projectileCollider.getModel();
        return (Projectile) model.decreaseLives(1);
    }
}
