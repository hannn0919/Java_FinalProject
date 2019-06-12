package HitMouse;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

import HitMouse.gameobject.*;
import HitMouse.gameobject.Character;

import Main.*;
import House.house.House;
import java.security.SecureRandom;



public class Mouse extends JLayeredPane{

    private JFrame frame;

    private ImageIcon imageBackGround;//背景圖
    private ImageIcon[] teacher=new ImageIcon[6];//老師圖片(好地鼠)
    private ImageIcon[] student = new ImageIcon[5];//學生圖片(壞地鼠)
    private ImageIcon shadow;
    private ImageIcon startImage;//開始圖片
    private ImageIcon backToMainImage; //回到主程式的圖片
    private ImageIcon disCountImage; //到數計時的圖片
    private ImageIcon expandMoneyImage; //經驗 前 肝的組合圖片
    private ImageIcon ruleImage;//簡單規則介紹的底圖
    private ImageIcon fist; //拳頭的圖片
    private ImageIcon heart;//愛心的圖片
    private ImageIcon staticsImg;//結算頁面底圖
    private ImageIcon checkImg;//OK按鈕
    private ImageIcon liverImg;


   // private JButton btnStart;//開始按鈕
    private JButton  btnItemOnlyTeacher;//使用老師道具的按鈕
    private JButton btnItemScoreDouble;//使用分數加倍的按鈕
    private JButton btnEquipment;//使用裝備的按鈕
    private JButton[] win;//放窗戶的圖片
    private JButton[] but;//人物出現的位置
    private JLabel[] obBut;//shadow出現的位置

    private Timer itemTeacherTime;//使用老師道具 生效時間
    private Timer itemScoreDouble;//使用分數加倍道具 生效時間

    private int specialCard;//紀錄道具使用與否
    private final int SHIFT=300;
    private Score score;
    private Score score2;

    private JLabel backGroundLabel;
    private JButton backToMainButton;
    private JButton checkBtn;
    private JLabel disCountLabel;
    private JLabel expandMoneyLabel;
    private JLabel ruleLabel;
    private JLabel liverLabel;

    private JLabel djs;
    private JLabel expFromMain;
    private JLabel moneyFromMain;

    private Main mainFrame;
    private House house;


    private JCheckBox moneycard;
    private JCheckBox expcard;
    private ImageIcon startImg;
    private JButton startBtn;
    private ImageIcon introduceImg;
    private JLabel introduce;
    private JLabel statics;
    private JLabel static_pxp;
    private JLabel static_money;
    private boolean equipIsUsed=false;

    private Timer timer;
    private Timer time2;
    private Timer time3;
    private Timer shadowTimer;

    private boolean expDoubleUsed;
    private boolean moneyDoubleUsed;
    private class UseItem implements ItemListener{
        @Override
        public void itemStateChanged(ItemEvent event) {
            int chkbox = event.getStateChange();
            if (chkbox == ItemEvent.SELECTED) {
                if (event.getItem() == expcard) {
                    expDoubleUsed = true;
                    house.setItem("經驗加倍券", house.getItem("經驗加倍券")-1);
                }
                if (event.getItem() == moneycard) {
                    moneyDoubleUsed= true;
                    house.setItem("金錢加倍券", house.getItem("金錢加倍券")-1);
                }

            }
            if (chkbox == ItemEvent.DESELECTED) {
                if (event.getItem() == expcard) {
                    expDoubleUsed = true;
                    house.setItem("經驗加倍券", house.getItem("經驗加倍券")+1);
                }
                if (event.getItem() == moneycard) {
                    moneyDoubleUsed= true;
                    house.setItem("金錢加倍券", house.getItem("金錢加倍券")+1);
                }

            }
        }
    }
    public ImageIcon resize(int width, int height, ImageIcon img) {
        Image i = img.getImage();
        Image new_img = i.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return  new ImageIcon(new_img);
    }

