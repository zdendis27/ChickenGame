import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {

    private JButton[][] gridButtons = new JButton[5][5];
    private GamePanelMenu popupMenu;
    private ImageIcon menuIcon = LoadIcons.loadResizedIcon("src/images/menuIcon.png",48,48);
    GameLogic gl = new GameLogic ();
    BettingSystem bs = new BettingSystem();


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

        initializeGrid();





        popupMenu = new GamePanelMenu(frame);
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton menuButton = new JButton();
        menuButton.setIcon(menuIcon);
        menuButton.setBorderPainted(false);
        menuButton.setContentAreaFilled(false);
        menuButton.setFocusPainted(false);
        menuButton.setOpaque(false);
        menuButton.addActionListener(e -> {
            popupMenu.showPopupMenu(menuButton, frame);
        });
        topPanel.add(menuButton);

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
        bottomPanel.add(bs.getStackedCurrentBombsPanel());
        bottomPanel.add(bs.getStackedCurrentBombsPanel());
        bottomPanel.add(bs.getChooseNumberOfBombsButton());





        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerWrapper, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    public void gameOver(Frame frame){
        int choice = JOptionPane.showConfirmDialog(this, "Konec hry! Chceš hrát znovu?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            frame.restartGame();



        }else{
            for(int row = 0; row < 5; row++){
                for(int col = 0; col < 5; col++){
                    gridButtons[row][col].setEnabled(false);
                }
            }
        }


    }
    private void initializeGrid() {
        gl.createBones();
        int index = 0;

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                JButton button = gridButtons[row][col];
                button.setEnabled(true);
                button.setBackground(null);

                boolean isBone = gl.getBones().get(index);
                int finalRow = row;
                int finalCol = col;
                index++;


                for (ActionListener al : button.getActionListeners()) {
                    button.removeActionListener(al);
                }

                button.addActionListener(e -> {
                    System.out.println("Kliknuto na pole [" + finalRow + ", " + finalCol + "]");
                    if (!isBone) {
                        button.setBackground(Color.RED);
                        gameOver((Frame) SwingUtilities.getWindowAncestor(this));
                    } else {
                        button.setBackground(Color.GREEN);
                    }
                    button.setEnabled(false);
                    bs.getChooseNumberOfBombsButton().setEnabled(false);
                    bs.getMinusButton().setEnabled(false);
                    bs.getPlusButton().setEnabled(false);
                });
            }
        }
    }
    public void resetGrid() {
        initializeGrid();
    }

}
