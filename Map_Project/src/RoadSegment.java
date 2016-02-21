import java.awt.geom.Point2D;

/**
 * Class for road objects
 * 
 * @author owencb. Created Feb 8, 2016.
 */
public class RoadSegment {

	// TODO: give the ability to set the speed limit
	private int speedLimit;
	private final static int DEFAULT_SPEEDLIMIT = 35;
	// TODO: containingRoadName is used for debugging purposes, can be removed
	// later
	private String containingRoadName;

	/**
	 * the endpoint locations of the road segment
	 */
	Point2D ends[];

	/**
	 * the MapNode objects on either end of the road segment
	 */
	Intersection intersects[];

	public RoadSegment(Point2D startpoint, Point2D endpoint, String roadName) {
		this.ends = new Point2D[2];
		this.ends[0] = startpoint;
		this.ends[1] = endpoint;
		this.intersects = new Intersection[2];
		this.intersects[0] = null;
		this.intersects[1] = null;
		this.speedLimit = RoadSegment.DEFAULT_SPEEDLIMIT;
		this.containingRoadName = roadName;
	}

	/**
	 * Returns the value of the field called 'speedLimit'.
	 * 
	 * @return Returns the speedLimit.
	 */
	public int getSpeedLimit() {
		return speedLimit;
	}

	/**
	 * Sets the field called 'speedLimit' to the given value.
	 * 
	 * @param speedLimit
	 *            The speedLimit to set.
	 */
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
	}

	/**
	 * @return the straight line distance from the intersections that mark the
	 *         ends of this road segment
	 */
	public double length() {
		// return this.ends[0].distance(this.ends[1]);
		return this.intersects[0].getLocation().distance(
				this.intersects[1].location);
	}

	@Override
	public String toString() {
		return this.containingRoadName;
	}

	public String getContainingRoadName() {
		return this.containingRoadName;
	}

}
