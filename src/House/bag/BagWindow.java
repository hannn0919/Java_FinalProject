package House.bag;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import Main.*;
import House.house.House;

public class BagWindow extends JPanel {
    private  JButton b1, b2, b3, b4, b5, b6, b7, b8, b9;
    private  JButton e1, e2, e3, e4;
    private  JButton item, equip;
    private  BufferedImage backGround;
    private  boolean IsBackGround=true;
    private  Main mainFrame ;
    private  House house;
    private ImageIcon iconB1,iconB2,iconB3,iconB4,iconB5,iconB6,iconB7,iconB8,iconB9;
    private ImageIcon iconE1,iconE2,iconE3,iconE4;

    public BagWindow(Main mainFrame,House house) {
        this.house = house;
        this.mainFrame = mainFrame;
        this.setBackground(Color.WHITE);
        setLayout(null);
        setPreferredSize(new Dimension(728,598));
        item = new JButton();
        equip = new JButton();

        item.setContentAreaFilled(false);
        equip.setContentAreaFilled(false);

        item.setBorderPainted(false);
        equip.setBorderPainted(false);

        item.setBounds(10,90,108,79);
        equip.setBounds(10,13,108,75);

        ButtonHandler handler = new ButtonHandler();
        MouseHandler handler2 = new MouseHandler();
        equip.addActionListener(handler);
        item.addActionListener(handler);

        add(equip);
        add(item);

        b1 = new JButton("經驗加倍券",iconB1);
        b2 = new JButton("金錢加倍券",iconB2);
        b3 = new JButton("電蚊拍",iconB3);
        b4 = new JButton("增時卡",iconB4);
        b5 = new JButton("地下室鑰匙",iconB5);
        b6 = new JButton("警察卡",iconB6);
        b7 = new JButton("老師卡",iconB7);
        b8 = new JButton("加倍卡",iconB8);
        b9 = new JButton("再看一次",iconB9);
        e1 = new JButton("竹蜻蜓",iconE1);
        e2 = new JButton("透視眼鏡",iconE2);
        e3 = new JButton("彈簧鞋",iconE3);
        e4 = new JButton("翅膀",iconE4);

        setButtonB(b1);
        setButtonB(b2);
        setButtonB(b3);
        setButtonB(b4);
        setButtonB(b5);
        setButtonB(b6);
        setButtonB(b7);
        setButtonB(b8);
        setButtonB(b9);
        setButtonE(e1);
        setButtonE(e2);
        setButtonE(e3);
        setButtonE(e4);

        b1.addMouseListener(handler2);
        b2.addMouseListener(handler2);
        b3.addMouseListener(handler2);
        b4.addMouseListener(handler2);
        b5.addMouseListener(handler2);
        b6.addMouseListener(handler2);
        b7.addMouseListener(handler2);
        b8.addMouseListener(handler2);
        b9.addMouseListener(handler2);
        e1.addMouseListener(handler2);
        e2.addMouseListener(handler2);
        e3.addMouseListener(handler2);
        e4.addMouseListener(handler2);

        b1.setBounds(142,32,172,174);
        b2.setBounds(314,32,200,174);
        b3.setBounds(514,32,172,174);
        b4.setBounds(142,206,172,174);
        b5.setBounds(314,206,200,174);
        b6.setBounds(514,206,172,174);
        b7.setBounds(142,387,172,174);
        b8.setBounds(314,387,200,174);
        b9.setBounds(514,387,172,174);
        e1.setBounds(144, 31, 275, 271);
        e2.setBounds(419, 31, 275, 271);
        e3.setBounds(144, 302, 275, 271);
        e4.setBounds(419, 302, 275, 271);

        add(e1);
        add(e2);
        add(e3);
        add(e4);
    }

