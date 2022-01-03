import javax.swing.*;
import java.awt.*;

public class Main{

    public static void initializareInterfata() {
        MyWindow window = new MyWindow();
        JFrame frame = new JFrame("Luxembourg");
        frame.setSize(700, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(window);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initializareInterfata();
            }
        });
    }
}
