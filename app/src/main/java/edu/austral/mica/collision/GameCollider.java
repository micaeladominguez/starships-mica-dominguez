package edu.austral.mica.collision;


import edu.austral.mica.asteroid.Asteroid;
import edu.austral.mica.damage.Projectile;
import edu.austral.mica.ship.Ship;

public interface GameCollider extends  Collider<GameCollider>{
    //El que cambia
    // es el que choca contra mi, yo respondo como le cambia al otro chocar contra mi
    Ship handleCollisionWith(ShipCollider shipCollider); //El que cambia
    // es el que choca contra mi, yo respondo como le cambia al otro chocar contra mi
    Asteroid handleCollisionWith(AsteroidCollider asteroidCollider);
    Projectile handleCollisionWith(ProjectileCollider projectileCollider);

}
