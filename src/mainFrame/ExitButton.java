package mainFrame;

import javax.swing.*;
import java.awt.*;

public class ExitButton extends JButton {
    
    public ExitButton(JFrame frame) {
        this.setText("EXIT");
        this.setPreferredSize(new Dimension(200, 50));
        this.setMaximumSize(new Dimension(200, 50));
        this.setFont(new Font("Impact", Font.BOLD, 25));
        this.setForeground(Color.RED);
        this.setContentAreaFilled(false);
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame, "Do you really want to exit?", "Accept", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                frame.dispose();
            }
        });
    }
}
