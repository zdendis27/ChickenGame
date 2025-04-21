import javax.swing.*;
import java.awt.*;

public class Menu {

    public Menu(JFrame frame) {
        ImageIcon backgroundImage = new ImageIcon("src/images/bq 2.png");

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Vykreslíme obrázek jako pozadí, přizpůsobíme velikosti panelu
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        JButton startButton = new JButton("START");
        startButton.setPreferredSize(new Dimension(400, 100));
        startButton.setMaximumSize(new Dimension(400, 100));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setContentAreaFilled(false);
        startButton.setForeground(Color.BLACK);
        startButton.setBorderPainted(false);
        startButton.setFont(new Font("Impact", Font.BOLD, 100));

        JButton exitButton = new JButton("KONEC");
        exitButton.setPreferredSize(new Dimension(200, 50));
        exitButton.setMaximumSize(new Dimension(200, 50));
        exitButton.setFont(new Font("Impact", Font.BOLD, 25));
        exitButton.setForeground(Color.RED);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame, "Do you really want to exit?", "Accept", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        panel.add(Box.createVerticalStrut(350)); // prostor nahoře
        panel.add(startButton);
        panel.add(Box.createVerticalStrut(20));
        panel.add(exitButton);
        panel.add(Box.createVerticalGlue());

        frame.getContentPane().removeAll();
        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();
    }
}
