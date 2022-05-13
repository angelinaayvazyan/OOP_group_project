package proj.core;
/**
 * A mutable class for representing the action going to jail in monopoly. Contains a nonstatic method
 * that prints all the actions that a player can do with a when being in a Location of gotoJail.
 */

public class GoToJail extends Location {

    /** A parameterized constructor,with arguments of type int.
     * The constructor initializes the position, gives the name, initializes the array of type String, containing
     * all applicable actions .
     */
    public GoToJail(int position) {

        super(position, "GoToJail");
        String[] applicableActions = new String[2];
        applicableActions[0] = "Go To Jail!!!";
        applicableActions[1] = "Pay $50 and you are free.";
        setApplicableActions(applicableActions);
    }

    /**
     * A non-static method that takes two arguments, with type String and Player,
     * and according to the first character of the given string prints the appropriate message to the user
     */

    public String doAction(String str, Player p) {

        String outputMessage = "";
        switch (str.charAt(0)) {
            
            case 'P':
                if (p.pay(50))
                    return "You are free!";

                else 
                    outputMessage = "You do not have enough money. ";
            
            case 'G':
                p.setInJail(true);
                return outputMessage + "Sadly , You will stay  in the jail for 3 rounds.";

            default:
                return "Done";
        }
       
    }
}