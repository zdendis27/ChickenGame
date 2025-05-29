package game;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Handles the core logic of the Chicken Game, including bone and bomb generation,
 * game state management, and connection to the user data.
 *
 * The game is based on bones (true) and bombs (false) shuffled in a list.
 * When a bomb is selected, the game is over.
 *
 * @author Zdeněk Vacek
 */
public class GameLogic {
    private int numberOfBones = 25;
    private int numberOfBombs = 1;
    private boolean gameOver = false;
    private ArrayList<Boolean> bones = new ArrayList<>();
    private User u;

    /**
     * Creates a new instance of GameLogic for the given user.
     *
     * @param user The user playing the game.
     */
    public GameLogic(User user) {
        this.u = user;
    }

    /**
     * Creates and shuffles the list of bones and bombs based on the user's selected number of bombs.
     * Loads the user's balance before generating the list.
     * Prints the generated list to the console for debugging.
     */
    public void createBones() {
        u.loadUserBalance();
        bones.clear();

        for (int i = 0; i < u.getNumberOfBombs(); i++){
            bones.add(false);
        }
        for (int i = u.getNumberOfBombs(); i < numberOfBones; i++){
            bones.add(true);
        }

        Collections.shuffle(bones);
        System.out.println(bones);
    }

    public ArrayList<Boolean> getBones() {
        return bones;
    }

    public void setBones(ArrayList<Boolean> bones) {
        this.bones = bones;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public int getNumberOfBones() {
        return numberOfBones;
    }

    public void setNumberOfBones(int numberOfBones) {
        this.numberOfBones = numberOfBones;
    }
}
