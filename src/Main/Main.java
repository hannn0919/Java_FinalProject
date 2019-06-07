package Main;

import Dinosaur.userinterface.DinoJLayer;
import Dinosaur.userinterface.GameScreen;
import Frogger.main.FroggerPanel;
import House.house.House;

import House.bag.BagWindow;
import House.store.Store;
import HitMouse.Mouse;
import Stock.main.StockWindow;

import javax.sound.sampled.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.File;
import java.io.IOException;


public class Main extends JFrame {
    private Clip entermusic, mainmusic; // 各遊戲畫面背景音樂
    private Clip dinosaurmusic, hamstermusic, froggermusic, cardmusic;  // 各遊戲畫面背景音樂
    private JPanel mainScreen;  // 主畫面panel
    private House house;    // 倉庫，所有數據資料

    public Main(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 675);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("大學肝什麼");
        this.mainScreen = new mainPanel(this);
        house = new House();
        // 所有遊戲畫面背景音樂初始化
        ///////////////
        //this.stratDrama();  //開場動畫
        Entermusic();
        Mainmusic();
        Dinosaurmusic();
        Hamstermusic();
        Froggermusic();
        Cardmusic();
        ///////////////

        this.changeToEnterScreen(); // 將主畫面切置登入頁面
    }

    //
    public void stratDrama(){
        this.setTitle("看片片");
        this.setContentPane(new drama(this));
    }

    // 切換至登入頁面
    public void changeToEnterScreen() {
        entermusic.loop(Clip.LOOP_CONTINUOUSLY);    // 將音樂切換至登入頁面背景音樂，其他音樂暫停撥放
        mainmusic.stop();
        dinosaurmusic.stop();
        hamstermusic.stop();
        froggermusic.stop();
        cardmusic.stop();
        this.setTitle("登入");
        this.setContentPane(new EnterScreen(this));
    }

    // 切換至主畫面
    public void changeToMainScreen() {
        entermusic.stop();
        mainmusic.loop(Clip.LOOP_CONTINUOUSLY);    // 將音樂切換至主畫面背景音樂，其他音樂暫停撥放
        dinosaurmusic.stop();
        hamstermusic.stop();
        froggermusic.stop();
        cardmusic.stop();
        this.setTitle("大學肝什麼");
        this.setContentPane(mainScreen);
    }

    // 切換至過馬路
    public void changeToFrog(){
        entermusic.stop();
        mainmusic.stop();
        dinosaurmusic.stop();
        hamstermusic.stop();
        froggermusic.loop(Clip.LOOP_CONTINUOUSLY);      // 將音樂切換至遊戲背景音樂，其他音樂暫停撥放
        cardmusic.stop();
        JPanel frog = new FroggerPanel(this, this.house);
        this.setTitle("馬路如虎口，抓到罰三百");
        this.setContentPane(frog);
        this.setVisible(true);
        frog.requestFocus();
    }

    // 切換至小恐龍
    public void changeToDinosaur(){
        entermusic.stop();
        mainmusic.stop();
        dinosaurmusic.loop(Clip.LOOP_CONTINUOUSLY);      // 將音樂切換至遊戲背景音樂，其他音樂暫停撥放
        hamstermusic.stop();
        froggermusic.stop();
        cardmusic.stop();
        DinoJLayer dinosaur = new DinoJLayer(this, this.house);
        this.setTitle("沒有網際網路連線");
        this.setContentPane(dinosaur);
        this.setVisible(true);
        dinosaur.requestFocus();
    }

    //切換至打地鼠
    public void changeToMouse(){
        entermusic.stop();
        mainmusic.stop();
        dinosaurmusic.stop();
        hamstermusic.loop(Clip.LOOP_CONTINUOUSLY);      // 將音樂切換至遊戲背景音樂，其他音樂暫停撥放
        froggermusic.stop();
        cardmusic.stop();
        Mouse mouse = new Mouse(this, this.house);
        this.setTitle("散播歡樂，散播愛");
        this.setContentPane(mouse);
        this.setVisible(true);
        mouse.requestFocus();
    }

/*
    // 切換至對對碰
    public void changeToCard(){
        entermusic.stop();
        mainmusic.stop();
        dinosaurmusic.stop();
        hamstermusic.stop();
        froggermusic.stop();
        cardmusic.loop(Clip.LOOP_CONTINUOUSLY);      // 將音樂切換至遊戲背景音樂，其他音樂暫停撥放
        JPanel card = new CardGame(this, this.house);
        this.setTitle("消滅一對是一對");
        this.setContentPane(card);
    }
*/
    // 切換至股市系統
    public void changeToStock(){
        entermusic.stop();
        mainmusic.loop(Clip.LOOP_CONTINUOUSLY);      // 將音樂切換至主畫面背景音樂，其他音樂暫停撥放
        dinosaurmusic.stop();
        hamstermusic.stop();
        froggermusic.stop();
        cardmusic.stop();
        StockWindow.nextStock(house);
        house.updataData();
        JPanel stock  = new StockWindow(this, this.house);
        this.setTitle("股市");
        this.setContentPane(stock);
        this.setVisible(true);
        stock.requestFocus();
    }


    // 切換至商店
    public void changeToShop(){
        JPanel store = new Store(this, this.house);
        this.setTitle("商店");
        this.setContentPane(store);
        this.setVisible(true);
        store.requestFocus();
    }

    // 切換至背包
    public void changeToBag(){
        JPanel bagWindow = new BagWindow(this, this.house);
        this.setTitle("背包");
        this.setContentPane(bagWindow);
        this.setVisible(true);
        bagWindow.requestFocus();
    }


    // 設定登入頁面背景音樂
    private void Entermusic() {
        try {
            File soundFile = new File("music/enter.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            entermusic = AudioSystem.getClip();
            entermusic.open(audioIn);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    // 設定主畫面背景音樂
    private void Mainmusic() {
        try {
            File soundFile = new File("music/game.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            mainmusic = AudioSystem.getClip();
            mainmusic.open(audioIn);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    // 設定小恐龍背景音樂
    private void Dinosaurmusic() {
        try {
            File soundFile = new File("music/dinosaur.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            dinosaurmusic = AudioSystem.getClip();
            dinosaurmusic.open(audioIn);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    // 設定打地鼠背景音樂
    private void Hamstermusic() {
        try {
            File soundFile = new File("music/hamster.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            hamstermusic = AudioSystem.getClip();
            hamstermusic.open(audioIn);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    // 設定過馬路背景音樂
    private void Froggermusic() {
        try {
            File soundFile = new File("music/frogger.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            froggermusic = AudioSystem.getClip();
            froggermusic.open(audioIn);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    // 設定對對碰背景音樂
    private void Cardmusic() {
        try {
            File soundFile = new File("music/card.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            cardmusic = AudioSystem.getClip();
            cardmusic.open(audioIn);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // 設定倉庫資料數據
    public void sethouse(House house) {
        this.house = house;
    }

    public static void main(String[] args) {
        Main mainFrame = new Main();
        mainFrame.house = new House();
        mainFrame.setVisible(true);
    }

}