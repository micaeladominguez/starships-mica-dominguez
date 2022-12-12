package edu.austral.mica.game.game;

public class CheckType {

    public static  boolean checkIfAsteroid(String k) {
        if(k != null){
            if(k.length() > 3){
                return k.startsWith("ast");
            }
            return false;
        }
        return false;
    }

    public static boolean checkIfProjectile(String k) {
        if(k != null){
            if(k.length() > 4){
                return k.startsWith("proj");
            }
            return false;
        }
        return false;
    }


    public static boolean checkIfShip(String k) {
        if(k != null){
            if(k.length() > 4){
                return k.startsWith("ship");
            }
            return false;
        }
        return false;
    }
}
