package proj.GUI;
import java.awt.GridLayout;
import java.awt.MenuBar;
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



public class MonoployUI extends JFrame {


    
    private Monopoly game;
    int numberOfPlayers;
    String[] names;

    
    public MonoployUI() {
 
        System.out.println();
        System.out.println("Game Monopoly has started");
        System.out.println();



        setSize(1000, 1000); 
        setTitle("Monoploy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLayout(new BorderLayout());
        //setBackground(Color.DARK_GRAY);
        

        

        


                


                //red cover
                String [] positions = {BorderLayout.NORTH, BorderLayout.WEST, BorderLayout.EAST, BorderLayout.SOUTH};
                for (int i = 0; i < 4; i++) {
                    JPanel panelCover = new JPanel();
                    panelCover.setBackground(Color.RED);
                    panelCover.setSize(500, 500);
                    add(panelCover, positions[i]);

                }
                //center
                JPanel innerPanel = new JPanel();
                innerPanel.setLayout(new GridLayout(3, 1));



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
                enterPanel.setLayout(new GridLayout(3, 1));
                JButton enterButton = new JButton("Enter");

                enterPanel.add(enterButton);
                enterPanel.setBackground(Color.WHITE);
                enterButton.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        Boolean validToStart = false;
                        names = test.getText().split("\\s+");
                        numberOfPlayers = (int) box.getSelectedItem();
                        try {
                            game =  new  Monopoly(numberOfPlayers, names);
                            validToStart = true;
                        } catch (NotValidNumberOfPlayerException exception) {
                            System.out.println(exception.getMessage());
                            JLabel message = new JLabel(exception.getMessage());
                            enterPanel.add(message);

                            System.out.println("Try again!");
                        }  
                        if (validToStart) {
                            System.out.println("i am here");
                        }
                        


                        

                    }
                });

                // 3 : 1 
                innerPanel.add(enterPanel);
                add(innerPanel, BorderLayout.CENTER);
                
                

        
                 

    }
        

}
