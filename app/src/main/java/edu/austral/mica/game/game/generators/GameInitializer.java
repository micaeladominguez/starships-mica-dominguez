package edu.austral.mica.game.game.generators;

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
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class GameInitializer {
    public static Map<String, Movable> initialize(){
        Map<String, Movable> map = new HashMap<>();
        try {
            Object obj = new JSONParser().parse(new FileReader(Constants.INITIAL_CONFIG_FILE_PATH));
            JSONObject initialConfigJson = (JSONObject) obj;
            JSONArray listOfShips = (JSONArray) initialConfigJson.get("ships");
            Iterator it = listOfShips.iterator();
            Integer idNumber = 1;
            Integer differenceOnPosition = 0;
            while(it.hasNext()){
                addShip(map, idNumber, it.next(), differenceOnPosition);
                idNumber++;
                differenceOnPosition += Constants.DIFFERENCE_BETWEEN_SHIP;
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    private static void addShip(Map<String, Movable> map, Integer idNumber, Object next, Integer differenceOnPosition) {
        map.put(generateShipId(idNumber), initializeShip(idNumber, (JSONObject) next, differenceOnPosition));
    }

    private static String generateShipId(Integer idNumber) {
        return "ship" + idNumber;
    }

    private static Ship initializeShip(Integer idNumber, JSONObject ship, Integer differenceOnPosition){
        Vector position = definePosition(differenceOnPosition);
        String initialDirection = (String) ship.get("initial_direction");
        Vector direction = defineDirection(initialDirection);
        float speed = Float.parseFloat((String) ship.get("speed"));
        float acceleration =Float.parseFloat((String) ship.get("acceleration"));
        int lives =  ((Long) ship.get("lives")).intValue();
        String weaponType = (String) ship.get("weapon");
        Weapon weapon = defineWeapon(weaponType);
        return new Ship(generateShipId(idNumber), position, direction, speed, acceleration, weapon, lives);
    }

    private static Weapon defineWeapon(String weaponType) {
        if(Objects.equals(weaponType, "DEFAULT")){
            return Constants.defaultWeapon;
        }else{
            return Constants.differentWeapon;
        }
    }

    private static Vector defineDirection(String initialDirection) {
        Vector vector = Constants.DEFAULT_STARSHIP_DIRECTION;
        return vector;
    }

    private static Vector definePosition(Integer differenceOnPosition) {
        Vector vector = Constants.DEFAULT_STARSHIP_POSITION;
        if(differenceOnPosition == 0){
            return vector;
        }else{
            return new Vector(vector.getX() - differenceOnPosition, vector.getY() + differenceOnPosition);
        }
    }


}
