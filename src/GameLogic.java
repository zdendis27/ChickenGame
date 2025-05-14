import java.util.ArrayList;
import java.util.Collections;

public class GameLogic {
    private int numberOfBones = 25;
    private int numberOfBombs = 1;
    private int maxNumberOfBombs = 3;
    private boolean gameOver = false;
    private ArrayList<Boolean> bones = new ArrayList<>();




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

    public int getMaxNumberOfBombs() {
        return maxNumberOfBombs;
    }

    public void setMaxNumberOfBombs(int maxNumberOfBombs) {
        this.maxNumberOfBombs = maxNumberOfBombs;
    }

    public int getNumberOfBones() {
        return numberOfBones;
    }

    public void setNumberOfBones(int numberOfBones) {
        this.numberOfBones = numberOfBones;
    }

    public void createBones() {
        for (int i = 0; i < numberOfBombs; i++){
            bones.add(false);
        }
        for (int i = maxNumberOfBombs; i < numberOfBones; i++){
            bones.add(true);
        }

        Collections.shuffle(bones);
        System.out.println(bones);
    }


}
