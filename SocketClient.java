import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * TCP client to connect to LabView
 */
public class SocketClient {
	final int PORT = 6123;
	final String ADDRESS = "172.16.0.10";
	Socket client = null;
	
	// Hashmap of all constellations and their coordinate ranges
	// Coordinates are stored as {xmin, ymin, xmax, ymax}
	@SuppressWarnings("serial")
	final Map<String, Integer[]> constellations = new HashMap<String, Integer[]>() {{
		Integer[] coordinates = {0, 0, 100, 100};
		put("Little Dipper", coordinates);
	}};

    /**
     * Start the server and wait for incoming connections
     * @throws IOException
     */
    public void startTCP() throws IOException {
    	client = new Socket(ADDRESS, PORT); // Connect to LabView server
    	BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
    	String answer = input.readLine(); // Read input stream from the server
    	System.out.println(answer);
    }
    
    /**
     * Send a message to the server
     * @param msg
     */
    public void sendMessage(String msg) {
    	if(client != null) {
    	    try {
    	    	// Connected, so try sending a message to the server
        		DataOutputStream out = new DataOutputStream(client.getOutputStream());
				out.writeUTF("Hi");
			} catch (IOException e) {
				e.printStackTrace();
			}
    	} else {
    		// Not connected to the server
    		System.err.println("Send message failed: Client not connected.");
    	}
    }
    
    /**
     * Look up if a constellation exists at a certain xy-coordinate
     * @param x
     * @param y
     */
    public void checkConstellation(int x, int y) {
    	for(String constellation : constellations.keySet()) {
    			
    		
    	}
    }
}
