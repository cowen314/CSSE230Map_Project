import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

import math.geom2d.Vector2D;

/**
 * A data structure for storing map information
 * 
 * Please please please don't break when I do this
 * 
 * @author owencb. Created Feb 3, 2016.
 */
public class MapGraph {

	// roads
	private HashMap<String, RoadList> roadsTable;

	// look-up table (hash map)
	private HashMap<String, Intersection> intersectionTable;

	/**
	 * Constructs a MapGraph
	 * 
	 */
	public MapGraph() {
		this.roadsTable = new HashMap<String, RoadList>();
		this.intersectionTable = new HashMap<String, Intersection>();
	}

	/**
	 * Attempts to create a new road
	 * 
	 * @return true if a road is successfully added, false otherwise
	 */
	public boolean addRoad(String newRoadName, Point2D startpoint,
			Point2D endpoint) {
		if (this.roadsTable.containsKey(newRoadName)){
			System.out.println("Attempted to add duplicate road");
			return false;
		}
			

		// make an entry in the table for the new road
		RoadList newRoad = new RoadList();
		newRoad.changeName(newRoadName);

		// intersections handling
		Point2D lastEndpoint = startpoint;
		Intersection lastIntersection = null;
		RoadSegment trailingSegment = null;
		Point2D intersectLoc;
		Set<String> roadNames = this.roadsTable.keySet();
		/*
		 * iterate over all road segments, checking for points at which the
		 * roads cross over. At these crossover points, an intersection is
		 * added.
		 */
		for (String name : roadNames) {
			if (name != newRoadName) {
				// check for intersection
				for (int i = 0; i < this.roadsTable.get(name).size(); i++) {
					intersectLoc = MapGraph.intersection(startpoint, endpoint,
							this.roadsTable.get(name).get(i).ends[0],
							this.roadsTable.get(name).get(i).ends[1]);
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
								intersectionKeyName,intersectLoc);
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
						RoadSegment toSplit = this.roadsTable.get(name).get(i);
						RoadSegment newPortion = new RoadSegment(intersectLoc,
								toSplit.ends[1]);
						newPortion.intersects[0] = curIntersection;
						newPortion.intersects[1] = toSplit.intersects[1];
						toSplit.ends[1] = intersectLoc;
						toSplit.intersects[1] = curIntersection;
						// TODO: the nature of this insertion makes me think a
						// LinkedList would be better underpinning of RoadList
						this.roadsTable.get(name).add(i + 1, newPortion);

						// update info for the last intersection
						lastEndpoint = intersectLoc;
						lastIntersection = curIntersection;
						break;
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
		this.roadsTable.put(newRoadName, newRoad);
		return true;
	}

	/**
	 * implementation of intersection() is based on:
	 * http://stackoverflow.com/questions
	 * /563198/how-do-you-detect-where-two-line-segments-intersect
	 * @param startpoint_road1
	 * @param endpoint_road1
	 * @param startpoint_road2
	 * @param endpoint_road2
	 * @return the point of intersection if it exists, or null if the segments
	 *         do not exist
	 */
	public static Point2D intersection(Point2D startpoint_road1,
			Point2D endpoint_road1, Point2D startpoint_road2,
			Point2D endpoint_road2) {

		// create p,q,r,s
		Vector2D p = new Vector2D(startpoint_road1.getX(),
				startpoint_road1.getY());
		Vector2D q = new Vector2D(startpoint_road2.getX(),
				startpoint_road2.getY());
		Vector2D r = new Vector2D(endpoint_road1.getX() - p.getX(),
				endpoint_road1.getY() - p.getY());
		Vector2D s = new Vector2D(endpoint_road2.getX() - q.getX(),
				endpoint_road2.getY() - q.getY());

		// make decision based on the case
		double r_cross_s = r.cross(s);
		double q_minus_p_cross_r = (q.minus(p)).cross(r);
		if (r_cross_s == 0) {
			if (q_minus_p_cross_r == 0)
				System.out.println("Collinear segment found");
			return null;
		}
		double u = q_minus_p_cross_r / r_cross_s;
		double t = (q.minus(p)).cross(s)/(r.cross(s));
		if (u <= 1 && u >= 0 && t <= 1 && t >= 0) {
			Vector2D intersection = p.plus((r.times(t)));
			return new Point2D.Double(intersection.getX(), intersection.getY());
		}
		// in this case, the segments are not parallel but do not intersect
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
	 * TODO: consider getting rid of this method
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

	public Collection<Intersection> getIntersections() {
		return this.intersectionTable.values();
	}

	/**
	 * @return a collection of all roads
	 */
	public Collection<RoadList> getRoads() {
		return this.roadsTable.values();
	}

}