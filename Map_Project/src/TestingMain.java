import java.awt.geom.Point2D;

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
		mapGraph.addRoad("Test drive", new Point2D.Double(50, 0),
				new Point2D.Double(50, 400));
		mapGraph.addRoad("Brick lane", new Point2D.Double(0, 100),
				new Point2D.Double(400, 100));
		mapGraph.addRoad("slanted road", new Point2D.Double(300, 0),
				new Point2D.Double(0, 400));
		// be sure to test three way intersection
		
		mapGraph.addRestaurant("Testraunt",new Point2D.Double(56,110),"532", "$");
		mapComp.repaint();
	}

}
