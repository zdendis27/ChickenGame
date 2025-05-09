import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {


    ImageIcon icon = new ImageIcon("src/images/cartoon-strong-chicken-mascot-design-vector-29152871.jpg");
    CardLayout cardLayout = new CardLayout();
    JPanel cards = new JPanel(cardLayout);

    Frame(){
        this.setVisible(true);
        this.setSize(800, 600);
        this.setResizable(false);
        this.setTitle("CHICKEN GAME");
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);


        Menu menuPanel = new Menu(this);
        GamePanel gamePanel = new GamePanel(this);

        cards.add(menuPanel,"menu");
        cards.add(gamePanel, "game");



        this.add(cards, BorderLayout.CENTER);
        this.setVisible(true);



    }

    public void switchTo(String panelName) {
        cardLayout.show(cards, panelName);
    }


}
