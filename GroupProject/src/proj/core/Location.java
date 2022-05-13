package proj.core;

/**
 * A mutable class that implements Cloneable, represents the location of monopoly. Contains a nonstatic method
 * applicableActionsGanerator that generates an array of String, containing all the applicable actions that a player can do .
 * It also contains an isActionsAvailable method of type boolean, which indicates if the action can be done or no
 * A doAction method that prints  the appropriate action that a player can do, according tot the first charachter of its String parameter
 * The class also contains accessors for each instance variable, and a mutator for one of the variables
 */
public class Location implements Cloneable {

    /**
     * Integer type place instance variable indicating the place of the certain location.
     * String type name indicating the name of the location
     * An array of Strings, applicableActions that indicates all the possible actions that can be made in a certain location
     *
     */
    private int place; // 0-40
    private String name;
    private String[] applicableActions;

    /** A parameterized constructor,with arguments with type int, type String, and an array of Strings, initializes the place, initializes the name, and actions.
     */
    public Location (int place, String name, String[] actions) {

        this(place, name);
        setApplicableActions(actions);
    }

    /** A parameterized constructor,with arguments with type int and type String, which initializes the name, and the place.
     */
    public Location (int place, String name) {

        this.place = place;
        this.name = name;

    }


    /**
     * A non-static method that given a valid array of actions generates and returns all the applicable actions of the location.
     * @param actions An array of actions
     * @return applicableActionsNew (a new array of applicable actions).
     */
    private String [] applicableActionsGanerator (String [] actions) {

        if (actions.length > 0) {

            String [] applicableActionsNew = new String [actions.length];
            for (int i = 0; i < actions.length; i++)
                applicableActionsNew[i] = actions[i];
            return applicableActionsNew;
        }
        else
            return new String [0];
    }

    /**
     * A non-static method that returns true if the action is available and false if it is not available
     * @return true or false.
     */
    public boolean isActionsAvailable() {

        return this.applicableActions.length > 0;
    }

    /**
     * Mutator method for the name and the place of the location.
     * @return A string representation of the location on the board.
     */

    public String toString() {

        return name + " : " + place;
    }

    public Location clone() { 
        try {

            Location copy =  (Location) super.clone();
            copy.applicableActions = getApplicableActions();
            return copy;

        } catch (CloneNotSupportedException e) {
            //never reached
            return null;
        }

    }
    /**
     * A non-static method that given a valid string and a player, prints the corresponding String message to the user
     * @param str,p
     * @return a String message.
     */

    public String doAction(String str, Player p) {
        int moneyPosition = 19;
        switch (str.charAt(0)) {

            case 'T':

                p.take( Integer.parseInt(str.substring(moneyPosition)) );
                return "You took the money successfully.";

            case 'P' :

                if( p.pay(Integer.parseInt(str.substring(moneyPosition))) ) 
                    return "You paid the money successfully.";
                else
                    return "OOPS! You do not have enough money!!!";

            default:

                return "Done.";
        }
    }
    /**
     * Mutator method for the applicableActions of the location.
     * @param actions actions. the array of actions.
     */
    public void setApplicableActions(String [] actions) {

        this.applicableActions = applicableActionsGanerator(actions);
    }

    public String getName() {
        return this.name;
    }

    /**
     * Accessor method for the place of the location.
     * @return place.
     */

    public int getPlace() {
        return this.place;
    }

    /**
     * Accessor method for the applicableActions.
     * @return applicable actions.
     */
    public String [] getApplicableActions() {

        return applicableActionsGanerator(this.applicableActions);
    }

    /**
     * Accessor method for the number of the applicable actions.
     * @return The length of the applicableActions array (the number of all applicable actions).
     */

    public int getCountOfApplicableActions() {
        
        return this.applicableActions.length;
    }
}
