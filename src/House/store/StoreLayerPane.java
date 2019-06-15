package House.store;

import javax.swing.*;
import Main.*;
import House.house.House;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoreLayerPane extends JLayeredPane {
    private Main frame;
    private House house;
    private ImageIcon liverImg;
    private ImageIcon backToMainImage;//回到主程式的圖片
    private ImageIcon expandMoneyImage;//經驗 前 肝的組合圖片
    private ImageIcon ruleImage;//簡單規則介紹的底圖
    private ImageIcon gameBarImage;
    private JButton backToMainButton;
    private JLabel expandMoneyLabel;
    private JLabel gameBarLabel;
    private JLabel expFromMain;
    private JLabel moneyFromMain;
    private JLabel liverLabel;
    private Timer time;

    public StoreLayerPane(Main frame, House house){
        this.frame = frame;
        this.house = house;
        Store store= new Store(frame,house);
        store.setBounds(370,20,900,675);
        add(store,JLayeredPane.DEFAULT_LAYER);
        int heightTotal=0;
        gameBarImage = new ImageIcon("data/Bag&Store/storebar.png");
        gameBarLabel = new JLabel(gameBarImage);
        gameBarLabel.setBounds(0,0,gameBarImage.getIconWidth(),gameBarImage.getIconHeight());
        add(gameBarLabel,JLayeredPane.DEFAULT_LAYER);
        backToMainImage = new ImageIcon("data/Bag&Store/backhome.png");
        backToMainButton = new JButton(backToMainImage);
        backToMainButton.setBounds(0, heightTotal, backToMainImage.getIconWidth(), backToMainImage.getIconHeight());
        add(backToMainButton, JLayeredPane.POPUP_LAYER);
        backToMainButton.setBorderPainted(false);
        backToMainButton.setBorder(null);
        backToMainButton.setFocusPainted(false);
        backToMainButton.setContentAreaFilled(false);
        backToMainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.changeToMainScreen();
                time.stop();
            }
        });
        heightTotal+=backToMainImage.getIconHeight();
        ruleImage = new ImageIcon("data/Bag&Store/rule.png");
        heightTotal+=ruleImage.getIconHeight();

        expandMoneyImage = new ImageIcon("data/gamebar/expandMoney.png");
        expandMoneyLabel = new JLabel(expandMoneyImage);
        expandMoneyLabel.setBounds(0, heightTotal, expandMoneyImage.getIconWidth(), expandMoneyImage.getIconHeight());
        add(expandMoneyLabel, JLayeredPane.POPUP_LAYER);

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
        time = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moneyFromMain.setText(Integer.toString(house.getHoldMoney()));
            }
        });
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
        character += ".png";
        Icon temp = new ImageIcon(character);
        liverImg = resize(temp.getIconWidth()+40, temp.getIconHeight()+32, (ImageIcon) temp);
       // liverImg = new ImageIcon( character+".png");//偷個恐龍圖片
        liverLabel = new JLabel(liverImg);
        liverLabel.setBounds(50,heightTotal+80,liverImg.getIconWidth(),liverImg.getIconHeight());
        add(liverLabel,JLayeredPane.DRAG_LAYER);
        time.start();
    }

    // 改變圖片大小
    public ImageIcon resize(int width, int height, ImageIcon img) {
        Image i = img.getImage();
        Image new_img = i.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return  new ImageIcon(new_img);
    }
}
