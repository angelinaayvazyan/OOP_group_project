package proj.core;
import java.util.Scanner;

public class MonopolyGame {
    
    MonopolyBoard monopolyBoard = new MonopolyBoard();
    private Location[] board = monopolyBoard.TEMPLATE;
    private Player[] players;
    private int numberOfPlayers;
    private int turn;
    private Scanner sc = new Scanner(System.in);

    public void play() {
        configGame();
        startGame();
    }

    //
    private void configGame() {

        System.out.println();
        System.out.println("Game Monopoly has started");
        System.out.println();

        boolean validToStart = false;
        String[] names;

        while ( !validToStart ) {
            try {

                System.out.println("How meny players are you going to be?");
                System.out.println("Please enter positive intager in a 2-6 range.");
                System.out.println("And please names!!!");

                int numberOfPlayers = sc.nextInt();
                names = new String[numberOfPlayers];
                for (int i = 0; i < numberOfPlayers; i++) 
                    names[i] = sc.next();

                playerGenerator(numberOfPlayers, names);
                System.out.println(this.monopolyBoard + " And with " + numberOfPlayers + "players.");
                validToStart = true;

            } catch (NotValidNumberOfPlayerException e) {

                System.out.println(e.getMessage());
                System.out.println("Try again!");
            } 
        }


        print();
        System.out.println("Let see who starts the game!!");
        whoStrats();

    }

    private void startGame() {
        // first hight score vins
        while (!isGameOver()) {
            players[turn].move( rollDice(2, players[turn]) );
            print();

            String [] actions = board[players[turn].getPosition()].applicableActions();
            for (int i = 0; i < actions.length; i++)
                System.out.println(i + " ) " + actions[i]);
            
            System.out.println("Please, select an action, by choosing propriate number.");
            String action  = actions[sc.nextInt()];

            if (action.startsWith("Buy"))
                board[players[turn].getPosition()].buy(players[turn]);
            //else if ( action.startsWith("Auction")
                //board[players[turn].getPosition()].auction(players[turn]);
            print();
                
            turn =( turn + 1 ) % players.length;
        
        }
        
        sc.close();
        System.out.println("End!");

    }

    private void print() {
        
        int squerLanght = 5 + numberOfPlayers;
        int numberOfSquers = 11;

        String line = "";
        for (int i = 1; i <= squerLanght * numberOfSquers  + 1; i++) 
            line += "-";

        String space = "";
        for (int i = 0; i <= (numberOfSquers - 2) * squerLanght - 2; i++) 
            space += " ";

        System.out.println(line);
        
        for( int i = 20; i < 31; i++) 
            System.out.print(printSquer(i));
        System.out.println("|");

        System.out.println(line);

        for( int i = 0; i < 9; i++) 
            System.out.println(printSquer(19 - i)+ "|" + space + printSquer(31 + i) + "|");
        System.out.println(line);

        for( int i = 10; i > -1; i--) { 
            System.out.print(printSquer(i));
        }
        System.out.println("|");
        System.out.println(line);

    }

    private String printSquer(int i) {

        String squer = ("| " + this.board[i] ) + "  ";

        if (i < 10) 
            squer = squer + " ";

        for (Player p: this.players){
            if( p.getPosition() == i )
                squer = squer + p;
            else 
                squer = squer + " ";
        }


        return squer;
    }

    public  int rollDice(int time, Player p) {
        int diceValue = 0;
        String toPrint = null;
        System.out.println("Dear "  + p.getNmae() +  " " + p + " , please, roll the die by prassing any key and enter.");
        if (sc.hasNext()) {
            
            if (time == 2) {
                int diceValueOne = Dice.roll();
                int diceValueTow = Dice.roll();
                toPrint =  diceValueOne + " & " + diceValueTow;
                diceValue = diceValueOne + diceValueTow;
            } else {
                diceValue = Dice.roll();
                toPrint =  diceValue + "";
            }
            System.out.println();
            System.out.println(p.getNmae() +  " " + p + " : dice value " + toPrint);
            sc.next();
        }
        return diceValue;

    }
//________________________________________________________________________________________________________________________________________________________________
    private boolean isGameOver() {
        return false;
    }

    private void  whoStrats() {      
        int maxDiceValue = 0; 
        for (int i  = 0; i < players.length; i++) {

            int diceValue = rollDice(1, players[i]);

            if(maxDiceValue < diceValue) {
                maxDiceValue = diceValue;
                this.turn = i;
            }
        }
 
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


}