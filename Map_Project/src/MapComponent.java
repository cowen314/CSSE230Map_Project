import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.JComponent;


/**
 * TODO Put here a description of what this class does.
 *
 * @author owencb.
 *         Created Feb 9, 2016.
 */
public class MapComponent extends JComponent{
	private MapGraph mapGraph;
	
	public MapComponent(MapGraph mapGraph){
		this.mapGraph = mapGraph;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		//pull in all roads
		Collection<RoadList> roads = this.mapGraph.getRoads();
		//draw them
		for(RoadList road : roads){
			drawRoad(g2,road);
		}
		
		//pull in all intersections
		Collection<Intersection> intersections = this.mapGraph.getIntersections();
		//draw them
	}
	
	private void drawRoad(Graphics2D g2, RoadList road){
		//this definitely needs to be changed if we want any bends in the roads
		Point2D[] endpoints = road.getEndPoints();
		Line2D line = new Line2D.Double(endpoints[0],endpoints[1]);
		g2.setColor(Color.black);
		g2.draw(line);
		Line2D line2 = new Line2D.Double(new Point2D.Double(10,10),new Point2D.Double(100,100));
		//line2.
	}
	
}
