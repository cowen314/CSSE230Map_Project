import java.awt.geom.Point2D;


/**
 * Class for road objects
 *
 * @author owencb.
 *         Created Feb 8, 2016.
 */
public class Road {
	Point2D[] ends;
	public Road(Point2D startpoint, Point2D endpoint){
		this.ends[0] = startpoint;
		this.ends[1] = endpoint;
	}

}
