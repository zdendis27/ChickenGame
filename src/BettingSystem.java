import javax.swing.*;
import java.awt.*;

public class BettingSystem {

    private int numberOfBombs;
    private int multiplyer = 2;
    private JPanel stackedCurrentBalanceLabel =  new JPanel(new GridLayout(2, 1));
    private JLabel currentBombsLabel =  new JLabel();
    User u = new User();

    public BettingSystem() {
        u.loadUserBalance();

        stackedCurrentBalanceLabel.setOpaque(false);

        JLabel line1 = new JLabel("Current ballance");
        line1.setFont(new Font("Arial", Font.BOLD, 12));
        line1.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel line2 = new JLabel(String.valueOf(u.getUserBalance()));
        line2.setFont(new Font("Arial", Font.BOLD, 12));
        line2.setHorizontalAlignment(SwingConstants.CENTER);

        stackedCurrentBalanceLabel.add(line1);
        stackedCurrentBalanceLabel.add(line2);








        currentBombsLabel.setText("Current bombs: " + u.getNumberOfBombs());
        currentBombsLabel.setPreferredSize(new Dimension(100, 50));
        currentBombsLabel.setMaximumSize(new Dimension(100, 50));
        currentBombsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        currentBombsLabel.setVerticalTextPosition(JLabel.CENTER);
        currentBombsLabel.setHorizontalTextPosition(JLabel.CENTER);
        currentBombsLabel.setHorizontalAlignment(JLabel.CENTER);
        currentBombsLabel.setVerticalAlignment(JLabel.CENTER);

    }

    public JPanel getStackedCurrentBalanceLabel() {
        return stackedCurrentBalanceLabel;
    }

    public void setStackedCurrentBalanceLabel(JPanel stackedCurrentBalanceLabel) {
        this.stackedCurrentBalanceLabel = stackedCurrentBalanceLabel;
    }

    public JLabel getCurrentBombsLabel() {
        return currentBombsLabel;
    }

    public void setCurrentBombsLabel(JLabel currentBombsLabel) {
        this.currentBombsLabel = currentBombsLabel;
    }
}
