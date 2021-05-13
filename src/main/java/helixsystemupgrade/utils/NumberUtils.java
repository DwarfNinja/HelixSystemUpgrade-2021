package helixsystemupgrade.utils;

import java.util.Random;

public class NumberUtils {

    public static int getRandomNumberInRange(int min, int max)  {
        Random randomNumber = new Random();
        return randomNumber.nextInt(max - min) + min;
    }
}
