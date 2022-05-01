package proj.core;
public class Location implements Cloneable {

    private int place; // 0-40
    private String name;

    public Location (int place) {
        this.place = place;
        this.name = (this.getClass() + " ");
    }

    public Location (int place, String name) {
        this.place = place;
        this.name = name;
    }

    //public abstract Action[] availableActions();

    public String toString() {
        return this.place + "";
    }

    public Location clone() {
        return new Location(place, name);
    }


}
