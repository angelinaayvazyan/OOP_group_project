package proj.core;

import javax.swing.text.Position;

public class Monopoly {

    private Player[] players;
    private int numberOfPlayers;
    public static final int BOARD_LENGTH = 40;
    private static final Location[] board = new Location[40];


    

    public Monopoly(int numberOfPlayers, String[] names) throws NotValidNumberOfPlayerException { 
        boardGenerator();
        playerGenerator(numberOfPlayers, names);
    }

    private void playerGenerator(int numberOfPlayers, String[] names) throws NotValidNumberOfPlayerException {
        // chacking
        numberOfPlayersCheck(numberOfPlayers, names.length);

        this.numberOfPlayers = numberOfPlayers;
        players = new Player[numberOfPlayers];
        for (int i = 0; i < this.numberOfPlayers; i++)  
            players[i] = new Player(names[i]);
    }



    private void boardGenerator() {

        for (int i = 0; i < BOARD_LENGTH; i++ ) 
            board[i] = new Location(i);
        
    }


    public String toString() {
        return "Monopoly Game with " + numberOfPlayers + " players";
    }

    //accsesors

    public static Location[] getBoard() {
        return board; // final harc
    }

    public int getPlayersCount() {
        return numberOfPlayers;
    }
    public Player [] getPlayers() {
        return 
    }
    // sanity
    private void numberOfPlayersCheck(int numberOfPlayers, int nameCount) throws NotValidNumberOfPlayerException {
        if (numberOfPlayers < 2 || numberOfPlayers > 6 || numberOfPlayers != nameCount) {
            throw new NotValidNumberOfPlayerException();
        }
            
    }


    
}
