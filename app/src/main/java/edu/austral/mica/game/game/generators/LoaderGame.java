package edu.austral.mica.game.game.generators;

import edu.austral.mica.game.game.Game;
import edu.austral.mica.game.game.LiveGame;
import edu.austral.mica.gameManage.asteroid.Asteroid;
import edu.austral.mica.gameManage.damage.Projectile;
import edu.austral.mica.gameManage.damage.Weapon;
import edu.austral.mica.gameManage.interfaces.Movable;
import edu.austral.mica.gameManage.ship.Ship;
import edu.austral.mica.gameManage.vector.Vector;
import edu.austral.mica.persistence.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoaderGame {
    public static Game loadGame() {
        try {
            Object obj = new JSONParser().parse(new FileReader(Constants.SAVE_FILE));
            JSONObject savedFiles = (JSONObject) obj;
            Map<String, Movable> elements = new HashMap<>();
            Map<String, Integer> shipScores = new HashMap<>();
            Map<String, Integer> shipLives = new HashMap<>();
            addShips(savedFiles, elements, shipLives, shipScores);
            addAsteroids(savedFiles, elements);
            addProjectiles(savedFiles,elements);
            return new LiveGame(elements,shipScores,shipLives);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addProjectiles(JSONObject savedFiles, Map<String, Movable> elements) {
        JSONArray listOfProjectiles = (JSONArray) savedFiles.get("projectiles");
        for (Object projectile : listOfProjectiles) {
            createProjectile((JSONObject) projectile, elements);
        }
    }

    private static void createProjectile(JSONObject projectile, Map<String, Movable> elements) {
        Vector position = getVector(projectile, "position");
        Vector direction = getVector(projectile, "direction");
        float speed = Float.parseFloat(projectile.get("speed").toString());
        int lives = ((Long) projectile.get("lives")).intValue();
        String id = (String) projectile.get("id");
        String shipId = (String) projectile.get("shipId");
        int damage = ((Long) projectile.get("damage")).intValue();
        addProjectileToMap(elements, shipId,position, direction, speed, lives, id, damage);
    }
    private static void addProjectileToMap(Map<String, Movable> elements,String shipId, Vector position, Vector direction, float speed, int lives, String id, int damage) {
        elements.put(id, new Projectile(id, position, direction, shipId, damage, speed, lives));
    }

    private static void addAsteroids(JSONObject savedFiles, Map<String, Movable> elements) {
        JSONArray listOfAsteroids = (JSONArray) savedFiles.get("asteroids");
        for (Object asteroid : listOfAsteroids) {
            createAsteroid((JSONObject) asteroid, elements);
        }
    }

    private static void createAsteroid(JSONObject asteroid, Map<String, Movable> elements) {
        Vector position = getVector(asteroid, "position");
        Vector direction = getVector(asteroid, "direction");
        float speed = Float.parseFloat(asteroid.get("speed").toString());
        int lives = ((Long) asteroid.get("lives")).intValue();
        String id = (String) asteroid.get("id");
        addAsteroidToMap(elements, position, direction, speed, lives, id);
    }

    private static void addAsteroidToMap(Map<String, Movable> elements, Vector position, Vector direction, float speed, int lives, String id) {
        elements.put(id, new Asteroid(position, direction, speed, lives, id));
    }

    private static void addShips(JSONObject savedFiles, Map<String, Movable> elements, Map<String, Integer> shipLives, Map<String, Integer> shipScores) {
        JSONArray listOfShips = (JSONArray) savedFiles.get("ships");
        for (Object listOfShip : listOfShips) {
            createShip((JSONObject) listOfShip, elements, shipLives, shipScores);
        }
    }

    private static void createShip(JSONObject listOfShip, Map<String, Movable> elements, Map<String, Integer> shipLives, Map<String, Integer> shipScores) {
        Vector position = getVector(listOfShip, "position");
        Vector direction = getVector(listOfShip, "direction");
        float acceleration = Float.parseFloat(listOfShip.get("acceleration").toString()) ;
        float speed = Float.parseFloat(listOfShip.get("speed").toString());
        int score = ((Long) listOfShip.get("score")).intValue();
        int lives = ((Long) listOfShip.get("lives")).intValue();
        Weapon weaponType = GameInitializer.defineWeapon((String) listOfShip.get("weaponType")) ;
        String id = (String) listOfShip.get("id");
        addSHipToMaps(elements, shipLives, shipScores, position, direction, acceleration, speed, score, lives, weaponType, id);
    }

    private static void addSHipToMaps(Map<String, Movable> elements, Map<String, Integer> shipLives, Map<String, Integer> shipScores, Vector position, Vector direction, float acceleration, float speed, int score, int lives, Weapon weaponType, String id) {
        shipLives.put(id, lives);
        shipScores.put(id, score);
        elements.put(id,new Ship(id, position, direction, speed, acceleration, weaponType, lives));
    }

    private static Vector getVector(JSONObject listOfShip, String name) {
        JSONObject initialDirection = (JSONObject) listOfShip.get(name);
        float x = Float.parseFloat(initialDirection.get("x").toString()) ;
        float y = Float.parseFloat(initialDirection.get("y").toString());
        return new Vector(x, y);
    }
}
