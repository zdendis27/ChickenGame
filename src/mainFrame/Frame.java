package mainFrame;

import game.GamePanel;
import other.BackgroundMusic;
import other.Help;
import shop.ShopPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Main application window using CardLayout to switch between different panels.
 * Panels include main menu, game, help, and shop.
 * Initializes the background music and sets up basic window properties.
 *
 * @author Zdeněk Vacek
 */
public class Frame extends JFrame {

    private ImageIcon icon = new ImageIcon("src/images/cartoon-strong-chicken-mascot-design-vector-29152871.jpg");
    private CardLayout cardLayout = new CardLayout();
    private JPanel cards = new JPanel(cardLayout);
    private GamePanel gamePanel;
    private Menu menuPanel;
    private Help helpPanel;
    private ShopPanel shopPanel;
    private BackgroundMusic bgm = new BackgroundMusic();

    /**
     * Constructs the main game window and initializes all panels and resources.
     */
    public Frame() {
        this.setVisible(true);
        this.setSize(800, 600);
        this.setResizable(false);
        this.setTitle("CHICKEN GAME");
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        bgm.play("src/music/bg_music_final.wav");

        menuPanel = new Menu(this);
        gamePanel = new GamePanel(this);
        helpPanel = new Help(this);
        shopPanel = new ShopPanel(this, gamePanel);

        cards.add(menuPanel, "menu");
        cards.add(gamePanel, "game");
        cards.add(helpPanel, "help");
        cards.add(shopPanel, "shop");

        this.add(cards, BorderLayout.CENTER);
        this.setVisible(true);
    }

    /**
     * Switches to the specified panel by name.
     *
     * @param panelName the name of the panel to switch to ("menu", "game", "help", "shop")
     */
    public void switchTo(String panelName) {
        cardLayout.show(cards, panelName);
    }

    /**
     * Restarts the game by creating a new instance of GamePanel
     * and switching to it.
     */
    public void restartGame() {
        cards.remove(gamePanel);
        gamePanel = new GamePanel(this);
        cards.add(gamePanel, "game");
        cardLayout.show(cards, "game");
    }

    public BackgroundMusic getBackgroundMusic() {
        return bgm;
    }
}
