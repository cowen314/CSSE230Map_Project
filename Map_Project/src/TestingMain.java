import java.awt.geom.Point2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

import math.geom2d.Vector2D;


/**
 * TODO Put here a description of what this class does.
 *
 * @author owencb.
 *         Created Feb 9, 2016.
 */
public class TestingMain {

	/**
	 * A main for testing purposes
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		MapGraph mapGraph = new MapGraph();
		MapComponent mapComp = new MapComponent(mapGraph);
		frame.add(mapComp);
		mapGraph.addRoad("Test drive",new Point2D.Double(10,10),new Point2D.Double(200,200));
		//mapGraph.addRoad()
		/*		while(true){
		mapComp.repaint();
		}*/
		Vector2D testVector = new Vector2D();
	}

}
