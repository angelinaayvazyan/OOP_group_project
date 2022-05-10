package proj.core;

public class Player implements Cloneable {
    
    private static int count = 0;
    private String name;
    private int id;
    private int position; 
    private int money;
    private int needToSetInJail;
    private int diceValue;
    private int numberOfProsperities;

    public Player(String name) {

        count = count  + 1;

        this.name = name;
        this.id = count;
        this.position = 0; 
        this.diceValue = 0;
        this.numberOfProsperities = 0;
        this.money = 1500; //in $s
        this.needToSetInJail = 0;

    }

    public Player(Player that) {

        this.name = that.name;
        this.id = that.id;
        this.position = that.position; 
        this.diceValue = that.diceValue;
        this.money = that.money;
        this.numberOfProsperities = that.numberOfProsperities;
        this.needToSetInJail = that.needToSetInJail;
    }

    public String toString() {
        return  "\u001B[3" + this.id + "m" + ((char)(9311 + this.id)) + "\u001b[0m";

    }
    public int getPosition() {
        return this.position;
    }

    public String getNmae() {
        return this.name;
    }
    public int getMoney() {
        return this.money;
    }
    public int getNumberOfProsperities() {
        return this.numberOfProsperities;
    }
    public int getId() {
        return this.id;
    }

    public int getDiceVlaue() {

        return this.diceValue;
    }

    public void setDiceVlaue(int value) {
        this.diceValue = value;
    }
    public void addNumberOfProsperitiesByOne() {
        this.numberOfProsperities = this.numberOfProsperities  + 1;
    }
    public boolean pay(int value) {
        if ( this.money  >= value) {
            this.money = this.money - value;
            return true;
        }
        else 
            return false;
    }
    public void take (int value) {

        this.money = money + value;
    }

    public Player clone() {

        try {
            Player copy = (Player) super.clone();
            //copy.money = (money) copy.money.clone(); 
            return copy; 
        } catch (CloneNotSupportedException e) {
            //never reached
            return null;
        }
    }
    public boolean equals(Object other) {
        if ( other == null)
            return false;
        else if (this.getClass() != other.getClass()) 
            return false;
        else {
            Player otherPlayer = (Player) other;
            return this.id == otherPlayer.id; // unique identifier :)
        }

    }

    public int move(int steps) {
        this.position = (this.position + steps) % 40;
        return this.position;
    }

    public void setInJail(boolean b) {

        if ( needToSetInJail  == 0 && b) 
            needToSetInJail = 3;
        else if (needToSetInJail  != 0 && !b)
            needToSetInJail --;
        
        
    } 
    public boolean isinJail() {
        return needToSetInJail != 0;
    }


}
