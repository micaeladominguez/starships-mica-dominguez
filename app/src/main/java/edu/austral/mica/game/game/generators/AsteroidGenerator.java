package edu.austral.mica.game.game.generators;

import edu.austral.mica.gameManage.asteroid.Asteroid;
import edu.austral.mica.gameManage.vector.Vector;
import edu.austral.mica.persistence.Constants;

import java.util.ArrayList;

import static edu.austral.mica.game.game.generators.RandomNumberGenerator.getRandomNumber;

public class AsteroidGenerator {
    static int counter = 0;

    public static Asteroid createAsteroid(){
        ArrayList<Vector> directionAndPosition = getInitialPositionAndDirection();

        return new Asteroid( directionAndPosition.get(1), directionAndPosition.get(0), Constants.DEFAULT_ASTEROID_SPEED, Constants.ASTEROID_LIVES, generateId());
    }

    private static String generateId() {
        int old_counter = counter;
        addCounter();
        return "ast"+old_counter;
    }

    private static void addCounter() {
        counter = counter +1;
    }

    private enum Side {
        TOP, BOTTOM, LEFT, RIGHT
    }


    private static Side getRandomSide(){
        Integer side = getRandomNumber(0, 4);
        return switch (side){
            case 0 -> Side.TOP;
            case 2 -> Side.BOTTOM;
            case 3 -> Side.LEFT;
            default -> Side.RIGHT;
        };
    }
    public static ArrayList<Vector> getInitialPositionAndDirection(){
        Side randomSide = getRandomSide();
        Vector direction = defineDirection(randomSide);
        ArrayList<Vector> vectors = new ArrayList<>();
        vectors.add(direction);
        switch (randomSide) {
            case TOP -> vectors.add(Constants.TOP_POSITION);
            case BOTTOM -> vectors.add(Constants.BOTTOM_POSITION);
            case LEFT -> vectors.add(Constants.LEFT_POSITION);
            case RIGHT -> vectors.add(Constants.RIGHT_POSITION);
        };
        return vectors;
    }

    private static Vector defineDirection(Side randomSide) {
        return switch (randomSide) {
            case TOP -> Constants.TOP_DIRECTION;
            case BOTTOM -> Constants.BOTTOM_DIRECTION;
            case LEFT -> Constants.LEFT_DIRECTION;
            case RIGHT -> Constants.RIGHT_DIRECTION;
        };
    }

}
