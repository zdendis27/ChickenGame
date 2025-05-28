

import game.User;
import org.junit.jupiter.api.*;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the game.User class.
 * Created in co-op with Chat-GPT.
 */
public class UserTest {

    private User user;
    private File tempFile;

    @BeforeEach
    public void setUp() throws IOException {

        tempFile = File.createTempFile("userData", ".txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
            bw.write("100,2");
        }

        user = new User() {
            @Override
            public void updateUserBalance(int newBalance) {
                try {
                    BufferedReader br = new BufferedReader(new FileReader(tempFile));
                    String line = br.readLine();
                    br.close();

                    if (line != null) {
                        String[] parts = line.split(",");
                        int bombs = Integer.parseInt(parts[1].trim());

                        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
                            bw.write(newBalance + "," + bombs);
                        }
                    }
                } catch (IOException e) {
                    fail("IOException in updateUserBalance: " + e.getMessage());
                }
            }

            @Override
            public void updateNumberOfBombs(int newBombs) {
                try {
                    if (newBombs > 3) {
                        throw new NumberFormatException("Number of bombs cannot be greater than 3.");
                    }

                    BufferedReader br = new BufferedReader(new FileReader(tempFile));
                    String line = br.readLine();
                    br.close();

                    if (line != null) {
                        String[] parts = line.split(",");
                        int balance = Integer.parseInt(parts[0].trim());

                        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
                            bw.write(balance + "," + newBombs);
                        }
                    }
                } catch (IOException e) {
                    fail("IOException in updateNumberOfBombs: " + e.getMessage());
                }
            }
        };
    }

    /**
     * Tests that the updateUserBalance method correctly updates the balance in the file.
     */
    @Test
    public void testUpdateUserBalance() throws IOException {
        user.updateUserBalance(250);

        BufferedReader br = new BufferedReader(new FileReader(tempFile));
        String line = br.readLine();
        br.close();

        assertEquals("250,2", line, "The user balance should be updated to 250.");
    }

    /**
     * Tests that the updateNumberOfBombs method correctly updates the number of bombs in the file.
     */
    @Test
    public void testUpdateNumberOfBombs() throws IOException {
        user.updateNumberOfBombs(3);

        BufferedReader br = new BufferedReader(new FileReader(tempFile));
        String line = br.readLine();
        br.close();

        assertEquals("100,3", line, "The number of bombs should be updated to 3.");
    }

    /**
     * Tests that updateNumberOfBombs throws an exception if the number of bombs is greater than 3.
     */
    @Test
    public void testUpdateNumberOfBombsAboveLimit() {
        Exception exception = assertThrows(NumberFormatException.class, () -> {
            user.updateNumberOfBombs(5);
        });

        assertTrue(exception.getMessage().contains("greater than 3"));
    }

    @AfterEach
    public void tearDown() {
        tempFile.delete();
    }
}
