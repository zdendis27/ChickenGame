import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel {

    private JButton[][] gridButtons = new JButton[5][5];

    public GamePanel(Frame frame) {
        this.setLayout(new GridBagLayout());

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(5, 5));

        //grid size
        gridPanel.setPreferredSize(new Dimension(400, 400));

        //creating grid
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                JButton button = new JButton();
                gridButtons[row][col] = button;


                int finalRow = row;
                int finalCol = col;

                button.addActionListener(e -> {
                    System.out.println("Kliknuto na pole [" + finalRow + ", " + finalCol + "]");
                    // here comes game logic
                    button.setBackground(Color.YELLOW);
                });

                gridPanel.add(button);
            }
        }

        //here i can set position of my grid
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0
                , 0, 50, 0);

        this.add(gridPanel,gbc);
    }
}
