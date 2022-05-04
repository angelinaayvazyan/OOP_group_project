package proj.core;
import java.util.Random;

public class Dice {

    public static int roll() {
        
        Random m = new Random();
        int diceFace = m.nextInt(6) + 1;
        return  diceFace;
    }
}
