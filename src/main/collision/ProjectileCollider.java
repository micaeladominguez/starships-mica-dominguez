package main.collision;

import main.asteroid.Asteroid;
import main.damage.Projectile;
import main.interfaces.Movable;
import main.ship.Ship;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class ProjectileCollider implements GameCollider{

    private Projectile projectile;

    public ProjectileCollider(Projectile projectile) {
        this.projectile = projectile;
    }

    @Override
    public @NotNull Shape getShape() {
        return null;
    }

    @Override
    public void handleCollisionWith(@NotNull GameCollider collider) {
        collider.handleCollisionWith(this);
    }

    @Override
    public Movable getModel() {
        return projectile;
    }

    @Override
    public Ship handleCollisionWith(ShipCollider shipCollider) {
        Movable model = shipCollider.getModel();
        return (Ship) model.decreaseLives(3);
    }

    @Override
    public Asteroid handleCollisionWith(AsteroidCollider asteroidCollider) {
        Movable model = asteroidCollider.getModel();
        return (Asteroid) model.decreaseLives(1);
    }

    @Override
    public Projectile handleCollisionWith(ProjectileCollider bulletCollider) {
        Movable model = bulletCollider.getModel();
        return (Projectile) model;
    }
}
