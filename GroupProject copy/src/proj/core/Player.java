package proj.core;

public class Player implements Cloneable {
    private static int count = 0;
    private String name;
    private int id;
    private int position; 
    private int money;

    public Player(String name) {

        count = count  + 1;
        this.name = name;
        this.id = count;
        this.position = 0; 
        this.money = 10000; //in $s

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

    public void move(int steps) {
        this.position = (this.position + steps) % 40;
    } 


}
