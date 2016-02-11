import java.awt.geom.Point2D;


/**
 * Class for road objects
 *
 * @author owencb.
 *         Created Feb 8, 2016.
 */
public class RoadSegment {
	
	//TODO: give the ability to set the speed limit
	private int speedLimit;
	private final int DEFAULT_SPEEDLIMIT = 35;
	
	/**
	 * the endpoint locations of the road segment
	 */
	Point2D ends[];
	
	/**
	 * the MapNode objects on either end of the road segment
	 */
	MapNode intersects[];
	
	
	public RoadSegment(Point2D startpoint, Point2D endpoint){
		this.ends = new Point2D[2];
		this.ends[0] = startpoint;
		this.ends[1] = endpoint;
		this.intersects = new MapNode[2];
		this.intersects[0] = null;
		this.intersects[1] = null;
		this.speedLimit = this.DEFAULT_SPEEDLIMIT;
	}


	/**
	 * Returns the value of the field called 'speedLimit'.
	 * @return Returns the speedLimit.
	 */
	public int getSpeedLimit() {
		return speedLimit;
	}


	/**
	 * Sets the field called 'speedLimit' to the given value.
	 * @param speedLimit The speedLimit to set.
	 */
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
	}

}
