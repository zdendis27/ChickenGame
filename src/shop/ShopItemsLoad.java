package shop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Responsible for loading pet items from a file and providing them to the shop system.
 * Pets are loaded from a text file and stored in a list.
 *
 * @author Zdeněk Vacek
 */
public class ShopItemsLoad {

    private ArrayList<Pet> pets;
    private String filePet = new String("src/files/pets");

    /**
     * Constructs the ShopItemsLoad and automatically loads pets from the default file.
     */
    public ShopItemsLoad() {
        pets = new ArrayList<>();
        pets = loadPets(filePet);
    }

    /**
     * Loads pet data from a specified file.
     * Each line must contain: imagePath,name,price,gifPath
     *
     * @param filePath the path to the file containing pet definitions
     * @return a list of Pet objects loaded from the file
     */
    public ArrayList<Pet> loadPets(String filePath) {
        ArrayList<Pet> allPets = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String imagePath = parts[0].trim();
                    String name = parts[1].trim();
                    int price = Integer.parseInt(parts[2].trim());
                    String gifPath = parts[3].trim();

                    Pet pet = new Pet(gifPath, imagePath, name, price);
                    allPets.add(pet);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return allPets;
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }

    public void setPets(ArrayList<Pet> pets) {
        this.pets = pets;
    }
}
