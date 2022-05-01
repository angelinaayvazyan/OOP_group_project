package proj.core;

public class Player {
    private static int count = 0;
    private String name;
    private int id;
    private int position; 
    private int money;

    public Player(String name) {

        count =+ 1;
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

        return (char)(9311 + this.id)+ "";

    }

}
