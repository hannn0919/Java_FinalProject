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
    private ImageIcon expandMoneyImage;//經驗 前 肝的組合圖片
    private ImageIcon ruleImage;//簡單規則介紹的底圖
    private ImageIcon liverImg;
    private JButton backToMainButton;
    private JLabel expandMoneyLabel;
    private JLabel ruleLabel;
    private JLabel expFromMain;
    private JLabel moneyFromMain;
    private JLabel liverLabel;
    
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

        expFromMain = new JLabel("");
        expFromMain.setFont(new Font("Hollywood Hills",Font.BOLD,17));
        expFromMain.setBounds(140,heightTotal+13,150,25);
        expFromMain.setText(Integer.toString(house.getExp()));
        add(expFromMain,JLayeredPane.DRAG_LAYER);

        moneyFromMain = new JLabel("");
        moneyFromMain.setFont(new Font("Hollywood Hills",Font.BOLD,17));
        moneyFromMain.setBounds(140,heightTotal+50,150,25);
        moneyFromMain.setText(Integer.toString(house.getHoldMoney()));
        add(moneyFromMain,JLayeredPane.DRAG_LAYER);

        String character = "data/dinosaur/character/"+house.getLevel()+"/肝";
        if(house.getEquipment("透視眼鏡")==1){
            character += "+眼鏡";
        }

        if(house.getEquipment("竹蜻蜓")==1){
            character += "+竹蜻蜓";
        }

        if(house.getEquipment("翅膀")==1){
            character += "+翅膀";
        }

        if(house.getEquipment("彈簧鞋")==1){
            character += "+彈簧鞋";
        }

        liverImg = new ImageIcon( character+".png");//偷個恐龍圖片
        System.out.println(liverImg);
        liverLabel = new JLabel(liverImg);
        liverLabel.setBounds(30,heightTotal+100,liverImg.getIconWidth(),liverImg.getIconHeight());
        System.out.println(liverImg.getIconWidth()+" "+liverImg.getIconHeight());
        add(liverLabel,JLayeredPane.DRAG_LAYER);
    }
    public String toString(){
        return "I am in Bag";
    }
}
