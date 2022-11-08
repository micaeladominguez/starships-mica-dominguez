package main.collision;

import main.damage.Projectile;
import main.interfaces.Movable;
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
    public void handleCollisionWith(ShipCollider shipCollider) {

    }

    @Override
    public void handleCollisionWith(AsteroidCollider asteroidCollider) {

    }

    @Override
    public void handleCollisionWith(ProjectileCollider bulletCollider) {

    }
}
