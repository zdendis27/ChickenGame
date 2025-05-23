import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {


    ImageIcon icon = new ImageIcon("src/images/cartoon-strong-chicken-mascot-design-vector-29152871.jpg");
    CardLayout cardLayout = new CardLayout();
    JPanel cards = new JPanel(cardLayout);
    private GamePanel gamePanel;
    private Menu menuPanel;
    private Help helpPanel;
    private BettingSystem bs = new BettingSystem();



    Frame(){
        this.setVisible(true);
        this.setSize(800, 600);
        this.setResizable(false);
        this.setTitle("CHICKEN GAME");
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);


        menuPanel= new Menu(this);
        gamePanel = new GamePanel(this);
        helpPanel = new Help(this);


        cards.add(menuPanel,"menu");
        cards.add(gamePanel, "game");
        cards.add(helpPanel, "help");







        this.add(cards, BorderLayout.CENTER);

        this.setVisible(true);



    }

    public void switchTo(String panelName) {
        cardLayout.show(cards, panelName);
    }

    public void restartGame(){
        cards.remove(gamePanel);
        gamePanel = new GamePanel(this);
        cards.add(gamePanel,"game");
        cardLayout.show(cards,"game");

    }


}
