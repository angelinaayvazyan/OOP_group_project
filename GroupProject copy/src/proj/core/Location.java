package proj.core;
public class Location implements Cloneable {

    private int place; // 0-40
    private String name;
    private String[] applicableActions;
    private String representation;



    public Location (int place) {
        this(place, "Anonimous" );
    }

    public Location (int place, String name) {

        this.place = place;
        this.name = name;
        this.setApplicableActions(new String [0]);
        setRepresentation(this.name + ": " + this.place);
    }

    public void setApplicableActions(String [] actions) {
        if (actions.length > 0) {
            this.applicableActions = new String [actions.length];
            for (int i = 0; i < actions.length; i++)
                this.applicableActions[i] = actions[i];
        }
        else
            this.applicableActions = new String [0];
    }

    public void setRepresentation(String representation) {
        this.representation = representation;
    }
    public boolean isActionsAvailable() {

        return this.applicableActions.length > 0;
    }

    public String toString() {
        return this.representation;
    }

    public Location clone() {
        try {

            Location copy =  (Location) super.clone();
            copy.applicableActions = getApplicableActions();
            return copy;

        } catch (CloneNotSupportedException e) {
            //never reached
            return null;
        }

    }
    public String [] getApplicableActions() {

        String[] applicableActions = new String[this.applicableActions.length];

        for (int i = 0; i < this.applicableActions.length; i++ ) 
            applicableActions[i] = this.applicableActions[i];
    
        return applicableActions;
    }

    public String getName() {
        return this.name;
    }

    public int getPlace() {
        return this.place;
    }
    public String getRepresentation() {
        return this.representation;
    }





}
