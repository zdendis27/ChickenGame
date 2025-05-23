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


    public ShopPanel(Frame frame) {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);

        heading = new JLabel("SHOP", SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 60));
        this.add(heading, BorderLayout.NORTH);


        pets = loadPets();


        petGrid = new JPanel(new GridLayout(0, 3, 20, 20));
        petGrid.setBackground(Color.WHITE);

        for (Pet pet : pets) {
            JButton petButton = createPetButton(pet);
            petGrid.add(petButton);
        }

        this.add(petGrid, BorderLayout.CENTER);
    }

    private JButton createPetButton(Pet pet) {
        u.loadUserBalance();
        ImageIcon icon = new ImageIcon(pet.getImagePath());
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


}
