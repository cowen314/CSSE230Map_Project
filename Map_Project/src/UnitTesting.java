import static org.junit.Assert.*;

import java.awt.geom.Point2D;

import org.junit.Test;



/**
 * Unit testing class
 *
 * @author owencb.
 *         Created Feb 10, 2016.
 */
public class UnitTesting {
	@Test
	
	public void testIntersection(){
		Point2D origin = new Point2D.Double(0,0);
		Point2D p02 = new Point2D.Double(0,2);
		Point2D p20 = new Point2D.Double(2,0);
		Point2D p11 = new Point2D.Double(1,1);
		Point2D p1_1 = new Point2D.Double(1,-1);
		assertEquals(MapGraph.intersection(origin,p02,p11,p1_1),null);
		assertEquals(MapGraph.intersection(origin,p20,p11,p1_1),new Point2D.Double(1,0));
	}
}
