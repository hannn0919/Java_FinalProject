package Main;

import House.house.House;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.io.*;
import javax.sound.sampled.*;
import Main.*;
import java.awt.event.MouseEvent;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.swing.border.EmptyBorder;


public class EnterScreen extends JPanel{

    private Main mainFrame;
    private House house;
    private drama dramaGo;
    private JButton btnNew,btnLoad;

    public EnterScreen(Main mainFrame){
        this.mainFrame = mainFrame;
        this.setSize(1200, 675);
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setLayout(null);

        btnNew = new JButton();
        cleanButtom(btnNew);
        btnNew.setBounds(600, 300, 290, 100);
        btnNew.setIcon(new ImageIcon("data/main/開始遊戲.png"));
        this.add(btnNew);

        btnLoad = new JButton();
        cleanButtom(btnLoad);
        btnLoad.setBounds(600, 450, 255, 100);
        btnLoad.setIcon(new ImageIcon("data/main/載入遊戲小.png"));
        this.add(btnLoad);

        MouseHandler mouseHandler = new MouseHandler();
        btnNew.addMouseListener(mouseHandler);
        btnLoad.addMouseListener(mouseHandler);

        ImageIcon img = new ImageIcon("data/main/主畫面.png");
        Image i = img.getImage();
        i = i.getScaledInstance(1200, 675, Image.SCALE_SMOOTH);
        JLabel background = new JLabel();
        background.setIcon(new ImageIcon(i));
        background.setSize(1200, 675);
        this.add(background);
    }
    private class MouseHandler implements  MouseListener{
        @Override public void mousePressed(MouseEvent e){}
        @Override public void mouseReleased(MouseEvent e){}
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource()==btnNew){
                String name = JOptionPane.showInputDialog(mainFrame, "請輸入遊戲名稱", "開始遊戲", JOptionPane.QUESTION_MESSAGE);
                if (name == null) {
                    return;
                }
                while (name.equals(new String(""))) {
                    name = JOptionPane.showInputDialog(mainFrame, "請輸入遊戲名稱", "開始遊戲", JOptionPane.QUESTION_MESSAGE);
                    if (name == null) {
                        return;
                    }
                }
                File file = null;
                file = new File("file");
                if(!file.exists()) file.mkdirs();
                house = new House();
                mainFrame.setFileName(name);
                mainFrame.setHouse(house);
                mainFrame.setGameStart(true);
                mainFrame.stratDrama();
            }
            else if(e.getSource()==btnLoad) {
                JFileChooser fileChooser = new JFileChooser("file");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                int result = fileChooser.showOpenDialog(mainFrame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String filename = fileChooser.getSelectedFile().getPath();
                    mainFrame.setFileName(fileChooser.getSelectedFile().getName().replace(".txt",""));
                    House temp = new House();
                    try {
                        File file = new File(filename);
                        InputStreamReader reader = new InputStreamReader(new FileInputStream(file), "UTF-8");
                        BufferedReader br = new BufferedReader(reader);
                        String line = "";
                        line = br.readLine();
                        while (line != null) {
                            if (line.compareTo("股市資料1") == 0) {
                                for (int i = 0; i < 4; i++) {
                                    line = br.readLine();
                                    temp.setStock(i, Float.parseFloat(line));
                                }
                            } else if (line.compareTo("股市資料2") == 0) {
                                for (int i = 0; i < 4; i++) {
                                    line = br.readLine();
                                    temp.setStockPrs(i, Float.parseFloat(line));
                                }
                            } else if (line.compareTo("股市資料3") == 0) {
                                for (int i = 0; i < 4; i++) {
                                    line = br.readLine();
                                    temp.setStockTicket(i, Integer.parseInt(line));
                                }
                            } else if (line.compareTo("股市資料4") == 0) {
                                for (int i = 0; i < 4; i++)
                                    for (int j = 0; j < 1; j++)
                                        for (int k = 0; k < 10; k++) {
                                            line = br.readLine();
                                            temp.setData(i, j, k, Double.parseDouble(line));
                                        }

                            } else if (line.indexOf(" ") != -1) {
                                String[] data = line.split(" ");
                                if (data[0].equals("cardTotalMoney")) temp.setCardTotalMoney(Integer.parseInt(data[1]));
                                else if (data[0].equals("hamsterTotalMoney"))
                                    temp.setHamsterTotalMoney(Integer.parseInt(data[1]));
                                else if (data[0].equals("froggerTotalMoney"))
                                    temp.setFroggerTotalMoney(Integer.parseInt(data[1]));
                                else if (data[0].equals("dinosaurTotalMoney"))
                                    temp.setDinosaurTotalMoney(Integer.parseInt(data[1]));

                                else if (data[0].equals("cardTotalExp"))
                                    temp.setCardTotalExp(Integer.parseInt(data[1]));
                                else if (data[0].equals("hamsterTotalExp"))
                                    temp.setHamsterTotalExp(Integer.parseInt(data[1]));
                                else if (data[0].equals("froggerTotalExp"))
                                    temp.setFroggerTotalExp(Integer.parseInt(data[1]));
                                else if (data[0].equals("dinosaurTotalExp"))
                                    temp.setDinosaurTotalExp(Integer.parseInt(data[1]));

                                else if (data[0].equals("cardTotalPair"))
                                    temp.setCardTotalPair(Integer.parseInt(data[1]));
                                else if (data[0].equals("hamsterTotalMistake"))
                                    temp.setHamsterTotalMistake(Integer.parseInt(data[1]));
                                else if (data[0].equals("froggerTotalDied"))
                                    temp.setFroggerTotalDied(Integer.parseInt(data[1]));
                                else if (data[0].equals("dinosaurTotalDied"))
                                    temp.setDinosaurTotalDied(Integer.parseInt(data[1]));

                                else if (data[0].equals("cardTotalPlay"))
                                    temp.setCardTotalPlay(Integer.parseInt(data[1]));
                                else if (data[0].equals("hamsterTotalPlay"))
                                    temp.setHamsterTotalPlay(Integer.parseInt(data[1]));
                                else if (data[0].equals("froggerTotalPlay"))
                                    temp.setFroggerTotalPlay(Integer.parseInt(data[1]));
                                else if (data[0].equals("dinosaurTotalPlay"))
                                    temp.setDinosaurTotalPlay(Integer.parseInt(data[1]));

                                else if (data[0].equals("竹蜻蜓") || data[0].equals("透視眼鏡") || data[0].equals("彈簧鞋") || data[0].equals("翅膀"))
                                    temp.sEquipment(data[0], Integer.parseInt(data[1]));
                                else if (data[0].equals("level")) temp.setLevel(Integer.parseInt(data[1]));
                                else if (data[0].equals("holdMoney")) temp.setHoldMoney(Integer.parseInt(data[1]));
                                else if (data[0].equals("exp")) temp.setExp(Integer.parseInt(data[1]));
                                else temp.setItem(data[0], Integer.parseInt(data[1]));

                            }
                            line = br.readLine(); // 一次讀入一行資料
                        }
                        reader.close();
                        br.close();
                        mainFrame.setHouse(temp);
                        mainFrame.setGameStart(true);
                        mainFrame.changeToMainScreen();
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }

        @Override
        public void mouseEntered(MouseEvent e){
            if(e.getSource()==btnNew) {
                buttonSound();
                btnNew.setIcon(resize(btnNew.getIcon().getIconWidth()+10,btnNew.getIcon().getIconHeight()+10,(ImageIcon)btnNew.getIcon()));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }else if(e.getSource()==btnLoad){
                buttonSound();
                btnLoad.setIcon(resize(btnLoad.getIcon().getIconWidth()+10,btnLoad.getIcon().getIconHeight()+10,(ImageIcon)btnLoad.getIcon()));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        }
        @Override
        public void mouseExited(MouseEvent arg0) {
            if(arg0.getSource()==btnNew){
                btnNew.setIcon(new ImageIcon("data/main/開始遊戲.png"));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            else if(arg0.getSource()==btnLoad) {
                btnLoad.setIcon(new ImageIcon("data/main/載入遊戲小.png"));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }
    }

    public ImageIcon resize(int width, int height, ImageIcon img) {
        Image i = img.getImage();
        Image new_img = i.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return  new ImageIcon(new_img);
    }

    public void buttonSound() {
        try {
            File soundFile = new File("music/enterbutton.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void cleanButtom(JButton button) {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorder(null);
        button.setBorderPainted(false);
    }

}
