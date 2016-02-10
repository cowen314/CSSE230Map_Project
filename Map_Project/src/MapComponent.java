import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
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
		//draw them
	}
	
	private void drawRoad(Graphics2D g2, RoadList road){
		//Line2D line = new Line2D.Double(road.get(0).ends[0],road.get().ends[1]);
		
	}
	
}
