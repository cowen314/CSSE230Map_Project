import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Collection;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author owencb. Created Feb 21, 2016.
 */
public class GraphVisualizer {

	private MapGraph mg;

	/**
	 * TODO Put here a description of what this constructor does.
	 * 
	 */
	public GraphVisualizer(MapGraph mg) {
		this.mg = mg;
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setSize(500, 500);

		TestMapComponent tc = new TestMapComponent();
		frame.add(tc);

	}

	private class TestMapComponent extends JComponent {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			drawIntersections(g2);
			drawIntersectionPointers(g2);

		}

		/**
		 * TODO Put here a description of what this method does.
		 * 
		 * @param g2
		 */
		private void drawIntersectionPointers(Graphics2D g2) {
			Collection<Intersection> intersections = GraphVisualizer.this.mg
					.getIntersections();
			// pull in all roads
			Collection<RoadList> roads = GraphVisualizer.this.mg.getRoads();
			for (Intersection intersection : intersections) {
				for (RoadSegment rs : intersection.neighborRoads) {
					Line2D line = new Line2D.Double(rs.intersects[1].location,
							rs.intersects[0].location);
					g2.setColor(Color.black);
					g2.draw(line);
					double x = (line.getX1() + line.getX2()) / 2;
					double y = (line.getY1() + line.getY2()) / 2;
					Ellipse2D marker = new Ellipse2D.Double(x
							- Intersection.DIAMETER / 2, y
							- Intersection.DIAMETER / 2,
							Intersection.DIAMETER * 1.5,
							Intersection.DIAMETER * 1.5);
					g2.setColor(Color.RED);
					//g2.fill(marker);
					//g2.draw(marker);
				}

			}

		}

		/**
		 * TODO Put here a description of what this method does.
		 * 
		 * @param g2
		 * 
		 */
		private void drawIntersections(Graphics2D g2) {
			// pull in all intersections
			Collection<Intersection> intersections = GraphVisualizer.this.mg
					.getIntersections();
			// draw them
			for (Intersection intersection : intersections) {
				Point2D loc = intersection.getLocation();
				Ellipse2D marker = new Ellipse2D.Double(loc.getX()
						- Intersection.DIAMETER / 2, loc.getY()
						- Intersection.DIAMETER / 2, Intersection.DIAMETER,
						Intersection.DIAMETER);
				g2.setColor(Intersection.color);
				g2.fill(marker);
				g2.draw(marker);
			}
		}

	}

}
