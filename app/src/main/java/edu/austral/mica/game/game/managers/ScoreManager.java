package edu.austral.mica.game.game.managers;

import edu.austral.mica.gameManage.damage.Projectile;
import edu.austral.mica.gameManage.interfaces.Movable;
import edu.austral.mica.gameManage.ship.Ship;

import java.util.ArrayList;

public class ScoreManager {

    public static ArrayList<Integer> checkScoreForCollision(ArrayList<Integer> scoresForShip, Movable movable1, Movable movable2) {
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
        if(bulletAndAsteroid(movable1, movable2)){
            bulletAndAsteroidCrash(scoresForShip, movable1, movable2);
        }
        return scoresForShip;
    }

    private static void bulletAndAsteroidCrash(ArrayList<Integer> scoresForShip, Movable movable1, Movable movable2) {
        Projectile projectile = getProjectile(movable1, movable2);
        if(projectile != null){
            int index = getIndexFromProjectile(projectile);
            int actual_score = scoresForShip.get(index);
            scoresForShip.set(index, actual_score + 15);
        }
    }

    private static int getIndexFromProjectile(Projectile projectile) {
        for(int i = 0; i < 10; i ++){
            if (projectile.getId().contains("ship" + i)) return i;
        }
        return 10;
    }

    private static boolean bulletAndAsteroid(Movable movable1, Movable movable2) {
        return (movable1.getId().contains("ast") && movable2.getId().contains("proj")) ||
                (movable1.getId().contains("proj") && movable2.getId().contains("ast"));
    }

    private static void bulletCrash(ArrayList<Integer> scoresForShip, Ship ship) {
        int index = getStarshipIndex(ship);
        int actual_score = scoresForShip.get(index);
        if(actual_score - 4 <= 0)
            scoresForShip.set(index, 0);
        scoresForShip.set(index, actual_score - 4);
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
        return (movable1.getId().contains("ship") && movable2.getId().contains("proj")) ||
                (movable1.getId().contains("proj") && movable2.getId().contains("ship"));
    }

    private static void asteroidCrash(ArrayList<Integer> scoresForShip, Movable movable1, Movable movable2) {
        Ship ship = getShip(movable1, movable2);
        if(ship != null){
            int index = getStarshipIndex(ship);
            int actual_score = scoresForShip.get(index);
            if(actual_score - 5 <= 0)
                scoresForShip.set(index, 0);
            scoresForShip.set(index, actual_score - 5);
        }
    }

    private static int getStarshipIndex(Ship ship) {
        for(int i = 0; i < 10; i ++){
            if (ship.getId().contains("ship" + i)) return i;
        }
        return 10;
    }

    private static Ship getShip(Movable movable1, Movable movable2) {
        if (movable2 instanceof Ship) return (Ship) movable2;
        if (movable1 instanceof  Ship) return (Ship) movable1;
        return null;
    }

    private static boolean starshipAndAsteroid(Movable movable1, Movable movable2) {
        return (movable1.getId().contains("ship") && movable2.getId().contains("ast")) ||
                (movable1.getId().contains("ast") && movable2.getId().contains("ship"));
    }

}
