import java.io.*;

public class User {


    private double userBalance;
    private int numberOfBombs;

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

}
