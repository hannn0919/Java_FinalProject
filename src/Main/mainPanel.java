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
    private JLabel name, LV, exp, money;
    private JLabel mouseToolTip;
    private Polygon card;
    private Polygon hamster;
    private Polygon frogger;
    private Polygon dinosaur;
    private JButton shopBtn, bagBtn, stockBtn;     //股市商店背包button
    private ImageIcon namePic, LVPic, expPic, moneyPic;
    private boolean cardIn= false;
    private boolean hamsterIn= false;
    private boolean froggerIn= false;
    private boolean dinosaurIn= false;

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

      //  MouseMotionHandler mouseMotionHandler = new MouseMotionHandler();
        MouseHandler mouseHandler = new MouseHandler();
        //this.addMouseMotionListener(mouseMotionHandler);
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

        card = new Polygon();
        card.addPoint(471,171);
        card.addPoint(360,250);
        card.addPoint(1017,247);
        card.addPoint(1110,140);

        hamster = new Polygon();
        hamster.addPoint(360,250);
        hamster.addPoint(358,379);
        hamster.addPoint(1013,379);
        hamster.addPoint(1111,281);
        hamster.addPoint(1110,140);
        hamster.addPoint(1016,247);

        frogger = new Polygon();
        frogger.addPoint(358,379);
        frogger.addPoint(356,514);
        frogger.addPoint(1017,514);
        frogger.addPoint(1113,436);
        frogger.addPoint(1111,281);
        frogger.addPoint(1013,379);

        dinosaur = new Polygon();
        dinosaur.addPoint(356,648);
        dinosaur.addPoint(356,514);
        dinosaur.addPoint(1017,514);
        dinosaur.addPoint(1111,436);
        dinosaur.addPoint(1115,567);
        dinosaur.addPoint(1018,645);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (card.contains(e.getPoint())) {
                    mainFrame.changeToCard();
                } else if (hamster.contains(e.getPoint())) {
                    mainFrame.changeToMouse();
                } else if (frogger.contains(e.getPoint())) {
                    mainFrame.changeToFrog();
                } else if (dinosaur.contains(e.getPoint())) {
                    mainFrame.changeToDinosaur();
                }
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                boolean hover = false;
                if (card.contains(e.getPoint())) {
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                    if (!cardIn) {
                        cardIn = true;
                        buttonSound();
                    }
                    hover = true;
                    mouseToolTip.setIcon(new ImageIcon("data/Bag&Store/警察卡.png"));
                    mouseToolTip.setLocation((int)(e.getPoint().getX() + 15), (int)(e.getPoint().getY() + 15));
                }
                else {
                    cardIn = false;
                }
                if (hamster.contains(e.getPoint())) {
                    hover = true;
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                    if (!hamsterIn) {
                        hamsterIn=true;
                        buttonSound();
                    }
                    mouseToolTip.setIcon(new ImageIcon("data/Bag&Store/警察卡.png"));
                    mouseToolTip.setLocation((int)(e.getPoint().getX() + 15), (int)(e.getPoint().getY() + 15));
                }
                else {
                    hamsterIn = false;
                }
                if (frogger.contains(e.getPoint())) {
                    hover = true;
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                    if (!froggerIn) {
                        froggerIn=true;
                        buttonSound();
                    }
                    mouseToolTip.setIcon(new ImageIcon("data/Bag&Store/加倍卡.png"));
                    mouseToolTip.setLocation((int)(e.getPoint().getX() + 15), (int)(e.getPoint().getY() + 15));
                }
                else {
                    froggerIn = false;
                }
                if (dinosaur.contains(e.getPoint())) {
                    hover = true;
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                    if (!dinosaurIn) {
                        dinosaurIn = true;
                        buttonSound();
                    }
                    mouseToolTip.setIcon(new ImageIcon("data/Bag&Store/老師卡.png"));
                    mouseToolTip.setLocation((int)(e.getPoint().getX() + 15), (int)(e.getPoint().getY() + 15));
                }
                else {
                    dinosaurIn = false;
                }if (hover == false) {
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    mouseToolTip.setIcon(null);
                    mouseToolTip.setText(null);
                }
            }
        });

        ImageIcon img = new ImageIcon("data/main/mainPic.png");
        Image i = img.getImage();
        i = i.getScaledInstance(1200, 675, Image.SCALE_SMOOTH);
        JLabel background = new JLabel();
        background.setIcon(new ImageIcon(i));
        background.setSize(1200, 675);
        this.add(background);
    }

    // 按鈕去除背景、去除邊框
    public void cleanButtom(JButton button) {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorder(null);
    }

    //點下去的音效
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

    // 文字框去除背景、去除邊框、靠右對齊、不可編輯
    public void TextFieldFix(JTextField field) {
        field.setOpaque(false);
        field.setEditable(false);
        field.setHorizontalAlignment(JTextField.RIGHT);
        field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));
    }

    //// 偵測滑鼠事件，切換氣球顏色
    private class MouseHandler implements MouseListener {
        public void mouseReleased(MouseEvent arg0) {
            if(arg0.getSource()==shopBtn) {
                shopBtn.setIcon(new ImageIcon("data/main/商店.png"));
            }else if(arg0.getSource()==bagBtn) {
                bagBtn.setIcon(new ImageIcon("data/main/背包.png"));
            }else if(arg0.getSource()==stockBtn) {
                stockBtn.setIcon(new ImageIcon("data/main/股市.png"));
            }
        }//用不到
        public void mousePressed(MouseEvent arg0){
            if(arg0.getSource()==shopBtn) {
                shopBtn.setIcon(new ImageIcon("data/main/商店2.png"));
            }else if(arg0.getSource()==bagBtn) {
                bagBtn.setIcon(new ImageIcon("data/main/背包2.png"));
            }else if(arg0.getSource()==stockBtn) {
                stockBtn.setIcon(new ImageIcon("data/main/股市2.png"));
            }
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
            if (e.getSource() == shopBtn) {
                mainFrame.changeToShop();
            } else if (e.getSource() == stockBtn) {
                mainFrame.changeToStock();
            } else if (e.getSource() == bagBtn) {
                mainFrame.changeToBag();
            }
        }
    }

}


