package edu.austral.mica.gameManage.collision;

import edu.austral.mica.gameManage.asteroid.Asteroid;
import edu.austral.mica.gameManage.damage.Projectile;
import edu.austral.mica.gameManage.interfaces.Movable;
import edu.austral.mica.gameManage.ship.Ship;
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
    public Movable handleCollisionWith(@NotNull GameCollider collider) {
        return collider.handleCollisionWith(this);
    }

    @Override
    public Movable getModel() {
        return projectile;
    }

    @Override
    public Ship handleCollisionWith(ShipCollider shipCollider) {
        Movable model = shipCollider.getModel();
        if(checkIfOwnBullet(shipCollider)){
            return (Ship) model;
        };
        return (Ship) model.decreaseLives(1);
    }

    private boolean checkIfOwnBullet(ShipCollider shipCollider) {
        Ship ship = (Ship) shipCollider.getModel();
        if(projectile.getId().length() > 4){
            return projectile.getId().startsWith("proj" + ship.getId());
        }
        return false;
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
