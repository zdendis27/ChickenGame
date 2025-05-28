

import game.GamePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;


import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for verifying the behavior of methods in the game.GamePanel class.
 */
public class GamePanelTest {

    private GamePanel gamePanel;

    @BeforeEach
    public void setUp() {
        gamePanel = new GamePanel((mainFrame.Frame) new Frame());
        gamePanel.setGameRunning(true);
        gamePanel.initializeGrid();
    }

    /**
     * Tests the {@code initializeGrid()} method.
     * Verifies that all buttons are enabled, have no icons set,
     * and have exactly one ActionListener assigned after initialization.
     */
    @Test
    public void testAllButtonsEnabledAndNoIconsInitially() {
        JButton[][] gridButtons = gamePanel.getGridButtons();

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                JButton btn = gridButtons[row][col];
                assertTrue(btn.isEnabled());
                assertNull(btn.getIcon());
                assertEquals(1, btn.getActionListeners().length);
            }
        }
    }

    /**
     * Tests the {@code initializeGrid()} method.
     * Verifies that clicking a button reveals either a chicken or a bomb icon.
     */
    @Test
    public void testButtonClickRevealsChickenOrBomb() {
        JButton[][] gridButtons = gamePanel.getGridButtons();
        boolean foundChicken = false;
        boolean foundBomb = false;

        for (int i = 0; i < 25; i++) {
            int row = i / 5;
            int col = i % 5;
            JButton btn = gridButtons[row][col];

            btn.doClick();
            Icon icon = btn.getIcon();
            if (icon != null) {
                String iconStr = icon.toString().toLowerCase();
                if (iconStr.contains("chicken")) foundChicken = true;
                if (iconStr.contains("bomb")) foundBomb = true;
            }

            if (foundChicken && foundBomb) break;
        }

        assertTrue(foundChicken);
        assertTrue(foundBomb);
    }

    /**
     * Tests the {@code disableAllButtons()} method.
     * Verifies that all buttons become disabled and show the correct icon
     * (either chicken or bomb) depending on whether there is a bone or not.
     */
    @Test
    public void testDisableAllButtonsSetsCorrectIconsAndDisablesButtons() {
        gamePanel.setGameRunning(true);
        gamePanel.initializeGrid();
        JButton[][] gridButtons = gamePanel.getGridButtons();

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                gridButtons[row][col].setEnabled(true);
            }
        }

        gamePanel.disableAllButtons();

        for (int i = 0; i < 25; i++) {
            int row = i / 5;
            int col = i % 5;
            JButton btn = gridButtons[row][col];

            assertFalse(btn.isEnabled());
            assertNotNull(btn.getIcon());

            String iconStr = btn.getIcon().toString().toLowerCase();
            assertTrue(iconStr.contains("bomb") || iconStr.contains("chicken"));
        }
    }
}
