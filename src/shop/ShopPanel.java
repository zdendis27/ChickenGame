package shop;

import game.GamePanel;
import game.User;
import mainFrame.Frame;
import other.LoadIcons;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * ShopPanel displays all available pets for purchase in a grid layout.
 * Each pet is represented as a button with its icon and name.
 * Allows users to purchase pets if they have enough balance.
 *
 * @author Zdeněk Vacek
 */
public class ShopPanel extends JPanel {

    private JPanel petGrid;
    private ArrayList<Pet> pets;
    private User u = new User();
    private GamePanel gamePanel;
    private JButton backButton;
    private Image bqIcon;

    /**
     * Constructs the shop panel with pet buttons and a back button.
     *
     * @param frame      The main game frame used to switch between panels.
     * @param gamePanel  Reference to the game panel to assign purchased pets.
     */
    public ShopPanel(Frame frame, GamePanel gamePanel) {
        bqIcon = new ImageIcon("src/images/shoppanel_bq.png").getImage();
        this.gamePanel = gamePanel;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);

        pets = loadPets();
        petGrid = new JPanel(new GridLayout(0, 3, 20, 20));
        petGrid.setBackground(Color.WHITE);

        for (Pet pet : pets) {
            JButton petButton = createPetButton(pet);
            petGrid.add(petButton);
        }

        this.add(petGrid, BorderLayout.CENTER);

        backButton = new JButton("BACK");
        backButton.setBackground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        backButton.addActionListener(e -> frame.switchTo("game"));

        this.add(backButton, BorderLayout.SOUTH);
        petGrid.setOpaque(false);
    }

    /**
     * Creates a button for a specific pet, showing its icon and name.
     * Handles purchase logic when clicked.
     *
     * @param pet The pet to be represented by the button.
     * @return JButton with icon and purchase logic.
     */
    public JButton createPetButton(Pet pet) {
        u.loadUserBalance();
        ImageIcon icon = new ImageIcon(LoadIcons.loadResizedIcon(pet.getImagePath(), 128, 128).getImage());
        JButton button = new JButton(pet.getName(), icon);

        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setToolTipText("Price: " + pet.getPrice());

        button.addActionListener(e -> {
            u.loadUserBalance();
            if (u.getUserBalance() >= pet.getPrice()) {
                u.setUserBalance(u.getUserBalance() - pet.getPrice());
                JOptionPane.showMessageDialog(this, "You bought: " + pet.getName());
                gamePanel.setSelectedPet(pet);
            } else {
                JOptionPane.showMessageDialog(this, "Not enough money");
            }
        });

        return button;
    }

    /**
     * Loads the list of pets from the ShopItemsLoad helper.
     *
     * @return ArrayList of available Pet objects.
     */
    public ArrayList<Pet> loadPets() {
        ShopItemsLoad sil = new ShopItemsLoad();
        return sil.getPets();
    }

    /**
     * Custom background painting using an image.
     *
     * @param g Graphics context to draw on.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bqIcon, 0, 0, getWidth(), getHeight(), this);
    }
}
