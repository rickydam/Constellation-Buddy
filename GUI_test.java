import java.io.IOException;

public class GUI_test {
	public static void main(String[] args) {
		GUI testGUI = new GUI();
		SocketServer server = new SocketServer();
		try {
			server.startServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
