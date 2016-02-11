import java.awt.geom.Point2D;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author owencb. Created Feb 8, 2016.
 */
public class Intersection extends MapNode {

	
	/**
	 * partial intersection constructor
	 * 
	 * @param name
	 * @param location 
	 */
	public Intersection(String name, Point2D location) {
		super(name,location);
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
	}

}
