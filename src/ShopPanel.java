import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ShopPanel extends JPanel {

    private JLabel heading;
    private JPanel petGrid;
    private ArrayList<Pet> pets;
    private User u = new User();
    private GamePanel gamePanel;
    private JButton backButton;
    private Image bqIcon;


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

    private JButton createPetButton(Pet pet) {
        u.loadUserBalance();
        ImageIcon icon = new ImageIcon(LoadIcons.loadResizedIcon(pet.getImagePath(), 128,128).getImage());
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
                u.setUserBalance(u.getUserBalance()-pet.getPrice());
                JOptionPane.showMessageDialog(this, "You bought: " + pet.getName());
                gamePanel.setSelectedPet(pet);
            } else {
                JOptionPane.showMessageDialog(this, "Not enough money");
            }
        });

        return button;
    }


    private ArrayList<Pet> loadPets() {
        ShopItemsLoad sil = new ShopItemsLoad();
        return sil.getPets();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(bqIcon, 0, 0, getWidth(), getHeight(), this);
    }


}
