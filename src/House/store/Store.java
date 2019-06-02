package House.store;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;

public class Store extends JPanel {
    private  JButton b1, b2, b3, b4, b5, b6, b7, b8, b9;
    private  JButton e1, e2, e3, e4;
    private  JButton item, equip;
    private  BufferedImage backGround;
    private  boolean IsBackGround=true;

    public static void main(String args[]) {
        JFrame frame = new JFrame();
        frame.setTitle("StoreWindow");
        Store storeWindow = new Store();
        frame.setLocationRelativeTo(null);
        frame.add(storeWindow);
        frame.setLocation(0,0);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public Store() {
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

            b1 = new JButton("時間加倍券");
            b1.addMouseListener(handler2);
            b2 = new JButton("經驗加倍券");
            b2.addMouseListener(handler2);
            b3 = new JButton("電蚊拍");
            b3.addMouseListener(handler2);
            b4 = new JButton("增加觀看時間");
            b4.addMouseListener(handler2);
            b5 = new JButton("地下道鑰匙");
            b5.addMouseListener(handler2);
            b6 = new JButton("警察卡");
            b6.addMouseListener(handler2);
            b7 = new JButton("老師卡");
            b7.addMouseListener(handler2);
            b8 = new JButton("地鼠王");
            b8.addMouseListener(handler2);
            b9 = new JButton("再看一次");
            b9.addMouseListener(handler2);

            e1 = new JButton();
            e2 = new JButton();
            e3 = new JButton();
            e4 = new JButton();

            e1.addMouseListener(handler2);
            e2.addMouseListener(handler2);
            e3.addMouseListener(handler2);
            e4.addMouseListener(handler2);

            b1.setContentAreaFilled(false);
            b2.setContentAreaFilled(false);
            b3.setContentAreaFilled(false);
            b4.setContentAreaFilled(false);
            b5.setContentAreaFilled(false);
            b6.setContentAreaFilled(false);
            b7.setContentAreaFilled(false);
            b8.setContentAreaFilled(false);
            b9.setContentAreaFilled(false);
            e1.setContentAreaFilled(false);
            e2.setContentAreaFilled(false);
            e3.setContentAreaFilled(false);
            e4.setContentAreaFilled(false);

            b1.setBorderPainted(false);
            b2.setBorderPainted(false);
            b3.setBorderPainted(false);
            b4.setBorderPainted(false);
            b5.setBorderPainted(false);
            b6.setBorderPainted(false);
            b7.setBorderPainted(false);
            b8.setBorderPainted(false);
            b9.setBorderPainted(false);
            e1.setBorderPainted(false);
            e2.setBorderPainted(false);
            e3.setBorderPainted(false);
            e4.setBorderPainted(false);

            add(e1);
            add(e2);
            add(e3);
            add(e4);
        }

        private class MouseHandler implements MouseListener {
            public void mouseClicked(MouseEvent event) {
            }//用不到

            public void mouseReleased(MouseEvent event) {
            }//用不到

            public void mouseEntered(MouseEvent event) {
            }//用不到

            public void mouseExited(MouseEvent event) {
            }//用不到

            public void mousePressed(MouseEvent event) {//當滑鼠移動物件時
                if (event.getSource() == b1) {
                    JOptionPane.showConfirmDialog(null, "時間加倍券的效果為該遊戲時間變為兩倍\n售價為5,000,000\n--------確定要使用嗎?--------", "", JOptionPane.YES_NO_OPTION);
                } else if (event.getSource() == b2) {
                    JOptionPane.showConfirmDialog(null, "經驗加倍券的效果為該遊戲經驗變為兩倍\n售價為5,000,000\n--------確定要使用嗎?--------", "", JOptionPane.YES_NO_OPTION);
                    // 0=yes, 1=no
                } else if (event.getSource() == b3) {
                    JOptionPane.showConfirmDialog(null, "電蚊拍的效果為\n售價為5,000,000\n--------確定要使用嗎?--------", "", JOptionPane.YES_NO_OPTION);
                    // 0=yes, 1=no
                } else if (event.getSource() == b4) {
                    JOptionPane.showConfirmDialog(null, "增加觀看時間的效果為\n售價為5,000,000\n--------確定要使用嗎?--------", "", JOptionPane.YES_NO_OPTION);
                    // 0=yes, 1=no
                } else if (event.getSource() == b5) {
                    JOptionPane.showConfirmDialog(null, "地下道鑰匙的效果為\n售價為5,000,000\n-------確定要使用嗎?--------", "", JOptionPane.YES_NO_OPTION);
                    // 0=yes, 1=no
                } else if (event.getSource() == b6) {
                    JOptionPane.showConfirmDialog(null, "警察卡的效果為\n售價為5,000,000\n--------確定要使用嗎?--------", "", JOptionPane.YES_NO_OPTION);
                    // 0=yes, 1=no
                } else if (event.getSource() == b7) {
                    JOptionPane.showConfirmDialog(null, "老師卡的效果為\n售價為5,000,000\n--------確定要使用嗎?--------", "", JOptionPane.YES_NO_OPTION);
                    // 0=yes, 1=no
                } else if (event.getSource() == b8) {
                    JOptionPane.showConfirmDialog(null, "地鼠王的效果為\n售價為5,000,000\n--------確定要使用嗎?--------", "", JOptionPane.YES_NO_OPTION);
                    // 0=yes, 1=no
                } else if (event.getSource() == b9) {
                    JOptionPane.showConfirmDialog(null, "再看一次的效果為\n售價為5,000,000\n--------確定要使用嗎?--------", "", JOptionPane.YES_NO_OPTION);
                    // 0=yes, 1=no
                } else if (event.getSource() == e1) {
                    JOptionPane.showConfirmDialog(null, "起點向前移動100公尺\n售價為5,000,000\n--------確定要使用嗎?--------", "", JOptionPane.YES_NO_OPTION);
                    // 0=yes, 1=no
                } else if (event.getSource() == e2) {
                    JOptionPane.showConfirmDialog(null, "可以一次前進兩格\n售價為5,000,000\n--------確定要使用嗎?--------", "", JOptionPane.YES_NO_OPTION);
                    // 0=yes, 1=no
                } else if (event.getSource() == e3) {
                    JOptionPane.showConfirmDialog(null, "可看到影子下的真實面貌\n售價為5,000,000\n--------確定要使用嗎?--------", "", JOptionPane.YES_NO_OPTION);
                    // 0=yes, 1=no
                } else if (event.getSource() == e4) {
                    JOptionPane.showConfirmDialog(null, "系統自動翻出一對配對組合\n售價為5,000,000\n--------確定要購買嗎?--------", "", JOptionPane.YES_NO_OPTION);
                    // 0=yes, 1=no
                }
            }
        }

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
                    /*bagTable.revalidate();*/
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

        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g); // clears drawing area
            try {
                if(IsBackGround) {
                    backGround = ImageIO.read(new File("data//Bag&Store/背包-裝備.png"));
                    ImageIcon iconE1 = new ImageIcon("data//Bag&Store/竹蜻蜓.png");
                    ImageIcon iconE2 = new ImageIcon("data//Bag&Store/彈簧鞋.png");
                    ImageIcon iconE3 = new ImageIcon("data//Bag&Store/透視眼鏡.png");
                    ImageIcon iconE4 = new ImageIcon("data//Bag&Store/翅膀.png");
                    e1.setIcon(iconE1);
                    e2.setIcon(iconE2);
                    e3.setIcon(iconE3);
                    e4.setIcon(iconE4);
                    e1.setBounds(144, 31, 275, 271);
                    e2.setBounds(144, 302, 275, 271);
                    e3.setBounds(419, 31, 275, 271);
                    e4.setBounds(419, 302, 275, 271);
                }
                else {
                    backGround = ImageIO.read(new File("data//Bag&Store/背包-道具.png"));
                }
            }
            catch (Exception ex) {
                System.out.println("No example.jpg!!");
            }
            g.drawImage(backGround, 0, 0, null);
        }
}