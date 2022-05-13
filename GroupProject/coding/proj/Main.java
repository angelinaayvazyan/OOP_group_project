package proj;
import javax.swing.UIManager;

import proj.ui.MonoployUI;

public class Main {
    public static void main (String [] args) {

        try {

            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
  
          } catch (Exception e) {
  
            e.printStackTrace();
        }

        MonoployUI ui = new MonoployUI();
        ui.setVisible(true);

    }
    
}