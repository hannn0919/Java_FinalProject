package Main;

import Card.RememberCard;
import Dinosaur.userinterface.DinoJLayer;
import Frogger.main.FrogJLayer;
import House.bag.BagLayerPane;
import House.house.House;
import HitMouse.Mouse;
import House.store.StoreLayerPane;
import Stock.main.StockWindow;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.Map;


public class Main extends JFrame {
    private Clip entermusic, mainmusic; // 各遊戲畫面背景音樂
    private Clip dinosaurmusic, hamstermusic, froggermusic, cardmusic, graduationmusic;  // 各遊戲畫面背景音樂
    private mainPanel mainScreen;  // 主畫面panel
    private House house;    // 倉庫，所有數據資料
    private boolean gameStart = false;
    private String fileName = new String("");
    private Object[] options = {"查看"};


    public Main(){
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(1200, 675);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("大學肝什麼");

        house = new House();
        mainScreen = new mainPanel(this, this.house);

        // 所有遊戲畫面背景音樂初始化
        Entermusic();
        Mainmusic();
        Dinosaurmusic();
        Hamstermusic();
        Froggermusic();
        Cardmusic();
        ///////////////

        this.changeToEnterScreen(); // 將主畫面切置登入頁面
    }

    // 開場動畫
    public void stratDrama(){
        this.setTitle("看片片");
        this.setContentPane(new drama(this));
    }

