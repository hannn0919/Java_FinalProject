package Dinosaur.userinterface;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    public static final int SCREEN_WIDTH = 1200;
    private GameScreen gameScreen;

    public GameWindow() {
        super("Java T-Rex game");
        setSize(SCREEN_WIDTH, 675);
        setLocation(50, 50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        JPanel data = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalStrut(150));
        panel.add(new JButton("Shop"));
        panel.add(Box.createVerticalStrut(30));
        panel.add(new JButton("Gift"));
        panel.add(Box.createVerticalStrut(30));
        panel.add(new JButton("Honor"));
        panel.add(Box.createVerticalStrut(30));
        panel.add(new JButton("Bag"));
        panel.add(Box.createVerticalStrut(30));
        panel.add(new JButton("Stock"));
        panel.add(Box.createVerticalStrut(100));
        data.add(new Label("  6666  "));
        data.add(new Label("  LV87  "));
        data.add(new Label("  exp 9487  "));
        data.add(new Label("  money 1234  "));

        gameScreen = new GameScreen();
        addKeyListener(gameScreen);
        add(gameScreen, BorderLayout.CENTER);

        add(data, BorderLayout.NORTH);

    }

    public void startGame() {
        setVisible(true);
        gameScreen.startGame();
    }

    public static void main(String args[]) {
        (new GameWindow()).startGame();
    }
}