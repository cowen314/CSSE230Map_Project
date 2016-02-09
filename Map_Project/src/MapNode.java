import java.awt.image.BufferedImage;
import java.util.LinkedList;


/**
 * TODO Put here a description of what this class does.
 *
 * @author owencb.
 *         Created Feb 3, 2016.
 */
public abstract class MapNode {
	
	//we'll end up changing these most likely
	final private int HEIGHT = 10;
	final private int WIDTH = 10;
	
	/**
	 * lookupName must be unique. It will be used as a key in the lookup table.
	 * There may be a better way to do this.
	 */
	protected String lookupName;
	
	protected LinkedList<RoadSegment> neighborRoads;
	
	//this will be the icon displayed on the map
	protected BufferedImage icon;
	
	public MapNode(String name){
		this.lookupName = name;
		this.neighborRoads = new LinkedList<RoadSegment>();
	}
	
	public MapNode(String name,RoadSegment rs1,RoadSegment rs2,RoadSegment rs3,RoadSegment rs4){
		this.lookupName = name;
		this.neighborRoads = new LinkedList<RoadSegment>();
		this.neighborRoads.addLast(rs1);
		this.neighborRoads.addLast(rs2);
		this.neighborRoads.addLast(rs3);
		this.neighborRoads.addLast(rs4);
	}
	
	
}
