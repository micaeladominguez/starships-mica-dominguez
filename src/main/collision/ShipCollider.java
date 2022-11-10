package main.collision;

import main.asteroid.Asteroid;
import main.damage.Projectile;
import main.interfaces.Movable;
import main.ship.Ship;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class ShipCollider implements  GameCollider{
    private Ship ship;
    public ShipCollider(Ship ship) {
        this.ship = ship;
    }
    @Override
    public @NotNull Shape getShape() {
        return null;
    }

    @Override
    public void handleCollisionWith(@NotNull GameCollider collider) {

    }

    @Override
    public Movable getModel() {
        return ship;
    }

    @Override
    public Ship handleCollisionWith(ShipCollider shipCollider) {
        Movable model = shipCollider.getModel();
        return (Ship) model;
    }

    @Override
    public Asteroid handleCollisionWith(AsteroidCollider asteroidCollider) {
        Movable model = asteroidCollider.getModel();
        return (Asteroid) model;
    }

    @Override
    public Projectile handleCollisionWith(ProjectileCollider projectileCollider) {
        Movable model = projectileCollider.getModel();
        return (Projectile) model.decreaseLives(1);
    }

}
