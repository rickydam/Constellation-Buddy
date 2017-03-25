import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

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
    	ServerSocket server = new ServerSocket(PORT);
    	
    	System.out.println("Server started on port " + PORT);
    	
    	try {
    		while(acceptConnections) {
    			Socket client = server.accept();
    			System.out.println("New client connected:\n" + client);
    			clientConnected = true;
    			try {
    				while(clientConnected) {
    					// Do stuff with motor
    				}
    			} finally {
    				client.close();
    			}
    		}
    	} finally {
    		server.close();
    	}
    }
    
    
}