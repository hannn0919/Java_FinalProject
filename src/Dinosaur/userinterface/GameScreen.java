package Dinosaur.userinterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import Dinosaur.gameObject.Clouds;
import Dinosaur.gameObject.EnemiesManager;
import Dinosaur.gameObject.Land;
import Dinosaur.gameObject.MainCharacter;
import Dinosaur.util.Resource;
import House.house.House;
import Main.*;

public class GameScreen extends JPanel implements Runnable {

    private static final int START_GAME_STATE = 0;
    private static final int GAME_PLAYING_STATE = 1;
    private static final int GAME_OVER_STATE = 2;

    private Timer timer;
    public int time = 6000;

    private Main frame;
    private House house;

    private Land land;
    public MainCharacter mainCharacter;
    private EnemiesManager enemiesManager;
    private Clouds clouds;
    public Thread thread;
    private float speed=5;
    public double max = 0;
    private boolean isKeyPressed;
    public boolean expcard = false;
    public boolean moneycard = false;
    private int dieTime = 0;

    private int gameState = START_GAME_STATE;

    private BufferedImage replayButtonImage, gameOverButtonImage;

    public GameScreen(Main frame, House house) {
        this.setFocusable(true);
        this.frame = frame;
        this.house = house;
        mainCharacter = new MainCharacter(house);
        land = new Land(1200, mainCharacter);
        mainCharacter.setSpeedX(5);
        replayButtonImage = Resource.getResouceImage("data/dinosaur/replay_button.png");
        gameOverButtonImage = Resource.getResouceImage("data/dinosaur/gameover_text.png");
        enemiesManager = new EnemiesManager(mainCharacter);
        clouds = new Clouds(1200, mainCharacter);
        Keylisten listener = new Keylisten();
        this.addKeyListener(listener);
    }

    // 遊戲開始，啟動thread
    public void startGame() {
        thread = new Thread(this);
        thread.start();
        gameState = GAME_PLAYING_STATE;
        //計時60秒
        timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                time--;
                if(time == 5000){
                    mainCharacter.invincible = false;
                }
                if(time == 0){
                    timer.cancel();
                    thread.stop();
                }
                repaint();
            }
        }, 0, 10);
    }

    // 更新遊戲狀態，產生背景物件、敵人，判斷角色是否有碰撞到障礙物
    public void gameUpdate() {
        if (gameState == GAME_PLAYING_STATE) {
            clouds.update();
            land.update();
            mainCharacter.update();
            mainCharacter.upScore();
            enemiesManager.update();
            mainCharacter.setSpeedX(speed);
            speed += 0.003;
            if(mainCharacter.score > max) max = mainCharacter.score;
            if (enemiesManager.isCollision()) {
                if(!mainCharacter.invincible) {
                    mainCharacter.playDeadSound();
                    gameState = GAME_OVER_STATE;
                    mainCharacter.dead(true);
                }
            }
        }
    }

    // 將背景、角色、障礙物的物件繪製出來(視覺化)。
    // 若角色死亡則撥放死亡音效、提示文字
    public void paint(Graphics g) {
        g.setColor(Color.decode("#f7f7f7"));
        g.fillRect(0, 0, getWidth(), getHeight());
        land.draw(g);
        switch (gameState) {
            case START_GAME_STATE:
                mainCharacter.draw(g);
                break;
            case GAME_PLAYING_STATE:
            case GAME_OVER_STATE:
                clouds.draw(g);
                //land.draw(g);
                enemiesManager.draw(g);
                mainCharacter.draw(g);
                g.setColor(Color.BLACK);
                g.drawString("Best Score : " + (int) max, 580, 20);
                g.drawString("Score : " + (int) mainCharacter.score, 750, 20);
                if (gameState == GAME_OVER_STATE) {
                    g.drawImage(gameOverButtonImage, 305, 280, null);
                    g.drawImage(replayButtonImage, 388, 300, null);

                }
                break;
        }
    }

    @Override
    public void run() {

        int fps = 100;
        long msPerFrame = 1000 * 1000000 / fps;
        long lastTime = 0;
        long elapsed;

        int msSleep;
        int nanoSleep;

        long endProcessGame;
        long lag = 0;

        while (true) {
            gameUpdate();
            repaint();
            elapsed = (lastTime + msPerFrame - System.nanoTime());
            msSleep = (int) (elapsed / 1000000);
            if (msSleep <= 0) {
                lastTime = System.nanoTime();
                continue;
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lastTime = System.nanoTime();
        }
    }

    // 偵測鍵盤輸入
    private class Keylisten extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            ////////////patchTest/////////
            if (e.getKeyCode() == KeyEvent.VK_Q){
                mainPanel mainScreen = new mainPanel(frame, house);
                frame.setContentPane(mainScreen);
            }
            ////////////patchTestFinish/////////
            if (e.getKeyCode() == KeyEvent.VK_P)
                frame.changeToMainScreen();
            if (!isKeyPressed) {
                isKeyPressed = true;
                switch (gameState) {
                    case START_GAME_STATE:
                        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                            gameState = GAME_PLAYING_STATE;
                        }
                        break;
                    case GAME_PLAYING_STATE:
                        if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP) {
                            mainCharacter.jump();
                        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                            mainCharacter.down(true);
                        }
                        break;
                    case GAME_OVER_STATE:
                        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                            gameState = GAME_PLAYING_STATE;
                            resetGame();
                        }
                        break;

                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            isKeyPressed = false;
            if (gameState == GAME_PLAYING_STATE) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    mainCharacter.down(false);
                }
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub

        }
    }

    public int getDieTime(){
        return dieTime;
    }

    // 若角色死亡，重置遊戲
    private void resetGame() {
        speed = 5;
        dieTime++;
        enemiesManager.reset();
        mainCharacter.dead(false);
        mainCharacter.reset();
    }

}