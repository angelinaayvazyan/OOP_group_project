package proj.core;

/**
 * A mutable class Player that implements Cloneable, represents the player of monopoly.
 * Contains a non-static method isinJail that indicates whether a player is in jail or not
 * setInJail non-static method that sets the player in jail
 * move non-static method that generates the move of the player
 * take non-static method by which the player receives money during the game
 * pay non-static method by which the player pays money during the game
 * addNumberOfPropertiesByOne non-static method which increases player's number of properties by one
 * toString method for generating string representation of the player by its id
 * accessors and mutators for the instance variables
 *
 */
public class Player implements Cloneable {

    /**
     * Static integer type count that changes as soon as a new player is generated.
     * String type name indicating the name of the player
     * Integer type id indicating the id of the player
     * Integer type position indicating the position of the player
     * Integer type money indicating the amount of money the player has
     * Integer type needToSetInJail that keeps the count of how many rounds the player will be in jail(is used in further setInJail method)
     * Integer type diceValue which is a random number generated by the dice of the game
     * Integer type numberOfProperties indicating the number of properties player owns
     *
     */
    private static int count = 0;
    private String name;
    private int id;
    private int position; 
    private int money;
    private int needToSetInJail;
    private int diceValue;
    private int numberOfProperties;

    /** A parameterized constructor,with argument name of type string
     * initializes the instance variable indicating the name of the player and sets values to the rest of the instance variables
     */

    public Player(String name) {

        count = count  + 1;

        this.name = name;
        this.id = count;
        this.position = 0; 
        this.diceValue = 0;
        this.numberOfProperties = 0;
        this.money = 1500; //in $s
        this.needToSetInJail = 0;

    }

    /** A parameterized constructor,with argument name of type string
     * initializes all the instance variables
     */
    public Player(Player that) {

        this.name = that.name;
        this.id = that.id;
        this.position = that.position; 
        this.diceValue = that.diceValue;
        this.money = that.money;
        this.numberOfProperties = that.numberOfProperties;
        this.needToSetInJail = that.needToSetInJail;
    }

    /**
     * Mutator method for the player.
     * @return A string representation of the player according to its id.
     */
    public String toString() {
        return  "\u001B[3" + this.id + "m" + ((char)(9311 + this.id)) + "\u001b[0m";
    }

    /**
     * Accessor method for the position of a player.
     * @return position (int).
     */

    public int getPosition() {
        return this.position;
    }

    /**
     * Accessor method for the name of a player.
     * @return name (String).
     */
    public String getNmae() {
        return this.name;
    }

    /**
     * Accessor method for the money a player has.
     * @return money (int).
     */
    public int getMoney() {
        return this.money;
    }

    /**
     * Accessor method for the number of properties a player owns.
     * @return numberOfProperties (int).
     */
    public int getNumberOfProperties() {
        return this.numberOfProperties;
    }

    /**
     * Accessor method for the id of a player.
     * @return id (int).
     */
    public int getId() {
        return this.id;
    }

    /**
     * Accessor method for the generated dice value.
     * @return diceValue (int).
     */
    public int getDiceVlaue() {

        return this.diceValue;
    }

    /**
     * Mutator method for the diceValue.
     * @param value actions. the array of actions.
     */

    public void setDiceVlaue(int value) {
        this.diceValue = value;
    }

    /**
     * A non-static method that increases the number of properties a player owns by one
     */
    public void addNumberOfPropertiesByOne() {
        this.numberOfProperties = this.numberOfProperties  + 1;
    }

    /**
     * A non-static method that given a value decreases the amount of money the player has by subtracting that value from player's money
     * @param value (int)
     * @return true or false
     */
    public boolean pay(int value) {
        if ( this.money  >= value) {
            this.money = this.money - value;
            return true;
        }
        else 
            return false;
    }

    /**
     * A non-static method that given a value increases the amount of money the player has by adding that value to player's money
     * @param value (int)
     */
    public void take (int value) {

        this.money = money + value;
    }


    public Player clone() {

        try {
            return (Player) super.clone();
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
            return this.id == otherPlayer.id; // unique identifier :) for our implamantation
        }

    }

    /**
     * A non-static method that given the number of steps adds it to the player's position and divides it to 40(the number of squares on the board) by taking the remainder
     * therefore gives new value to the player's current position
     * @param steps (int)
     * @return position (int)
     */

    public int move(int steps) {

        this.position = (this.position + steps) % 40;
        return this.position;
    }

    /**
     * A non-static method that sets the player in jail by checking whether the player is in jail or no, if yes it  initially sets the needToSetInJail instance variable equal to 3, and in the other case starts subtracting from it
     * @param b (boolean)
     */

    public void setInJail(boolean b) {

        if ( ! isinJail() && b) 
            needToSetInJail = 3;
        else if ( isinJail() && !b)
            needToSetInJail --;
    }

    /**
     * A non-static method that checks whether the player is in jail or no
     */
    public boolean isinJail() {

        return needToSetInJail != 0;
    }

}