package proj.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import proj.core.Location;
import proj.core.Monopoly;
import proj.core.NotValidNumberOfPlayerException;
import proj.core.Player;



public class MonoployUI extends JFrame {


    
    private Monopoly game;
    private int numberOfPlayers;
    private String[] names;
    private SquerBoard [][] squerBoards;
    private JPanel innerPanel;



    
    public MonoployUI() {

        System.out.println();
        System.out.println("Game Monopoly has started..");
        System.out.println();


        setMaximumSize(new Dimension(980,653));
        setMinimumSize(new Dimension(980,653));
        setTitle("Monoploy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLayout(new BorderLayout());

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
        JLabel picLabel = new JLabel(new ImageIcon("proj/imgFiles/logo.png"));
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

        for (int i = 0; i < 10; i++) {

            if (i == 2) 
                panelInput.add(playerCountLable);

            else if( i == 3)
                panelInput.add(box );

            else if (i == 6)
                panelInput.add(label);
            
            else if (i == 7)
                panelInput.add(test);

            else 
                panelInput.add(new JPanel());
        }



        innerPanel.add(panelInput);


                

        // 3 : 1 
        JPanel enterPanel = new JPanel();
        JButton enterButton = new JButton("Enter");

        enterPanel.add(enterButton);
        enterPanel.setBackground(Color.WHITE);
                
        innerPanel.add(enterPanel);

        // 4 : 1
        JPanel messagePanel =  new JPanel();
        messagePanel.setBackground(Color.WHITE);
        innerPanel.add(messagePanel);
        add(innerPanel, BorderLayout.CENTER);
        enterButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                        
                Boolean validToStart = false;

                String inputeName = test.getText();
                if (Character.isWhitespace( inputeName.charAt(0) ) )
                    names = inputeName.substring(1).split("\\s+");
                else
                    names = test.getText().split("\\s+");

                numberOfPlayers = (int) box.getSelectedItem();
                try {
                    game =  new  Monopoly(numberOfPlayers, names);
                    validToStart = true;
                } catch (NotValidNumberOfPlayerException exception) { 
                    
                    JLabel message = new JLabel(exception.getMessage());
                            
                    messagePanel.removeAll();  
                    messagePanel.revalidate();
                    messagePanel.add(message);
                } 

                if (validToStart) 
                    startGame();   
            }

        });
    }

    private void startGame() {

        innerPanel.removeAll(); 
        innerPanel.revalidate();
        innerPanel.setLayout(new GridLayout(11, 11));
        innerPanel.setBackground(Color.RED);


        for (int i = 0; i < 11; i ++) {
            for (int j = 0; j < 11; j ++) {


                int positionUi = fromMatrixToArray(i, j);
                // Location
                if (positionUi >= 0) {

                    squerBoards[i][j] = new SquerBoard(i, j, game.getLocationAt(positionUi).getName());
                    playerUpdate(i, j);

                } 
                // dice 
                else if (positionUi == -108) {

                    squerBoards[i][j] = new SquerBoard(i, j);
                    squerBoards[i][j].setLayout(new BorderLayout());
                    // squerBoards[i][j].add(new JLabel(new ImageIcon( "proj/imgFiles/dice.png" )));
                    //squerBoards[i][j].setVisible(false);

                }
                
                else if (positionUi == -30) {
                    squerBoards[i][j] = new SquerBoard(i, j);
                    JLabel playerInfo = new JLabel("Turn");
                    squerBoards[i][j].setLayout(new BorderLayout());
                    playerInfo.setForeground(Color.RED);
                    playerInfo.setHorizontalAlignment(JLabel.CENTER);
                    squerBoards[i][j].add(playerInfo, BorderLayout.SOUTH);
               

                }
                else if (positionUi == -41) {
                    squerBoards[i][j] = new SquerBoard(i, j);
                    messageTurnUpdate(); 

                }

                else if (positionUi % 11 == -3 &&  positionUi > -81) {
                    squerBoards[i][j] = new SquerBoard(i, j);
                    squerBoards[i][j].setLayout(new BorderLayout());
                    int p = ((positionUi / 11) * -1) - 2;

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

                    if (p == -1) {
                        JLabel playerInfo = new JLabel("Piece");
                        playerInfo.setForeground(Color.RED);
                        playerInfo.setHorizontalAlignment(JLabel.CENTER);
                        squerBoards[i][j].add(playerInfo, BorderLayout.SOUTH);
                    }
                    else if( game.getPlayerAt(p) != null ) { 
                        squerBoards[i][j].add(new JLabel(new ImageIcon( ("proj/imgFiles/" +  game.getPlayerAt(p).getId() + ".png") )));
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
    
                            JLabel playerInfo = new JLabel("Property");
                            squerBoards[i][j].setLayout(new BorderLayout());
                            playerInfo.setForeground(Color.RED);
                            playerInfo.setHorizontalAlignment(JLabel.CENTER);
                            squerBoards[i][j].add(playerInfo, BorderLayout.SOUTH);
    
                        }
                        else if( game.getPlayerAt(p) != null ) { 
    
                            squerBoards[i][j].setInfo(game.getPlayerAt(p).getNumberOfProperties() + "");
                   
                        }
                }
                else if (positionUi == - 91) {
                    squerBoards[i][j] = new SquerBoard(i, j);
                    //JButton start = new JButton("start");
                    squerBoards[i][j].setInfo("Strat");
                    squerBoards[i][j].addActionListener(new ActionListener() {


                        public void actionPerformed(ActionEvent e) {
                            SquerBoard squerBoard = (SquerBoard) e.getSource();

    
                            squerBoards[8][9].add(new JLabel("Press"));
                            squerBoards[9][9].add(new JLabel(new ImageIcon( "proj/imgFiles/dice.png" )));
                            squerBoards[9][9].addActionListener(new ActionListener() {

                                public void actionPerformed(ActionEvent e) {

                                    //dice rolling
                                    int value = Monopoly.dice();
                     
                                    //whos turn
                                    int turn = game.getTurn();
        
                                    if ( game.getPlayerAt(turn).isinJail() ) {
                                        jailMessage();
                                        game.getPlayerAt(turn).setInJail(false);
                                        
                                        game.setNextTurn();
                                        messageTurnUpdate();
                                    }
                                    else {                                
        
                                        // dice value update
                                        game.getPlayerAt(turn).setDiceVlaue(value);
                                        squerBoards[game.getPlayerAt(turn).getId() + 1][6].setInfo(value + "");
                                        squerBoards[8][9].setText("" + value); 
                                        
                                        // removing player
                                        int positionUi = game.getPlayerAt(turn).getPosition();
                                        int[] matrixPositionOld = fromArrayToMatrix(positionUi);
                                        int [] matrixPositionNew = fromArrayToMatrix(game.getPlayerAt(turn).move(value));
                                        playerUpdate(matrixPositionOld[0], matrixPositionOld[1]);
                                        //add player
                                        squerBoards[matrixPositionNew[0]][matrixPositionNew[1]].add(new JLabel(new ImageIcon( ("proj/imgFiles/" +  game.getPlayerAt(game.getTurn()).getId() + ".png") )));
        
                                        // message
                                        window();
                                        
                                        //new turn
                                        game.setNextTurn();
                                        // new updates
                                        messageTurnUpdate();
                                        messageDiceUpdate();
                                         
                                    }
                                }
                            });
                            messageDiceUpdate();
                            messageTurnUpdate( );
                            squerBoard.setInfo("");




                        }

                        
                    });
                }
                else { 
                    squerBoards[i][j] = new SquerBoard(i, j);
                } 

                squerBoards[i][j].setSize( innerPanel.getPreferredSize() );
                innerPanel.add(squerBoards[i][j]);

            }
 
        }
    }








    private void window(){

        Player player = game.getPlayerAt(game.getTurn());
        Location location = game.getLocationAt(player.getPosition());
        String[] options =  location.getApplicableActions();


        int n = JOptionPane.showOptionDialog(this, "Choose your acction!!",
        "Dear " + game.getPlayerAt(game.getTurn()).getNmae(),
        JOptionPane.YES_NO_CANCEL_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[0]);

        String outputMassage = location.doAction(options[n], player);
        
      
        squerBoards[player.getId() + 1][4].setInfo("$" + player.getMoney());
        squerBoards[player.getId() + 1][5].setInfo(player.getNumberOfProperties() + "");
        //default title and icon
        JOptionPane.showMessageDialog(this, outputMassage );

        if (outputMassage.contains("OOPS") ) {
           
            int maxProperties = -1;
            int winer = -1;
            for( int p = 0; p < game.getNumberOfPlayer(); p++) {
                int numberOfProperties = game.getPlayerAt(p).getNumberOfProperties();
                if (numberOfProperties > maxProperties) {
                    maxProperties = numberOfProperties;
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
    // Updaters
    private void playerUpdate(int i, int j) {

        squerBoards[i][j].removeAll();
        squerBoards[i][j].repaint();

        for (int p = 0; p < game.getNumberOfPlayer(); p++ ){
            Player player = game.getPlayerAt(p);
            
            if(  player.getPosition() == fromMatrixToArray(i, j) ) {
                squerBoards[i][j].add(new JLabel(new ImageIcon( ("proj/imgFiles/" +  player.getId() + ".png") )));

            } 

        }
    }

    private void messageTurnUpdate( ) {

        squerBoards[3][8].removeAll();
        squerBoards[3][8].repaint();
        squerBoards[3][8].add(new JLabel(new ImageIcon("proj/imgFiles/" +  game.getPlayerAt(game.getTurn()).getId() + ".png")));

    }

    private void messageDiceUpdate() {
        
        squerBoards[9][2].setInfo(game.getPlayerAt(game.getTurn() ).getNmae() );
        squerBoards[9][3].setInfo( "please");
        squerBoards[9][4].setInfo( "roll ");
        squerBoards[9][5].setInfo( "the die.");
        

    }



    // Transformations

    // ui bord => monopoly bord
    private int fromMatrixToArray(int i, int j) {
        int ij = i * 11 + j;
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
    private int[] fromArrayToMatrix (int ij) {
        
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
}
