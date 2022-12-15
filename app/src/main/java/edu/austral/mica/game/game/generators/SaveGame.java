package edu.austral.mica.game.game.generators;

import edu.austral.mica.gameManage.asteroid.Asteroid;
import edu.austral.mica.gameManage.damage.Projectile;
import edu.austral.mica.gameManage.interfaces.Movable;
import edu.austral.mica.gameManage.ship.Ship;
import edu.austral.mica.gameManage.vector.Vector;
import edu.austral.mica.persistence.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class SaveGame {

    public static void saveGame( Map<String, Movable> elements, Map<String,Integer> scoresForShip){
        ArrayList<Ship> ships = new ArrayList<>();
        ArrayList<Asteroid> asteroids = new ArrayList<>();
        ArrayList<Projectile> projectiles = new ArrayList<>();
        elements.forEach((k,v) ->{
            if(v instanceof Ship){
                ships.add((Ship) v);
            }else if( v instanceof Asteroid){
                asteroids.add((Asteroid) v);
            }else{
                projectiles.add((Projectile) v);
            }
        });
        saveEntities(ships, asteroids, projectiles, scoresForShip);
    }

    private static void saveEntities(ArrayList<Ship> ships, ArrayList<Asteroid> asteroids, ArrayList<Projectile> projectiles, Map<String, Integer> scoresForShip) {
        try {
            JSONArray jsonShips = saveShips(ships, scoresForShip);
            JSONArray jsonAsteroids = saveAsteroids(asteroids);
            JSONArray jsonProjectiles = saveProjectiles(projectiles);
            JSONObject jsonObject = new JSONObject();
            addEntities(jsonObject, jsonAsteroids, jsonProjectiles, jsonShips);
            FileWriter file = new FileWriter(Constants.SAVE_FILE);
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void addEntities(JSONObject jsonObject, JSONArray jsonAsteroids, JSONArray jsonProjectiles, JSONArray jsonShips) {
        jsonObject.put("ships", jsonShips);
        jsonObject.put("asteroids", jsonAsteroids);
        jsonObject.put("projectiles", jsonProjectiles);

    }

    private static JSONArray saveProjectiles(ArrayList<Projectile> projectiles) {
        JSONArray jsonArray = new JSONArray();
        for (Projectile projectile : projectiles) {
            jsonArray.add(saveProjectile(projectile));
        }
        return jsonArray;
    }

    private static JSONArray saveAsteroids(ArrayList<Asteroid> asteroids) {
        JSONArray jsonArray = new JSONArray();
        for (Asteroid asteroid : asteroids) {
            jsonArray.add(saveAsteroid(asteroid));
        }
        return jsonArray;
    }

    private static JSONArray saveShips(ArrayList<Ship> ships, Map<String, Integer> scoresForShip) {
        JSONArray jsonArray = new JSONArray();
        for (Ship ship : ships) {
            jsonArray.add(saveShip(ship, scoresForShip));
        }
        return jsonArray;
    }

    private static JSONObject saveProjectile(Projectile projectile) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", projectile.getId());
        addVector(projectile.getPosition(), jsonObject, "position");
        addVector(projectile.getDirection(), jsonObject, "direction");
        jsonObject.put("shipId", projectile.getIdShip());
        jsonObject.put("speed", projectile.getSpeed());
        jsonObject.put("lives", projectile.getLives());
        jsonObject.put("damage",projectile.getDamage());

        return jsonObject;

    }

    private static JSONObject saveAsteroid(Asteroid asteroid) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",asteroid.getId());
        addVector(asteroid.getDirection(), jsonObject, "direction");
        addVector(asteroid.getPosition(), jsonObject, "position");
        jsonObject.put("speed",asteroid.getSpeed());
        jsonObject.put("lives",asteroid.getLives());
        return jsonObject;
    }

    private static JSONObject saveShip(Ship ship, Map<String, Integer> scoresForShip) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", ship.getId());
        addVector(ship.getPosition(), jsonObject, "position");
        addVector(ship.getDirection(), jsonObject, "direction");
        jsonObject.put("speed", ship.getSpeed());
        jsonObject.put("weaponType", ship.getWeapon().getType());
        jsonObject.put("lives", ship.getLives());
        jsonObject.put("acceleration", ship.getAcceleration());
        addScore(ship, scoresForShip, jsonObject);
        return jsonObject;
    }

    private static void addScore(Ship ship, Map<String, Integer> scoresForShip, JSONObject jsonObject) {
        Integer scoreForShip = scoresForShip.get(ship.getId());
        if(scoreForShip != null)
            jsonObject.put("score", scoreForShip);
    }


    private static void addVector(Vector vector, JSONObject jsonObject, String name) {
        JSONObject vectorObject = new JSONObject();
        vectorObject.put("x", vector.getX());
        vectorObject.put("y", vector.getY());
        jsonObject.put(name, vectorObject);
    }


}
