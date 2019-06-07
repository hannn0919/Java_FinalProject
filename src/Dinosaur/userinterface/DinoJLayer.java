package Dinosaur.userinterface;
import House.house.House;
import Main.Main;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DinoJLayer extends JLayeredPane {
    private Main frame;
    private House house;

    private ImageIcon backToMainImage;
    private ImageIcon disCountImage;
    private ImageIcon expandMoneyImage;
    private ImageIcon ruleImage;
    private ImageIcon testCharacterImage;

    private JLabel backGroundLabel;
    private JButton backToMainButton;
    private JLabel disCountLabel;
    private JLabel expandMoneyLabel;
    private JLabel ruleLabel;
    private JLabel testCharacterLabel;

    public DinoJLayer(Main frame, House house){
        this.frame = frame;
        this.house = house;
        GameScreen game = new GameScreen(frame,house);
        game.setBounds(300,0,1200,675);
        add(game,JLayeredPane.DEFAULT_LAYER);
        addKeyListener(game);
        backToMainImage = new ImageIcon("data/HitMouse/icon/backhome.png");
        backToMainButton = new JButton(backToMainImage);
        backToMainButton.setBounds(0,0,backToMainImage.getIconWidth(),backToMainImage.getIconHeight());
        add(backToMainButton,JLayeredPane.DEFAULT_LAYER);
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

        disCountImage = new ImageIcon("data/HitMouse/icon/discount.png");
        disCountLabel = new JLabel(disCountImage);
        disCountLabel.setBounds(0,backToMainImage.getIconHeight(),disCountImage.getIconWidth(),disCountImage.getIconHeight());
        add(disCountLabel,JLayeredPane.DEFAULT_LAYER);

        ruleImage = new ImageIcon("data/HitMouse/icon/rule.png");
        ruleLabel = new JLabel(ruleImage);
        ruleLabel.setBounds(0,backToMainImage.getIconHeight()+disCountImage.getIconHeight(),ruleImage.getIconWidth(),ruleImage.getIconHeight());
        add(ruleLabel,JLayeredPane.DEFAULT_LAYER);

        expandMoneyImage = new ImageIcon("data/HitMouse/icon/expandMoney.png");
        expandMoneyLabel = new JLabel(expandMoneyImage);
        expandMoneyLabel.setBounds(0,backToMainImage.getIconHeight()+disCountImage.getIconHeight()+ruleImage.getIconHeight(),expandMoneyImage.getIconWidth(),expandMoneyImage.getIconHeight());
        add(expandMoneyLabel,JLayeredPane.DEFAULT_LAYER);


        System.out.println(ruleImage.getIconHeight());
        testCharacterImage = new ImageIcon("data/HitMouse/icon/test_charater.png");
        testCharacterLabel = new JLabel(testCharacterImage);
        testCharacterLabel.setBounds(0,397,testCharacterImage.getIconWidth(),testCharacterImage.getIconHeight());
        add(testCharacterLabel,JLayeredPane.DEFAULT_LAYER);

    }
}
