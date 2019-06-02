package House.bag;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import javax.swing.event.*;
import java.net.URL;

public class BagWindow extends JPanel {
    private  JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11;
    private  JButton e1, e2, e3, e4;
    private  JButton item, equip;
    private  JPanel bagTable;
    private  BufferedImage backGround;
    private  boolean IsBackGround=true;

    public static void main(String args[]) {
        JFrame frame = new JFrame();
        frame.setTitle("BagWindow");
        BagWindow bagWindow = new BagWindow();
        frame.setLocationRelativeTo(null);
        frame.add(bagWindow);
        frame.setLocation(0,0);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public BagWindow() {
        setLayout(null);
        setPreferredSize(new Dimension(623,620));
        //setLayout(new BorderLayout());
        //JPanel jpanel = new JPanel();
        //jpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        item = new JButton();
        equip = new JButton();
        item.setContentAreaFilled(false);
        equip.setContentAreaFilled(false);
        item.setBorderPainted(false);
        equip.setBorderPainted(false);
        item.setBounds(205,23,150,94);
        equip.setBounds(26,25,168,94);
        ButtonHandler handler = new ButtonHandler();
        MouseHandler handler2 = new MouseHandler();
        equip.addActionListener(handler);
        item.addActionListener(handler);
        add(equip);
        add(item);
        //add(jpanel, BorderLayout.NORTH);
        bagTable = new JPanel();
        bagTable.setLayout(new GridLayout(4, 3));
        b1 = new JButton("時間加倍券");
        b1.addMouseListener(handler2);
        b2 = new JButton("經驗加倍券");
        b2.addMouseListener(handler2);
        b3 = new JButton("電蚊拍");
        b3.addMouseListener(handler2);
        b4 = new JButton("哥吉拉");
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
        b10 = new JButton("增加觀看時間");
        b10.addMouseListener(handler2);
        b11 = new JButton("金卡");
        b11.addMouseListener(handler2);
        e1 = new JButton("竹蜻蜓");
        //ImageIcon iconE1 = new ImageIcon("../data/Bag&Store/竹蜻蜓.png");
        //e1.setIcon(iconE1);
        e1.addMouseListener(handler2);
        e2 = new JButton("彈簧鞋");
        e2.addMouseListener(handler2);
        e3 = new JButton("透視眼鏡");
        e3.addMouseListener(handler2);
        e4 = new JButton("翅膀");
        e4.addMouseListener(handler2);

        /*bagTable.add(b1);
        bagTable.add(b2);
        bagTable.add(b3);
        bagTable.add(b4);
        bagTable.add(b5);
        bagTable.add(b6);
        bagTable.add(b7);
        bagTable.add(b8);
        bagTable.add(b9);
        bagTable.add(b10);
        bagTable.add(b11);
        add(bagTable);*/
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
                //int input = JOptionPane.showConfirmDialog(null, "時間加倍券的效果為該遊戲時間變為兩倍\n--------------確定要使用嗎?--------------","", JOptionPane.YES_NO_OPTION);
                // 0=yes, 1=no
                JOptionPane.showConfirmDialog(null, "時間加倍券的效果為該遊戲時間變為兩倍\n--------------確定要使用嗎?--------------", "", JOptionPane.YES_NO_OPTION);
                //System.out.println(input);
            } else if (event.getSource() == b2) {
                JOptionPane.showConfirmDialog(null, "經驗加倍券的效果為該遊戲經驗變為兩倍\n--------------確定要使用嗎?--------------", "", JOptionPane.YES_NO_OPTION);
                // 0=yes, 1=no
            } else if (event.getSource() == b3) {
                JOptionPane.showConfirmDialog(null, "電蚊拍的效果為\n--------------確定要使用嗎?--------------", "", JOptionPane.YES_NO_OPTION);
                // 0=yes, 1=no
            } else if (event.getSource() == b4) {
                JOptionPane.showConfirmDialog(null, "哥吉拉的效果為\n--------------確定要使用嗎?--------------", "", JOptionPane.YES_NO_OPTION);
                // 0=yes, 1=no
            } else if (event.getSource() == b5) {
                JOptionPane.showConfirmDialog(null, "地下道鑰匙的效果為\n--------------確定要使用嗎?--------------", "", JOptionPane.YES_NO_OPTION);
                // 0=yes, 1=no
            } else if (event.getSource() == b6) {
                JOptionPane.showConfirmDialog(null, "警察卡的效果為\n--------------確定要使用嗎?--------------", "", JOptionPane.YES_NO_OPTION);
                // 0=yes, 1=no
            } else if (event.getSource() == b7) {
                JOptionPane.showConfirmDialog(null, "老師卡的效果為\n--------------確定要使用嗎?--------------", "", JOptionPane.YES_NO_OPTION);
                // 0=yes, 1=no
            } else if (event.getSource() == b8) {
                JOptionPane.showConfirmDialog(null, "地鼠王的效果為\n--------------確定要使用嗎?--------------", "", JOptionPane.YES_NO_OPTION);
                // 0=yes, 1=no
            } else if (event.getSource() == b9) {
                JOptionPane.showConfirmDialog(null, "再看一次的效果為\n--------------確定要使用嗎?--------------", "", JOptionPane.YES_NO_OPTION);
                // 0=yes, 1=no
            } else if (event.getSource() == b10) {
                JOptionPane.showConfirmDialog(null, "增加觀看時間的效果為\n--------------確定要使用嗎?--------------", "", JOptionPane.YES_NO_OPTION);
                // 0=yes, 1=no
            } else if (event.getSource() == b11) {
                JOptionPane.showConfirmDialog(null, "金卡的效果為\n--------------確定要使用嗎?--------------", "", JOptionPane.YES_NO_OPTION);
                // 0=yes, 1=no
            } else if (event.getSource() == e1) {
                JOptionPane.showConfirmDialog(null, "竹蜻蜓的效果為\n--------------確定要使用嗎?--------------", "", JOptionPane.YES_NO_OPTION);
                // 0=yes, 1=no
            } else if (event.getSource() == e2) {
                JOptionPane.showConfirmDialog(null, "彈簧鞋的效果為\n--------------確定要使用嗎?--------------", "", JOptionPane.YES_NO_OPTION);
                // 0=yes, 1=no
            } else if (event.getSource() == e3) {
                JOptionPane.showConfirmDialog(null, "隱形眼鏡的效果為\n--------------確定要使用嗎?--------------", "", JOptionPane.YES_NO_OPTION);
                // 0=yes, 1=no
            } else if (event.getSource() == e4) {
                JOptionPane.showConfirmDialog(null, "翅膀的效果為\n--------------確定要使用嗎?--------------", "", JOptionPane.YES_NO_OPTION);
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
                /*bagTable.removeAll();
                bagTable.revalidate();
                bagTable.repaint();
                bagTable.setLayout(new GridLayout(4, 3));
                bagTable.add(b1);
                bagTable.add(b2);
                bagTable.add(b3);
                bagTable.add(b4);
                bagTable.add(b5);
                bagTable.add(b6);
                bagTable.add(b7);
                bagTable.add(b8);
                bagTable.add(b9);
                bagTable.add(b10);
                bagTable.add(b11);
                add(bagTable);*/

            } else if (event.getSource() == equip) {
                repaint();
                IsBackGround=true;
                /*bagTable.removeAll();
                bagTable.revalidate();
                bagTable.repaint();
                bagTable.setLayout(new GridLayout(2, 2));
                bagTable.add(e1);
                e1.setOpaque(true);
                ImageIcon iconE1 = new ImageIcon("data//Bag&Store/竹蜻蜓.png");
                e1.setIcon(iconE1);
                bagTable.add(e2);
                bagTable.add(e3);
                bagTable.add(e4);
                add(bagTable);*/
            }
        }
    }

   @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g); // clears drawing area
        try {
            if(IsBackGround)
                backGround = ImageIO.read(new File("data//Bag&Store/裝備道具欄-按裝備.png"));
            else
                backGround = ImageIO.read(new File("data//Bag&Store/裝備道具欄-按道具.png"));
        }
        catch (Exception ex) {
            System.out.println("No example.jpg!!");
        }

        g.drawImage(backGround, 0, 0, null);
    }

}