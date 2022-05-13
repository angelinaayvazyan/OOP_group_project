package proj.core;

/**
 * A mutable class for representing chance card of monopoly. Contains a nonstatic method
 * that generate all the applicable actions that a player can do with a chance card.
*/

import java.util.Random;

public class Chance extends Location {

    /** A parameterized constructor,with arguments with type int and array of Strings, initializes the place, initializes the name, and actions by calling the parent constructor.
     */
    public Chance(int place, String[] actions) {

        super(place, "Chance", actions);
    }


    /**
     * A non-static method that takes no arguments,
     * generates a random number, and gives that random number as an index to parent's applicable actions accessor, which all is being stored inside an array with type String inside an array named action.
     * Returns that array named action.
     */
    public String[] getApplicableActions() {
        
        Random rand = new Random();

        String[] action = { super.getApplicableActions()[rand.nextInt(getCountOfApplicableActions())] };
    
        return action;
    }
}


    

