import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {

    private JButton[][] gridButtons = new JButton[5][5];
    private GamePanelMenu popupMenu;
    private ImageIcon menuIcon = LoadIcons.loadResizedIcon("src/images/menuIcon.png",48,48);
    private GameLogic gl = new GameLogic ();
    private GamePanelButtons startButton = new GamePanelButtons("START");
    private BettingSystem bs = new BettingSystem();
    private boolean gameRunning = false;
    private  User u = new User();
    private Pet selectedPet;
    private JPanel topPanel;
    private JLabel petLabel;
    private ImageIcon chickenIcon = new ImageIcon("src/images/chicken.png");
    private ImageIcon bombIcon = new ImageIcon("src/images/bomb.png");
    private Image bqIcon;




    public GamePanel(Frame frame) {
        bqIcon = new ImageIcon("src/images/gamepanel_bq.png").getImage();
        u.resetUserBalanceOnStartup();
        bs.updateBalanceLabel();
        bs.setGamePanel(this);
        this.setLayout(new BorderLayout());


        JPanel gridPanel = new JPanel(new GridLayout(5, 5));
        gridPanel.setPreferredSize(new Dimension(400, 400));

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                JButton button = new JButton();
                gridButtons[row][col] = button;
                gridPanel.add(button);
            }
        }

        startButton.setBackground(Color.white);
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

        gridPanel.setOpaque(false);
        bottomPanel.setOpaque(false);
        topPanel.setOpaque(false);
        centerWrapper.setOpaque(false);

    }

    public void gameOver(Frame frame) {
        gameRunning = false;
        disableAllButtons();

        JOptionPane.showMessageDialog(this, "Game over!", "Game Over", JOptionPane.INFORMATION_MESSAGE);

        bs.getChooseNumberOfBombsButton().setEnabled(true);
        bs.getMinusButton().setEnabled(true);
        bs.getPlusButton().setEnabled(true);
        startButton.setText("START");
        bs.checkAndGiveMoneyRain();
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

                button.setIcon(null);
                button.setDisabledIcon(null);


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
        animateGridReset();
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

    private void animateGridReset() {
        int delay = 50;
        Timer timer = new Timer(delay, null);
        final int[] index = {0};

        timer.addActionListener(e -> {
            if (index[0] >= 25) {
                ((Timer) e.getSource()).stop();
                initializeGrid();
                return;
            }

            int row = index[0] / 5;
            int col = index[0] % 5;
            JButton button = gridButtons[row][col];

            button.setIcon(null);
            button.setDisabledIcon(null);
            button.setEnabled(true);
            index[0]++;
        });

        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bqIcon, 0, 0, getWidth(), getHeight(), this);
    }


}
