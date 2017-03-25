import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener {
    // Global Variables
    private JFrame frame;

    // Construction for GUI
    public GUI() {
        frame = new JFrame("RoboHacks2017");
        frame.setPreferredSize(new Dimension(1200, 1200));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
    }
}
