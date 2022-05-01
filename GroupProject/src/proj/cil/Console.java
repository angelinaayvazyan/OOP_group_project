package proj.cil;
import java.util.Scanner;
import java.util.InputMismatchException;

import proj.core.Location;
import proj.core.Monopoly;
import proj.core.NotValidNumberOfPlayerException;

public class Console {
    Monopoly game;
    Location[] board = Monopoly.getBoard();
    Scanner sc = new Scanner(System.in);

    public void startGame() {

        System.out.println();
        System.out.println("Game Monopoly has started");
        System.out.println();

        boolean validToStart = false;
        int numberOfPlayer;
        String[] names;

        while ( !validToStart ) {
            try {

                System.out.println("How meny players are you going to be?");
                System.out.println("Please enter positive intager in a 2-6 range.");
                System.out.println("And please names!!!");

                numberOfPlayer = sc.nextInt();
                names = sc.nextLine().split(" ");

                this.game = new Monopoly(numberOfPlayer, names);
                System.out.println(this.game);
                validToStart = true;

            } catch (NotValidNumberOfPlayerException e) {

                System.out.println(e.getMessage());
                System.out.println("Try again!");

            } 

        }


        print();
        
        sc.close();
        System.out.println("End!");

    }

    private void print() {
        int squerLanght = 5;
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
        String squer = ("| " + board[i]) + " ";
        if (i < 10) 
            squer = squer + " ";

        return squer;
    }



}
