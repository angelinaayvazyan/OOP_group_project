package proj.core;

public class GoToJail extends Location {


    public GoToJail(int position) {
        super(position, "Go To Jail");
        String[] applicableActions = new String[2];
        applicableActions[0] = "Go To Jail";
        applicableActions[1] = "Pay $50 and you are free";
        setApplicableActions(applicableActions);
    }

    public String doAction(String a, Player p) {
        if (a.charAt(0) == 'P') {
            p.pay(50);
            return "You are free!";
        } else if (a.charAt(0) == 'G') {
            p.setInJail(true);
            return "You are in Jail for 3 rounds";
        }


    }
}
