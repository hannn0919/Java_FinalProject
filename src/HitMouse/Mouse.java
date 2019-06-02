package Mouse;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import gameobject.*;

class MyPanel extends JPanel {
    public void paint(Graphics g1) {
        Graphics2D g=(Graphics2D)g1;
        super.paintComponents(g);
    }
}
public class Mouse {
    private JFrame frame;
    private ImageIcon[] teacher=new ImageIcon[6];
    private ImageIcon kaishi;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Mouse window = new Mouse();

                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public Mouse(){
        ImageIcon image=new ImageIcon("data/HitMouse/background/background.png");//background
        frame = new JFrame("打地鼠");
        frame.setSize(900,675);
        //frame.setBounds(0,0,900,675);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JLayeredPane layeredPane=new JLayeredPane();
        MyPanel panel=new MyPanel();
        frame.add(panel);
        panel.setLayout(null);
        panel.setBounds(0,0,image.getIconWidth(),image.getIconHeight());

        JLabel jl=new JLabel(image);
        jl.setBounds(0,0,image.getIconWidth(),image.getIconHeight());
        panel.add(jl);

        layeredPane.add(panel,JLayeredPane.DEFAULT_LAYER);  //背景放在最下層
        frame.setLayeredPane(layeredPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        for(int i=0;i<6;i++){
            teacher[i]=new ImageIcon(Teachers.teachers[i]);
        }
        ImageIcon dishu=new ImageIcon("data/HitMouse/image/dishu.jpg");//mouse icon
        kaishi=new ImageIcon("data/HitMouse/image/start.jpg");
        JButton btnStart = new JButton();
        btnStart.setIcon(kaishi);
        btnStart.setBounds(736, 35, 125, 59);
        layeredPane.add(btnStart,JLayeredPane.MODAL_LAYER);

        Windows[] but=new Windows[16];
        int k=0;
        for(int i=65;i<=695;i+=210){
            for(int j=155;j<=505;j+=170){
                but[k] = new Windows(i,j,"data/HitMouse/background/窗戶.png");
                layeredPane.add(but[k],JLayeredPane.MODAL_LAYER);
                k++;
            }
        }
        Score score =new Score();
        JLabel djs =new JLabel("倒計時："); //Countdown
        djs.setBounds(8, 16, 250, 59);
        layeredPane.add(djs,JLayeredPane.MODAL_LAYER);


        JLabel jfb =new JLabel("得分：0"); //Scoreboard
        jfb.setBounds(8, 40, 200, 59);
        layeredPane.add(jfb,JLayeredPane.MODAL_LAYER);
        Timer timer=new Timer(score.getSd()-10,new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                but[score.getLast()].setIcon(null);
                score.setLast((int)(9*Math.random()));
                but[score.getLast()].setIcon(dishu);
            }
        });
        Timer time2=new Timer(2,new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timer.setDelay(score.getSd());
                jfb.setText("得分："+score.getScore());
                djs.setText("倒計時："+(score.getStarttime()/1000+score.getDjs()-System.currentTimeMillis()/1000)+"s");
                if((score.getStarttime()/1000+score.getDjs()-System.currentTimeMillis()/1000)==0)
                {
                    timer.stop();
                    but[score.getLast()].setIcon(null);
                    JOptionPane.showMessageDialog(frame, "Game Over\n 您的得分为："+score.getScore(),"得分信息",JOptionPane.PLAIN_MESSAGE);
                    score.setScore(0);
                    jfb.setText("得分：0");
                }
            }
        });
        Timer time3=new Timer(2,new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if((score.getStarttime()/1000+score.getDjs()-System.currentTimeMillis()/1000)==-1)
                {
                    time2.stop();
                    djs.setText("倒計時：");
                }
            }
        });
        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                score.setStarttime(System.currentTimeMillis());
                timer.start();
                time2.start();
                time3.start();
            }
        });
        for(int i=0;i<12;i++){
            final int ii=i;
            but[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (but[ii].getIcon() != null) {
                        score.setScore(score.getScore() + 1);
                        but[score.getLast()].setIcon(null);
                        score.setLast((int) (12 * Math.random()));
                        but[score.getLast()].setIcon(dishu);
                    }
                }
            });
        }

        frame.setVisible(true);
    }
}
