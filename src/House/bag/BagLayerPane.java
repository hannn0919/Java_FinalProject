package House.bag;

import javax.swing.*;
import Main.*;
import House.house.House;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BagLayerPane extends JLayeredPane {
    private Main frame;
    private House house;

    private ImageIcon backToMainImage;//回到主程式的圖片
  //  private ImageIcon disCountImage;//到數計時的圖片
    private ImageIcon expandMoneyImage;//經驗 前 肝的組合圖片
    private ImageIcon ruleImage;//簡單規則介紹的底圖


    private JButton backToMainButton;
  //  private JLabel disCountLabel;
    private JLabel expandMoneyLabel;
    private JLabel ruleLabel;

    public BagLayerPane(Main frame, House house){
        this.frame = frame;
        this.house = house;
        BagWindow bagWindow= new BagWindow(frame,house);
        bagWindow.setBounds(370,20,900,675);
        add(bagWindow,JLayeredPane.DEFAULT_LAYER);
        int heightTotal=0;
        backToMainImage = new ImageIcon("data/Bag&Store/backhome.png");
        backToMainButton = new JButton(backToMainImage);
        backToMainButton.setBounds(0, heightTotal, backToMainImage.getIconWidth(), backToMainImage.getIconHeight());
        add(backToMainButton, JLayeredPane.DEFAULT_LAYER);
        backToMainButton.setBorderPainted(false);
        backToMainButton.setBorder(null);
        backToMainButton.setFocusPainted(false);
        backToMainButton.setContentAreaFilled(false);
        backToMainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.changeToMainScreen();
            }
        });
        heightTotal+=backToMainImage.getIconHeight();




        ruleImage = new ImageIcon("data/Bag&Store/rule.png");
        ruleLabel = new JLabel(ruleImage);
        ruleLabel.setBounds(0, heightTotal, ruleImage.getIconWidth(), ruleImage.getIconHeight());
        add(ruleLabel, JLayeredPane.DEFAULT_LAYER);
        heightTotal+=ruleImage.getIconHeight();

        expandMoneyImage = new ImageIcon("data/gamebar/expandMoney.png");
        expandMoneyLabel = new JLabel(expandMoneyImage);
        expandMoneyLabel.setBounds(0, heightTotal, expandMoneyImage.getIconWidth(), expandMoneyImage.getIconHeight());
        add(expandMoneyLabel, JLayeredPane.DEFAULT_LAYER);
    }
}
