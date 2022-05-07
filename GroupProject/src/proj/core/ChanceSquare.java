package proj.core;

import java.util.Random;

public class ChanceSquare extends Location {
    private String[] cards;
    Random rand;

    public ChanceSquare(int place) {
        super(place, "Chance");
        String[] actions = new String[1];
        actions[0] = "Choose chanc caed";
        this.setApplicableActions(actions);
        genarteCards();
        rand = new Random();

    }

    private void genarteCards() {
        cards[0] = "Pay $30";
        cards[1] = "Pay $10";
        cards[2] = "Take $20";
        cards[3] = "Take $30";
    }

    public String[] getApplicableActions() {
        String[] action = {cards[rand.nextInt(cards.length)]};
        return action;
    }

    public String doAction(String str, Player p) {
        if (str.charAt(0) == 'P') {
            p.pay(Integer.parseInt(str.substring(5)));
            return p.toString() + " paid $ " + str.substring(5);
        } else if (str.charAt(0) == 'T') {
            p.take(Integer.parseInt(str.substring(6)));
            return p.toString() + " take $ " + str.substring(6);
        }
    }
}




}
