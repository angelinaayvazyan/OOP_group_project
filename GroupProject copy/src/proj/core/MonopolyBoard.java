package proj.core;

public class MonopolyBoard {
    
    public static final int NUMBER_OF_SQUER = 40;
    public final Location[] TEMPLATE = new Location[NUMBER_OF_SQUER];
     

    public MonopolyBoard()  { 
        boardGenerator();
    }

    private void boardGenerator() {

        for (int i = 0; i < NUMBER_OF_SQUER; i++ ) {
            switch (i) {
                case 1:
                case 3:
                case 6:
                case 8:
                case 9:
                case 11:
                case 13:
                case 14:
                case 16:
                case 18:
                case 19:
                case 21:
                case 23:
                case 24:
                case 26:
                case 27:
                case 29:
                case 31:
                case 32:
                case 34:
                case 37:
                case 39:
                    TEMPLATE[i] = new Street(i, "St.");
                    break;
            
                default:
                    TEMPLATE[i] = new Location(i, "No.");
                    break;
            }
            
        }   
    }


    public String toString() {
        return "Monopoly Board with " + NUMBER_OF_SQUER + " squers.";
    }
    
}
