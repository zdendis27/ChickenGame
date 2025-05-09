import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {

    public Menu(Frame frame) {
        ImageIcon backgroundImage = new ImageIcon("src/images/bq 2.png");

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS) {
            @Override
            public void layoutContainer(Container target) {
                super.layoutContainer(target);
            }
        });

        this.setOpaque(false);

        StartButton startButton = new StartButton(frame);
        ExitButton exitButton = new ExitButton(frame);

        this.add(Box.createVerticalStrut(350));
        this.add(startButton);
        this.add(Box.createVerticalStrut(20));
        this.add(exitButton);
        this.add(Box.createVerticalGlue());
    }

    @Override
    protected void paintComponent(Graphics g) {
        ImageIcon backgroundImage = new ImageIcon("src/images/bq 2.png");
        g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}
