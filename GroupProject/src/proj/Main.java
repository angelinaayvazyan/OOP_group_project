package proj;
import javax.swing.UIManager;

import proj.GUI.MonoployUI;
import proj.core.MonopolyConsol;


public class Main {
    public static void main (String [] args) {
        // MonopolyConsol game = new MonopolyConsol();
        // game.play();
        try {

            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
  
          } catch (Exception e) {
  
            e.printStackTrace();
        }

        MonoployUI ui = new MonoployUI();
        ui.setVisible(true);

    }
    
}