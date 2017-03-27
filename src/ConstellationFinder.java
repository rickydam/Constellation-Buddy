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
	private final String dataPath = "data.txt";
	// Hashmap of all constellations and their coordinate ranges
	// Coordinates are stored as {xmin, xmax, ymin, ymax, xrot, yrot}
	private Map<String, Integer[]> constellations;
	private int maxX, maxY, minX, minY;
	public ConstellationFinder(int MAX_X, int MAX_Y, int MIN_X, int MIN_Y) {
		this.maxX = MAX_X;
		this.maxY = MAX_Y;
		this.minX = MIN_X;
		this.minY = MIN_Y;
		writeToFile(0,0, dataPath);
		constellations = new HashMap<String, Integer[]>() {{
			Integer[][] allCoordinates = {
					{0, 500, 345, 705, -200, -57},
					{489, 845, 142, 313, -97, -141},
					{950, 1080, 105, 333, 0, -156},
					{1173, 1215, 335, 381, 47, -62},
					{1184, 1369, 617, 755, 50, 55},
					{1147, 1423, 54, 285, 42, -178},
					{1430, 1706, 188, 582, 101, -122},
					{1540, 1896, 53, 296, 124, -178}
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
    public int[] checkConstellation(int x, int y) {
    	//System.out.println(x + ", " + y);
    	for(Map.Entry<String, Integer[]> entry : constellations.entrySet()) {
    		String constellation = entry.getKey();
    		Integer[] bounds = entry.getValue();
    		if(x >= bounds[0] && x <= bounds[1] && y >= bounds[2] && y <= bounds[3]) {
    			// Within bounds, so constellation is a match
    			System.out.println("You clicked on " + constellation);
    			// hack in a scaling from -200 to 200
    			// (x - min)/(max - min) * (MAX_X + MIN_X) - MIN_X is in [MIN_X, MAX_X]
    			int new_x = (int)((bounds[4] + 200.0)/(124 + 200)*(maxX - minX) + minX);
    			int new_y = (int)((bounds[5] + 178.0)/(55 + 178) * (maxY - minY) + minY);
    			System.out.println("Going to " + new_x + "," + new_y + " which was originally " + bounds[4] + "," + bounds[5]);
    			writeToFile(new_x, new_y, dataPath);
    			int[] array = {new_x, new_y};
    			return array;
    		}
    	}
		return null;
    }
    
    /**
     * Write a string to a file
     * @param text
     * @param filePath
     */
    public void writeToFile(int x, int y, String filePath) {
		BufferedWriter bw = null;
		try {
			String text = x + "," + y;
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
