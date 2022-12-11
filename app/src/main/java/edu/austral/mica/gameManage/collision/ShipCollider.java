package edu.austral.mica.gameManage.collision;


import edu.austral.mica.gameManage.asteroid.Asteroid;
import edu.austral.mica.gameManage.damage.Projectile;
import edu.austral.mica.gameManage.interfaces.Movable;
import edu.austral.mica.gameManage.ship.Ship;
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
    public Movable handleCollisionWith(@NotNull GameCollider collider) {
        return collider.handleCollisionWith(this);
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
        return (Asteroid) model.dead();
    }

    @Override
    public Projectile handleCollisionWith(ProjectileCollider projectileCollider) {
        Movable model = projectileCollider.getModel();
        return (Projectile) model.decreaseLives(1);
    }

}
