import java.util.HashMap;
import java.util.Map;

public class ConstellationFinder {
	// Hashmap of all constellations and their coordinate ranges
	// Coordinates are stored as {xmin, ymin, xmax, ymax}
	Map<String, Integer[]> constellations;
	
	public ConstellationFinder() {
		constellations = new HashMap<String, Integer[]>() {{
			Integer[] coordinates = {0, 0, 100, 100};
			put("Little Dipper", coordinates);
		}};
	}
    
    /**
     * Look up if a constellation exists at a certain xy-coordinate
     * @param x - Mouse x-coordinate
     * @param y - Mouse y-coordinate
     */
    public String checkConstellation(int x, int y) {
    	for(Map.Entry<String, Integer[]> entry : constellations.entrySet()) {
    		String constellation = entry.getKey();
    		Integer[] bounds = entry.getValue();
    		if(x >= bounds[0] && y >= bounds[1] && x <= bounds[2] && y <= bounds[3]) {
    			// Within bounds, so constellation is a match
    			System.out.println("You clicked on " + constellation);
    			return constellation;
    		}
    	}
		return null;
    }
}