    private class MouseHandler implements  MouseListener{
        @Override public void mousePressed(MouseEvent e){
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
        @Override public void mouseReleased(MouseEvent e){}
        @Override
        public void mouseClicked(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e){
            if(e.getSource()==checkBtn) {
                Sound.buttonSound();
                checkBtn.setIcon(resize(checkBtn.getIcon().getIconWidth()+10,checkBtn.getIcon().getIconHeight()+10,(ImageIcon)checkBtn.getIcon()));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }else if(e.getSource()==startBtn){
                Sound.buttonSound();
                startBtn.setIcon(resize(startBtn.getIcon().getIconWidth()+10,startBtn.getIcon().getIconHeight()+10,(ImageIcon)startBtn.getIcon()));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }else if(e.getSource()==backToMainButton){
                Sound.buttonSound();
                backToMainButton.setIcon(resize(backToMainButton.getIcon().getIconWidth()+10,backToMainButton.getIcon().getIconHeight()+10,(ImageIcon)backToMainButton.getIcon()));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        }
        @Override
        public void mouseExited(MouseEvent arg0) {
            if(arg0.getSource()==checkBtn){
                checkBtn.setIcon(new ImageIcon("data/HitMouse/OK鍵.png"));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            else if(arg0.getSource()==startBtn) {
                startBtn.setIcon(new ImageIcon("data/HitMouse/開始遊戲按鈕.png"));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            else if(arg0.getSource()==backToMainButton) {
                backToMainButton.setIcon(new ImageIcon("data/gamebar/backhome.png"));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }
    }
     public Mouse(Main mainFrame,House house) {
         //public Mouse(){
         MouseHandler mouseHandler = new MouseHandler();
         UseItem useItem = new UseItem();
         this.house = house;
         this.mainFrame = mainFrame;


         imageBackGround = new ImageIcon("data/HitMouse/background/background.png");//background
         backGroundLabel = new JLabel(imageBackGround);
         backGroundLabel.setBounds(SHIFT, 0, imageBackGround.getIconWidth(), imageBackGround.getIconHeight());
         add(backGroundLabel, JLayeredPane.DEFAULT_LAYER);

             int heightTotal=0;
             backToMainImage = new ImageIcon("data/gamebar/backhome.png");
             backToMainButton = new JButton(backToMainImage);
             backToMainButton.setBounds(0, heightTotal, backToMainImage.getIconWidth(), backToMainImage.getIconHeight());
             add(backToMainButton, JLayeredPane.DEFAULT_LAYER);
             backToMainButton.setBorderPainted(false);
             backToMainButton.setBorder(null);
             backToMainButton.setFocusPainted(false);
             backToMainButton.setContentAreaFilled(false);

             heightTotal+=backToMainImage.getIconHeight();



             disCountImage = new ImageIcon("data/gamebar/discount.png");
             disCountLabel = new JLabel(disCountImage);
             disCountLabel.setBounds(0, heightTotal, disCountImage.getIconWidth(), disCountImage.getIconHeight());
             add(disCountLabel, JLayeredPane.DEFAULT_LAYER);
             heightTotal+=disCountImage.getIconHeight();

             ruleImage = new ImageIcon("data/gamebar/hitMouseRule.png");
             ruleLabel = new JLabel(ruleImage);
             ruleLabel.setBounds(0, heightTotal, ruleImage.getIconWidth(), ruleImage.getIconHeight());
             add(ruleLabel, JLayeredPane.DEFAULT_LAYER);
             heightTotal+=ruleImage.getIconHeight();

             expandMoneyImage = new ImageIcon("data/gamebar/expandMoney.png");
             expandMoneyLabel = new JLabel(expandMoneyImage);
             expandMoneyLabel.setBounds(0, heightTotal, expandMoneyImage.getIconWidth(), expandMoneyImage.getIconHeight());
             add(expandMoneyLabel, JLayeredPane.DEFAULT_LAYER);

             String character = "data/dinosaur/character/"+house.getLevel()+"/肝";
             if(house.getEquipment("透視眼鏡")==1){
                 character += "+眼鏡";
             }

             if(house.getEquipment("竹蜻蜓")==1){
                 character += "+竹蜻蜓";
             }

             if(house.getEquipment("翅膀")==1){
                 character += "+翅膀";
             }

             if(house.getEquipment("彈簧鞋")==1){
                 character += "+彈簧鞋";

             }
             liverImg = new ImageIcon( character+".png");//偷個恐龍圖片
             System.out.println(liverImg);
             liverLabel = new JLabel(liverImg);
             liverLabel.setBounds(30,heightTotal+100,liverImg.getIconWidth(),liverImg.getIconHeight());
             System.out.println(liverImg.getIconWidth()+" "+liverImg.getIconHeight());
             add(liverLabel,JLayeredPane.DRAG_LAYER);

             score =new Score();
             score2 = new Score();
             djs =new JLabel(""); //Countdown
             djs.setFont(new Font("微軟正黑體", Font.BOLD, 32));;
             djs.setBounds(176,76,300,100);
             add(djs,JLayeredPane.MODAL_LAYER);

             expFromMain = new JLabel("");
             expFromMain.setFont(new Font("Hollywood Hills",Font.BOLD,17));
             expFromMain.setBounds(140,heightTotal+13,150,25);
             expFromMain.setText(Integer.toString(house.getExp()));
             add(expFromMain,JLayeredPane.MODAL_LAYER);

             moneyFromMain = new JLabel("");
             moneyFromMain.setFont(new Font("Hollywood Hills",Font.BOLD,17));
             moneyFromMain.setBounds(140,heightTotal+50,150,25);
             moneyFromMain.setText(Integer.toString(house.getHoldMoney()));
             add(moneyFromMain,JLayeredPane.MODAL_LAYER);


         {
             introduceImg = new ImageIcon("data/HitMouse/introduce.png");
             introduce = new JLabel(introduceImg);
             introduce.setBounds(400, 99, 676, 476);
             add(introduce, JLayeredPane.POPUP_LAYER);


             expcard = new JCheckBox("經驗加倍卡");
             expcard.setFont(new Font("微軟正黑體", Font.PLAIN, 23));
             expcard.setBorderPainted(false);
             expcard.setFocusPainted(false);
             expcard.setContentAreaFilled(false);
             expcard.setBounds(580, 460, 150, 30);
             add(expcard, JLayeredPane.DRAG_LAYER);
             expcard.addItemListener(useItem);
             if(house.getItem("經驗加倍券")<=0) expcard.setEnabled(false);

             moneycard = new JCheckBox("金錢加倍卡");
             moneycard.setFont(new Font("微軟正黑體", Font.PLAIN, 23));
             moneycard.setBounds(730, 460, 150, 30);
             moneycard.setBorderPainted(false);
             moneycard.setFocusPainted(false);
             moneycard.setContentAreaFilled(false);
             add(moneycard, JLayeredPane.DRAG_LAYER);
             moneycard.addItemListener(useItem);
             if(house.getItem("金錢加倍券")<=0) moneycard.setEnabled(false);


             startImg = new ImageIcon("data/HitMouse/開始遊戲按鈕.png");
             startBtn = new JButton(startImg);
             startBtn.setBounds(660, 510, 150, 60);
             startBtn.setBorderPainted(false);
             startBtn.setBorder(null);
             startBtn.setFocusPainted(false);
             startBtn.setContentAreaFilled(false);
             startBtn.addMouseListener(mouseHandler);
             startBtn.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     if(house.getEquipment("透視眼鏡")==1) equipIsUsed=true;
                     else equipIsUsed=false;
                     remove(moneycard);

                     remove(startBtn);
                     remove(introduce);
                     remove(expcard);
                     repaint();

                     score.setStarttime(System.currentTimeMillis());
                     if(equipIsUsed == false){
                         timer.start();
                         time2.start();
                         time3.start();
                         shadowTimer.start();
                     }
                     else{
                         timer.start();
                         time2.start();
                         time3.start();
                     }
                 }
             });
             add(startBtn, JLayeredPane.DRAG_LAYER);

             staticsImg = new ImageIcon("data/Hitmouse/遊戲結算.png");
             statics = new JLabel(staticsImg);
             statics.setBounds(400, 99, 504,476);
             //add(statics, JLayeredPane.DEFAULT_LAYER);
             //statics.setVisible(false);

         }






             //introduce.setVisible(false);





        for(int i=0;i<6;i++){
            teacher[i]=new ImageIcon(Character.teachers[i]);
        }
        for(int i=0;i<5;i++){
            student[i]=new ImageIcon(Character.students[i]);
        }
        shadow = new ImageIcon((Character.shadow));

        startImage=new ImageIcon("data/HitMouse/image/start.jpg");
        //btnStart = new JButton();
        //btnStart.setIcon(startImage);
      //  btnStart.setBounds(736+SHIFT, 35, 125, 59);
       // add(btnStart,JLayeredPane.MODAL_LAYER);

        btnItemOnlyTeacher = new JButton();
        btnItemOnlyTeacher.setBounds(180,heightTotal+130,100,50);
        btnItemOnlyTeacher.setBorderPainted(false);
        btnItemOnlyTeacher.setBorder(null);
        btnItemOnlyTeacher.setFocusPainted(false);
        btnItemOnlyTeacher.setContentAreaFilled(false);
         //btnItemOnlyTeacher.setBounds(200,200,200,200);
        btnItemOnlyTeacher.setIcon(new ImageIcon("data/HitMouse/老師卡按鈕.png"));
        add(btnItemOnlyTeacher,JLayeredPane.MODAL_LAYER);
        if(house.getItem("老師卡")<=0) btnItemOnlyTeacher.setEnabled(false);
        else btnItemOnlyTeacher.setEnabled(true);


        btnItemScoreDouble = new JButton();
        btnItemScoreDouble.setBounds(180,heightTotal+180,100,50);
        btnItemScoreDouble.setIcon(new ImageIcon("data/HitMouse/分數加倍按鈕.png"));
        btnItemScoreDouble.setBorderPainted(false);
        btnItemScoreDouble.setBorder(null);
        btnItemScoreDouble.setFocusPainted(false);
        btnItemScoreDouble.setContentAreaFilled(false);
        add(btnItemScoreDouble,JLayeredPane.MODAL_LAYER);
        if(house.getItem("加倍卡")<=0) btnItemScoreDouble.setEnabled(false);
        else btnItemScoreDouble.setEnabled(true);

        btnEquipment = new JButton("裝備");
        btnEquipment.setBounds(200,550,100,30);
        //add(btnEquipment,JLayeredPane.MODAL_LAYER);
        btnEquipment.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equipIsUsed=true;
            }
        }));

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
        btnItemScoreDouble.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("分數加倍卡"+ house.getItem("加倍卡"));
                 if(house.getItem("加倍卡")>=1){
                     house.setItem("加倍卡",house.getItem("加倍卡")-1);
                     score.setAddScore(score.getAddScore()*2);

                     score.setMinusScore(0);
                     itemScoreDouble.start();
                 }

             }
         });
        btnItemOnlyTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                specialCard=1;
                if(house.getItem("老師卡")>=1){
                    house.setItem("老師卡",house.getItem("老師卡")-1);
                    itemTeacherTime.start();
                }
            }
        });
        win=new JButton[16];
        but=new JButton[16];
        obBut = new JLabel[16];
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

                obBut[k] = new JLabel();
                obBut[k].setIcon(new ImageIcon(""));
                obBut[k].setBounds(i+SHIFT+30,j,153,134);
                obBut[k].setBorder(null);
                add(obBut[k],JLayeredPane.POPUP_LAYER);
                k++;
            }
        }
        fist = new ImageIcon(Character.fist);
        heart = new ImageIcon(Character.heart);


        JLabel jfb =new JLabel("得分 : 0"); //Scoreboard
        jfb.setBounds(1030, 40, 200, 59);
        jfb.setFont(new Font("微軟正黑體",Font.BOLD,30));
        add(jfb,JLayeredPane.MODAL_LAYER);

        SecureRandom secureRandom = new SecureRandom();
         shadowTimer = new Timer(score.getSd(), new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 int windowpos=secureRandom.nextInt(12);
                 but[score.getLast()].setEnabled(true);
                 but[score.getLast()].setIcon(null);
                 obBut[score.getLast()].setIcon(null);
                 score.setLast(windowpos);
                 but[score.getLast()].setIcon(shadow);
                 int fakepos=secureRandom.nextInt(12);
                 while(fakepos==windowpos){
                     fakepos=secureRandom.nextInt(12);
                 }

                 but[score2.getLast()].setIcon(null);
                 score2.setLast(fakepos);

                 but[score2.getLast()].setIcon(shadow);

             }
         });
         //開始按鈕並設定第一支地鼠出現的時間延遲
         timer=new Timer(score.getSd()+750,new ActionListener() {//主要圖片出現

            public void actionPerformed(ActionEvent e) {
                int windowpos=secureRandom.nextInt(12);
                int choose=secureRandom.nextInt(2);


                if(equipIsUsed==false){
                    if( choose==0 || specialCard==1)  but[score.getLast()].setIcon(teacher[secureRandom.nextInt(6)]);
                    else but[score.getLast()].setIcon(student[secureRandom.nextInt(5)]);
                }
                else{
                    but[score.getLast()].setIcon(null);
                    obBut[score.getLast()].setIcon(null);
                    score.setLast(windowpos);
                    if( choose==0 || specialCard==1)  but[score.getLast()].setIcon(teacher[secureRandom.nextInt(6)]);
                    else but[score.getLast()].setIcon(student[secureRandom.nextInt(5)]);
                }


            }
         });

        //timer2 生成最後的得分訊息
        time2=new Timer(2,new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(equipIsUsed==false){
                    timer.setDelay(score.getSd());//設定地鼠出現速度
                    shadowTimer.setDelay(score.getSd());
                }

                else
                    timer.setDelay(score.getSd());

                jfb.setText("得分 : "+score.getScore());
                djs.setText((score.getStarttime()/1000+score.getDjs()-System.currentTimeMillis()/1000)+"");
                if((score.getStarttime()/1000+score.getDjs()-System.currentTimeMillis()/1000)==0)
                {
                    timer.stop();
                    shadowTimer.stop();
                    but[score.getLast()].setIcon(null);
                    obBut[score.getLast()].setIcon(null);
//                    add(statics,JLayeredPane.DRAG_LAYER);
                    //JOptionPane.showMessageDialog(frame, "Game Over\n 您的得分為："+score.getScore(),"得分信息",JOptionPane.PLAIN_MESSAGE);
                    int gainexp = score.getScore()*24+500;
                    int gainmoney = score.getScore()*47+800;
                    if(expDoubleUsed) gainexp*=2;
                    if(moneyDoubleUsed) gainmoney*=2;
                    static_pxp = new JLabel();
                    static_pxp.setFont(new Font("微軟正黑體",Font.BOLD,25));
                    static_pxp.setBounds(625, 238, 150,40);
                    static_pxp.setText(String.valueOf(gainexp));
                    add(static_pxp, JLayeredPane.DRAG_LAYER);
                    //static_pxp.setVisible(false);

                    static_money = new JLabel();
                    static_money.setFont(new Font("微軟正黑體",Font.BOLD,25));
                    static_money.setBounds(625, 389, 150,40);
                    static_money.setText(String.valueOf(gainmoney));
                    add(static_money, JLayeredPane.DRAG_LAYER);

                    //static_money.setVisible(false);
                    //add(statics,JLayeredPane.DRAG_LAYER);
                    System.out.println("exp:" + gainexp);
                    System.out.println("money" + gainmoney);
                    house.setExp(house.getExp()+gainexp);
                    house.setHoldMoney(house.getHoldMoney()+gainmoney);


                    checkImg = new ImageIcon("data/HitMouse/OK鍵.png");
                    checkBtn = new JButton(checkImg);
                    checkBtn.setBorderPainted(false);
                    checkBtn.setBorder(null);
                    checkBtn.setFocusPainted(false);
                    checkBtn.setContentAreaFilled(false);

                    checkBtn.setBounds(630, 490, 62, 60);
                    checkBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            timer.stop();
                            time2.stop();
                            time3.stop();
                            shadowTimer.stop();
                            equipIsUsed=false;
                            mainFrame.changeToMainScreen();
                        }
                    });
                    checkBtn.addMouseListener(mouseHandler);
                    add(checkBtn, new Integer(600));

                    but[score.getLast()].setIcon(null);
                    System.out.println(score.getLast());
                    score.setScore(0);
                    //jfb.setText("得分 : 0");
                    add(statics,JLayeredPane.DRAG_LAYER);
                    try {
                        time2.wait();
                    }
                    catch(Exception ex) {
                        System.out.println("stop");
                        time2.stop();
                    }

                }
            }
        });


        time3=new Timer(2,new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if((score.getStarttime()/1000+score.getDjs()-System.currentTimeMillis()/1000)==-1)
                {
                    time2.stop();
                    shadowTimer.stop();
                    itemTeacherTime.stop();
                    djs.setText("");
                }
            }
        });
        /*btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                score.setStarttime(System.currentTimeMillis());
                timer.start();
                time2.start();
                time3.start();
                shadowTimer.start();
            }
        });*/
        for(int i=0;i<12;i++){
            final int ii=i;
            but[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(but[ii].getIcon() != null && !Character.shadow.equals(but[ii].getIcon().toString())){//按下去不適Shadow
                        int c = e.getButton();// 得到按下的滑鼠鍵
                        String temp=but[ii].getIcon().toString();
                        if (c == MouseEvent.BUTTON1){ // 判斷是滑鼠左鍵按下

                            Sound.KissSound();
                            boolean judge=false;
                            for(int j=0;j<6;j++){//圖片是老師
                                if(Character.teachers[j].equals(but[ii].getIcon().toString())) judge=true;
                            }
                            if(but[score.getLast()].isEnabled()==true){
                                if(judge==true) score.setScore(score.getScore() + score.getAddScore());
                                else score.setScore(score.getScore() - score.getMinusScore());
                            }


                            obBut[score.getLast()].setIcon(heart);
                            //but[score.getLast()].setEnabled(false);
                            //Sound.KissSound();

                        }
                        if (c == MouseEvent.BUTTON3) {// 判斷是滑鼠右鍵按下

                            Sound.HitSound();
                            boolean judge=false;
                            for(int j=0;j<5;j++){
                                if(Character.students[j].equals(but[ii].getIcon().toString())) judge=true;
                            }
                            if(but[score.getLast()].isEnabled()==true){
                                if(judge==true) score.setScore(score.getScore() + score.getAddScore());
                                else score.setScore(score.getScore() - score.getMinusScore());
                            }

                            obBut[score.getLast()].setIcon(fist);
                            //but[score.getLast()].setEnabled(false);
                        }

                        if(!Character.shadow.equals(but[ii].getIcon().toString())) {
                           but[score.getLast()].setEnabled(false);
                           but[score.getLast()].setDisabledIcon(new ImageIcon(temp));
                           //but[score.getLast()].setIcon(null);
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
        backToMainButton.addMouseListener(mouseHandler);
         backToMainButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 mainFrame.changeToMainScreen();

               //  house.debugString="Back to main from HitMouse";
               //  mainPanel.debugText.setText( house.debugString );
                 timer.stop();
                 time2.stop();
                 time3.stop();
                 shadowTimer.stop();
             }
         });
//        frame.setVisible(true);
    }


}