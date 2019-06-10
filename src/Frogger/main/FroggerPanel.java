package Frogger.main;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.border.EmptyBorder;


import Frogger.util.*;
import House.house.House;
import Main.*;


public class FroggerPanel extends JPanel
{

    private Timer timer;
    public int time = 6000;
    private Image frogImage;
    private ArrayList<Car> cars;
    private Frog frog;
    private Car[] CarsRoadOne = new Car[5];
    private Car[] CarsRoadTwo = new Car[3];
    private Car[] CarsRoadThree = new Car[4];
    private Car[] CarsRoadFour = new Car[6];
    private Car police;
    private int policeShow;
    private UnderWay[] underWays = new UnderWay[3];
    private int end;
    private Main mainFrame ;
    private House house;
    private int stepX ;
    private int stepY ;
    private final int oneStep = 80;
    private int policeTime;
    private Random random;
    // set up GUI and register key event handler
    public FroggerPanel(Main mainFrame, House house)
    {
        this.random = new Random();
        this.house = house;
        this.mainFrame = mainFrame;
        this.setFocusable(true);
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLayout(null);

        Init();
        repaint();
        Keylisten listener = new Keylisten();
        this.addKeyListener(listener);
        // handle frame mouse motion event
        //requestFocusInWindow();
        //gameStart();
    }


    public class Keylisten extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            int key1 = e.getKeyCode();
            char key2 = e.getKeyChar();

            switch (key1) {
                case KeyEvent.VK_UP:
                    frog.move(0, -stepY);
                    if(stepY == 240){
                        setStep(1);
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    frog.move(-stepX, 0);
                    if(stepY == 240){
                        setStep(1);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    frog.move(0, stepY);
                    if(stepY == 240){
                        setStep(1);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    frog.move(stepX, 0);
                    if(stepY == 240){
                        setStep(1);
                    }
                    break;
                case KeyEvent.VK_P:
                    time =0;
                    timer.cancel();
                    mainFrame.changeToMainScreen();
                    end = 0;
                    break;
                default:
                    System.out.println(key2);
            }
        }
    }

    private void Init()
    {

        this.end = 1;
        cars = new ArrayList<>();
        frog = new Frog(Frog.startX, Frog.startY, 70, 70, "characters50.png");
        policeTime = random.nextInt(5000) + 200;
        police = new Car( 900, 300, 120 -10, 80-  10, 0, "police170r.png");
        policeShow = 0;
        for(int i =0;i<5;i++){
            CarsRoadOne[i] = new Car(10 + i * 280, 460, 120-10, 80-10, 1, "orangecarr.png"); // 5
            cars.add(CarsRoadOne[i]);
        }
        for(int i =0;i<3;i++){
            CarsRoadTwo[i] = new Car( 20 + i * 466 , 380, 200-10, 80-10, 4, "truck200r.png"); // 3
            cars.add(CarsRoadTwo[i]);
        }
        for(int i =0;i<4;i++){
            CarsRoadThree[i] = new Car( 40 + i * 350, 220, 170-10, 80-10, -3, "truck170l.png"); // 4
            cars.add(CarsRoadThree[i]);
        }
        for(int i =0;i<6;i++){
            CarsRoadFour[i] = new Car( 15 + i * 240 , 140, 120-10 , 80-10, -2, "bluecarl.png"); // 6
            cars.add(CarsRoadFour[i]);
        }
        for(int i = 0; i< 3;i++){
            underWays[i] = new UnderWay(225, 545 - i * 240, 80, 80, "underWay.png");
        }
        setStep(1);
    }

    public void gameStart()
    {
        timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                if(frog.getX() == 225 && frog.getY() == underWays[0].getY()){
                    setStep(3);
                }else if(frog.getX() == 225 && frog.getY() == underWays[1].getY()){
                    setStep(3);
                }
                if(time == 0){
                    timer.cancel();
                }
                time--;
                if(time == 99){
                    //int temp = JOptionPane.showConfirmDialog(null, "遊戲結束\n是否要重新(Yes : 重新遊戲 No : 回選單)", "", JOptionPane.YES_NO_OPTION);
                    house.setHoldMoney(house.getHoldMoney() + 1000);
                    house.setExp(house.getExp() + 200);
                    time = 0;
                    timer.cancel();
                    //mainFrame.changeToMainScreen();
                }
                if(time == policeTime){
                    police.setSpeed(-20);
                    policeShow = 1;
                }
                if(police.getX() < 0){
                    police.setSpeed(0);
                    policeShow = 0;
                    policeTime = random.nextInt(policeTime) + 200;
                }
                if(end == 0){
                    timer.cancel();
                }
                for( Car c : cars){
                    if(c.intersect(frog)){
                        frog.setX(Frog.startX);
                        frog.setY(Frog.startY);
                    }
                    c.update();
                }
                if(police.getSpeed() != 0){
                    if(police.intersect(frog)){
                        frog.setX(Frog.startX);
                        frog.setY(Frog.startY);
                        house.setHoldMoney(house.getHoldMoney() - 300); // 抓到罰三百
                    }
                    police.update();
                }
                if(frog.win()){
                    house.setExp( house.getExp() + time / 6 * house.getLevel());
                    if(time > 3000){
                        house.setHoldMoney(house.getHoldMoney() + 3000);
                    }else if(time > 1000){
                        house.setHoldMoney(house.getHoldMoney() + 2500);
                    }else if(time > 0){
                        house.setHoldMoney(house.getHoldMoney() + 2000);
                    }
                    time = 0;
                    timer.cancel();
                }
                repaint();
            }
        }, 0, 10);
    }

    // draw ovals in a 4-by-4 bounding box at specified locations on window
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g); // clears drawing area
        //g.fillRect( 610, 550, frog.getW(), frog.getH());
        //g.fillRect(frog.getX()+20, frog.getY()+20,frog.getW(), frog.getH());


        try {
            Image backGroundImage = ImageIO.read(new File("data/Frogger/image/frogBackground.png"));
            g.drawImage(backGroundImage, 0, 0, null);
            frogImage = ImageIO.read(new File("data/Frogger/image/" + frog.getImageName()));
            g.drawImage(frogImage, frog.getX(), frog.getY(), null);
            for (Car c : cars) {
                Image tempImage = ImageIO.read(new File("data/Frogger/image/" + c.getImageName()));
                g.drawImage(tempImage, c.getX() - 5, c.getY() - 5, null);
            }
            if(policeShow == 1){
                Image tempImage = ImageIO.read(new File("data/Frogger/image/" + police.getImageName()));
                g.drawImage(tempImage, police.getX()-5, police.getY()-5, null);
            }
        }
        catch (Exception ex) {
                System.out.println("No example.jpg!!");
        }

//        for(int i =0;i<3;i++){
//            g.fillRect(underWays[i].getX(), underWays[i].getY(), underWays[i].getW(), underWays[i].getH());
//        }
//        g.fillRect(Frog.endX, Frog.endY, frog.getW(),frog.getH());
    }

    private void setStep(int k) {
        stepY = k * oneStep;
        stepX = oneStep / 2;
    }

} // end class PaintPanel
