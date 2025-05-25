import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {

    private JButton[][] gridButtons = new JButton[5][5];
    private GamePanelMenu popupMenu;
    private ImageIcon menuIcon = LoadIcons.loadResizedIcon("src/images/menuIcon.png",48,48);
    private GameLogic gl = new GameLogic ();
    private GamePanelButtons startButton = new GamePanelButtons("START");
    private ImageIcon petGif;
    private BettingSystem bs = new BettingSystem();
    private boolean gameRunning = false;
    private  User u = new User();
    private Pet selectedPet;
    private JPanel topPanel;
    private JLabel petLabel;
    private ImageIcon chickenIcon = new ImageIcon("src/images/chicken.png");
    private ImageIcon bombIcon = new ImageIcon("src/images/bomb.png");




    public GamePanel(Frame frame) {
        bs.setGamePanel(this);
        this.setLayout(new BorderLayout());
        int index = 0;


        JPanel gridPanel = new JPanel(new GridLayout(5, 5));
        gridPanel.setPreferredSize(new Dimension(400, 400));



        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                JButton button = new JButton();
                gridButtons[row][col] = button;
                gridPanel.add(button);
            }
        }

        startButton.addActionListener(e -> {
            if (!gameRunning) {
                initializeGrid();
                bs.bet();
                bs.getChooseNumberOfBombsButton().setEnabled(false);
                bs.getMinusButton().setEnabled(false);
                bs.getPlusButton().setEnabled(false);
                gameRunning = true;
                startButton.setText("END GAME");
            } else {


                bs.rewardIfWin();
                if (bs.getRevealedBones() > 0) {
                    u.loadUserBalance();
                    JOptionPane.showMessageDialog(this, "Vyhrál jsi " + bs.getRewardAmount() + "!", "Výhra", JOptionPane.INFORMATION_MESSAGE);
                }
                disableAllButtons();
                prepareForNewGame();
            }
        });


        popupMenu = new GamePanelMenu(frame);
        topPanel = new JPanel(new BorderLayout());
        JButton menuButton = new JButton();
        menuButton.setIcon(menuIcon);
        menuButton.setBorderPainted(false);
        menuButton.setContentAreaFilled(false);
        menuButton.setFocusPainted(false);
        menuButton.setOpaque(false);
        menuButton.addActionListener(e -> {
            popupMenu.showPopupMenu(menuButton, frame);
        });


        petLabel = new JLabel();
        petLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(petLabel, BorderLayout.CENTER);
        topPanel.add(menuButton,  BorderLayout.EAST);
        topPanel.add(bs.getCurrentBallanceLabel(), BorderLayout.WEST);

        JPanel centerWrapper = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(-70, 0, 0, 0);
        centerWrapper.add(gridPanel, gbc);



        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,10));


        bottomPanel.add(bs.getMinusButton());
        bottomPanel.add(bs.getStackedCurrentBalancePanel());
        bottomPanel.add(bs.getPlusButton());
        bottomPanel.add(startButton);
        bottomPanel.add(bs.getStackedCurrentBombsPanel());
        bottomPanel.add(bs.getChooseNumberOfBombsButton());


        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerWrapper, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    public void gameOver(Frame frame) {
        gameRunning = false;
        disableAllButtons();
        int choice = JOptionPane.showConfirmDialog(this, "Konec hry! Chceš hrát znovu?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            disableAllButtons();
            frame.restartGame();
            bs.updateBalanceLabel();
        } else {
            disableAllButtons();
            bs.getChooseNumberOfBombsButton().setEnabled(true);
            bs.getMinusButton().setEnabled(true);
            bs.getPlusButton().setEnabled(true);
            startButton.setText("START");
        }
    }


    private void initializeGrid() {
        gl.createBones();
        bs.resetRevealedBones();
        int index = 0;

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                JButton button = gridButtons[row][col];
                button.setBackground(null);
                button.setEnabled(true);

                boolean isBone = gl.getBones().get(index);
                int finalRow = row;
                int finalCol = col;
                index++;




                for (ActionListener al : button.getActionListeners()) {
                    button.removeActionListener(al);
                }

                button.addActionListener(e -> {
                    if(!gameRunning) return;

                    System.out.println("Kliknuto na pole [" + finalRow + ", " + finalCol + "]");

                    button.setEnabled(false);


                    if (!isBone) {
                        button.setIcon(bombIcon);
                        button.setDisabledIcon(bombIcon);
                        gameOver((Frame) SwingUtilities.getWindowAncestor(this));
                    } else {
                        bs.plusRevealedBones();
                        button.setIcon(chickenIcon);
                        button.setDisabledIcon(chickenIcon);
                    }

                });
            }
        }
    }
    public void resetGrid() {
        initializeGrid();
    }

    private void disableAllButtons() {
        int index = 0;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                JButton button = gridButtons[row][col];
                boolean isBone = gl.getBones().get(index);
                index++;

                if (!button.isEnabled()) {
                    continue;
                }

                button.setEnabled(false);
                button.setIcon(null);
                if (isBone) {
                    button.setDisabledIcon(chickenIcon);
                    button.setIcon(chickenIcon);
                } else {
                    button.setDisabledIcon(bombIcon);
                    button.setIcon(bombIcon);
                }

            }
        }
    }


    private void prepareForNewGame() {
        bs.updateBalanceLabel();
        bs.getChooseNumberOfBombsButton().setEnabled(true);
        bs.getMinusButton().setEnabled(true);
        bs.getPlusButton().setEnabled(true);
        startButton.setText("START");
        gameRunning = false;
    }

    public void setSelectedPet(Pet selectedPet) {
        this.selectedPet = selectedPet;

        if (petLabel != null && selectedPet != null) {
            System.out.println(selectedPet);
            ImageIcon petGif = new ImageIcon(selectedPet.getGifPath());
            petLabel.setIcon(petGif);
            petLabel.revalidate();
            petLabel.repaint();
        }
    }
}
