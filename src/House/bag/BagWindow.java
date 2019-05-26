import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class BagWindow extends JFrame {
    private final JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11;
    private final JButton e1,e2,e3,e4;
    private final JButton item,equip;
    private final JPanel bagTable;
    public static void main(String args[]) {
        BagWindow bagwindow=new BagWindow();
        bagwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bagwindow.setSize(500,300);
        bagwindow.setVisible(true);
    }
    public BagWindow() {
        super("BagWindow");
        setLayout(new BorderLayout());
        JPanel jpanel= new JPanel();
        jpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        item= new JButton("道具");
        equip= new JButton("裝備");
        ButtonHandler handler = new ButtonHandler();
        item.addActionListener(handler);
        equip.addActionListener(handler);
        item.setPreferredSize(new Dimension(60, 45));
        equip.setPreferredSize(new Dimension(60, 45));
        jpanel.add(item);
        jpanel.add(equip);
        add(jpanel,BorderLayout.NORTH);
        bagTable=new JPanel();
        bagTable.setLayout(new GridLayout(4,3));
        //Icon  sth= new ImageIcon(getClass().getResource("sth.gif"));
        b1=new JButton("時間加倍券");
        b2=new JButton("經驗加倍券");
        b3=new JButton("電蚊拍");
        b4=new JButton("哥吉拉");
        b5=new JButton("地下道鑰匙");
        b6=new JButton("警察卡");
        b7=new JButton("老師卡");
        b8=new JButton("地鼠王");
        b9=new JButton("再看一次");
        b10=new JButton("增加觀看時間");
        b11=new JButton("金卡");
        e1=new JButton("竹蜻蜓");
        e2=new JButton("彈簧鞋");
        e3=new JButton("透視眼鏡");
        e4=new JButton("翅膀");

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
        add(bagTable);
    }
    private class ButtonHandler implements ActionListener {
        // handle button event
        @Override
        public void actionPerformed(ActionEvent event) {
            if(event.getSource()==item){
                bagTable.removeAll();
                bagTable.revalidate();
                bagTable.repaint();
                bagTable.setLayout(new GridLayout(4,3));
                //Icon  sth= new ImageIcon(getClass().getResource("sth.gif"));
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
                add(bagTable);

            }
            else if(event.getSource()==equip){
                bagTable.removeAll();
                bagTable.revalidate();
                bagTable.repaint();
                bagTable.setLayout(new GridLayout(2,2));
                //Icon  sth= new ImageIcon(getClass().getResource("sth.gif"));
                bagTable.add(e1);
                bagTable.add(e2);
                bagTable.add(e3);
                bagTable.add(e4);
                add(bagTable);
            }
        }
    }
}