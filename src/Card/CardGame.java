package Card;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import Card.RememberCard;

public class CardGame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                RememberCard remCard = new RememberCard();
                remCard.setSize(1200, 675);
                remCard.setLocationRelativeTo(null);
                remCard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                remCard.setVisible(true);
            }
        });
    }

}
