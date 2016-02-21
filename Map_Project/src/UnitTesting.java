import static org.junit.Assert.*;

import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.LinkedList;

import org.junit.Test;

/**
 * Unit testing class
 * 
 * @author owencb. Created Feb 10, 2016.
 */
public class UnitTesting {
	@Test
	public void testIntersection() {
		Point2D origin = new Point2D.Double(0, 0);
		Point2D p02 = new Point2D.Double(0, 2);
		Point2D p20 = new Point2D.Double(2, 0);
		Point2D p11 = new Point2D.Double(1, 1);
		Point2D p1_1 = new Point2D.Double(1, -1);
		assertEquals(MapGraph.intersection(origin, p02, p11, p1_1), null);
		assertEquals(MapGraph.intersection(origin, p20, p11, p1_1),
				new Point2D.Double(1, 0));
	}
	
	@Test
	public void testaddRoad_simple(){
		MapGraph mapGraph = new MapGraph();
		mapGraph.addRoad("Road 1", new Point2D.Double(5, 0), new Point2D.Double(5, 10));
		mapGraph.addRoad("Road 2", new Point2D.Double(0, 5), new Point2D.Double(10, 5));
		mapGraph.addRoad("Road 3", new Point2D.Double(0, 7), new Point2D.Double(10, 7));
	}
	
	@Test
	public void testAStar_distance_tictac() {
		MapGraph mapGraph = new MapGraph();
		mapGraph.addRoad("Road 1", new Point2D.Double(0, 20), new Point2D.Double(100, 20));
		mapGraph.addRoad("Road 2", new Point2D.Double(20, 0), new Point2D.Double(20, 100));
		mapGraph.addRoad("Road 3", new Point2D.Double(0, 80), new Point2D.Double(100, 80));
		LinkedList<Intersection> shortestPath1 = mapGraph.shortestPath_distance(
				mapGraph.getIntersectionByName("Road 1 + Road 2"),
				mapGraph.getIntersectionByName("Road 2 + Road 3"));
		if(shortestPath1==null)
			fail();
		Iterator<Intersection> test = shortestPath1.iterator();
		assertEquals("Road 1 + Road 2",test.next().toString());
		assertEquals("Road 2 + Road 3",test.next().toString());
		assertFalse(test.hasNext());
		
		mapGraph.addRoad("Road 4", new Point2D.Double(80, 0), new Point2D.Double(80, 100));
		
		LinkedList<Intersection> shortestPath1b = mapGraph.shortestPath_distance(
				mapGraph.getIntersectionByName("Road 1 + Road 2"),
				mapGraph.getIntersectionByName("Road 3 + Road 4"));
		if(shortestPath1b==null)
			fail();
		System.out.println(shortestPath1b);
//		test = shortestPath1b.iterator();
//		assertEquals("Road 1 + Road 2",test.next().toString());
//		assertEquals("Road 2 + Road 3",test.next().toString());
//		assertFalse(test.hasNext());
	}
	
	@Test
	public void testAStar_distance_slantedRoad() {
		MapGraph mapGraph = new MapGraph();
		mapGraph.addRoad("Road 1", new Point2D.Double(0, 20), new Point2D.Double(100, 20));
		mapGraph.addRoad("Road 2", new Point2D.Double(20, 0), new Point2D.Double(20, 100));
		mapGraph.addRoad("Road 3", new Point2D.Double(0, 80), new Point2D.Double(100, 80));
		mapGraph.addRoad("Road 4", new Point2D.Double(80, 0), new Point2D.Double(80, 100));
		mapGraph.addRoad("Slanted road", new Point2D.Double(25, 15),
				new Point2D.Double(75, 85));
		
		GraphVisualizer gv = new GraphVisualizer(mapGraph);
		LinkedList<Intersection> shortestPath1 = mapGraph.shortestPath_distance(
				mapGraph.getIntersectionByName("Road 1 + Road 2"),
				mapGraph.getIntersectionByName("Road 1 + Slanted road"));
		if(shortestPath1==null)
			fail();
		Iterator<Intersection> test = shortestPath1.iterator();
		assertEquals("Road 1 + Road 2",test.next().toString());
		assertEquals("Road 1 + Slanted road",test.next().toString());
		assertFalse(test.hasNext());
		
		LinkedList<Intersection> shortestPath2 = mapGraph.shortestPath_distance(
				mapGraph.getIntersectionByName("Road 1 + Road 2"),
				mapGraph.getIntersectionByName("Road 3 + Road 4"));
		if(shortestPath2==null)
			fail();
		test = shortestPath2.iterator();
		assertEquals("Road 1 + Road 2",test.next().toString());
		assertEquals("Road 1 + Slanted road",test.next().toString());
		assertEquals("Road 3 + Slanted road",test.next().toString());
		assertEquals("Road 3 + Road 4",test.next().toString());
		assertFalse(test.hasNext());
		
	}
	
	@Test
	public void testAStar_distance() {
		// this will also test the potential road ending problem
		MapGraph mapGraph = new MapGraph();
		mapGraph.addRoad("Road 1", new Point2D.Double(10, 10), new Point2D.Double(99, 10));
		mapGraph.addRoad("Road 2", new Point2D.Double(10, 10), new Point2D.Double(10, 99));
		mapGraph.addRoad("Road 3", new Point2D.Double(99, 99), new Point2D.Double(10, 99));
		mapGraph.addRoad("Road 4", new Point2D.Double(99, 99), new Point2D.Double(99, 10));
		mapGraph.addRoad("Slanted road", new Point2D.Double(20, 0),
				new Point2D.Double(80, 100));
		LinkedList<Intersection> shortestPath = mapGraph.shortestPath_distance(
				mapGraph.getIntersectionByName("Road 1 + Road 2"),
				mapGraph.getIntersectionByName("Road 3 + Road 4"));
		if(shortestPath==null)
			fail();
		Iterator<Intersection> test = shortestPath.iterator();
		assertEquals("Road 1 + Road 2",test.next().toString());
		assertEquals("Road 1 + Slanted road",test.next().toString());
		assertEquals("Road 3 + Slanted road",test.next().toString());
		assertEquals("Road 3 + Road 4",test.next().toString());
	}
}
