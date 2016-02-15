import java.awt.geom.Point2D;

import javax.swing.JFrame;


public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		MapGraph mapGraph = new MapGraph();
		MapComponent mapComp = new MapComponent(mapGraph);

		addRoads(mapGraph);

		frame.add(mapComp);
		frame.setSize(540, 540);
		// be sure to test three way intersection
		// for some reason, putting "mapComp.repaint()" at the end of the code
		// made the roads not draw sometimes

	}

	private static void addRoads(MapGraph mapGraph) {
		mapGraph.addRoad("3rd Street", new Point2D.Double(80, 75),
				new Point2D.Double(80, 500));
		
		mapGraph.addRoad("Fort Harrison Rd.", new Point2D.Double(100, 20),
				new Point2D.Double(265, 20));
		
		mapGraph.addRoad("Wabash Ave.", new Point2D.Double(80, 225),
				new Point2D.Double(500, 120));
		
		mapGraph.addRoad("Fruitridge Ave.", new Point2D.Double(265, 0),
				new Point2D.Double(265, 394));
		mapGraph.addRoad("Fruitridge Ave. After I-70", new Point2D.Double(266, 396),
				new Point2D.Double(265, 500));
		
		mapGraph.addRoad("Interstate 70", new Point2D.Double(80, 395),
				new Point2D.Double(450, 395));
		
		mapGraph.addRoad("US-40/State Rd. 46", new Point2D.Double(355, 150),
				new Point2D.Double(355, 500));
		
		mapGraph.addRoad("Poplar St.", new Point2D.Double(80, 235),
				new Point2D.Double(355, 235));
		
		mapGraph.addRoad("Ohio St.", new Point2D.Double(80, 230),
				new Point2D.Double(265, 230));
		
		mapGraph.addRoad("Hulman St.", new Point2D.Double(80, 305),
				new Point2D.Double(355, 305));
		
		mapGraph.addRoad("7th St.", new Point2D.Double(105, 150),
				new Point2D.Double(105, 394));
		mapGraph.addRoad("7th St. After I-70", new Point2D.Double(106, 396),
				new Point2D.Double(105, 500));
		
		mapGraph.addRoad("Lafayette Ave.", new Point2D.Double(105, 150),
				new Point2D.Double(180, 0));
		
		mapGraph.addRoad("13th St.", new Point2D.Double(135, 0),
				new Point2D.Double(135, 340));
		
		mapGraph.addRoad("Erie Canal Rd.", new Point2D.Double(150, 394),
				new Point2D.Double(135, 340));
		mapGraph.addRoad("Erie Canal Rd. After I-70", new Point2D.Double(150, 396),
				new Point2D.Double(185, 500));
		
		mapGraph.addRoad("Margaret Ave.", new Point2D.Double(80, 380),
				new Point2D.Double(355, 380));
		
		mapGraph.addRoad("US-41", new Point2D.Double(110, 0),
				new Point2D.Double(80, 75));
		
		mapGraph.addRoad("Chamberlain Rd.", new Point2D.Double(480, 20),
				new Point2D.Double(480, 235));
		
		mapGraph.addRoad("IN Rt 42", new Point2D.Double(355, 235),
				new Point2D.Double(500, 234));
	}

}