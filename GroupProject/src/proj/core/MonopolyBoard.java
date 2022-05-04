package proj.core;

public class MonopolyBoard {
    
    public static final int NUMBER_OF_SQUER = 40;
    public final Location[] TEMPLATE = new Location[NUMBER_OF_SQUER];
     

    public MonopolyBoard()  { 
        boardGenerator();
    }

    private void boardGenerator() {

        for (int i = 0; i < NUMBER_OF_SQUER; i++ ) 
            TEMPLATE[i] = new Location(i);  
    }


    public String toString() {
        return "Monopoly Board with " + NUMBER_OF_SQUER + " squers.";
    }
    
}
