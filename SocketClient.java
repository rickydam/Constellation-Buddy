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

    /**
     * Start the server and wait for incoming connections
     * @throws IOException
     */
    public void startTCP() throws IOException {
        client = new Socket(ADDRESS, PORT); // Connect to LabView server
    }

    /**
     * Receive message from the server
     * @return
     */
    public String getMessage() {
        if (client != null) {
        	try {
        		BufferedReader in = null;
        		String fromServer = null;
        		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        		while ((in.readLine()) != null) {
		            fromServer = in.readLine();
		            System.out.println("Server: " + fromServer);
		        }
		        in.close();
//                BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
//                String answer = input.readLine();
                return fromServer;
        	} catch(IOException e) {
        		e.printStackTrace();
        	}
        }
        return "";
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
            	System.out.println("Failed send :( " + e.toString());
            }
        } else {
            // Not connected to the server
            System.err.println("Send message failed: Client not connected.");
        }
    }
}
