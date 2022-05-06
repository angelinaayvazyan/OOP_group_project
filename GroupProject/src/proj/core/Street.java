package proj.core;

public class Street extends Location {

    private Player owner;
    private int price;
    private int rent; 

    public Street(int place, String name) {
        super(place, name);
        this.owner = null; // no owner
        this.price = 100; // for now
        this.rent = 10; // for now

        String [] actions = new String[2];
        actions[0] = "Buy " + this.getName() + " street. Price : " + this.price + "  $.";
        actions[1] = "Auction";
        this.setApplicableActions(actions);
  
    }



    public void buy(Player p) {
        // new action/s
        String [] actions = new  String[1];
        actions[0] = "Pay the rent. Rent " + this.rent;
        this.setApplicableActions(actions);

        p.pay(this.price);
        this.owner = p;
        setRepresentation( "\u001B[3" + this.owner.getId()  + "m" + (this.getRepresentation()) + "\u001b[0m");

    }
    public boolean isBought() {

        if (this.owner == null) 
            return false;
        else
            return true;
    }
    
}
