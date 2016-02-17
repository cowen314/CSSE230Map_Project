import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author owencb. Created Feb 9, 2016.
 */
public class MapComponent extends JComponent {
	private MapGraph mapGraph;
	private ImageIcon background;
	private ImageIcon RoseHulmanSprite;
	private static final Color MAP_FRAME_COLOR = Color.BLUE;
	private static final int MAP_OFFSET = 20;

	/**
	 * constructs a MapGraph object
	 *
	 * @param mapGraph
	 */
	public MapComponent(MapGraph mapGraph) {
		this.mapGraph = mapGraph;
		// Get directory we are currently in, then add on \src\imageName.png
		File currentDir = new File("");
		String picPath = currentDir.getAbsolutePath() + "\\src\\BlankMapDrawing.png";
		this.background = new ImageIcon(picPath);
		
		picPath = currentDir.getAbsolutePath() + "\\src\\RoseR.png";
		this.RoseHulmanSprite = new ImageIcon(picPath);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		Rectangle2D.Double mapFrame = new Rectangle2D.Double(0, 0, 500 + (2 * MAP_OFFSET), 500 + (2 * MAP_OFFSET));
		g2.setColor(MAP_FRAME_COLOR);
		g2.fill(mapFrame);
		
		// Paint ImageIcons for Map and RH Logo onto Component
		this.background.paintIcon(this, g2, MAP_OFFSET, MAP_OFFSET);
		this.RoseHulmanSprite.paintIcon(this, g2, MAP_OFFSET + 375, MAP_OFFSET + 110);

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
		
		// pull in all restaurants
		Collection<Restaurant> restaurants = this.mapGraph.getRestaurants();
		// draw them
		for (Restaurant restaurant : restaurants) {
			drawRestaurant(g2, restaurant);
		}
	}
	private void drawRoad(Graphics2D g2, RoadList road) {
		// this definitely needs to be changed if we want any bends in the roads
		Point2D[] endpoints = road.getEndPoints();
		Line2D line = new Line2D.Double(endpoints[0], endpoints[1]);
		g2.setColor(Color.black);
		g2.draw(line);
		// line2.
	}
	
	private void drawIntersection(Graphics2D g2, Intersection intersection) {
		Point2D loc = intersection.getLocation();
		Ellipse2D marker = new Ellipse2D.Double(loc.getX()
				- Intersection.DIAMETER / 2, loc.getY() - Intersection.DIAMETER
				/ 2, Intersection.DIAMETER, Intersection.DIAMETER);
		g2.setColor(Intersection.color);
		g2.fill(marker);
		g2.draw(marker);
	}
	
	private void drawRestaurant(Graphics2D g2, Restaurant restaurant) {
		Point2D loc = restaurant.getLocation();
		Ellipse2D marker = new Ellipse2D.Double(loc.getX()
				- Restaurant.DIAMETER / 2, loc.getY() - Restaurant.DIAMETER
				/ 2, Restaurant.DIAMETER, Restaurant.DIAMETER);
		g2.setColor(Restaurant.color);
		g2.fill(marker);
		g2.draw(marker);
	}
	

}
