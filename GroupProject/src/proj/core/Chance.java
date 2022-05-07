package proj.core;

import java.util.Random;

public class Chance{


    private static int surprise(Player player){
        Random m = new Random();
        int x = (m.nextInt(10) + 1)*10;
        player.addOrSubtractMoney(x);
        return x;
    }

}
