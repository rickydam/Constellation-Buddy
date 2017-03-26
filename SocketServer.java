import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Simple TCP server
 *
 */
public class SocketServer {

	final int PORT = 9090;
	boolean acceptConnections = true;
	boolean clientConnected = false;

    /**
     * Runs the server.
     */
    public void startServer() throws IOException {
    	ServerSocket server = new ServerSocket(PORT, 0, InetAddress.getByName("localhost"));
    	
    	System.out.println("Server started: " + server);
    	
    	try {
    		while(acceptConnections) {
    			// Wait for a connection
    			Socket client = server.accept();
    			System.out.println("New client connected: " + client);
    			clientConnected = true;
    			/*try {
    				while(clientConnected) {
    					// Do stuff with motor
    				}
    			} finally {
    				client.close();
    			}*/
    		}
    	} finally {
    		server.close();
    	}
    }
}
