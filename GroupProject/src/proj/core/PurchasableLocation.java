package proj.core;

public class PurchasableLocation extends Location {

    private Player owner;
    private int price;
    private int cost;
    private String message; //  " for ranting this street."  // " for using this transportaion." // " for your bills."
    
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
    
        public String doAction(String srt, Player p) {

            String messageOutput = "";
    
            switch (srt.charAt(0)) {

                case 'B':
                    if (p.pay(this.price)) {
    
                        String [] actions = new  String[1];
                        actions[0] = "Pay $ " + this.cost + this.message;
                        this.setApplicableActions(actions);
                        this.owner = p;
                        p.addNumberOfProsperitiesByOne();
                        return p.getNmae() + " bought this " + getName() + ".";
    
                    } else 
    
                        messageOutput = "Dear " + p.getNmae() + ", you do not have enough money for buying  this " + getName() + " . /n";
    
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
    
        public boolean isPurchased() {
    
            if (this.owner == null) 
                return false;
            else
                return true;
        }
    
}