    //設計道具按鈕
    private void setButtonB(JButton j){
        j.setFont(new Font("微軟正黑體",Font.BOLD,20));
        j.setContentAreaFilled(false);
        j.setBorderPainted(false);
        j.setVerticalTextPosition(AbstractButton.BOTTOM);
        j.setHorizontalTextPosition(AbstractButton.CENTER);
        j.setForeground(new Color(0,0,128));
    }

    //設計裝備按鈕
    private void setButtonE(JButton j){
        j.setFont(new Font("微軟正黑體",Font.BOLD,30));
        j.setContentAreaFilled(false);
        j.setBorderPainted(false);
        j.setVerticalTextPosition(AbstractButton.BOTTOM);
        j.setHorizontalTextPosition(AbstractButton.CENTER);
        j.setForeground(Color.PINK);
    }

    //當點擊裝備或道具時的反應
    private class MouseHandler implements MouseListener {
        public void mouseClicked(MouseEvent event) {
        }//用不到

        public void mouseReleased(MouseEvent event) {
        }//用不到

        public void mouseEntered(MouseEvent event) {
                if(event.getSource()==b1){
                    b1.setText("擁有數量"+house.getEquipment("經驗加倍券"));
                } else if (event.getSource() == b2) {
                    b2.setText("擁有數量"+house.getEquipment("金錢加倍券"));
                } else if (event.getSource() == b3) {
                    b3.setText("擁有數量"+house.getEquipment("電蚊拍"));
                } else if (event.getSource() == b4) {
                    b4.setText("擁有數量"+house.getEquipment("增時卡"));
                } else if (event.getSource() == b5) {
                    b5.setText("擁有數量"+house.getEquipment("地下道鑰匙"));
                } else if (event.getSource() == b6) {
                    b6.setText("擁有數量"+house.getEquipment("警察卡"));
                } else if (event.getSource() == b7) {
                    b7.setText("擁有數量"+house.getEquipment("老師卡"));
                } else if (event.getSource() == b8) {
                    b8.setText("擁有數量"+house.getEquipment("加倍卡"));
                } else if (event.getSource() == b9) {
                    b9.setText("擁有數量"+house.getEquipment("再看一次"));
                } else if (event.getSource() == e1) {
                    if(house.getEquipment("竹蜻蜓") == 1)
                        e1.setText("已裝備");
                    else
                        e1.setText("未裝備");
                } else if (event.getSource() == e2) {
                    if(house.getEquipment("透視眼鏡") == 1)
                        e2.setText("已裝備");
                    else
                        e2.setText("未裝備");
                } else if (event.getSource() == e3) {
                    if(house.getEquipment("彈簧鞋") == 1)
                        e3.setText("已裝備");
                    else
                        e3.setText("未裝備");
                } else if (event.getSource() == e4) {
                    if(house.getEquipment("翅膀") == 1)
                        e4.setText("已裝備");
                    else
                        e4.setText("未裝備");
                }
        }

        public void mouseExited(MouseEvent event) {
            if(event.getSource()==b1){
                b1.setText("經驗加倍券");
            } else if (event.getSource() == b2) {
                b2.setText("金錢加倍券");
            } else if (event.getSource() == b3) {
                b3.setText("電蚊拍");
            } else if (event.getSource() == b4) {
                b4.setText("增時卡");
            } else if (event.getSource() == b5) {
                b5.setText("地下道鑰匙");
            } else if (event.getSource() == b6) {
                b6.setText("警察卡");
            } else if (event.getSource() == b7) {
                b7.setText("老師卡");
            } else if (event.getSource() == b8) {
                b8.setText("加倍卡");
            } else if (event.getSource() == b9) {
                b9.setText("再看一次");
            } else if (event.getSource() == e1) {
                e1.setText("竹蜻蜓");
            } else if (event.getSource() == e2) {
                e2.setText("透視眼鏡");
            } else if (event.getSource() == e3) {
                e3.setText("彈簧鞋");
            } else if (event.getSource() == e4) {
                e4.setText("翅膀");
            }
        }//用不到

