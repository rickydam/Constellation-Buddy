import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GUI_test {
    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                JPanel panel = new GUI();
//                JFrame frame = new JFrame();
//                frame.setContentPane(panel);
//                frame.setSize(1500, 895);
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                frame.setVisible(true);
//            }
//        });
        
        SocketClient server = new SocketClient();
        System.out.println("Started Socket Client");
//        try {
//            server.startTCP();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println("Started Client");

        //String answer;
        //while(true) {
        // Blocking call to show received messages.
        //answer = server.getMessage();
        //if (answer != null) {
            //System.out.println(answer);
            //server.sendMessage("Hello Stuff");
            //answer = "";
        //}
        //} else {
            //System.err.println("Error: Null message was received");
        //}
    }
}
