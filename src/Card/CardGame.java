import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import CardGameModule.RememberCard;

public class CardGame {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                RememberCard remCard = new RememberCard();
                remCard.setSize(400, 300);
                remCard.setLocationRelativeTo(null);
                remCard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                remCard.setVisible(true);
            }
        });
	}

}
