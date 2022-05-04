package proj.core;
public class Location implements Cloneable {

    private int place; // 0-40
    private String name;
    private String[] appendableActions;
    private Player owner;
    private String representation;
    private int price;
    private int rent; 

    public Location (int place) {
        this.owner = null; // no owner
        this.price = 100; // for now
        this.rent = 10; // for now
        this.place = place;
        this.name = "Anonimuse";

        this.appendableActions = new String[2];
        this.appendableActions[0] = "Buy " + this.name + " street. Price : " + this.place;
        this.appendableActions[1] = "Auction";

        this.representation = this.place + ""; // in the borad


    }

    public Location (int place, String name) {
        this.place = place;
        this.name = name;
    }

    //public abstract Action[] availableActions();

    public String toString() {
        return this.representation;
    }

    public Location clone() {
        try {
            return (Location) super.clone();
        } catch (CloneNotSupportedException e) {
            //never reached
            return null;
        }

    }
    public String [] applicableActions() {
        String[] appendableActions = new String[this.appendableActions.length];
        for (int i = 0; i < this.appendableActions.length; i++ ) 
            appendableActions[i] = this.appendableActions[i];
    
        return appendableActions;
    }

    public void buy(Player p) {
        appendableActions = new  String[1];
        appendableActions[0] = "Pay the rent. Rent " + this.rent;

        this.owner = p;
        this.owner.pay(this.price);
        this.representation =  "\u001B[3" + this.owner.getId()  + "m" + (this.place) + "\u001b[0m";
    }
    public boolean isBought() {

        if (this.owner == null) 
            return false;
        else
            return true;
    }


}
