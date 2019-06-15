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
        ImageIcon image = new ImageIcon("data/main/畢業證書.png");
        JLabel label = new JLabel();
        label.setBounds(-10, -20, 1200, 675);
        label.setIcon(image);
        //畢業證書切換至結算畫面的按鈕位置
        hat = new Polygon();
        hat.addPoint(278,110);
        hat.addPoint(210,140);
        hat.addPoint(233,151);
        hat.addPoint(232,161);
        hat.addPoint(233,175);
        hat.addPoint(243,184);
        hat.addPoint(263,191);
        hat.addPoint(293,187);
        hat.addPoint(308,181);
        hat.addPoint(312,152);
        hat.addPoint(335,132);
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
        label.add(backToEnterScreenButton);


        add(label);

    }

    public ImageIcon resize(int width, int height, ImageIcon img) {
        Image i = img.getImage();
        Image new_img = i.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return  new ImageIcon(new_img);
    }

}
