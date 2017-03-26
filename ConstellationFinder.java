import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConstellationFinder {
	// Hashmap of all constellations and their coordinate ranges
	// Coordinates are stored as {xmin, xmax, ymin, ymax}
	Map<String, Integer[]> constellations;
	
	@SuppressWarnings("serial")
	public ConstellationFinder() {
		constellations = new HashMap<String, Integer[]>() {{
			Integer[] coordinates = {221, 364, 258, 302};
			put("Some comet", coordinates);
		}};
	}
    
    /**
     * Look up if a constellation exists at a certain xy-coordinate
     * @param x - Mouse x-coordinate
     * @param y - Mouse y-coordinate
     */
    public String checkConstellation(int x, int y) {
    	//System.out.println(x + ", " + y);
    	for(Map.Entry<String, Integer[]> entry : constellations.entrySet()) {
    		String constellation = entry.getKey();
    		Integer[] bounds = entry.getValue();
    		if(x >= bounds[0] && x <= bounds[1] && y >= bounds[2] && y <= bounds[3]) {
    			// Within bounds, so constellation is a match
    			System.out.println("You clicked on " + constellation);
    			return constellation;
    		}
    	}
		return null;
    }
    
    /**
     * Write a string to a file
     * @param text
     * @param filePath
     */
    public void writeToFile(String text, String filePath) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(filePath));
			bw.write(text);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null) bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
}
