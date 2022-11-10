package main.collision;

import main.asteroid.Asteroid;
import main.damage.Projectile;
import main.ship.Ship;

public interface GameCollider extends  Collider<GameCollider>{
    //El que cambia
    // es el que choca contra mi, yo respondo como le cambia al otro chocar contra mi
    Ship handleCollisionWith(ShipCollider shipCollider); //El que cambia
    // es el que choca contra mi, yo respondo como le cambia al otro chocar contra mi
    Asteroid handleCollisionWith(AsteroidCollider asteroidCollider);
    Projectile handleCollisionWith(ProjectileCollider projectileCollider);

}
