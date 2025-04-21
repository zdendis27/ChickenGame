import javax.swing.*;
import java.awt.*;

public class CustomButtons extends JButton {
    public CustomButtons(String label) {
        super(label);
        setContentAreaFilled(false);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        ((Graphics2D) g).drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 50, 50);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
        super.paintComponent(g);
        g2.dispose();
    }
}
