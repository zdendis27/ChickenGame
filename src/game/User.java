package game;

import shop.Pet;

import java.io.*;
import java.util.ArrayList;

/**
 * Represents a user in the game, storing their balance,pets and number of bombs.
 * User data is read from and written to the {@code src/files/userData} file.
 *
 * This class provides functionality to load, update, and reset user data.
 *
 * @author Zdeněk Vacek
 */
public class User {


    private double userBalance;
    private int numberOfBombs;
    private ArrayList<Pet> ownedPets;

    public int getNumberOfBombs() {
        return numberOfBombs;
    }

    public void setNumberOfBombs(int numberOfBombs) {
        this.numberOfBombs = numberOfBombs;
    }

    public double getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(double userBalance) {
        this.userBalance = userBalance;
    }

    /**
     * Loads the user’s balance and number of bombs from the file.
     * Expects data in the format: {@code balance,bombs}.
     */
    public void loadUserBalance() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/files/userData"))) {
            String line = br.readLine();
            if (line != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    userBalance = Integer.parseInt(parts[0].trim());
                    numberOfBombs = Integer.parseInt(parts[1].trim());

                    if (numberOfBombs > 3) {
                        throw new NumberFormatException("Number of bombs cannot be greater than 3.");
                    }

                } else {
                    throw new NumberFormatException("Invalid number of values in file line.");
                }
            } else {
                System.err.println("File is empty.");
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Failed to read file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Bad number format in file: " + e.getMessage());
        }
    }


    /**
     * Updates the user’s balance in the file, keeping the bomb count unchanged.
     *
     * @param newBalance new balance to store
     */
    public void updateUserBalance(int newBalance) {
        try {
            File file = new File("src/files/userData");
            if (!file.exists()) {
                System.err.println("File does not exist.");
                return;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            br.close();

            if (line != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    int bombs = Integer.parseInt(parts[1].trim());
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                        bw.write(newBalance + "," + bombs);
                    }
                } else {
                    System.err.println("Invalid file format.");
                }
            } else {
                System.err.println("File is empty.");
            }

        } catch (IOException | NumberFormatException e) {
            System.err.println("Failed to update user balance: " + e.getMessage());
        }
    }


    /**
     * Updates the number of bombs in the file, keeping the balance unchanged.
     *
     * @param newBombs new number of bombs to store
     */
    public void updateNumberOfBombs(int newBombs) {
        try {
            File file = new File("src/files/userData");
            if (!file.exists()) {
                System.err.println("File does not exist.");
                return;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            br.close();

            if (line != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    int balance = Integer.parseInt(parts[0].trim());


                    if (newBombs > 3) {
                        throw new NumberFormatException("Number of bombs cannot be greater than 3.");
                    }

                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                        bw.write(balance + "," + newBombs);
                    }
                } else {
                    System.err.println("Invalid file format.");
                }
            } else {
                System.err.println("File is empty.");
            }

        } catch (IOException | NumberFormatException e) {
            System.err.println("Failed to update number of bombs: " + e.getMessage());
        }
    }

    /**
     * Resets the user’s balance to 100 on startup.
     * If the file doesn't exist, it is created with default values.
     */
    public void resetUserBalanceOnStartup() {
        File file = new File("src/files/userData");

        try {
            int bombs = 1;
            if (!file.exists()) {

                file.getParentFile().mkdirs();
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                    bw.write("100,1");
                    System.out.println("userData file created with default values.");
                    return;
                }
            }


            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            br.close();

            if (line != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    bombs = Integer.parseInt(parts[1].trim());
                } else {
                    System.err.println("Invalid file format. Using default bomb value.");
                }
            } else {
                System.err.println("File is empty. Using default bomb value.");
            }


            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                bw.write("100," + bombs);
                System.out.println("game.User balance reset to 100 on startup.");
            }

        } catch (IOException | NumberFormatException e) {
            System.err.println("Error while resetting user balance: " + e.getMessage());
        }
    }








}
