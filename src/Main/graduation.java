package Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import House.house.House;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class graduation extends JPanel {

    private Main mainFrame;
    private House house;
    private Polygon hat;
    private ImageIcon imageForReturn= new ImageIcon("data/main/畢業returnBTN.png");

    public graduation(Main mainFrame, House house) {

        this.mainFrame = mainFrame;
        this.house = house;

        this.setSize(1200, 675);
        this.setLayout(null);

        //畢業證書切換至結算畫面的按鈕位置
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


        //返回登入畫面的按鈕
        JButton backToEnterScreenButton = new JButton(imageForReturn);
        backToEnterScreenButton.setBounds(341,440 , imageForReturn.getIconWidth(), imageForReturn.getIconHeight());
        backToEnterScreenButton.setBorderPainted(false);
        backToEnterScreenButton.setBorder(null);
        backToEnterScreenButton.setFocusPainted(false);
        backToEnterScreenButton.setContentAreaFilled(false);
        backToEnterScreenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.changeToEnterScreen();
            }
        });
        add(backToEnterScreenButton);

        ImageIcon image = new ImageIcon("data/main/畢業證書.png");
        JLabel label = new JLabel();
        label.setBounds(0, 0, 1200, 675);
        label.setIcon(image);
        add(label);

    }

    public ImageIcon resize(int width, int height, ImageIcon img) {
        Image i = img.getImage();
        Image new_img = i.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return  new ImageIcon(new_img);
    }

}
