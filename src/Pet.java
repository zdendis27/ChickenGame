public class Pet {


    private String imagePath;
    private String name;
    private int price;
    private String gifPath;

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
