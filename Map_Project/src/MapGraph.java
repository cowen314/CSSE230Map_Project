import java.awt.geom.Point2D;
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
	private HashMap<String,Road> roads;
	
	// look-up table (hash map)
	private HashMap<String,MapNode> nodeTable;

	/**
	 * Constructs a MapGraph
	 * 
	 */
	public MapGraph() {
		this.roads = new HashMap<String,Road>();
		this.nodeTable = new HashMap<String,MapNode>();
		
	}
	
	/**
	 * Attempts to create a new road 
	 *
	 * @return true if a road is successfully added, false otherwise
	 */
	public boolean addRoad(String roadName, Point2D endpoint1, Point2D endpoint2){
		if(this.roads.containsKey(roadName))
			return false;
		
		//make an entry in the table for the new road
		this.roads.put(roadName,new Road(endpoint1,endpoint1));
		
		//intersections handling
		Set<String> roadNames = this.roads.keySet();
		for(String name : roadNames){
			if(name!=roadName){
				//check for intersection
				
			}
		}
		
		return false;
	}
	
	/**
	 * Adds a restaurant to the specified intersection location
	 * TODO: consider adding location parameters to this
	 *
	 * @param intersectionName
	 * @return true if the restaurant is successfully added, false otherwise
	 */
	public boolean addRestaurant(String intersectionName){
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