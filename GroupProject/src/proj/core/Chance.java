package proj.core;

import java.util.Random;


public class Chance extends Location {
    private Random rand = new Random();


    public Chance(int place, String[] actions) {

        super(place, "Chance", actions);
    }


    public String[] getApplicableActions() {

        String[] action = { super.getApplicableActions()[rand.nextInt(getCountOfApplicableActions())] };
    
        return action;
    }
}


    

