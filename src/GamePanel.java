import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private JButton[][] gridButtons = new JButton[5][5];
    private GamePanelMenu popupMenu;
    private ImageIcon menuIcon = new ImageIcon("src/images/menuIcon.png");

    public GamePanel(Frame frame) {
        this.setLayout(new BorderLayout());


        JPanel gridPanel = new JPanel(new GridLayout(5, 5));
        gridPanel.setPreferredSize(new Dimension(400, 400));

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                JButton button = new JButton();
                int finalRow = row;
                int finalCol = col;

                button.addActionListener(e -> {
                    System.out.println("Kliknuto na pole [" + finalRow + ", " + finalCol + "]");
                    //here comes game logic
                    button.setBackground(Color.YELLOW);
                });

                gridButtons[row][col] = button;
                gridPanel.add(button);
            }
        }


        popupMenu = new GamePanelMenu(frame);
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton menuButton = new JButton();
        menuButton.setIcon(menuIcon);
        menuButton.setBorderPainted(false);
        menuButton.setContentAreaFilled(false);
        menuButton.setFocusPainted(false);
        menuButton.setOpaque(false);
        menuButton.addActionListener(e -> {
            popupMenu.showPopupMenu(menuButton, frame);
        });
        topPanel.add(menuButton);

        JPanel centerWrapper = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(-100, 0, 0, 0); // posune nahoru
        centerWrapper.add(gridPanel, gbc);



        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerWrapper, BorderLayout.CENTER);
    }
}
