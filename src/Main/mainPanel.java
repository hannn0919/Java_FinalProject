package Main;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class mainPanel extends JPanel {

    private Main mainFrame;
    private JButton btnDi, btnFrog, btnMouse, btnCard;   // 進入各遊戲按鈕

    private JLabel backLabel;    //放背景的label
    private JLabel name, LV, exp, money;
    private JLabel mouseToolTip;
    private JPanel frameforBtn;     //放三個btn的Panel
    private JPanel p_for_Label;
    private JPanel p_for_btn;
    private JButton shopBtn, bagBtn, stockBtn;     //股市商店背包button
    private ImageIcon shopPic, bagPic, stockPic;    //商店背包股市btn的圖片
    private ImageIcon namePic, LVPic, expPic, moneyPic;

    public mainPanel(Main mainFrame) {
        this.mainFrame = mainFrame;
        this.setSize(1200, 675);
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLayout(null);

        mouseToolTip = new JLabel("", JLabel.CENTER);
        mouseToolTip.setSize(80, 40);
        mouseToolTip.setVisible(true);
        this.add(mouseToolTip);

        //商店 背包 股市的按鈕
        shopBtn = new JButton();
        bagBtn = new JButton();
        stockBtn = new JButton();
        shopBtn.setBounds(67, 117, 171, 93);
        bagBtn.setBounds(67, 210, 171, 87);
        stockBtn.setBounds(68, 297, 171, 123);
        cleanButtom(shopBtn);
        cleanButtom(bagBtn);
        cleanButtom(stockBtn);

        shopBtn.setIcon(new ImageIcon("data/main/商店.png"));
        bagBtn.setIcon(new ImageIcon("data/main/背包.png"));
        stockBtn.setIcon(new ImageIcon("data/main/股市.png"));

        add(shopBtn);
        add(bagBtn);
        add(stockBtn);

        MouseMotionHandler mouseMotionHandler = new MouseMotionHandler();
        MouseHandler mouseHandler = new MouseHandler();
        shopBtn.addMouseListener(mouseHandler);
        bagBtn.addMouseListener(mouseHandler);
        stockBtn.addMouseListener(mouseHandler);


        Handler handler = new Handler();
        shopBtn.addActionListener(handler);
        bagBtn.addActionListener(handler);
        stockBtn.addActionListener(handler);


        //肝的名稱
        JLabel name = new JLabel();
        name.setBounds(208, 42, 166, 52);
        add(name);

        //等級
        JLabel LV = new JLabel();
        LV.setBounds(68, 52, 78, 40);
        add(LV);

        //經驗
        JLabel exp = new JLabel();
        JTextField exptxt = new JTextField("9487/66666");
        exp.setBounds(620, 44, 229, 52);
        exptxt.setBounds(670, 44, 167, 52);
        TextFieldFix(exptxt);
        add(exptxt);
        add(exp);

        //錢錢
        JLabel money = new JLabel();
        JTextField moneytxt = new JTextField("9487");
        money.setBounds(900, 40, 222, 54);
        moneytxt.setBounds(950, 40, 160, 54);
        TextFieldFix(moneytxt);
        add(moneytxt);
        add(money);

        name.setIcon(new ImageIcon("data/main/尚未硬化的肝.png"));
        LV.setIcon(new ImageIcon("data/main/LV2.png"));
        exp.setIcon(new ImageIcon("data/main/exp.png"));
        money.setIcon(new ImageIcon("data/main/money.png"));

        btnDi = new JButton();
        cleanButtom(btnDi);
        btnDi.setBounds(358, 516, 660, 130);
        //btnDi.setIcon(new ImageIcon("data/main/載入遊戲小.png"));
        btnDi.addActionListener(handler);
        this.add(btnDi);

        btnFrog = new JButton();
        cleanButtom(btnFrog);
        btnFrog.setBounds(358, 379, 660, 137);
        //btnDi.setIcon(new ImageIcon("data/main/載入遊戲小.png"));
        btnFrog.addActionListener(handler);
        btnFrog.addMouseListener(mouseHandler);
        btnFrog.addMouseMotionListener(mouseMotionHandler);
        this.add(btnFrog);

        btnMouse = new JButton();
        cleanButtom(btnMouse);
        btnMouse.setBounds(358,121,660, 137);
        btnMouse.addActionListener(handler);
        this.add(btnMouse);

        ImageIcon img = new ImageIcon("data/main/mainPic.png");
        Image i = img.getImage();
        i = i.getScaledInstance(1200, 675, Image.SCALE_SMOOTH);
        JLabel background = new JLabel();
        background.setIcon(new ImageIcon(i));
        background.setSize(1200, 675);
        this.add(background);
    }

    public void buttonSound() {
        try {
            File soundFile = new File("music/buttonClicked.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // 按鈕去除背景、去除邊框
    public void cleanButtom(JButton button) {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorder(null);
    }

    // 文字框去除背景、去除邊框、靠右對齊、不可編輯
    public void TextFieldFix(JTextField field) {
        field.setOpaque(false);
        field.setEditable(false);
        field.setHorizontalAlignment(JTextField.RIGHT);
        field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));
    }

    private class MouseMotionHandler implements MouseMotionListener{
        public void mouseDragged(MouseEvent e) {
        }//用不到
        @Override
        public void mouseMoved(MouseEvent e) {
            if(e.getSource()==btnFrog) {
                System.out.println((int)(e.getPoint().getX() + 15)+"  "+(int)(e.getPoint().getY() + 15));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                mouseToolTip.setIcon(new ImageIcon("data/main/商店2.png"));
                mouseToolTip.setLocation((int)(e.getPoint().getX() + 15), (int)(e.getPoint().getY() + 15));
            }
        }
    }
    private class MouseHandler implements MouseListener {
        public void mouseReleased(MouseEvent event) {
        }//用不到
        public void mousePressed(MouseEvent event){
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            mainFrame.changeToMainScreen();
        }
        @Override
        public void mouseEntered(MouseEvent arg0){
            buttonSound();
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            if(arg0.getSource()==shopBtn) {
                shopBtn.setIcon(new ImageIcon("data/main/商店2.png"));
            }else if(arg0.getSource()==bagBtn) {
                bagBtn.setIcon(new ImageIcon("data/main/背包2.png"));
            }else if(arg0.getSource()==stockBtn) {
                stockBtn.setIcon(new ImageIcon("data/main/股市2.png"));
            }
        }
        @Override
        public void mouseExited(MouseEvent arg0) {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            if(arg0.getSource()==shopBtn) {
                shopBtn.setIcon(new ImageIcon("data/main/商店.png"));
            }else if(arg0.getSource()==bagBtn) {
                bagBtn.setIcon(new ImageIcon("data/main/背包.png"));
            }else if(arg0.getSource()==stockBtn) {
                stockBtn.setIcon(new ImageIcon("data/main/股市.png"));
            }
        }
    }

    // 偵測滑鼠事件，切換至正確的畫面
    private class Handler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnDi) {
                mainFrame.changeToDinosaur();
            } else if (e.getSource() == btnFrog) {
                mainFrame.changeToFrog();
            } else if (e.getSource() == shopBtn) {
                mainFrame.changeToShop();
            } else if (e.getSource() == stockBtn) {
                mainFrame.changeToStock();
            } else if (e.getSource() == bagBtn) {
                mainFrame.changeToBag();
            } else if (e.getSource() == btnMouse) {
                mainFrame.changeToMouse();
            }
        }
    }
}

