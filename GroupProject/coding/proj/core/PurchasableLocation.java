package proj.core;

/**
 * A mutable class for representing the locations that can be bought in the game.
 * Contains a nonstatic method doAction which gives a String message according to which player is said what to do
 * isPurchased non-static method that indicates whether the location is purchased or not
 * that generate all the applicable actions that a player can do with a chance card.
 */

public class PurchasableLocation extends Location {

    /**
     * Player type owner that indicates who is the owner of the purchased location
     * Integer type price indicating the price of the purchasable location
     * Integer type cost indicating how much the other players should pay when they come to the purchased location of a certain player
     * String type message indicating what to do with certain purchased locations (to pay the bills, pay for transportation)
     */

    private Player owner;
    private int price;
    private int cost;
    private String message; //  " for ranting this street."  // " for using this transportaion." // " for your bills."

    /** A parameterized constructor,with arguments place of type int, price of type int, name and message of types String,
     * initializes some of the instance variables, gives initial values to the instance variables owner and cost
     */

    public PurchasableLocation(int place, String name, String message, int price) {

            super(place, name);
            this.owner = null; // no owner
            this.price = price; 
            this.cost = (int) (price * 0.3); 
            this.message = message;
    
            String [] actions = new String[2];
            actions[0] = "Buy this " + this.getName() + " . Price : $" + this.price + " .";
            actions[1] = "Pay $ " + this.cost + this.message;
            setApplicableActions(actions);
      
    }

    /**
     * A non-static method that given the first letter of a string, tells what has been done/ what can be done with the purchasable location
     * @param srt (String)
     * @param p (Player)
     * @return corresponding message(String)
     */

        public String doAction(String srt, Player p) {

            String messageOutput = "";
    
            switch (srt.charAt(0)) {

                case 'B':
                    if (p.pay(this.price)) {
    
                        String [] actions = new  String[1];
                        actions[0] = "Pay $ " + this.cost + this.message;
                        this.setApplicableActions(actions);
                        this.owner = p;
                        p.addNumberOfPropertiesByOne();
                        return p.getNmae() + " bought this " + getName() + ".";
    
                    } else 
    
                        messageOutput =  p.getNmae() + ", you do not have enough money for buying  this " + getName() + " .";
    
                case 'P' : 
                        if (p.equals(owner))  // would never reached if is isPurchased() - false

                            return p.getNmae() + " can use this" + getName() + "free :).";
                            
                        else if (p.pay(this.cost)) {
                            if (isPurchased())
                                owner.take(this.cost);
                            return messageOutput + "You paid" + this.message;

                        } else 

                            return "OOPS!! You dont have enough money" + this.message +" Not taklking about that " + messageOutput;
                default:
                // for complier  
                 return "Done";
    
            }
       
           
        }

    /**
     * A non-static method that indicates whether the purchasable location was already purchased or no (whether it has owner or not)
     * @return true or false(boolean)
     */
    
        public boolean isPurchased() {
    
            if (this.owner == null) 
                return false;
            else
                return true;
        }
    
}
