import javax.swing.*;
import java.awt.*;

public class BettingSystem {

    private int numberOfBombs;
    private int multiplyer = 2;
    private int currentBet = 0;
    private int currentBombs = 0;
    private ImageIcon minusIcon = LoadIcons.loadResizedIcon("src/images/minusIcon.png",100,100);
    private ImageIcon plusIcon = LoadIcons.loadResizedIcon("src/images/plusIcon.png",100,100);
    private JPanel stackedCurrentBalancePanel =  new JPanel(new GridLayout(2, 1));
    private JLabel currentBetLabel = new JLabel(String.valueOf(currentBet));
    private GamePanelButtons minusButton = new GamePanelButtons("");
    private GamePanelButtons plusButton = new GamePanelButtons("");
    private JPanel stackedCurrentBombsPanel =  new JPanel(new GridLayout(2,1));
    private JLabel currentNumberOfBombsLabel = new JLabel();
    private GamePanelButtons chooseNumberOfBombsButton = new GamePanelButtons("Choose bombs:");
    User u = new User();

    public BettingSystem() {
        u.loadUserBalance();

        stackedCurrentBalancePanel.setOpaque(false);

        JLabel line1 = new JLabel("Current bet:");
        line1.setFont(new Font("Impact", Font.BOLD, 12));
        line1.setHorizontalAlignment(SwingConstants.CENTER);

        currentBetLabel = new JLabel(String.valueOf(currentBet));
        currentBetLabel.setFont(new Font("Impact", Font.BOLD, 12));
        currentBetLabel.setHorizontalAlignment(SwingConstants.CENTER);

        stackedCurrentBalancePanel.add(line1);
        stackedCurrentBalancePanel.add(currentBetLabel);


        minusButton.setIcon(minusIcon);
        minusButton.setBorderPainted(false);
        minusButton.setContentAreaFilled(false);
        minusButton.setFocusPainted(false);
        minusButton.setOpaque(false);
        minusButton.addActionListener(e -> {
            if(currentBet>10){
                currentBet = currentBet - 10;
                currentBetLabel.setText(String.valueOf(currentBet));

            }else{
                System.out.println("You cannot bet negative number");
            }

        });


        plusButton.setIcon(plusIcon);
        plusButton.setBorderPainted(false);
        plusButton.setContentAreaFilled(false);
        plusButton.setFocusPainted(false);
        plusButton.setOpaque(false);
        plusButton.addActionListener(e -> {
            if(currentBet<100){
                currentBet = currentBet + 10;
                currentBetLabel.setText(String.valueOf(currentBet));
            }else {
                System.out.println("You cannot bet more than 100");
            }
        });










        stackedCurrentBombsPanel.setOpaque(false);
        JLabel bombsFirstLine = new JLabel("Current bombs:");
        bombsFirstLine.setFont(new Font("Impact", Font.BOLD, 12));
        bombsFirstLine.setHorizontalAlignment(SwingConstants.CENTER);

        currentNumberOfBombsLabel = new JLabel(String.valueOf(currentBombs));
        currentNumberOfBombsLabel.setFont(new Font("Impact", Font.BOLD, 12));
        currentNumberOfBombsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        stackedCurrentBombsPanel.add(bombsFirstLine);
        stackedCurrentBombsPanel.add(currentNumberOfBombsLabel);

        chooseNumberOfBombsButton.addActionListener(e -> {

                String[] options = {"1", "2", "3"};
                int choice = JOptionPane.showOptionDialog(
                        null,
                        "Choose number of bombs:",
                        "Bomb Selection",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

                if (choice >= 0 && choice < options.length) {
                    int selectedBombs = Integer.parseInt(options[choice]);
                    setCurrentBombs(selectedBombs);
                    u.updateNumberOfBombs(selectedBombs);
                }


        });


    }

    public JPanel getStackedCurrentBalancePanel() {
        return stackedCurrentBalancePanel;
    }

    public void setStackedCurrentBalancePanel(JPanel stackedCurrentBalancePanel) {
        this.stackedCurrentBalancePanel = stackedCurrentBalancePanel;
    }

    public JPanel getStackedCurrentBombsPanel() {
        return stackedCurrentBombsPanel;
    }

    public void setStackedCurrentBombsPanel(JPanel stackedCurrentBombsPanel) {
        this.stackedCurrentBombsPanel = stackedCurrentBombsPanel;
    }

    public GamePanelButtons getMinusButton() {
        return minusButton;
    }

    public void setMinusButton(GamePanelButtons minusButton) {
        this.minusButton = minusButton;
    }

    public GamePanelButtons getPlusButton() {
        return plusButton;
    }

    public void setPlusButton(GamePanelButtons plusButton) {
        this.plusButton = plusButton;
    }

    public int getCurrentBombs() {
        return currentBombs;
    }

    public void setCurrentBombs(int value) {
        currentBombs = value;
        currentNumberOfBombsLabel.setText(String.valueOf(currentBombs));
    }

    public GamePanelButtons getChooseNumberOfBombsButton() {
        return chooseNumberOfBombsButton;
    }

    public void setChooseNumberOfBombsButton(GamePanelButtons chooseNumberOfBombsButton) {
        this.chooseNumberOfBombsButton = chooseNumberOfBombsButton;
    }
}
