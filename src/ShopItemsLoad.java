import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ShopItemsLoad {

    private ArrayList<Pet> pets;
    private String filePet = new String("src/files/pets");

    public ShopItemsLoad() {
        pets = new ArrayList<>();
        pets = loadPets(filePet);
    }

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