        public void mousePressed(MouseEvent event) {//當滑鼠移動物件時
                if (event.getSource() == b1) {
                    JOptionPane.showConfirmDialog(null, "遊戲結算時，獲得兩倍經驗\n", "", JOptionPane.DEFAULT_OPTION);
                    house.getItem("經驗加倍券");
                } else if (event.getSource() == b2) {
                    JOptionPane.showConfirmDialog(null, "遊戲結算時，獲得兩倍金錢\n", "", JOptionPane.DEFAULT_OPTION);
                    house.getItem("金錢加倍券");
                } else if (event.getSource() == b3) {
                    JOptionPane.showConfirmDialog(null, "變身無敵狀態，效果維持十秒\n", "", JOptionPane.DEFAULT_OPTION);
                    house.getItem("電蚊拍");
                } else if (event.getSource() == b4) {
                    JOptionPane.showConfirmDialog(null, "記憶時間延長10秒\n", "", JOptionPane.DEFAULT_OPTION);
                    house.getItem("增時卡");
                } else if (event.getSource() == b5) {
                    JOptionPane.showConfirmDialog(null, "起點向前移至分隔島\n", "", JOptionPane.DEFAULT_OPTION);
                    house.getItem("地下道鑰匙");
                } else if (event.getSource() == b6) {
                    JOptionPane.showConfirmDialog(null, "被警察抓到時不需處罰\n", "", JOptionPane.DEFAULT_OPTION);
                    house.getItem("警察卡");
                } else if (event.getSource() == b7) {
                    JOptionPane.showConfirmDialog(null, "只會出現老師，效果維持10秒\n", "", JOptionPane.DEFAULT_OPTION);
                    house.getItem("老師卡");
                } else if (event.getSource() == b8) {
                    JOptionPane.showConfirmDialog(null, "分數增為兩倍，效果維持10秒\n", "", JOptionPane.DEFAULT_OPTION);
                    house.getItem("加倍卡");
                } else if (event.getSource() == b9) {
                    JOptionPane.showConfirmDialog(null, "將所有牌翻至正面看10秒\n", "", JOptionPane.DEFAULT_OPTION);
                    house.getItem("再看一次");
                }  else if (event.getSource() == e1) {
                    int opt = JOptionPane.showConfirmDialog(null, "起點向前移動100公尺\n--------確定要裝備嗎?--------", "", JOptionPane.YES_NO_OPTION);
                    if(opt == 0){
                        if(house.getEquipment("竹蜻蜓") == 0)
                            JOptionPane.showConfirmDialog(null, "無此道具!!!", "", JOptionPane.DEFAULT_OPTION);
                        else
                            house.useEquipment("竹蜻蜓");
                    }
                    else if (opt==1){
                        if(house.getEquipment("竹蜻蜓") == 1)
                            house.setEquipment("竹蜻蜓");
                    }
                    // 0=yes, 1=no
                }  else if (event.getSource() == e2) {
                    int opt= JOptionPane.showConfirmDialog(null, "可看到影子下的真實面貌\n--------確定要裝備嗎?--------", "", JOptionPane.YES_NO_OPTION);
                    // 0=yes, 1=no
                    if(opt==0){
                        if(house.getEquipment("透視眼鏡") == 0)
                            JOptionPane.showConfirmDialog(null, "無此道具!!!", "", JOptionPane.DEFAULT_OPTION);
                        else
                            house.useEquipment("透視眼鏡");
                    }
                    else if (opt==1){
                        if(house.getEquipment("透視眼鏡") == 1)
                            house.setEquipment("透視眼鏡");
                    }
                }else if (event.getSource() == e3) {
                    int opt=  JOptionPane.showConfirmDialog(null, "可以一次前進兩格\n--------確定要裝備嗎?--------", "", JOptionPane.YES_NO_OPTION);
                    // 0=yes, 1=no
                    if(opt==0){
                        if(house.getEquipment("彈簧鞋") == 0)
                            JOptionPane.showConfirmDialog(null, "無此道具!!!", "", JOptionPane.DEFAULT_OPTION);
                        else
                            house.useEquipment("彈簧鞋");
                    }
                    else if (opt==1){
                        if(house.getEquipment("彈簧鞋") == 1)
                            house.setEquipment("彈簧鞋");
                    }
                } else if (event.getSource() == e4) {
                    int opt= JOptionPane.showConfirmDialog(null, "系統自動翻出一對配對組合\n--------確定要裝備嗎?--------", "", JOptionPane.YES_NO_OPTION);
                    // 0=yes, 1=no
                    if(opt==0){
                        if(house.getEquipment("翅膀") == 0)
                            JOptionPane.showConfirmDialog(null, "無此道具!!!", "", JOptionPane.DEFAULT_OPTION);
                        else
                            house.useEquipment("翅膀");
                    }
                    else if (opt==1){
                        if(house.getEquipment("翅膀") == 1)
                            house.setEquipment("翅膀");
                    }
                }
        }
    }