    // 切換至登入頁面
    public void changeToEnterScreen() {
        //entermusic.loop(Clip.LOOP_CONTINUOUSLY);    // 將音樂切換至登入頁面背景音樂，其他音樂暫停撥放
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
        //mainmusic.loop(Clip.LOOP_CONTINUOUSLY);    // 將音樂切換至主畫面背景音樂，其他音樂暫停撥放
        dinosaurmusic.stop();
        hamstermusic.stop();
        froggermusic.stop();
        cardmusic.stop();

        mainScreen = new mainPanel(this, this.house);
        this.setTitle("大學肝什麼");
        this.setContentPane(mainScreen);
        this.setVisible(true);
        mainScreen.requestFocus();

        if(house.getExp()>=10000){
            JFrame.setDefaultLookAndFeelDecorated(true);
            JOptionPane.showOptionDialog(null,"啾咪，您有一則訊息要接收！", "提示信息來囉^.<",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            this.changeToGraduation();
        }
    }

    // 切換至過馬路
    public void changeToFrog(){
        entermusic.stop();
        mainmusic.stop();
        dinosaurmusic.stop();
        hamstermusic.stop();
        //froggermusic.loop(Clip.LOOP_CONTINUOUSLY);      // 將音樂切換至遊戲背景音樂，其他音樂暫停撥放
        cardmusic.stop();
        FrogJLayer frog = new FrogJLayer(this, this.house);
        this.setTitle("馬路如虎口，抓到罰三百");
        this.setContentPane(frog);
        this.setVisible(true);
    }

    // 切換至小恐龍
    public void changeToDinosaur(){
        entermusic.stop();
        mainmusic.stop();
        //dinosaurmusic.loop(Clip.LOOP_CONTINUOUSLY);      // 將音樂切換至遊戲背景音樂，其他音樂暫停撥放
        hamstermusic.stop();
        froggermusic.stop();
        cardmusic.stop();
        DinoJLayer dinosaur = new DinoJLayer(this, this.house);
        this.setTitle("沒有網際網路連線");
        this.setContentPane(dinosaur);
        this.setVisible(true);
    }

    //切換至打地鼠
    public void changeToMouse(){
        entermusic.stop();
        mainmusic.stop();
        dinosaurmusic.stop();
        //hamstermusic.loop(Clip.LOOP_CONTINUOUSLY);      // 將音樂切換至遊戲背景音樂，其他音樂暫停撥放
        froggermusic.stop();
        cardmusic.stop();
        Mouse mouse = new Mouse(this, this.house);
        this.setTitle("散播歡樂，散播愛");
        this.setContentPane(mouse);
        this.setVisible(true);
        mouse.requestFocus();
    }


    // 切換至對對碰
    public void changeToCard(){
        entermusic.stop();
        mainmusic.stop();
        dinosaurmusic.stop();
        hamstermusic.stop();
        froggermusic.stop();
        //cardmusic.loop(Clip.LOOP_CONTINUOUSLY);      // 將音樂切換至遊戲背景音樂，其他音樂暫停撥放
        RememberCard card = new RememberCard(this, this.house);
        this.setTitle("消滅一對是一對");
        this.setContentPane(card);
        this.setVisible(true);
        card.requestFocus();
    }

    // 切換至股市系統
    public void changeToStock(){
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
        StoreLayerPane store = new StoreLayerPane(this,this.house);
        this.setTitle("商店");
        this.setContentPane(store);
        this.setVisible(true);
        this.setBackground(Color.WHITE);
        store.requestFocus();
    }

    // 切換至背包
    public void changeToBag(){
        BagLayerPane bagLayerPane = new BagLayerPane(this,this.house);
        this.setTitle("背包");
        this.setContentPane(bagLayerPane);
        this.setVisible(true);
        this.setBackground(Color.WHITE);
        bagLayerPane.requestFocus();
    }

    //切換至畢業畫面
    public void changeToGraduation(){
        Graduationmusic();
        mainmusic.stop();
        dinosaurmusic.stop();
        hamstermusic.stop();
        froggermusic.stop();
        cardmusic.stop();
        graduation byebye = new graduation(this, this.house);
        this.setTitle("畢業囉");
        this.setContentPane(byebye);
        this.setVisible(true);
        byebye.requestFocus();
    }

    //切換至結算畫面
    public void changeToSettlement(){
        GameSettlement endOfGame = new GameSettlement(this, this.house);
        this.setTitle("結算統計");
        this.setContentPane(endOfGame);
        this.setVisible(true);
        endOfGame.requestFocus();
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

    // 設定畢業背景音樂
    private void Graduationmusic() {
        try {
            File soundFile = new File("music/graduation.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            graduationmusic = AudioSystem.getClip();
            graduationmusic.open(audioIn);
            graduationmusic.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }



    // 設定倉庫資料數據
    public void setHouse(House house) {
        this.house = house;
    }

    public boolean getGameStart() {
        return this.gameStart;
    }

    public void setGameStart(boolean t) {
        this.gameStart = t;
    }

    public void setFileName(String name) {
        this.fileName = name;
    }

    public void saveFile() {
        PrintWriter writer;
        try {
            writer = new PrintWriter("file/" + fileName + ".txt", "UTF-8");
            writer.println();
            Map<String, Integer> houseData = this.house.getObject();
            for (String key : houseData.keySet()) {
                writer.print(key);
                writer.print(" ");
                writer.println(houseData.get(key));
            }
            System.out.println("角色資料存檔完成");
            writer.println();
            writer.println("股市資料1");
            float [] stockData1 = this.house.getStock();
            for(int i=0;i<4;i++)
                writer.println(stockData1[i]);
            writer.println();
            writer.println("股市資料2");
            float [] stockData2 = this.house.getStockPrs();
            for(int i=0;i<4;i++)
                writer.println(stockData2[i]);
            writer.println();
            writer.println("股市資料3");
            int [] stockData3 = this.house.getStockTicket();
            for(int i=0;i<4;i++)
                writer.println(stockData3[i]);
            writer.println();
            writer.println("股市資料4");
            double[][][] stockData4 = this.house.getData();
            for(int i=0;i<4;i++)
                for(int j=0;j<1;j++)
                    for(int k=0;k<10;k++)
                        writer.println(stockData4[i][j][k]);

            System.out.println("股市資料存檔完成");
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main mainFrame = new Main();
                    mainFrame.setVisible(true);

                    mainFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent windowEvent) {
                            if (mainFrame.getGameStart()) {
                                int choose = JOptionPane.showConfirmDialog(mainFrame,"是否要存檔?", "關閉視窗", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                                if (choose == JOptionPane.YES_OPTION){
                                    mainFrame.saveFile();
                                    System.exit(0);
                                }
                                else System.exit(0);
                            }
                            else {
                                System.exit(0);
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}