import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener {
    // Global Variables
    private JFrame frame;
    private JLabel stars;
    private JButton leftButton;
    private JButton rightButton;

    // Construction for GUI
    public GUI() {
        frame = new JFrame("RoboHacks2017");
        frame.setPreferredSize(new Dimension(1200, 1200));

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());

        stars = new JLabel(new ImageIcon("stars.jpg"));
        frame.add(stars, BorderLayout.CENTER);

        leftButton = new JButton("<");
        leftButton.setPreferredSize(new Dimension(100, 100));
        leftButton.setMaximumSize(new Dimension(100, 100));
        frame.add(leftButton, BorderLayout.WEST);

        rightButton = new JButton(">");
        frame.add(rightButton, BorderLayout.EAST);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
    }
}
