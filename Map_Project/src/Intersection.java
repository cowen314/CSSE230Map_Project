import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author owencb. Created Feb 8, 2016.
 */
public class Intersection extends MapNode {
	/**
	 * the default color of intersections
	 */
	public static final Color color = Color.BLACK;
	
	/**
	 * default diameter of intersections
	 */
	public static final double DIAMETER = 5;
	private boolean roadEndMarker; //set true if this is the end of a road
	private ArrayList<Restaurant> restaurants;
	
	/**
	 * partial intersection constructor
	 * 
	 * @param name
	 * @param location 
	 */
	public Intersection(String name, Point2D location) {
		super(name,location);
		this.restaurants = new ArrayList<Restaurant>();
		this.roadEndMarker = false;
	}

	/**
	 * Complete constructor of intersection
	 *
	 * @param name
	 * @param rs1
	 * @param rs2
	 * @param rs3
	 * @param rs4
	 * @param location
	 */
	public Intersection(String name, RoadSegment rs1, RoadSegment rs2,
			RoadSegment rs3, RoadSegment rs4, Point2D location) {
		super(name,rs1,rs2,rs3,rs4,location);
		this.restaurants = new ArrayList<Restaurant>();
	}
	
	/**
	 * add a restaurant to the list of restaurants that are nearest to this intersection
	 *
	 * @param restaurant
	 */
	public void addRestaurant(Restaurant restaurant){
		this.restaurants.add(restaurant);
	}
	
	@Override
	public String toString() {
		return this.lookupName;
	}
	
	public void setRoadEndMarker(){
		this.roadEndMarker = true;
	}
	
	public boolean endMarker(){
		return this.roadEndMarker;
	}

}
