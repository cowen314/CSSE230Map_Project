import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;


/**
 * Restaurant class
 * The current plan is to tie each restaurant to an intersection.
 * The shortest path to that intersection is the shortest path to the restaurant.
 *
 * @author owencb.
 *         Created Feb 11, 2016.
 */
public class Restaurant {
	/**
	 * the default color of restaurant marker
	 */
	public static final Color color = Color.blue;
	
	/**
	 * default diameter of restaurant marker
	 */
	public static final double DIAMETER = 8;
	
	private String name;
	private Point2D location;
	private Intersection intersection;
	private ArrayList<Integer> reviews;
	private String streetAddress;
	
	/**
	 * Returns the value of the field called 'name'.
	 * @return Returns the name.
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Returns the value of the field called 'intersection'.
	 * @return Returns the intersection.
	 */
	public Intersection getIntersection() {
		return this.intersection;
	}
	/**
	 * Returns the value of the field called 'location'.
	 * @return Returns the location.
	 */
	public Point2D getLocation() {
		return this.location;
	}
	
	public Restaurant(String name, Point2D location, Intersection intersection, String streetAddress){
		this.name = name;
		this.location = location;
		this.intersection = intersection;
		this.streetAddress = streetAddress;
	}
}
