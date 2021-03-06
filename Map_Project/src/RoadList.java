import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * RoadList is a list of RoadSegments. Collectively, the RoadSegments of a
 * RoadList make up the entire road.
 * 
 * @author owencb. Created Feb 9, 2016.
 */
public class RoadList extends ArrayList<RoadSegment> {
	private String roadName;
	private Point2D start;
	private Point2D end;
	private int speedLimit;
	
	/**
	 * the default color of roads
	 */
	public static final Color color = Color.black;

	public RoadList(String name, Point2D start, Point2D end, int speedLimit){
		this.roadName = name;
		this.start = start;
		this.end = end;
		this.speedLimit = speedLimit;
	}
	
	
	public void changeName(String name) {
		this.roadName = name;
	}

	public String getName() {
		return this.roadName;
	}

	public Point2D[] getEndPoints() {
		Point2D[] out = { this.start, this.end };
		return out;
	}
	
	@Override
	public String toString() {
		return this.roadName;
	}


	/**
	 * Returns the value of the field called 'speedLimit'.
	 * @return Returns the speedLimit.
	 */
	public int getSpeedLimit() {
		return this.speedLimit;
	}

}
