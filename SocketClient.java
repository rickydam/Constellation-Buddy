import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * TCP client to connect to LabView
 */
public class SocketClient {
    private final int PORT = 6123;
    private final String ADDRESS = "172.16.0.10";
    private Socket client = null;
    private BufferedReader in = null;
    private DataOutputStream out = null;

    /**
     * Start the server and wait for incoming connections
     * @throws IOException
     */
    public void startTCP() throws IOException {
        client = new Socket(ADDRESS, PORT); // Connect to LabView server
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new DataOutputStream(client.getOutputStream());
    }

    /**
     * Receive message from the server
     * @return
     */
    public String getMessage() {
        if (client != null) {
        	try {
        		String fromServer = "";
        		String line;
        		while ((line = in.readLine()) != null) {
		            fromServer = line;
		            System.out.println("Server: " + fromServer);
		        }
                return fromServer;
        	} catch(IOException e) {
        		System.err.println("Failed to get: " + e.getMessage());
        	}
        }
        System.err.println("Error: Client is null when message was received");
        return null;
    }
    
    /**
     * Send a message to the server
     * @param msg
     */
    public void sendMessage(String msg) {
        if(client != null) {
            try {
                // Connected, so try sending a message to the server
                out.writeUTF("Hi");
            } catch (IOException e) {
            	System.out.println("Failed to send " + e.getMessage());
            }
        } else {
            // Not connected to the server
            System.err.println("Send message failed: Client not connected.");
        }
    }
}
