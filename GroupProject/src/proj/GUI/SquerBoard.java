package proj.GUI;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

public class SquerBoard extends JPanel {

    private int x;
    private int y;
    Color color;
    Image backgroundImage;


    public SquerBoard(int x, int y) {
        this(x, y, "empty");
    }
    public SquerBoard(int x, int y, String str) {
   

        this.x = x;
        this.y = y;
        try {
            backgroundImage = ImageIO.read(new File("GroupProject/src/proj/imgFiles/" +  str + ".png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
        setBackground(Color.RED);
        setLayout(new GridLayout(2, 3));
        //setSize( this.getPreferredSize() );
        // JLabel picLabel = new JLabel(x * 11 + y + "");
        // add(picLabel); 
        // setPosition(str);


    }

    public void setPosition(String str) {
        // BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        // Graphics2D graphics2D = resizedImage.createGraphics();
        // graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        // graphics2D.dispose();
        JLabel picLabel = new JLabel(new ImageIcon( ("GroupProject/src/proj/imgFiles/" +  str + ".png") ));
        //picLabel.setSize( picLabel.getPreferredSize() );
        // picLabel.setMinimumSize(new size);
        // picLabel.setMaximumSize(1);
        picLabel.setSize(90, 58);
        add(picLabel);  

    }  

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    
        // Draw the background image.
        g.drawImage(backgroundImage, 0, 0, this);
      }

    
    
}
