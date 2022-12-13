package edu.austral.mica.game.game.managers;

import edu.austral.mica.gameManage.damage.Projectile;
import edu.austral.mica.gameManage.interfaces.Movable;
import edu.austral.mica.gameManage.ship.Ship;

import java.util.ArrayList;
import java.util.Map;

public class ScoreManager {

    public static Map<String, Integer> checkScoreForCollision(Map<String, Integer> scoresForShip, Movable movable1, Movable movable2) {
        if(starshipAndAsteroid(movable1, movable2))
            asteroidCrash(scoresForShip, movable1, movable2);

        if(starshipAndBullet(movable1, movable2)){
            Ship ship = getShip(movable1, movable2);
            Projectile projectile = getProjectile(movable1, movable2);
            if(ship != null && projectile != null){
                if(ownBullet(ship,projectile )) return scoresForShip;
                bulletCrash(scoresForShip, ship);
            }
        }

        if(bulletAndAsteroid(movable1, movable2))
            bulletAndAsteroidCrash(scoresForShip, movable1, movable2);

        return scoresForShip;
    }

    private static void bulletAndAsteroidCrash(Map<String, Integer> scoresForShip, Movable movable1, Movable movable2) {
        Projectile projectile = getProjectile(movable1, movable2);
        if(projectile != null){
            int index = getIndexFromProjectile(projectile);
            String shipId = "ship" + index;
            int actual_score = scoresForShip.get(shipId);
            scoresForShip.put(shipId, actual_score + 15);
        }
    }

    private static int getIndexFromProjectile(Projectile projectile) {
        for(int i = 0; i < 10; i ++){
            if (projectile.getId().contains("ship" + i)) return i;
        }
        return 10;
    }

    private static boolean bulletAndAsteroid(Movable movable1, Movable movable2) {
        if(anyNull(movable1, movable2)) return false;
        return (movable1.getId().contains("ast") && movable2.getId().contains("proj")) ||
                (movable1.getId().contains("proj") && movable2.getId().contains("ast"));
    }

    private static void bulletCrash(Map<String, Integer> scoresForShip, Ship ship) {
        String id = getStarshipId(ship);
        int actual_score = scoresForShip.get(id);
        if(actual_score - 4 <= 0)
            scoresForShip.put(id, 0);
        scoresForShip.put(id, actual_score - 4);
    }

    private static boolean ownBullet(Ship ship, Projectile projectile) {
        if(projectile.getId().length() > 4){
            return projectile.getId().startsWith("proj" + ship.getId());
        }
        return false;
    }

    private static Projectile getProjectile(Movable movable1, Movable movable2) {
        if(movable2 instanceof  Projectile) return (Projectile) movable2;
        if(movable1 instanceof Projectile) return (Projectile)  movable1;
        return null;
    }

    private static boolean starshipAndBullet(Movable movable1, Movable movable2) {
        if(anyNull(movable1, movable2)) return false;
        return (movable1.getId().contains("ship") && movable2.getId().contains("proj")) ||
                (movable1.getId().contains("proj") && movable2.getId().contains("ship"));
    }

    private static void asteroidCrash(Map<String, Integer> scoresForShip, Movable movable1, Movable movable2) {
        Ship ship = getShip(movable1, movable2);
        if(ship != null){
            String id = getStarshipId(ship);
            int actual_score = scoresForShip.get(id);
            if(actual_score - 5 <= 0)
                scoresForShip.put(id, 0);
            scoresForShip.put(id, actual_score - 5);

        }
    }

    private static String getStarshipId(Ship ship) {
        if(ship == null) return "";
        return ship.getId();
    }

    private static Ship getShip(Movable movable1, Movable movable2) {
        if (anyNull(movable1, movable2)) return null;
        if (movable2 instanceof Ship) return (Ship) movable2;
        if (movable1 instanceof  Ship) return (Ship) movable1;
        return null;
    }

    private static boolean starshipAndAsteroid(Movable movable1, Movable movable2) {
        if(anyNull(movable1, movable2)) return false;
        return (movable1.getId().contains("ship") && movable2.getId().contains("ast")) ||
                (movable1.getId().contains("ast") && movable2.getId().contains("ship"));
    }

    private static boolean anyNull(Movable movable1, Movable movable2) {
        return movable1 == null || movable2 == null;
    }

}
