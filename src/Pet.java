public class Pet {


    private String imagePath;
    private String name;
    private int price;

    public Pet(String imagePath, String name, int price) {
        this.imagePath = imagePath;
        this.name = name;
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
