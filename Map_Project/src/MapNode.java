import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author owencb. Created Feb 3, 2016.
 */
public abstract class MapNode {

	// we'll end up changing these most likely
	final private int HEIGHT = 10;
	final private int WIDTH = 10;

	/**
	 * lookupName must be unique. It will be used as a key in the lookup table.
	 * There may be a better way to do this.
	 */
	protected String lookupName;

	/**
	 * Location of the node
	 */
	protected Point2D location;

	/**
	 * A linked list of all neighboring roads
	 * i.e. all roads leading to/away from this node
	 */
	//protected LinkedList<RoadSegment> neighborRoads;
	protected ArrayList<RoadSegment> neighborRoads;

	// this will be the icon displayed on the map
	/**
	 * the icon of the node (what the user sees)
	 */
	protected BufferedImage icon;

	/**
	 * partial constructor of MapNode
	 *
	 * @param name
	 */
	public MapNode(String name,Point2D location) {
		this.lookupName = name;
		this.neighborRoads = new ArrayList<RoadSegment>();
		this.location = location;
	}
	

	/**
	 * The complete constructor of MapNode
	 *
	 * @param name
	 * @param rs1
	 * @param rs2
	 * @param rs3
	 * @param rs4
	 * @param location
	 */
	public MapNode(String name, RoadSegment rs1, RoadSegment rs2,
			RoadSegment rs3, RoadSegment rs4, Point2D location) {
		this.lookupName = name;
		this.neighborRoads = new ArrayList<RoadSegment>();
		this.neighborRoads.add(rs1);
		this.neighborRoads.add(rs2);
		this.neighborRoads.add(rs3);
		this.neighborRoads.add(rs4);
		this.location = location;
	}

	/**
	 * @return the location of this node
	 */
	public Point2D getLocation(){
		return this.location;
	}
		
	
}
