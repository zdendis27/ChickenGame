import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {


    ImageIcon icon = new ImageIcon("src/images/cartoon-strong-chicken-mascot-design-vector-29152871.jpg");

    Frame(){
        this.setVisible(true);
        this.setSize(800, 600);
        this.setResizable(false);
        this.setTitle("CHICKEN GAME");
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        new Menu(this);


    }


}
