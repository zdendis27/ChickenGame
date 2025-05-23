import java.io.*;
import java.util.ArrayList;

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





}