    //判斷遊玩者切換為裝備或是道具
    private class ButtonHandler implements ActionListener {
        // handle button event
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == item) {
                repaint();
                IsBackGround=false;
                remove(e1);
                remove(e2);
                remove(e3);
                remove(e4);
                add(b1);
                add(b2);
                add(b3);
                add(b4);
                add(b5);
                add(b6);
                add(b7);
                add(b8);
                add(b9);
            }
            else if (event.getSource() == equip) {
                repaint();
                IsBackGround=true;
                add(e1);
                add(e2);
                add(e3);
                add(e4);
                remove(b1);
                remove(b2);
                remove(b3);
                remove(b4);
                remove(b5);
                remove(b6);
                remove(b7);
                remove(b8);
                remove(b9);

            }
        }
    }

    @Override//抓入圖檔 (ok)
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g); // clears drawing area
        try {
            if(IsBackGround) {
                backGround = ImageIO.read(new File("data//Bag&Store/背包-裝備.png"));
                iconE1 = new ImageIcon("data//Bag&Store/竹蜻蜓.png");
                iconE2 = new ImageIcon("data//Bag&Store/透視眼鏡.png");
                iconE3 = new ImageIcon("data//Bag&Store/彈簧鞋.png");
                iconE4 = new ImageIcon("data//Bag&Store/翅膀.png");
                e1.setIcon(iconE1);
                e2.setIcon(iconE2);
                e3.setIcon(iconE3);
                e4.setIcon(iconE4);

            }
            else {
                backGround = ImageIO.read(new File("data//Bag&Store/背包-道具.png"));
                iconB1 = new ImageIcon("data//Bag&Store/exp.png");
                iconB2 = new ImageIcon("data//Bag&Store/錢加倍.jpg");
                iconB3 = new ImageIcon("data//Bag&Store/電蚊拍.png");
                iconB4 = new ImageIcon("data//Bag&Store/增時卡.png");
                iconB5 = new ImageIcon("data//Bag&Store/地下道鑰匙.png");
                iconB6 = new ImageIcon("data//Bag&Store/警察卡.png");
                iconB7 = new ImageIcon("data//Bag&Store/老師卡.png");
                iconB8 = new ImageIcon("data//Bag&Store/加倍卡.png");
                iconB9 = new ImageIcon("data//Bag&Store/再看一次.png");
                b1.setIcon(iconB1);
                b2.setIcon(iconB2);
                b3.setIcon(iconB3);
                b4.setIcon(iconB4);
                b5.setIcon(iconB5);
                b6.setIcon(iconB6);
                b7.setIcon(iconB7);
                b8.setIcon(iconB8);
                b9.setIcon(iconB9);

            }
        }
        catch (Exception ex) {
            System.out.println("No example.jpg!!");
        }
        g.drawImage(backGround, 0, 0, null);
    }
}