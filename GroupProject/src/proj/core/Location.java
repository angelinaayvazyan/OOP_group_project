package proj.core;
public class Location implements Cloneable {

    private int place; // 0-40
    private String name;
    private String[] applicableActions;





    public Location (int place, String name, String[] actions) {

        this(place, name);
        setApplicableActions(actions);
    }


    public Location (int place, String name) {

        this.place = place;
        this.name = name;

    }

    private String [] applicableActionsGanerator (String [] actions) {

        if (actions.length > 0) {

            String [] applicableActionsNew = new String [actions.length];
            for (int i = 0; i < actions.length; i++)
                applicableActionsNew[i] = actions[i];
            return applicableActionsNew;
        }
        else
            return new String [0];
    }


    public boolean isActionsAvailable() {

        return this.applicableActions.length > 0;
    }

    public String toString() {

        return name + " : " + place;
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


    public String doAction(String str, Player p) {
        switch (str.charAt(0)) {

            case 'T':

                p.take( Integer.parseInt(str.substring(19)) );
                return "You took the money successfully.";

            case 'P' :

                if( p.pay(Integer.parseInt(str.substring(19))) ) 
                    return "You paid the money successfully.";
                else
                    return "OOPS! You do not have enough money!!!";

            default:

                return "Done.";
        }
    }
    public void setApplicableActions(String [] actions) {

        this.applicableActions = applicableActionsGanerator(actions);
    }

    public String getName() {
        return this.name;
    }

    public int getPlace() {
        return this.place;
    }

    public String [] getApplicableActions() {

        return applicableActionsGanerator(this.applicableActions);
    }

    public int getCountOfApplicableActions() {
        
        return this.applicableActions.length;
    }







}
