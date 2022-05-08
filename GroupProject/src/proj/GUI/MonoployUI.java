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
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicBorders.MenuBarBorder;

import proj.core.Monopoly;
import proj.core.NotValidNumberOfPlayerException;
import proj.core.Player;



public class MonoployUI extends JFrame {


    
    private Monopoly game;
    private int numberOfPlayers;
    private String[] names;
    private JPanel innerPanel;
    private SquerBoard [][] squerBoards;

    
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
                            messagePanel.add(message);
                            messagePanel.repaint();
                            innerPanel.repaint();
                            System.out.println("Try again!");
                        }  
                        if (validToStart) {
                            startGame();

                        }
                        


                        

                    }
                });


        

    }

    private void startGame() {
        innerPanel.removeAll(); 
        innerPanel.revalidate();
        innerPanel.setLayout(new GridLayout(11, 11));
        innerPanel.setBackground(Color.RED);
        //innerPanel.setSize(2200, 2200);
        //innerPanel.add(new SquerBoard(0, 0, "GO"));
        for (int i = 0; i < 11; i ++) {
            for (int j = 0; j < 11; j ++) {

                int positionUi = positionUiGanerator(i * 11 + j);
                if (positionUi >= 0) {
                    squerBoards[i][j] = new SquerBoard(i, j, game.getLocationAt(positionUi).getName());
                    for (int p = 0; p < game.getNumberOfPlayer(); p++ ){
                        Player player = game.getPlayerAt(p);
                        if(  player.getPosition() == positionUi ) {
      
                            squerBoards[i][j].add(new JLabel(new ImageIcon( ("GroupProject/src/proj/imgFiles/" +  player.getId() + ".png") )));
                        } 

                    }
                }
                else { 
                    squerBoards[i][j] = new SquerBoard(i, j);
                } 

                squerBoards[i][j].setSize( innerPanel.getPreferredSize() );
                
                innerPanel.add(squerBoards[i][j]);

            }
        }


        //innerPanel.repaint(); 
        
        // innerPanel.setVisible(true);

    }

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
            return -1;
    }
    
        

}
