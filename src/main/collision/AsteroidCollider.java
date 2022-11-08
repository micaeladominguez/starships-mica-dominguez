package main.collision;

import main.asteroid.Asteroid;
import main.interfaces.Movable;
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
    public void handleCollisionWith(@NotNull GameCollider collider) {
        collider.handleCollisionWith(this);
    }

    @Override
    public Movable getModel() {
        return asteroid;
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
