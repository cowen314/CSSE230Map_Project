import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

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

	// intersections
	private HashMap<String, Intersection> intersectionTable;

	// restaurants
	private HashMap<String, Restaurant> restaurantsTable;

	private final int MAXIMUM_SPEED_LIMIT = 70;

	/**
	 * Constructs a MapGraph
	 * 
	 */
	public MapGraph() {
		this.roadsTable = new HashMap<String, RoadList>();
		this.intersectionTable = new HashMap<String, Intersection>();
		this.restaurantsTable = new HashMap<String, Restaurant>();
	}

	/**
	 * Attempts to create a new road
	 * 
	 * @return true if a road is successfully added, false otherwise
	 */
	public boolean addRoad(String newRoadName, Point2D startpoint,
			Point2D endpoint, int speedLimit) {
		if (this.roadsTable.containsKey(newRoadName)) {
			System.out.println("Attempted to add duplicate road");
			return false;
		}

		// make an entry in the table for the new road
		RoadList newRoad = new RoadList(newRoadName, startpoint, endpoint, speedLimit);

		// intersections handling
		Point2D lastEndpoint = startpoint;
		Point2D intersectLoc;
		Set<String> roadNames = this.roadsTable.keySet();
		TreeSet<IntersectionNode> intersections = new TreeSet<IntersectionNode>();

		// Iterate over all roads
		for (String name : roadNames) {
			if (name != newRoadName) {
				// iterate over all road segments
				for (int i = 0; i < this.roadsTable.get(name).size(); i++) {
					intersectLoc = MapGraph
							.intersection(
									startpoint,
									endpoint,
									this.roadsTable.get(name).get(i).intersects[0].location,
									this.roadsTable.get(name).get(i).intersects[1].location);
					if (intersectLoc != null) {
						// use road names to define keys for nodes
						// THIS IS WHERE INTERSECTIONS ARE NAMED
						String intersectionKeyName = name
								.compareTo(newRoadName) < 0 ? (name + " + " + newRoadName)
								: (newRoadName + " + " + name);
						Intersection curIntersection = new Intersection(
								intersectionKeyName, intersectLoc);
						this.intersectionTable.put(intersectionKeyName,
								curIntersection);
						// add to intersections tree so that it can be processed
						// in order
						intersections.add(new IntersectionNode(curIntersection,
								this.roadsTable.get(name).get(i), startpoint));
						break;
					}
				}
			}
		}

		Intersection lastIntersection = new Intersection("Start of "
				+ newRoadName, startpoint);
		lastIntersection.setRoadEndMarker();
		this.intersectionTable.put("Start of " + newRoadName, lastIntersection);

		Iterator<IntersectionNode> it = intersections.iterator();
		while (it.hasNext()) {
			IntersectionNode in = it.next();
			// create the trailing segment
			RoadSegment trailingRoad = new RoadSegment(
					lastIntersection.location, in.i.location, newRoadName, newRoad.getSpeedLimit());
			newRoad.add(trailingRoad);

			// here are the connections and pointer deletion (four actions in
			// total):
			lastIntersection.neighborRoads.add(trailingRoad);
			trailingRoad.intersects[0] = lastIntersection;
			trailingRoad.intersects[1] = in.i;
			in.i.neighborRoads.add(trailingRoad);

			// handle cross roads
			// completely tie in RS2
			RoadSegment RS2 = new RoadSegment(in.i.location,
					in.RS.intersects[0].location, in.RS.getContainingRoadName(), in.RS.getSpeedLimit());
			this.roadsTable.get(RS2.getContainingRoadName()).add(RS2);
			RS2.intersects[1] = in.RS.intersects[1];
			RS2.intersects[0] = in.i;
			in.i.neighborRoads.add(RS2);
			in.RS.intersects[1].neighborRoads.add(RS2);
			in.RS.intersects[1].neighborRoads.remove(in.RS);
			in.RS.intersects[1] = in.i;
			in.i.neighborRoads.add(in.RS);

			// this.roadsTable.get(RS2.getContainingRoadName()).add(RS2);
			// RS2.intersects[0] = in.i;
			// RS2.intersects[1] = in.RS.intersects[1];
			// in.RS.intersects[0] = lastIntersection;
			// in.RS.intersects[1] = in.i;
			// in.i.neighborRoads.addLast(in.RS);
			// in.i.neighborRoads.addLast(RS2);
			// //Il.neighborRoads.addLast(RS2);

			lastIntersection = in.i;
			lastEndpoint = in.i.location;
		}

		// // make the last road segment
		RoadSegment lastSegment = new RoadSegment(lastEndpoint, endpoint,
				newRoadName, newRoad.getSpeedLimit());
		lastSegment.intersects[0] = lastIntersection;
		lastIntersection.neighborRoads.add(lastSegment);
		// TODO: making the end intersection null might cause problems in the
		// case of 3-ways
		lastIntersection = new Intersection("End of " + newRoadName, endpoint);
		lastIntersection.setRoadEndMarker();
		this.intersectionTable.put(lastIntersection.lookupName,
				lastIntersection);
		lastSegment.intersects[1] = lastIntersection;
		newRoad.add(lastSegment);

		// and finally, put the new road in the roads HashMap
		this.roadsTable.put(newRoadName, newRoad);
		return true;
	}

	private class IntersectionNode implements Comparable<IntersectionNode> {

		Intersection i;
		RoadSegment RS;
		Point2D refPoint;

		public IntersectionNode(Intersection i, RoadSegment RS, Point2D refPoint) {
			this.i = i;
			this.RS = RS;
			this.refPoint = refPoint;
		}

		@Override
		public int compareTo(IntersectionNode arg0) {
			double distThis = this.i.location.distance(this.refPoint);
			double distThat = arg0.i.location.distance(this.refPoint);
			if (distThis < distThat)
				return 1;
			if (distThis > distThat)
				return -1;
			return 0;
		}

	}

	/**
	 * implementation of intersection() is based on:
	 * http://stackoverflow.com/questions
	 * /563198/how-do-you-detect-where-two-line-segments-intersect
	 * 
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
		double t = (q.minus(p)).cross(s) / (r.cross(s));
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
	 * @param restaurant
	 * 
	 * @param intersectionName
	 * @return true if the restaurant is successfully added, false otherwise
	 */
	public boolean addRestaurant(String name, Point2D location,
			String streetAddress, String price, String type, Double rating) {
		// check the input
		if (this.restaurantsTable.containsKey(name))
			return false;
		Intersection closestIntersection = calculateClosestIntersection(location);
		if (closestIntersection == null)
			return false;
		Restaurant newRes = new Restaurant(name, location, closestIntersection,
				streetAddress, price, type, rating);
		// add the restaurant to the restaurant table, and the specified
		// intersection
		this.restaurantsTable.put(name, newRes);
		closestIntersection.addRestaurant(newRes);

		return true;
	}

	/**
	 * calculates the closest intersection to "location"
	 * 
	 * @param location
	 * @return the closest intersection
	 */
	public Intersection calculateClosestIntersection(Point2D location) {
		Collection<Intersection> allIntersections = this.intersectionTable
				.values();
		double minDistance = -1;
		double distance;
		Intersection closestIntersection = null;
		for (Intersection intersection : allIntersections) {
			distance = intersection.getLocation().distance(location);
			if (minDistance == -1 || distance < minDistance) {
				minDistance = distance;
				closestIntersection = intersection;
			}
		}
		return closestIntersection;
	}

	/**
	 * @return a collection of all intersections
	 */
	public Collection<Intersection> getIntersections() {
		return this.intersectionTable.values();
	}

	/**
	 * @return a collection of all roads
	 */
	public Collection<RoadList> getRoads() {
		return this.roadsTable.values();
	}

	/**
	 * 
	 * @return a collection of all restaurants
	 */
	public Collection<Restaurant> getRestaurants() {
		return this.restaurantsTable.values();
	}

	/**
	 * @param intersectionName
	 * @return the intersection matching the name of the input intersection
	 *         names are formatted as follows: "A + B" where A is
	 *         alphanumerically the first intersection, and B is second
	 *         intersection alphanumerically
	 */
	public Intersection getIntersectionByName(String intersectionName) {
		if (this.intersectionTable.containsKey(intersectionName))
			return this.intersectionTable.get(intersectionName);
		System.out.println("Warning: Tried to find non-existent intersection");
		return null;
	}

	/**
	 * A* used to find shortest path
	 * 
	 * @return a LinkedList containing the road segments of the path that gives
	 *         the shortest time TODO: implement this
	 */
	public LinkedList<Intersection> shortestPath_distance(Intersection startPoint,
			Intersection endPoint) {
		// Initialize stuff
		PriorityQueue<AStarElement> openSet = new PriorityQueue<AStarElement>();
		AStarElement startElement = new AStarElement(startPoint);
		startElement.f = startPoint.location.distance(endPoint.location);
		openSet.add(startElement);

		// main loop
		while (!openSet.isEmpty()) {
			AStarElement X = openSet.poll();
			if (X.element == endPoint)
				return reconstructPath(X);
			// closedSet.add(X);
			// openSetMap.remove(X.element);
			Intersection i;
			for (RoadSegment roadSegment : X.element.neighborRoads) {
				// we don't know which of the intersections in intersects[] that
				// X.element is
				// assign neighbor to the end that isn't X.element
				if (roadSegment.intersects[0] == X.element)
					i = roadSegment.intersects[1];
				else
					i = roadSegment.intersects[0];

				if (i.endMarker() == true)
					continue;

				AStarElement temp = new AStarElement(i);
				temp.g = X.g + roadSegment.length();
				temp.f = temp.g + i.location.distance(endPoint.location);
				temp.parent = X;
				openSet.add(temp);

			}
		}
		// if we reach this point, no route exists, and null is returned
		return null;
	}

	/**
	 * Reconstructs path using parent pointers
	 * 
	 * @param current
	 * @param Came_From
	 * 
	 * @return the shortest path
	 */
	private LinkedList<Intersection> reconstructPath(AStarElement X) {
		LinkedList<Intersection> path = new LinkedList<Intersection>();
		path.add(X.element);
		while (X.parent != null) {
			path.addFirst(X.parent.element);
			X = X.parent;
		}
		return path;
	}

	/**
	 * A* used to find shortest path.
	 * 
	 * @return a LinkedList containing the road segments of the path that gives
	 *         the shortest distance
	 */
	public LinkedList<Intersection> shortestPath_time(
			Intersection startPoint, Intersection endPoint) {
		// Initialize stuff
		PriorityQueue<AStarElement> openSet = new PriorityQueue<AStarElement>();
		AStarElement startElement = new AStarElement(startPoint);
		startElement.f = startPoint.location.distance(endPoint.location)
				/ ((double) EaglesBeard.getMaxSpeedLimit());
		openSet.add(startElement);

		// main loop
		while (!openSet.isEmpty()) {
			AStarElement X = openSet.poll();
			if (X.element == endPoint)
				return reconstructPath(X);
			// closedSet.add(X);
			// openSetMap.remove(X.element);
			Intersection i;
			for (RoadSegment roadSegment : X.element.neighborRoads) {
				// we don't know which of the intersections in intersects[] that
				// X.element is
				// assign neighbor to the end that isn't X.element
				if (roadSegment.intersects[0] == X.element)
					i = roadSegment.intersects[1];
				else
					i = roadSegment.intersects[0];

				if (i.endMarker() == true)
					continue;

				AStarElement temp = new AStarElement(i);
				temp.g = X.g + roadSegment.length()
						/ ((double) roadSegment.getSpeedLimit());
				temp.f = temp.g + i.location.distance(endPoint.location)
						/ ((double) EaglesBeard.getMaxSpeedLimit());
				temp.parent = X;
				openSet.add(temp);
			}
		}
		// if we reach this point, no route exists, and null is returned
		return null;
	}

	private class AStarElement implements Comparable<AStarElement> {
		@SuppressWarnings("unused")
		private double f;
		@SuppressWarnings("unused")
		private Intersection element;

		private AStarElement parent;

		private double g;

		/**
		 * Default constructor
		 * 
		 * @param element
		 * 
		 */
		@SuppressWarnings("unused")
		public AStarElement(Intersection element) {
			this.f = 0;
			this.g = 0;
			this.element = element;
			this.parent = null;
		}

		@Override
		public int compareTo(AStarElement arg0) {
			if (this.f > arg0.f)
				return 1;
			if (this.f < arg0.f)
				return -1;
			return 0;
		}
	}

}