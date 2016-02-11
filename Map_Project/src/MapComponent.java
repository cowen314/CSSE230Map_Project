import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Ellipse2D;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.JComponent;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author owencb. Created Feb 9, 2016.
 */
public class MapComponent extends JComponent {
	private MapGraph mapGraph;

	/**
	 * constructs a MapGraph object
	 *
	 * @param mapGraph
	 */
	public MapComponent(MapGraph mapGraph) {
		this.mapGraph = mapGraph;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		// pull in all roads
		Collection<RoadList> roads = this.mapGraph.getRoads();
		// draw them
		for (RoadList road : roads) {
			drawRoad(g2, road);
		}

		// pull in all intersections
		Collection<Intersection> intersections = this.mapGraph
				.getIntersections();
		// draw them
		for (Intersection intersection : intersections) {
			drawIntersection(g2, intersection);
		}
	}

	private void drawIntersection(Graphics2D g2, Intersection intersection) {
		Point2D loc = intersection.getLocation();
		Ellipse2D marker = new Ellipse2D.Double(loc.getX()
				- Intersection.DIAMETER / 2, loc.getY() - Intersection.DIAMETER
				/ 2, Intersection.DIAMETER, Intersection.DIAMETER);
		g2.setColor(Intersection.color);
		g2.fill(marker);
	}

	private void drawRoad(Graphics2D g2, RoadList road) {
		// this definitely needs to be changed if we want any bends in the roads
		Point2D[] endpoints = road.getEndPoints();
		Line2D line = new Line2D.Double(endpoints[0], endpoints[1]);
		g2.setColor(Color.black);
		g2.draw(line);
		// line2.
	}

}
