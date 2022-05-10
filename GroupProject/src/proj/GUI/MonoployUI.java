package proj.GUI;
import java.awt.GridLayout;
import java.awt.MenuBar;
import java.awt.Window;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import java.rmi.activation.ActivationDesc;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicBorders.MenuBarBorder;

import proj.core.Location;
import proj.core.Monopoly;
import proj.core.NotValidNumberOfPlayerException;
import proj.core.Player;



public class MonoployUI extends JFrame {


    
    private Monopoly game;
    private int numberOfPlayers;
    private String[] names;
    private JPanel innerPanel;
    private SquerBoard [][] squerBoards;

    JLabel diceValue;

    
    public MonoployUI() {

    
 
        System.out.println();
        System.out.println("Game Monopoly has started..");
        System.out.println();



        setSize(1000, 1000); 
        setTitle("Monoploy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLayout(new BorderLayout());
        //setBackground(Color.DARK_GRAY);
        squerBoards = new SquerBoard[11][11];
        

        

        


                


                //red cover
                String [] positions = {BorderLayout.NORTH, BorderLayout.WEST, BorderLayout.EAST, BorderLayout.SOUTH};
                for (int i = 0; i < 4; i++) {
                    JPanel panelCover = new JPanel();
                    panelCover.setBackground(Color.RED);
                    panelCover.setSize(500, 500);
                    add(panelCover, positions[i]);

                }
                //center
                innerPanel = new JPanel();
                innerPanel.setLayout(new GridLayout(4, 1));



                // 1 : 1
                JLabel picLabel = new JLabel(new ImageIcon("GroupProject/src/proj/imgFiles/logo.png"));
                JPanel picPanel = new JPanel();
                picPanel.setBackground(Color.WHITE);
                picPanel.setLayout(new BorderLayout());
                picPanel.add(picLabel, BorderLayout.CENTER);
                innerPanel.add(picPanel);


                // test count
                JLabel playerCountLable = new JLabel("How meny players are you going to be?");
                playerCountLable.setHorizontalAlignment(JLabel.CENTER);
                //input count
                JComboBox box = new JComboBox();
                for (int i = 2; i <= 6; i ++)
                    box.addItem(i);
                box.setBackground(Color.WHITE);
                box.setForeground(Color.DARK_GRAY);
                // text names
                JLabel label = new JLabel("Please, enter your names.");
                label.setHorizontalAlignment(JLabel.CENTER);
                // inpute names
                JTextField test = new JTextField();
                test.setBackground(Color.WHITE);

                



                // 2 : 1
                JPanel panelInput = new JPanel();
                panelInput.setLayout(new GridLayout(5, 2));

                panelInput.add(new JPanel());
                panelInput.add(new JPanel());

                panelInput.add(playerCountLable);
                panelInput.add(box );

                panelInput.add(new JPanel());
                panelInput.add(new JPanel());


                panelInput.add(label);
                panelInput.add(test);

                panelInput.add(new JPanel());
                panelInput.add(new JPanel());

                innerPanel.add(panelInput);


                


                JPanel enterPanel = new JPanel();
                // enterPanel.setLayout(new GridLayout(3, 1));
                JButton enterButton = new JButton("Enter");

                enterPanel.add(enterButton);
                enterPanel.setBackground(Color.WHITE);
                // 3 : 1 
                innerPanel.add(enterPanel);
                // 4 :1
                JPanel messagePanel =  new JPanel();
                messagePanel.setBackground(Color.WHITE);
                innerPanel.add(messagePanel);
                add(innerPanel, BorderLayout.CENTER);
                enterButton.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        Boolean validToStart = false;
                        names = test.getText().split("\\s+");
                        numberOfPlayers = (int) box.getSelectedItem();
                        try {
                            game =  new  Monopoly(numberOfPlayers, names);
                            validToStart = true;
                        } catch (NotValidNumberOfPlayerException exception) { //harc chi erevum // chi ashxatum
                            JLabel message = new JLabel(exception.getMessage());
                            
                            messagePanel.removeAll();  
                            messagePanel.revalidate();
                            // messagePanel.setLayout(new GridLayout(3, 1));

                            messagePanel.add(message);

                            System.out.println("Try again!");
                        }  
                        if (validToStart) {
                            startGame();

                        }
                        


                        

                    }
                });


        

    }

    private void startGame() {
        // while ( !game.isGameOver() ) {
            printBorad();
        // }

       
        // gamePart1();
 


        //innerPanel.repaint(); 
        
        // innerPanel.setVisible(true);

    }
    private void printBorad() {

        innerPanel.removeAll(); 
        innerPanel.revalidate();
        innerPanel.setLayout(new GridLayout(11, 11));
        innerPanel.setBackground(Color.RED);

        for (int i = 0; i < 11; i ++) {
            for (int j = 0; j < 11; j ++) {
                //System.out.println("aarrrrrr");

                int positionUi = positionUiGanerator(i * 11 + j);
                if (positionUi >= 0) {
                    squerBoards[i][j] = new SquerBoard(i, j, game.getLocationAt(positionUi).getName());
                    for (int p = 0; p < game.getNumberOfPlayer(); p++ ){
                        Player player = game.getPlayerAt(p);
                        if(  player.getPosition() == positionUi ) {
      
                            squerBoards[i][j].add(new JLabel(new ImageIcon( ("GroupProject/src/proj/imgFiles/" +  player.getId() + ".png") )));
                        } 

                    }
                } else if (positionUi == -108) {
                    squerBoards[i][j] = new SquerBoard(i, j);
                    squerBoards[i][j].setLayout(new BorderLayout());
                    JButton dice = new JButton("dice");
                    dice.setIcon(new ImageIcon("GroupProject/src/proj/imgFiles/dice.png"));
                    dice.addActionListener(new ActionListener() {


                        public void actionPerformed(ActionEvent e) {

                            
                            
                            //dice rolling
                            // int value = Monopoly.dice();
                            int value = 10;

                            int turn = game.getTurn();
                            if ( game.getPlayerAt(turn).isinJail() ) {
                                jailMessage();
                                turn = game.getTurn();
                                messageTurn( );
                            }
                            else {                                

                                // dice value update
                                game.getPlayerAt(turn).setDiceVlaue(value);
                                squerBoards[game.getPlayerAt(turn).getId() + 1][6].setInfo(value + "");
                                diceValue.setText("" + value); 
                                // removing player
                                int positionUi = game.getPlayerAt(turn).getPosition();
                                int[] iANDj_old = ijTOiANDj(positionUi);
                                int [] iANDj = ijTOiANDj(game.getPlayerAt(turn).move(value));
                                //squerBoards[iANDj[0]][iANDj[1]].setLayout(new GridLayout(3, 2));
                                squerBoards[iANDj_old[0]][iANDj_old[1]].removeAll();
                                squerBoards[iANDj_old[0]][iANDj_old[1]].repaint();
                                for (int p = 0; p < game.getNumberOfPlayer(); p++ ){
                                    Player player = game.getPlayerAt(p);
                                    System.out.println("old " + positionUi);
                                    System.out.println(p +  ": " + player.getPosition());
                                    
                                    if(  player.getPosition() == positionUi ) {
                                        System.out.println("here");
                                        squerBoards[iANDj_old[0]][iANDj_old[1]].add(new JLabel(new ImageIcon( ("GroupProject/src/proj/imgFiles/" +  player.getId() + ".png") )));
                                    } 
            
                                }

                                //add player
                                squerBoards[iANDj[0]][iANDj[1]].add(new JLabel(new ImageIcon( ("GroupProject/src/proj/imgFiles/" +  game.getPlayerAt(game.getTurn()).getId() + ".png") )));



                                //Custom button text
                                window();

                                game.setNextTurn();
                                messageDice();
                            }

                           
                        }
                        
                        
                    });
                    squerBoards[i][j].add(dice);

                } else if (positionUi == -97) {
                    squerBoards[i][j] = new SquerBoard(i, j);
                    squerBoards[i][j].setLayout(new BorderLayout());
                    squerBoards[i][j].setBackground(Color.WHITE);
                    diceValue = new JLabel("Press the die!");
                    diceValue.setHorizontalAlignment(JLabel.CENTER);
                    squerBoards[i][j].add(diceValue );

                } 
                else if (positionUi % 11 == -3 &&  positionUi > -81) {
                    squerBoards[i][j] = new SquerBoard(i, j);
                    squerBoards[i][j].setLayout(new BorderLayout());
                    int p = ((positionUi / 11) * -1) - 2;

                    //System.out.println(p);
                    if (p == -1) {
                        JLabel playerInfo = new JLabel("Name");
                        playerInfo.setForeground(Color.RED);
                        playerInfo.setHorizontalAlignment(JLabel.CENTER);
                        squerBoards[i][j].add(playerInfo, BorderLayout.SOUTH);
                    }
                    else if( game.getPlayerAt(p ) != null ) { 
                        JLabel playerInfo = new JLabel(game.getPlayerAt(p).getNmae() );
                        squerBoards[i][j].add(playerInfo);
                    }
                } 
                else if (positionUi % 11 == -4 &&  positionUi > -82) {
                    squerBoards[i][j] = new SquerBoard(i, j);
                    
                    int p = ((positionUi / 11) * -1) - 2;
                    //System.out.println(p);
                    if (p == -1) {
                        squerBoards[i][j].setLayout(new BorderLayout());
                        JLabel playerInfo = new JLabel("Wealth");
                        playerInfo.setForeground(Color.RED);
                        playerInfo.setHorizontalAlignment(JLabel.CENTER);
                        squerBoards[i][j].add(playerInfo, BorderLayout.SOUTH);
                    }
                    else if( game.getPlayerAt(p) != null ) { 
                        squerBoards[i][j].setInfo("$" + game.getPlayerAt(p).getMoney() + "");
                    }
                } 
                else if (positionUi % 11 == -2 &&  positionUi > -80) {
                    squerBoards[i][j] = new SquerBoard(i, j);
                    squerBoards[i][j].setLayout(new BorderLayout());
                    int p = ((positionUi / 11) * -1) - 2;
                    //System.out.println(p);
                    if (p == -1) {
                        JLabel playerInfo = new JLabel("Pice");
                        playerInfo.setForeground(Color.RED);
                        playerInfo.setHorizontalAlignment(JLabel.CENTER);
                        squerBoards[i][j].add(playerInfo, BorderLayout.SOUTH);
                    }
                    else if( game.getPlayerAt(p) != null ) { 
                        squerBoards[i][j].add(new JLabel(new ImageIcon( ("GroupProject/src/proj/imgFiles/" +  game.getPlayerAt(p).getId() + ".png") )));
                    }
                } 
                else if (positionUi % 11 == -6 &&  positionUi > -84) {
                    squerBoards[i][j] = new SquerBoard(i, j);
                    
                    int p = ((positionUi / 11) * -1) - 2;
                    
                    if (p == -1) {

                        JLabel playerInfo = new JLabel("Dice");
                        squerBoards[i][j].setLayout(new BorderLayout());
                        playerInfo.setForeground(Color.RED);
                        playerInfo.setHorizontalAlignment(JLabel.CENTER);
                        squerBoards[i][j].add(playerInfo, BorderLayout.SOUTH);

                    }
                    else if( game.getPlayerAt(p) != null ) { 

                        squerBoards[i][j].setInfo(game.getPlayerAt(p).getDiceVlaue() + "");
               
                    }
                } 

                else if (positionUi % 11 == -5 &&  positionUi > -83) {
                        squerBoards[i][j] = new SquerBoard(i, j);
                        
                        int p = ((positionUi / 11) * -1) - 2;
                        
                        if (p == -1) {
    
                            JLabel playerInfo = new JLabel("Prosperities");
                            squerBoards[i][j].setLayout(new BorderLayout());
                            playerInfo.setForeground(Color.RED);
                            playerInfo.setHorizontalAlignment(JLabel.CENTER);
                            squerBoards[i][j].add(playerInfo, BorderLayout.SOUTH);
    
                        }
                        else if( game.getPlayerAt(p) != null ) { 
    
                            squerBoards[i][j].setInfo(game.getPlayerAt(p).getNumberOfProsperities() + "");
                   
                        }
                }
                else if (positionUi == - 91) {
                    squerBoards[i][j] = new SquerBoard(i, j);
                    //JButton start = new JButton("start");
                    squerBoards[i][j].setInfo("Strat");
                    squerBoards[i][j].addActionListener(new ActionListener() {


                        public void actionPerformed(ActionEvent e) {
                            SquerBoard squerBoard = (SquerBoard) e.getSource();

                            /// message 
                            // squerBoards[9][2].setInfo(game.getPlayerAt(game.getTurn() ).getNmae() );
                            // squerBoards[9][3].setInfo( "please");
                            // squerBoards[9][4].setInfo( "roll ");
                            // squerBoards[9][5].setInfo( "the die.");
                            messageDice();
                            
                            squerBoard.setInfo("");

                            // 
                            // //System.out.println(game.getTurn());
                            // if ( game.getTurn() == 0 ) {
                            //     System.out.println("HERE");
                            //     game.whoStrats();
                            //     squerBoards[9][2].setInfo(game.getPlayerAt(game.getTurn() ).getNmae() );
                            //     squerBoards[9][3].setInfo( "please");
                            //     squerBoards[9][4].setInfo( "start ");
                            //     squerBoards[9][5].setInfo( "the game.");

                            // }



                        }
                        
                        
                    });
                    //squerBoards[i][j].add(start);
                }
                else { 
                    squerBoards[i][j] = new SquerBoard(i, j);
                } 

                squerBoards[i][j].setSize( innerPanel.getPreferredSize() );
                innerPanel.add(squerBoards[i][j]);

            }
 
        }
        
        // gamePart1();

    }
    private void gamePart1() {
        printBorad();

        while (numberOfPlayers != 0) {
            squerBoards[8][6].setInfo(game.getPlayerAt(numberOfPlayers - 1).getNmae() + "please, roll the die.");
        }
            game.whoStrats();
            squerBoards[8][6].setInfo(game.getPlayerAt(game.getTurn()).getNmae() + "starts the game.");
    }

    // ui bord => monopoly bord
    private int positionUiGanerator(int ij) {
        if ( ij > 110 ) { 
            return 120 - ij;
        } else if ( ij % 11 == 0 ) {
            return 20 - ij/11;
        } else if (ij <= 10 ) {
            return 20 + ij;
        } else if (ij % 11 == 10) {
            return 30 + ij % 10;
        } else 
            return -1 * ij;
    }
    // monopoly bord => ui bord 
    private int[] ijTOiANDj (int ij) {
        
        if ( ij <= 10 ) { 

            int[] output =  {10, (10 - ij)};
            return output;

        } else if ( ij <= 19  ) {

            int[] output =  {20 - ij, 0};
            return output;

        } else if ( ij <= 30 ) {

            int[] output =  {0,  ij - 20};
            return output;

        } else  {
            int[] output =  { ij % 30, 10};
            return output;
        }
    
    } 
    private void messageDice() {
        
        squerBoards[9][2].setInfo(game.getPlayerAt(game.getTurn() ).getNmae() );
        squerBoards[9][3].setInfo( "please");
        squerBoards[9][4].setInfo( "roll ");
        squerBoards[9][5].setInfo( "the die.");
        messageTurn( );



    }
    private void messageTurn( ) {
        squerBoards[2][8].setInfo( "Turn");
        squerBoards[3][8].removeAll();
        squerBoards[3][8].repaint();
        squerBoards[3][8].add(new JLabel(new ImageIcon("GroupProject/src/proj/imgFiles/" +  game.getPlayerAt(game.getTurn()).getId() + ".png")));

    }


    private void window(){
        Player player = game.getPlayerAt(game.getTurn());
        Location location = game.getLocationAt(player.getPosition());
        String[] options =  location.getApplicableActions();
        int n = JOptionPane.showOptionDialog(this, "Choose your acction!!",
        "Available Actions",
        JOptionPane.YES_NO_CANCEL_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[0]);
        System.out.println(n);
        String outputMassage = location.doAction(options[n], player);
        
      
        squerBoards[player.getId() + 1][4].setInfo("$" + player.getMoney());
        squerBoards[player.getId() + 1][5].setInfo(player.getNumberOfProsperities() + "");
        //default title and icon
        JOptionPane.showMessageDialog(this, outputMassage );
        if (outputMassage.contains("Sadly")) { 
            System.out.println("jaill");

        }
        else if (outputMassage.contains("OOPS") ) {
           
            int maxProperties = -1;
            int winer = -1;
            for( int p = 0; p < game.getNumberOfPlayer(); p++) {
                int numberOfProsperities = game.getPlayerAt(p).getNumberOfProsperities();
                if (numberOfProsperities > maxProperties) {
                    maxProperties = numberOfProsperities;
                    winer = p;
                }    
            }

            if (winer < 0) 
                JOptionPane.showMessageDialog(this,  "Game is ended. No winer");
         
            else 
                JOptionPane.showMessageDialog(this,"Game is ended. " + game.getPlayerAt(winer).getNmae() + " wines.");

            System.exit(0);
        }


    }
    private void jailMessage(){
        JOptionPane.showMessageDialog(this,  game.getPlayerAt(game.getTurn()).getNmae() +  " is in jail ((." );
    }

}
