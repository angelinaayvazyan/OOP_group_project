package proj.core;

public class GoToJail extends Location {


    public GoToJail(int position) {

        super(position, "GoToJail");
        String[] applicableActions = new String[2];
        applicableActions[0] = "Go To Jail!!!";
        applicableActions[1] = "Pay $50 and you are free.";
        setApplicableActions(applicableActions);
    }

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
                return outputMessage + "You will stay  in the jail for 3 rounds.";

            default:
                return "Done";
        }
       
    }
}