package HitMouse;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

import HitMouse.gameobject.*;
import HitMouse.gameobject.Character;

import Main.*;
import House.house.House;



public class Mouse extends JLayeredPane{
    private JFrame frame;



    private ImageIcon imageBackGround;//背景圖
    private ImageIcon[] teacher=new ImageIcon[6];//老師圖片(好地鼠)
    private ImageIcon[] student = new ImageIcon[5];//學生圖片(壞地鼠)
    private ImageIcon startImage;//開始圖片
    private ImageIcon backToMainImage;
    private ImageIcon disCountImage;
    private ImageIcon expandMoneyImage;
    private ImageIcon ruleImage;
    private ImageIcon testCharacterImage;

    private JButton btnStart;//開始按鈕
    private JButton  btnItemOnlyTeacher;//使用老師道具的按鈕
    private JButton btnItemScoreDouble;
    private JButton[] win;//放窗戶的圖片
    private JButton[] but;//人物出現的位置

    private Timer itemTeacherTime;//使用道具 生效時間
    private Timer itemScoreDouble;

    private int specialCard;//紀錄道具使用與否
    private final int SHIFT=300;
    private Score score;

    private JLabel backGroundLabel;
    private JButton backToMainButton;
    private JLabel disCountLabel;
    private JLabel expandMoneyLabel;
    private JLabel ruleLabel;
    private JLabel testCharacterLabel;
    private JTextField scoreText;
    private Main mainFrame;
    private House house;
     public Mouse(Main mainFrame,House house){
    //public Mouse(){
        this.house=house;
        this.mainFrame=mainFrame;

       /* frame = new JFrame("打地鼠");
        frame.setSize(900,675);
        //frame.setBounds(0,0,900,675);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
*/
       imageBackGround=new ImageIcon("data/HitMouse/background/background.png");//background
        backGroundLabel=new JLabel(imageBackGround);
        backGroundLabel.setBounds(SHIFT,0,imageBackGround.getIconWidth(),imageBackGround.getIconHeight());
        add(backGroundLabel,JLayeredPane.DEFAULT_LAYER);


        backToMainImage = new ImageIcon("data/gamebar/backhome.png");
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
             mainFrame.changeToMainScreen();
         }
        });

        disCountImage = new ImageIcon("data/gamebar/discount.png");
        disCountLabel = new JLabel(disCountImage);
        disCountLabel.setBounds(0,backToMainImage.getIconHeight(),disCountImage.getIconWidth(),disCountImage.getIconHeight());
        add(disCountLabel,JLayeredPane.DEFAULT_LAYER);

        ruleImage = new ImageIcon("data/gamebar/rule.png");
        ruleLabel = new JLabel(ruleImage);
        ruleLabel.setBounds(0,backToMainImage.getIconHeight()+disCountImage.getIconHeight(),ruleImage.getIconWidth(),ruleImage.getIconHeight());
        add(ruleLabel,JLayeredPane.DEFAULT_LAYER);

        expandMoneyImage = new ImageIcon("data/gamebar/expandMoney.png");
        expandMoneyLabel = new JLabel(expandMoneyImage);
        expandMoneyLabel.setBounds(0,backToMainImage.getIconHeight()+disCountImage.getIconHeight()+ruleImage.getIconHeight(),expandMoneyImage.getIconWidth(),expandMoneyImage.getIconHeight());
        add(expandMoneyLabel,JLayeredPane.DEFAULT_LAYER);




        scoreText = new JTextField();
        scoreText.setEditable(false);
        scoreText.setColumns(5);
        scoreText.setBounds(175,115,30,20);


        //add(scoreText,JLayeredPane.MODAL_LAYER);

        for(int i=0;i<6;i++){
            teacher[i]=new ImageIcon(Character.teachers[i]);
        }
        for(int i=0;i<5;i++){
            student[i]=new ImageIcon(Character.students[i]);
        }
        startImage=new ImageIcon("data/HitMouse/image/start.jpg");
        btnStart = new JButton();
        btnStart.setIcon(startImage);
        btnStart.setBounds(736+SHIFT, 35, 125, 59);
        add(btnStart,JLayeredPane.MODAL_LAYER);
        btnItemOnlyTeacher = new JButton("道具");
        btnItemOnlyTeacher.setBounds(200,580,100,30);
        add(btnItemOnlyTeacher,JLayeredPane.MODAL_LAYER);
        btnItemScoreDouble = new JButton("道具2");
        btnItemScoreDouble.setBounds(200,610,100,30);
        add(btnItemScoreDouble,JLayeredPane.MODAL_LAYER);

        btnItemScoreDouble.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                score.setAddScore(score.getAddScore()*2);
                score.setMinusScore(0);
                itemScoreDouble.start();
            }
        });
        itemScoreDouble = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                score.setAddScore(score.getAddScore()/2);
                score.setMinusScore(1);
            }
        });
        itemTeacherTime = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                specialCard=0;
            }
        });
        btnItemOnlyTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                specialCard=1;
                itemTeacherTime.start();


            }
        });
        win=new JButton[16];
        but=new JButton[16];
        int k=0;
        for(int i=65;i<=695;i+=210){
            for(int j=155;j<=505;j+=170){

                but[k] = new JButton();
               // but[k].setIcon(new ImageIcon("data/HitMouse/background/窗戶.png"));
                but[k].setBounds(i+2+SHIFT,j+2,150,130);

                but[k].setBorderPainted(false);
                but[k].setBorder(null);
                but[k].setFocusPainted(false);
                but[k].setContentAreaFilled(false);


                add(but[k],JLayeredPane.MODAL_LAYER);

                win[k] = new JButton();
                win[k].setIcon(new ImageIcon("data/HitMouse/background/窗戶.png"));
                win[k].setBounds(i+SHIFT,j,153,134);
                win[k].setBorderPainted(false);
                win[k].setBorder(null);
                win[k].setFocusPainted(false);
                win[k].setContentAreaFilled(false);
                add(win[k],JLayeredPane.PALETTE_LAYER);
                k++;
            }
        }
        score =new Score();
        JLabel djs =new JLabel(""); //Countdown
        djs.setFont(new Font("標楷體", Font.BOLD, 25));;
        djs.setBounds(175,115,30,25);
        add(djs,JLayeredPane.MODAL_LAYER);


        JLabel jfb =new JLabel("得分：0"); //Scoreboard
        jfb.setBounds(8+SHIFT, 40, 200, 59);
        add(jfb,JLayeredPane.MODAL_LAYER);

        //開始按鈕並設定第一支地鼠出現的時間延遲
        Timer timer=new Timer(score.getSd()-10,new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                but[score.getLast()].setIcon(null);
                score.setLast((int)(12*Math.random()));
                int choose=(int) (2 * Math.random());

                if( choose==0 || specialCard==1)  but[score.getLast()].setIcon(teacher[(int) (6 * Math.random())]);
                else but[score.getLast()].setIcon(student[(int) (5 * Math.random())]);

            }
        });

        //timer2 生成最後的得分訊息
        Timer time2=new Timer(2,new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timer.setDelay(score.getSd());//設定地鼠出現速度
                jfb.setText("得分："+score.getScore());
                djs.setText((score.getStarttime()/1000+score.getDjs()-System.currentTimeMillis()/1000)+"");
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
                    itemTeacherTime.stop();
                    djs.setText("");
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
            but[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    int c = e.getButton();// 得到按下的滑鼠鍵
                    if (c == MouseEvent.BUTTON1){ // 判斷是滑鼠左鍵按下
                        if (but[ii].getIcon() != null) {
                            Sound.KissSound();
                            boolean judge=false;
                            for(int j=0;j<6;j++){//圖片是老師
                                if(Character.teachers[j].equals(but[ii].getIcon().toString())) judge=true;
                            }
                            if(judge==true) score.setScore(score.getScore() + score.getAddScore());
                            else score.setScore(score.getScore() - score.getMinusScore());

                            but[score.getLast()].setIcon(null);
                            //Sound.KissSound();
                        }
                    }
                    if (c == MouseEvent.BUTTON3) {// 判斷是滑鼠右鍵按下
                        if (but[ii].getIcon() != null) {
                            Sound.HitSound();
                            boolean judge=false;
                            for(int j=0;j<5;j++){
                                if(Character.students[j].equals(but[ii].getIcon().toString())) judge=true;
                            }
                            if(judge==true) score.setScore(score.getScore() + score.getAddScore());
                            else score.setScore(score.getScore() - score.getMinusScore());

                            but[score.getLast()].setIcon(null);

                        }
                    }
                }
                @Override
                public void mousePressed(MouseEvent e) {
                    mouseClicked(e);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    mouseClicked(e);
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
