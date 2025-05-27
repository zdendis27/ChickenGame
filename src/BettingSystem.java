import javax.swing.*;
import java.awt.*;

public class BettingSystem {

    private int numberOfBombs;
    private int multiplyer = 2;
    private int currentBet = 0;
    private int revealedBones = 0;
    private ImageIcon minusIcon = LoadIcons.loadResizedIcon("src/images/minusIcon.png",100,100);
    private ImageIcon plusIcon = LoadIcons.loadResizedIcon("src/images/plusIcon.png",100,100);
    private ImageIcon bombIcon = LoadIcons.loadResizedIcon("src/images/bombIconn.png",100,50);
    private JPanel stackedCurrentBalancePanel =  new JPanel(new GridLayout(2, 1));
    private JLabel currentBetLabel = new JLabel(String.valueOf(currentBet));
    private GamePanelButtons minusButton = new GamePanelButtons("");
    private GamePanelButtons plusButton = new GamePanelButtons("");
    private JPanel stackedCurrentBombsPanel =  new JPanel(new GridLayout(2,1));
    private JLabel currentNumberOfBombsLabel = new JLabel();
    private JLabel currentBallanceLabel =new JLabel();
    private GamePanelButtons chooseNumberOfBombsButton = new GamePanelButtons("");
    private User u = new User();
    private GamePanel gamePanel;
    private int currentBombs = 0;


    public BettingSystem() {
        u.loadUserBalance();
        currentBombs = u.getNumberOfBombs();

        stackedCurrentBalancePanel.setOpaque(false);

        JLabel line1 = new JLabel("Current bet:");
        line1.setFont(new Font("Arial", Font.BOLD, 14));
        line1.setHorizontalAlignment(SwingConstants.CENTER);

        currentBetLabel = new JLabel(String.valueOf(currentBet));
        currentBetLabel.setFont(new Font("Arial", Font.BOLD, 16));
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
        bombsFirstLine.setFont(new Font("Arial", Font.BOLD, 14));
        bombsFirstLine.setHorizontalAlignment(SwingConstants.CENTER);

        currentNumberOfBombsLabel = new JLabel(String.valueOf(currentBombs));
        currentNumberOfBombsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        currentNumberOfBombsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        stackedCurrentBombsPanel.add(bombsFirstLine);
        stackedCurrentBombsPanel.add(currentNumberOfBombsLabel);

        currentBallanceLabel = new JLabel("Balance: " + String.valueOf(u.getUserBalance()));
        currentBallanceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        currentBallanceLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));





        chooseNumberOfBombsButton.setIcon(bombIcon);
        chooseNumberOfBombsButton.setBorderPainted(false);
        chooseNumberOfBombsButton.setContentAreaFilled(false);
        chooseNumberOfBombsButton.setFocusPainted(false);
        chooseNumberOfBombsButton.setOpaque(false);
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
                    gamePanel.resetGrid();
                }


        });


    }

    public void bet() {
        u.loadUserBalance();
        if (currentBet > 0 && currentBet <= u.getUserBalance()) {
            int updatedBalance = (int) (u.getUserBalance() - currentBet);
            u.updateUserBalance(updatedBalance);
            System.out.println("Updated balance: " + updatedBalance);
            updateBalanceLabel();
        } else {

            JOptionPane.showMessageDialog(
                    null,
                    "You do not have enough money to place this bet!",
                    "You are broke...",
                    JOptionPane.WARNING_MESSAGE
            );
            updateBalanceLabel();



        }






    }


    public void rewardIfWin() {
        u.loadUserBalance();
        int reward = currentBet * multiplyer * u.getNumberOfBombs() * revealedBones;
        int updatedBalance = (int) (u.getUserBalance() + reward);
        u.updateUserBalance(updatedBalance);
        System.out.println("Win: " + reward + ", new Ballance: " + updatedBalance);
        updateBalanceLabel();
    }

    public void updateBalanceLabel() {
        u.loadUserBalance();


        currentBallanceLabel.setText("Balance: " + u.getUserBalance());
    }

    public void checkAndGiveMoneyRain() {
        u.loadUserBalance();
        if (u.getUserBalance() < 10) {
            int newBalance = (int) (u.getUserBalance() + 100);
            u.updateUserBalance(newBalance);
            JOptionPane.showMessageDialog(
                    null,
                    "Money rain! You received 100!",
                    "How lucky you are...",
                    JOptionPane.INFORMATION_MESSAGE
            );
            updateBalanceLabel();
        }
    }




    public int getRewardAmount() {
        u.loadUserBalance();
        return (int) (currentBet * multiplyer * revealedBones*u.getNumberOfBombs());
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
    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    public void plusRevealedBones() {
        revealedBones++;
    }

    public void resetRevealedBones() {
        revealedBones = 0;
    }

    public int getRevealedBones() {
        return revealedBones;
    }

    public void setRevealedBones(int revealedBones) {
        this.revealedBones = revealedBones;
    }

    public JLabel getCurrentBallanceLabel() {
        return currentBallanceLabel;
    }

    public void setCurrentBallanceLabel(JLabel currentBallanceLabel) {
        this.currentBallanceLabel = currentBallanceLabel;
    }
}
