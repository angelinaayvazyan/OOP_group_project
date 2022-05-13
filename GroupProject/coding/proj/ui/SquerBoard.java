package proj.ui;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class SquerBoard extends JButton {

    private int x;
    private int y;
    Color color;
    Image backgroundImage;
    JLabel info;


    public SquerBoard(int x, int y) {
       
        this(x, y, "empty");
        add(info);
    }

    public SquerBoard(int x, int y, String str) {
   
        info = new JLabel();
        info.setHorizontalAlignment(JLabel.CENTER);
        this.x = x;
        this.y = y;
        try {
            backgroundImage = ImageIO.read(new File("proj/imgFiles/" +  str + ".png"));
        } catch (IOException e) {

            e.printStackTrace();
        }
       
        // player in one location
        setLayout(new GridLayout(2, 3));

    }
    public void setInfo(String text){

        info.setText(text);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    
        // Draw the background image.
        g.drawImage(backgroundImage, 0, 0, this);
    }

    public int getPostionJ() {
        return x;
    }

    public int getPostionI() {
        return y;
    }

    
    
}
