package shop;

/**
 * Represents a virtual pet available in the game shop.
 * Each pet has a name, price, static image path, and optional GIF animation path.
 *
 * @Author Zdenek Vacek
 * @version 1.0
 */
public class Pet {

    private String imagePath;
    private String name;
    private int price;
    private String gifPath;

    /**
     * Constructs a new Pet instance with the specified attributes.
     *
     * @param gifPath    the file path to the animated image (GIF) of the pet
     * @param imagePath  the file path to the static image of the pet
     * @param name       the name of the pet
     * @param price      the price of the pet in in-game currency
     */
    public Pet(String gifPath, String imagePath, String name, int price) {
        this.gifPath = gifPath;
        this.imagePath = imagePath;
        this.name = name;
        this.price = price;
    }

    public String getGifPath() {
        return gifPath;
    }

    public void setGifPath(String gifPath) {
        this.gifPath = gifPath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
