package main.collision;

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
    public void handleCollisionWith(ShipCollider shipCollider) {

    }

    @Override
    public void handleCollisionWith(AsteroidCollider asteroidCollider) {

    }

    @Override
    public void handleCollisionWith(ProjectileCollider bulletCollider) {

    }
}
