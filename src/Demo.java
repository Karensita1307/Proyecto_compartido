import javax.swing.*;
import java.awt.*;

public class Demo extends JFrame {
    Container contenedor;
    public Demo() {
        contenedor = getContentPane();
        contenedor.setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        setVisible(true);
    }
    public static void main(String[] args){
        Demo demo = new Demo();
    }
}
