package edu.austral.mica.game.game.generators;

import java.util.Random;

public class RandomNumberGenerator {
    public static Integer getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }
}
