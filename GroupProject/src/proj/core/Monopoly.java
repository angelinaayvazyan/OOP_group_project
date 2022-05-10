package proj.core;

import java.util.Random;


public class Monopoly {

    public static final int NUMBER_OF_SQUER = 40;
    private Location[] board = new Location[NUMBER_OF_SQUER];

    private Player[] players;
    private int numberOfPlayers;
    private int turn;

    public Monopoly (int numberOfPlayers, String[] names) throws NotValidNumberOfPlayerException {
        playerGenerator( numberOfPlayers, names ); 
        boardGenerator();
        turn = 0;
    }



    public boolean isGameOver() {
        return false;
    }
    // accsesor
    public Player getPlayerAt(int position) {

        if (position < numberOfPlayers) {
            //return players[position].clone();
            return players[position];
        }  
        else
            return null;
    }

    public int getNumberOfPlayer() {
        return numberOfPlayers;
    }

    public Location getLocationAt(int position) {
        return board[position];
    }

    public boolean hasPositionAt(int i, int j) {
        return i * 11 + j < 40;
    }


    private void playerGenerator(int numberOfPlayers, String[] names) throws NotValidNumberOfPlayerException {
        // chacking
        numberOfPlayersCheck(numberOfPlayers, names.length);

        this.numberOfPlayers = numberOfPlayers;
        players = new Player[numberOfPlayers];
        for (int i = 0; i < this.numberOfPlayers; i++)  
            players[i] = new Player(names[i]);
    }

    private void numberOfPlayersCheck(int numberOfPlayers, int nameCount) throws NotValidNumberOfPlayerException {
        if (numberOfPlayers < 2 || numberOfPlayers > 6 || numberOfPlayers != nameCount) {
            throw new NotValidNumberOfPlayerException();
        }      
    }

    private void boardGenerator() {

        for (int i = 0; i < NUMBER_OF_SQUER; i++ ) {
            switch (i) {
                case 0:

                    String [] actions0 = {"Take your salary $ 200"};
                    board[i] = new Location(i, "GO", actions0);
                    break;

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
                    board[i] = new PurchasableLocation(i, "Street",  " for ranting this street.", 100);
                    break;
                
                case 4:
                    String [] actions2 = {"Pay your new tax $ 200"};
                    board[i] = new Location(i, "IncomeTax", actions2);
                    break;

                case 38:
                    String [] actions38 = {"Pay your new tax $ 100"};
                    board[i] = new Location(i, "LuxuryTex", actions38);
                    break;

                case 2:
                case 7:
                case 17:
                case 22:
                case 33:
                case 36:

                    String [] actions36 = { "Take your bonus  $ 100",
                                            "Take your bonus  $ 50",
                                            "Pay your panalty $ 70",
                                            "Pay your panalty $ 35"
                    };
                    board[i] = new Chance(i,  actions36);
                    break;

                case 5:
                case 15:
                case 25:
                case 35:
                
                    board[i] = new PurchasableLocation(i, "Transportation", " for using this transportaion.", 200); 
                    break;

                case 10:

                    String [] actions10 = {"Visit your friends in jail!", "I dont want to vist anyone."};
                    board[i] = new Location(i, "Jail", actions10); 
                    break;

                case 20:

                    String [] actions20 = {"You can park here free."};
                    board[i] = new Location(i, "Parking", actions20); 
                    break;

                case 30:
                    board[i] = new GoToJail(i);
                    break;


                case 12:
                case 28:

                    board[i] = new PurchasableLocation(i, "Utility", " for your bills." , 150); 
                    break;



                default:
                    board[i] = new Location(i, "No.");
                    break;
            }
            
        }   
    }

    public static int dice() {
        
        Random m = new Random();
        int diceFace = m.nextInt(6) + 1;
        return  diceFace;
    }
    public String toString() {
        return "Monopoly Game with " + numberOfPlayers + " players.";
    }

    public int getTurn() {
        return  this.turn;
    }
    public void setNextTurn() {
        this.turn = (this.turn + 1) % numberOfPlayers;
    }

    public void  whoStrats() {  

        int maxDiceValue = 0; 
        for (int i  = 0; i < getNumberOfPlayer(); i++) {

            int diceValue = players[i].getDiceVlaue();

            if(maxDiceValue < diceValue) {
                maxDiceValue = diceValue;
                this.turn = i;
            }
        }
 
    }



}
