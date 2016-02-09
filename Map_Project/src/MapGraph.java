import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

/**
 * A data structure for storing map information
 * 
 * Please please please don't break when I do this
 * 
 * @author owencb. Created Feb 3, 2016.
 */
public class MapGraph {

	// roads
	private HashMap<String, ArrayList<Road>> roads;

	// look-up table (hash map)
	private HashMap<String, MapNode> nodeTable;

	/**
	 * Constructs a MapGraph
	 * 
	 */
	public MapGraph() {
		HashMap<String, Road> the = new HashMap<String, Road>();
		this.roads = new HashMap<String, ArrayList<Road>>();
		this.nodeTable = new HashMap<String, MapNode>();
	}

	/**
	 * Attempts to create a new road
	 * 
	 * @return true if a road is successfully added, false otherwise
	 */
	public boolean addRoad(String newRoadName, Point2D startpoint, Point2D endpoint) {
		if (this.roads.containsKey(newRoadName))
			return false;

		// make an entry in the table for the new road
		ArrayList<Road> stub = new ArrayList<Road>();
		stub.add(new Road(startpoint, startpoint));
		this.roads.put(newRoadName, stub);

		// intersections handling
		Point2D lastEndpoint = startpoint;
		Point2D intersect;
		Set<String> roadNames = this.roads.keySet();
		for (String name : roadNames) {
			if (name != newRoadName) {
				// check for intersection
				for (int i = 0; i < this.roads.get(name).size(); i++) {
					intersect = MapGraph.intersection(startpoint, endpoint,
							this.roads.get(name).get(i).ends[0], this.roads
									.get(name).get(i).ends[1]);
					if(intersect!=null){
						//add an intersection object to the intersections HashMap
						//add a new segment to this.roads.get(roadName) (new road)
						//split this.roads.get(name).get(i) (the existing road segment)
						//build the graph...
						//update lastEndpoint
					}
				}

			}
		}

		return false;
	}

	private static Point2D intersection(Point2D startpoint, Point2D endpoint, Point2D ends, Point2D ends2) {
		return null;
	}

	/**
	 * Adds a restaurant to the specified intersection location TODO: consider
	 * adding location parameters to this
	 * 
	 * @param intersectionName
	 * @return true if the restaurant is successfully added, false otherwise
	 */
	public boolean addRestaurant(String intersectionName) {
		return false;
	}

	/**
	 * 
	 * @return true if addition of node 'a' was successful, false otherwise
	 */
	public boolean addIntersection(String nodeName, MapNode a) {
		return false;
	}

	/**
	 * @return true if the graph contains node 'c', false otherwise
	 */
	public boolean containsNode(MapNode c) {
		return false;
	}

}