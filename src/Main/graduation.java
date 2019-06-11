package Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import House.house.House;
import java.awt.*;

public class graduation extends JPanel {

    private Main mainFrame;
    private House house;

    public graduation(Main mainFrame, House house) {

        this.mainFrame = mainFrame;
        this.house = house;

        this.setSize(1200, 675);
        this.setLayout(null);

        ImageIcon image = new ImageIcon("data/main/畢業證書.png");
        JLabel label = new JLabel();
        label.setBounds(0, 0, 1200, 675);
        label.setIcon(image);
        add(label);

    }

}
