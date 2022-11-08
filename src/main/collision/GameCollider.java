package main.collision;

public interface GameCollider extends Collider<GameCollider>{
    void handleCollisionWith(ShipCollider shipCollider);
    void handleCollisionWith(AsteroidCollider asteroidCollider);
    void handleCollisionWith(ProjectileCollider bulletCollider);

}
