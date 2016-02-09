import java.awt.geom.Point2D;
import java.util.HashMap;
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
	private HashMap<String, RoadList<RoadSegment>> roads;

	// look-up table (hash map)
	private HashMap<String, MapNode> nodeTable;

	/**
	 * Constructs a MapGraph
	 * 
	 */
	public MapGraph() {
		HashMap<String, RoadSegment> the = new HashMap<String, RoadSegment>();
		this.roads = new HashMap<String, RoadList<RoadSegment>>();
		this.nodeTable = new HashMap<String, MapNode>();
	}

	/**
	 * Attempts to create a new road
	 * 
	 * @return true if a road is successfully added, false otherwise
	 */
	public boolean addRoad(String newRoadName, Point2D startpoint,
			Point2D endpoint) {
		if (this.roads.containsKey(newRoadName))
			return false;

		// make an entry in the table for the new road
		RoadList<RoadSegment> stub = new RoadList<RoadSegment>();
		stub.changeName(newRoadName);
		stub.add(new RoadSegment(startpoint, startpoint));
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
					if (intersect != null) {
						// add an intersection object to the intersections
						// HashMap
						// //make sure to point the intersection to the correct
						// roads

						// use road names to define keys for nodes
						String intersectionKeyName = name
								.compareTo(newRoadName) > 0 ? (name + newRoadName)
								: (newRoadName + name);
						this.nodeTable.put(intersectionKeyName,new Intersection(intersectionKeyName));

						// add a new segment to this.roads.get(newRoadName) (new
						// road)
						RoadSegment new_trailing = new RoadSegment(
								lastEndpoint, intersect);
						this.roads.get(newRoadName).add(new_trailing);
						// split this.roads.get(name).get(i) (the existing road
						// segment)

						// update lastEndpoint
						lastEndpoint = intersect;
					}
				}
			}
		}

		return false;
	}

	private static Point2D intersection(Point2D startpoint, Point2D endpoint,
			Point2D ends, Point2D ends2) {
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