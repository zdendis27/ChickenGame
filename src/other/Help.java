package other;

import mainFrame.Frame;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * Help panel displaying various help topics to the player.
 * Loads help sections from text files and displays them in a scrollable text area.
 *
 * Files are expected to be located in the {@code src/help/} directory.
 *
 * @author Zdeněk Vacek
 */
public class Help extends JPanel {

    private JTextArea helpArea;
    private JButton backButton;

    /**
     * Constructs the Help panel and loads help content from files.
     *
     * @param frame the main application frame, used to switch back to the game panel
     */
    public Help(Frame frame) {
        setLayout(new BorderLayout());
        setBackground(Color.DARK_GRAY);

        JLabel title = new JLabel("Help", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);

        helpArea = new JTextArea();
        helpArea.setEditable(false);
        helpArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        helpArea.setForeground(Color.BLACK);
        helpArea.setBackground(Color.LIGHT_GRAY);
        helpArea.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(helpArea);
        add(scrollPane, BorderLayout.CENTER);

        backButton = new JButton("Back");
        backButton.setBackground(Color.DARK_GRAY);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Monospaced", Font.PLAIN, 14));
        backButton.addActionListener(e -> frame.switchTo("game"));
        add(backButton, BorderLayout.SOUTH);

        appendHelpBlockFromFile("How to Play", new File("src/help/how_to_play.txt"));
        appendHelpBlockFromFile("Betting System", new File("src/help/betting.txt"));
        appendHelpBlockFromFile("Bomb Selection", new File("src/help/bombs.txt"));
        appendHelpBlockFromFile("Game Goal", new File("src/help/goal.txt"));
        appendHelpBlockFromFile("Controls", new File("src/help/controls.txt"));
        appendHelpBlockFromFile("Shop", new File("src/help/shop.txt"));
    }

    /**
     * Appends a help section with a title and the contents of the given file to the help area.
     *
     * @param title the title of the help section
     * @param file  the file containing the help content
     */
    public void appendHelpBlockFromFile(String title, File file) {
        helpArea.append(title + "\n");
        helpArea.append(loadHelpText(file));
        helpArea.append("\n\n");
    }

    /**
     * Loads the text content of a given file into a String.
     *
     * @param file the file to read from
     * @return the file content as a string, or an error message if loading fails
     */
    public String loadHelpText(File file) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            sb.append("Error loading file: ").append(file.getName()).append("\n");
        }
        return sb.toString();
    }
}
