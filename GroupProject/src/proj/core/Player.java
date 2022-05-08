package proj.core;

public class Player implements Cloneable {
    private static int count = 0;
    private String name;
    private int id;
    private int position; 
    private int money;
    private int numberofHouses;

    public Player(String name) {

        count = count  + 1;
        this.name = name;
        this.id = count;
        this.position = 0; 
        this.money = 10000; //in $s
        this.numberofHouses = 0;

    }

    public Player(Player that) {

        that.name = this.name;
        that.id = this.id;
        that.position = this.position; 
        that.money = this.money;
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
    public int getId() {
        return this.id;
    }

    public void pay(int value) {
        if ( this.money  >= value) {
            this.money = this.money - value;
            System.out.println("Succesfuly paid. - . "  + value );
        }
        else {
            System.out.println("Not enaf money.");
        }
        
    }

    public void take(int value) {
        this.money = this.money + value;
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

    public int getnumberofHouses(){
        return this.numberofHouses;
    }

    public void move(int steps) {
        this.position = (this.position + steps) % 40;
    }

    public void addOrSubtractMoney(int amount){
        this.money = money + amount;
    }

    public void increaseRent(int amount){
        money= money + (amount * getnumberofHouses());
    }


}
