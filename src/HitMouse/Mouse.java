package HitMouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.EventQueue;
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
import java.util.Random;
import HitMouse.gameobject.*;
import HitMouse.gameobject.Character;

import java.lang.Thread;

public class Mouse extends JLayeredPane{
    private JFrame frame;
    private ImageIcon[] teacher=new ImageIcon[6];
    private ImageIcon[] student = new ImageIcon[5];
    private ImageIcon kaishi;
    public static void main1(String[] args) {
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
       /* frame = new JFrame("打地鼠");
        frame.setSize(900,675);
        //frame.setBounds(0,0,900,675);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JLayeredPane layeredPane=new JLayeredPane();

        JPanel panel=new JPanel();
        frame.add(panel);
        panel.setLayout(null);
        panel.setBounds(0,0,image.getIconWidth(),image.getIconHeight());
        panel.add(layeredPane);
*/
       // layeredPane.add(panel,JLayeredPane.DEFAULT_LAYER);  //背景放在最下層
        JLabel jl=new JLabel(image);
        jl.setBounds(0,0,image.getIconWidth(),image.getIconHeight());
        add(jl,JLayeredPane.DEFAULT_LAYER);

        // panel.add(jl);
        //frame.add(layeredPane);
        /*
        frame.setLayeredPane(layeredPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        */
        for(int i=0;i<6;i++){
            teacher[i]=new ImageIcon(Character.teachers[i]);
        }
        for(int i=0;i<5;i++){
            student[i]=new ImageIcon(Character.students[i]);
        }
        kaishi=new ImageIcon("data/HitMouse/image/start.jpg");
        JButton btnStart = new JButton();
        btnStart.setIcon(kaishi);
        btnStart.setBounds(736, 35, 125, 59);
        add(btnStart,JLayeredPane.MODAL_LAYER);
        JButton[] win=new JButton[16];
        JButton[] but=new JButton[16];
        int k=0;
        for(int i=65;i<=695;i+=210){
            for(int j=155;j<=505;j+=170){

                but[k] = new JButton();
               // but[k].setIcon(new ImageIcon("data/HitMouse/background/窗戶.png"));
                but[k].setBounds(i+2,j+2,150,130);

                but[k].setBorderPainted(false);
                but[k].setBorder(null);
                but[k].setFocusPainted(false);
                but[k].setContentAreaFilled(false);


                add(but[k],JLayeredPane.MODAL_LAYER);

                win[k] = new JButton();
                win[k].setIcon(new ImageIcon("data/HitMouse/background/窗戶.png"));
                win[k].setBounds(i,j,153,134);
                win[k].setBorderPainted(false);
                win[k].setBorder(null);
                win[k].setFocusPainted(false);
                win[k].setContentAreaFilled(false);
                add(win[k],JLayeredPane.PALETTE_LAYER);
                k++;
            }
        }
        Score score =new Score();
        JLabel djs =new JLabel("倒計時："); //Countdown
        djs.setBounds(8, 16, 250, 59);
        add(djs,JLayeredPane.MODAL_LAYER);


        JLabel jfb =new JLabel("得分：0"); //Scoreboard
        jfb.setBounds(8, 40, 200, 59);
        add(jfb,JLayeredPane.MODAL_LAYER);


        Timer timer=new Timer(score.getSd()-10,new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                but[score.getLast()].setIcon(null);
                score.setLast((int)(12*Math.random()));
                int choose=(int) (2 * Math.random());
                if(choose==0)  but[score.getLast()].setIcon(teacher[(int) (6 * Math.random())]);
                else but[score.getLast()].setIcon(student[(int) (5 * Math.random())]);

            }
        });

        //timer2 生成最後的得分訊息
        Timer time2=new Timer(2,new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timer.setDelay(score.getSd());
                jfb.setText("得分："+score.getScore());
                djs.setText("倒計時："+(score.getStarttime()/1000+score.getDjs()-System.currentTimeMillis()/1000)+"s");
                if((score.getStarttime()/1000+score.getDjs()-System.currentTimeMillis()/1000)==0)
                {
                    timer.stop();
                    but[score.getLast()].setIcon(null);
                    JOptionPane.showMessageDialog(frame, "Game Over\n 您的得分為："+score.getScore(),"得分信息",JOptionPane.PLAIN_MESSAGE);
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
        //    Random  r = new Random();
            but[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int c = e.getButton();// 得到按下的滑鼠鍵
                    if (c == MouseEvent.BUTTON1){ // 判斷是滑鼠左鍵按下
                        if (but[ii].getIcon() != null) {
                            boolean judge=false;
                            for(int j=0;j<6;j++){
                                if(Character.teachers[j].equals(but[ii].getIcon().toString())) judge=true;
                            }
                            if(judge==true) score.setScore(score.getScore() + 1);
                            else score.setScore(score.getScore() - 1);

                            but[score.getLast()].setIcon(null);

                        }
                    }
                    if (c == MouseEvent.BUTTON3) {// 判斷是滑鼠右鍵按下
                        if (but[ii].getIcon() != null) {
                            boolean judge=false;
                            for(int j=0;j<5;j++){
                                if(Character.students[j].equals(but[ii].getIcon().toString())) judge=true;
                            }
                            if(judge==true) score.setScore(score.getScore() + 1);
                            else score.setScore(score.getScore() - 1);

                            but[score.getLast()].setIcon(null);

                        }
                    }
                }
                @Override
                public void mousePressed(MouseEvent e) {
                    int c = e.getButton();// 得到按下的滑鼠鍵
                    if (c == MouseEvent.BUTTON1){ // 判斷是滑鼠左鍵按下
                        if (but[ii].getIcon() != null) {
                            boolean judge=false;
                            for(int j=0;j<6;j++){
                                if(Character.teachers[j].equals(but[ii].getIcon().toString())) judge=true;
                            }
                            if(judge==true) score.setScore(score.getScore() + 1);
                            else score.setScore(score.getScore() - 1);

                            but[score.getLast()].setIcon(null);

                        }
                    }
                    if (c == MouseEvent.BUTTON3) {// 判斷是滑鼠右鍵按下
                        if (but[ii].getIcon() != null) {
                            boolean judge=false;
                            for(int j=0;j<5;j++){
                                if(Character.students[j].equals(but[ii].getIcon().toString())) judge=true;
                            }
                            if(judge==true) score.setScore(score.getScore() + 1);
                            else score.setScore(score.getScore() - 1);

                            but[score.getLast()].setIcon(null);

                        }
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    int c = e.getButton();// 得到按下的滑鼠鍵
                    if (c == MouseEvent.BUTTON1){ // 判斷是滑鼠左鍵按下
                        if (but[ii].getIcon() != null) {
                            boolean judge=false;
                            for(int j=0;j<6;j++){
                                if(Character.teachers[j].equals(but[ii].getIcon().toString())) judge=true;
                            }
                            if(judge==true) score.setScore(score.getScore() + 1);
                            else score.setScore(score.getScore() - 1);

                            but[score.getLast()].setIcon(null);

                        }
                    }
                    if (c == MouseEvent.BUTTON3) {// 判斷是滑鼠右鍵按下
                        if (but[ii].getIcon() != null) {
                            boolean judge=false;
                            for(int j=0;j<5;j++){
                                if(Character.students[j].equals(but[ii].getIcon().toString())) judge=true;
                            }
                            if(judge==true) score.setScore(score.getScore() + 1);
                            else score.setScore(score.getScore() - 1);

                            but[score.getLast()].setIcon(null);

                        }
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });

        }

//        frame.setVisible(true);
    }
}
