import java.awt.geom.Point2D;


/**
 * Class for road objects
 *
 * @author owencb.
 *         Created Feb 8, 2016.
 */
public class RoadSegment {
	
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
	}

}
