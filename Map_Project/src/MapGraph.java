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
	private HashMap<String, Intersection> intersectionTable;

	/**
	 * Constructs a MapGraph
	 * 
	 */
	public MapGraph() {
		this.roads = new HashMap<String, RoadList<RoadSegment>>();
		this.intersectionTable = new HashMap<String, Intersection>();
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
		RoadList<RoadSegment> newRoad = new RoadList<RoadSegment>();
		newRoad.changeName(newRoadName);

		// intersections handling
		Point2D lastEndpoint = startpoint;
		Intersection lastIntersection = null;
		RoadSegment trailingSegment = null;
		Point2D intersectLoc;
		Set<String> roadNames = this.roads.keySet();
		/*
		 * iterate over all road segments, checking for points at which the
		 * roads cross over. At these crossover points, an intersection is
		 * added.
		 */
		for (String name : roadNames) {
			if (name != newRoadName) {
				// check for intersection
				for (int i = 0; i < this.roads.get(name).size(); i++) {
					intersectLoc = MapGraph.intersection(startpoint, endpoint,
							this.roads.get(name).get(i).ends[0], this.roads
									.get(name).get(i).ends[1]);
					if (intersectLoc != null) {
						// add an intersection object to the intersections
						// HashMap
						// //make sure to point the intersection to the correct
						// roads

						// use road names to define keys for nodes
						String intersectionKeyName = name
								.compareTo(newRoadName) > 0 ? (name + newRoadName)
								: (newRoadName + name);
						Intersection curIntersection = new Intersection(
								intersectionKeyName);
						this.intersectionTable.put(intersectionKeyName,
								curIntersection);

						// add a new segment to newRoad
						trailingSegment = new RoadSegment(lastEndpoint,
								intersectLoc);
						newRoad.add(trailingSegment);
						trailingSegment.intersects[0] = lastIntersection;
						trailingSegment.intersects[1] = curIntersection;

						// split this.roads.get(name).get(i) (the existing road
						// segment)
						RoadSegment toSplit = this.roads.get(name).get(i);
						RoadSegment newPortion = new RoadSegment(intersectLoc,
								toSplit.ends[1]);
						newPortion.intersects[0] = curIntersection;
						newPortion.intersects[1] = toSplit.intersects[1];
						toSplit.ends[1] = intersectLoc;
						toSplit.intersects[1] = curIntersection;
						// TODO: the nature of this insertion makes me think a
						// LinkedList would be better underpinning of RoadList
						this.roads.get(name).add(i + 1, newPortion);

						// update info for the last intersection
						lastEndpoint = intersectLoc;
						lastIntersection = curIntersection;
					}
				}
			}
		}

		// make the last road segment
		RoadSegment lastSegment = new RoadSegment(lastEndpoint, endpoint);
		lastSegment.intersects[0] = lastIntersection;
		// TODO: making the end intersection null might cause problems in the
		// case of 3-ways
		lastSegment.intersects[1] = null;
		newRoad.add(lastSegment);

		// and finally, put the new road in the roads HashMap
		this.roads.put(newRoadName, newRoad);
		return true;
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