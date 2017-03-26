import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.swing.ActionMap;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.AbstractAction;
import javax.swing.BoundedRangeModel;
import java.net.URL;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GUI extends JPanel {
	private final String dataPath = "data.txt";
    private static final String LEFT = "Left";
    private static final String RIGHT = "Right";
    private static final String UP = "Up";
    private static final String DOWN = "Down";
    private BufferedImage image;
    private JPanel canvas;
    private ConstellationFinder cFinder;
	
    public GUI() {
    	cFinder = new ConstellationFinder();
        try {
        	// Old image at https://amazingsky.files.wordpress.com/2013/07/reesor-ranch-night-sky-panorama.jpg
            this.image = ImageIO.read(new URL("http://media.nj.com/inside-jersey/photo/njnightsky-jupiterpng-1f7ae063bff77335.png"));
        } catch(IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);
            }
        };
        
        this.canvas.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
				cFinder.checkConstellation(e.getX(), e.getY());
            }

			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
        });

        canvas.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        JScrollPane scrollPane = new JScrollPane(canvas);

        InputMap inputmap = canvas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputmap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), LEFT);
        inputmap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), RIGHT);
        inputmap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), UP);
        inputmap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), DOWN);

        int scrollableIncrement = 10;
        ActionMap actmap = canvas.getActionMap();
        actmap.put(LEFT, new LeftRightAction(LEFT, scrollPane.getHorizontalScrollBar().getModel(), scrollableIncrement));
        actmap.put(RIGHT, new LeftRightAction(RIGHT, scrollPane.getHorizontalScrollBar().getModel(), scrollableIncrement));
        actmap.put(UP, new UpDownAction(UP, scrollPane.getVerticalScrollBar().getModel(), scrollableIncrement));
        actmap.put(DOWN, new UpDownAction(DOWN, scrollPane.getVerticalScrollBar().getModel(), scrollableIncrement));

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    // Action for vertical key binding to perform when bound event occurs
    private class UpDownAction extends AbstractAction {
        private BoundedRangeModel vScrollBarModel;
        private int scrollableIncrement;
        public UpDownAction(String name, BoundedRangeModel model, int scrollableIncrement) {
            super(name);
            this.vScrollBarModel = model;
            this.scrollableIncrement = scrollableIncrement;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            String name = getValue(AbstractAction.NAME).toString();
            int value = vScrollBarModel.getValue();
            if (name.equals(UP)) {
                cFinder.writeToFile("0,-1", dataPath);
                value -= scrollableIncrement;
                vScrollBarModel.setValue(value);
            } else if (name.equals(DOWN)) {
                cFinder.writeToFile("0,1", dataPath);
                value += scrollableIncrement;
                vScrollBarModel.setValue(value);
            }
        }
    }
    
    // Action for horizontal key binding to perform when bound event occurs
    private class LeftRightAction extends AbstractAction {
        private BoundedRangeModel vScrollBarModel;
        private int scrollableIncrement;
        public LeftRightAction(String name, BoundedRangeModel model, int scrollableIncrement) {
            super(name);
            this.vScrollBarModel = model;
            this.scrollableIncrement = scrollableIncrement;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            String name = getValue(AbstractAction.NAME).toString();
            int value = vScrollBarModel.getValue();
            if (name.equals(LEFT)) {
                cFinder.writeToFile("-1,0", dataPath);
                value -= scrollableIncrement;
                vScrollBarModel.setValue(value);
            } else if (name.equals(RIGHT)) {
                cFinder.writeToFile("1,0", dataPath);
                value += scrollableIncrement;
                vScrollBarModel.setValue(value);
            }
        }
    }
}
