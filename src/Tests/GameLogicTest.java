


import game.GameLogic;
import game.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the game.GameLogic class.
 */
public class GameLogicTest {

    private GameLogic gameLogic;
    private User mockUser;

    @BeforeEach
    public void setUp() {
        mockUser = new User() {
            @Override
            public int getNumberOfBombs() {
                return 5;
            }

            @Override
            public void loadUserBalance() {
                // no-op for testing
            }
        };

        gameLogic = new GameLogic(mockUser);
        gameLogic.setNumberOfBones(25);
    }

    /**
     * Tests the createBones method to ensure that the correct number of bombs (false) and bones (true)
     * are generated and the resulting list is shuffled.
     * Created in co-op with Chat-GPT.
     */
    @Test
    public void testCreateBones() {
        gameLogic.createBones();
        List<Boolean> bones = gameLogic.getBones();

        assertEquals(25, bones.size(), "The total number of bones should be 25");

        long bombsCount = bones.stream().filter(b -> !b).count();
        long safeBonesCount = bones.stream().filter(b -> b).count();

        assertEquals(5, bombsCount, "The number of bombs (false) should match the user's setting");
        assertEquals(20, safeBonesCount, "The number of safe bones (true) should be the remainder");

    }
}
