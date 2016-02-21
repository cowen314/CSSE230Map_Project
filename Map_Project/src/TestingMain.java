import java.awt.geom.Point2D;
import java.util.LinkedList;

import javax.swing.JComponent;
import javax.swing.JFrame;

import math.geom2d.Vector2D;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author owencb. Created Feb 9, 2016.
 */
public class TestingMain {

	/**
	 * A main for testing purposes
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		MapGraph mapGraph = new MapGraph();
		MapComponent mapComp = new MapComponent(mapGraph);
		mapComp.setSize(500,500);
		mapComp.setVisible(true);
		frame.add(mapComp);
//		mapGraph.addRoad("Test drive", new Point2D.Double(50, 0),
//				new Point2D.Double(50, 400));
//		mapGraph.addRoad("Brick lane", new Point2D.Double(0, 100),
//				new Point2D.Double(400, 100));
//		mapGraph.addRoad("slanted road", new Point2D.Double(300, 0),
//				new Point2D.Double(0, 400));
//		mapGraph.addRoad("another road", new Point2D.Double(150, 50),
//				new Point2D.Double(150, 400));
		// be sure to test three way intersection
		
		mapGraph.addRoad("Road 1", new Point2D.Double(10, 10), new Point2D.Double(99, 10),35);
		mapGraph.addRoad("Road 2", new Point2D.Double(10, 10), new Point2D.Double(10, 99),35);
		mapGraph.addRoad("Road 3", new Point2D.Double(99, 99), new Point2D.Double(10, 99),35);
		mapGraph.addRoad("Road 4", new Point2D.Double(99, 99), new Point2D.Double(99, 10),35);
		mapGraph.addRoad("Slanted road", new Point2D.Double(20, 0),
				new Point2D.Double(80, 100),35);
		LinkedList<Intersection> shortestPath = mapGraph.shortestPath_distance(
				mapGraph.getIntersectionByName("Road 1 + Road 2"),
				mapGraph.getIntersectionByName("Road 3 + Slanted road"));
		
		
		//mapGraph.addRestaurant("Testraunt",new Point2D.Double(56,110),"532", "$");
		mapComp.repaint();
		
	}

}
