package Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import House.house.House;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class graduation extends JPanel {

    private Main mainFrame;
    private House house;
    private Polygon hat;

    public graduation(Main mainFrame, House house) {

        this.mainFrame = mainFrame;
        this.house = house;

        this.setSize(1200, 675);
        this.setLayout(null);

        hat = new Polygon();
        hat.addPoint(288,130);
        hat.addPoint(220,160);
        hat.addPoint(243,171);
        hat.addPoint(242,181);
        hat.addPoint(243,195);
        hat.addPoint(253,204);
        hat.addPoint(273,211);
        hat.addPoint(303,207);
        hat.addPoint(318,201);
        hat.addPoint(322,172);
        hat.addPoint(345,153);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (hat.contains(e.getPoint())) {
                    mainFrame.changeToSettlement();
                }
            }
        });

        ImageIcon image = new ImageIcon("data/main/畢業證書.png");
        JLabel label = new JLabel();
        label.setBounds(0, 0, 1200, 675);
        label.setIcon(image);
        add(label);

    }

}
