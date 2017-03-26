import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Determines what constellations the user is trying to see
 * and how to point the telescope at that constellation
 */
public class ConstellationFinder {
	// Hashmap of all constellations and their coordinate ranges
	// Coordinates are stored as {xmin, xmax, ymin, ymax, xrot, yrot}
	Map<String, Integer[]> constellations;
	
	public ConstellationFinder() {
		constellations = new HashMap<String, Integer[]>() {{
			Integer[][] allCoordinates = {
					{0, 500, 345, 705, 20, 20},
					{489, 845, 142, 313, 20, 20},
					{950, 1080, 105, 333, 20, 20},
					{1173, 1215, 335, 381, 20, 20},
					{1184, 1369, 617, 755, 20, 20},
					{1147, 1423, 54, 285, 20, 20},
					{1430, 1706, 188, 582, 20, 20},
					{1540, 1896, 53, 296, 20, 20}
			};
			Integer[] coordinates = allCoordinates[0];
			put("Virgo", coordinates);
			coordinates = allCoordinates[1];
			put("Leo", coordinates);
			coordinates = allCoordinates[2];
			put("Cancer", coordinates);
			coordinates = allCoordinates[3];
			put("Canis Minor", coordinates);
			coordinates = allCoordinates[4];
			put("Canis Major", coordinates);
			coordinates = allCoordinates[5];
			put("Gemini", coordinates);
			coordinates = allCoordinates[6];
			put("Orion", coordinates);
			coordinates = allCoordinates[7];
			put("Taurus", coordinates);
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
    			writeToFile(bounds[4] + " " + bounds[5], "data.txt");
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
