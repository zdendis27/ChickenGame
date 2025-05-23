import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Help extends JPanel {

    private JTextArea helpArea;
    private JButton backButton;

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
        add(backButton, BorderLayout.SOUTH);
        backButton.addActionListener(e -> frame.switchTo("game"));


        appendHelpBlockFromFile("How to Play", new File("src/help/how_to_play.txt"));
        appendHelpBlockFromFile("Betting System", new File("src/help/betting.txt"));
        appendHelpBlockFromFile("Bomb Selection", new File("src/help/bombs.txt"));
        appendHelpBlockFromFile("Game Goal", new File("src/help/goal.txt"));
        appendHelpBlockFromFile("Controls", new File("src/help/controls.txt"));
    }

    private void appendHelpBlockFromFile(String title, File file) {
        helpArea.append(title + "\n");
        helpArea.append(loadHelpText(file));
        helpArea.append("\n\n");
    }

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
