import java.awt.BorderLayout;
import java.awt.geom.Point2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Main {

	static final int OFFSET = 20;

	public static void main(String[] args) {
		JFrame frame = new JFrame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);

		MapGraph mapGraph = new MapGraph();
		MapComponent mapComp = new MapComponent(mapGraph);

		addRoads(mapGraph);
		addRestaurants(mapGraph);
		// be sure to test three way intersection
		// for some reason, putting "mapComp.repaint()" at the end of the code
		// made the roads not draw sometimes
		
		TripPlanner tripPlanner = new TripPlanner(mapGraph);
		
		JPanel controlPanel = new JPanel();
		JButton randomRestSelect = new RandomRestaurantSelectButton(frame, mapGraph);
		
		controlPanel.add(randomRestSelect);
		frame.add(tripPlanner, BorderLayout.EAST);
		frame.add(mapComp, BorderLayout.CENTER);
		frame.add(controlPanel, BorderLayout.SOUTH);
		frame.setSize(1300, 610);
//		frame.pack();

	}

	private static void addRoads(MapGraph mapGraph) {
		mapGraph.addRoad("3rd Street", new Point2D.Double(OFFSET + 80,
				OFFSET + 75), new Point2D.Double(OFFSET + 80, OFFSET + 500));

		mapGraph.addRoad("Fort Harrison Rd.", new Point2D.Double(OFFSET + 100,
				OFFSET + 20), new Point2D.Double(OFFSET + 265, OFFSET + 20));

		mapGraph.addRoad("Wabash Ave.", new Point2D.Double(OFFSET + 80,
				OFFSET + 225), new Point2D.Double(OFFSET + 500, OFFSET + 120));

		mapGraph.addRoad("Blakely Ave.", new Point2D.Double(OFFSET + 265,
				OFFSET + 0), new Point2D.Double(OFFSET + 265, OFFSET + 394));
		mapGraph.addRoad("Fruitridge Ave. After I-70", new Point2D.Double(
				OFFSET + 266, OFFSET + 396), new Point2D.Double(OFFSET + 265,
				OFFSET + 500));

		mapGraph.addRoad("Interstate 70", new Point2D.Double(OFFSET + 80,
				OFFSET + 395), new Point2D.Double(OFFSET + 450, OFFSET + 395));

		mapGraph.addRoad("US-40/State Rd. 46", new Point2D.Double(OFFSET + 355,
				OFFSET + 150), new Point2D.Double(OFFSET + 355, OFFSET + 500));

		mapGraph.addRoad("Poplar St.", new Point2D.Double(OFFSET + 80,
				OFFSET + 235), new Point2D.Double(OFFSET + 355, OFFSET + 235));

		mapGraph.addRoad("Ohio St.", new Point2D.Double(OFFSET + 80,
				OFFSET + 230), new Point2D.Double(OFFSET + 265, OFFSET + 230));

		mapGraph.addRoad("Hulman St.", new Point2D.Double(OFFSET + 80,
				OFFSET + 305), new Point2D.Double(OFFSET + 355, OFFSET + 305));

		mapGraph.addRoad("7th St.", new Point2D.Double(OFFSET + 105,
				OFFSET + 150), new Point2D.Double(OFFSET + 105, OFFSET + 394));
		mapGraph.addRoad("7th St. After I-70", new Point2D.Double(OFFSET + 106,
				OFFSET + 396), new Point2D.Double(OFFSET + 105, OFFSET + 500));

		mapGraph.addRoad("Lafayette Ave.", new Point2D.Double(OFFSET + 105,
				OFFSET + 150), new Point2D.Double(OFFSET + 180, OFFSET + 0));

		mapGraph.addRoad("13th St.", new Point2D.Double(OFFSET + 135,
				OFFSET + 0), new Point2D.Double(OFFSET + 135, OFFSET + 340));

		mapGraph.addRoad("Erie Canal Rd.", new Point2D.Double(OFFSET + 150,
				OFFSET + 394), new Point2D.Double(OFFSET + 135, OFFSET + 340));
		mapGraph.addRoad("Erie Canal Rd. After I-70", new Point2D.Double(
				OFFSET + 150, OFFSET + 396), new Point2D.Double(OFFSET + 185,
				OFFSET + 500));

		mapGraph.addRoad("Margaret Ave.", new Point2D.Double(OFFSET + 80,
				OFFSET + 380), new Point2D.Double(OFFSET + 355, OFFSET + 380));

		mapGraph.addRoad("US-41", new Point2D.Double(OFFSET + 110, OFFSET + 0),
				new Point2D.Double(OFFSET + 80, OFFSET + 75));

		mapGraph.addRoad("Chamberlain Rd.", new Point2D.Double(OFFSET + 480,
				OFFSET + 20), new Point2D.Double(OFFSET + 480, OFFSET + 235));

		mapGraph.addRoad("IN Rt 42", new Point2D.Double(OFFSET + 355,
				OFFSET + 235), new Point2D.Double(OFFSET + 500, OFFSET + 234));
	}

	private static void addRestaurants(MapGraph mg) {
		mg.addRestaurant("Real Hacienda", new Point2D.Double(OFFSET + 340, OFFSET + 340),
				"2141 IN-46, Terre Haute, IN 47803", "$");
		
		mg.addRestaurant("Rick's Smokehouse", new Point2D.Double(OFFSET + 225, OFFSET + 180),
				"3102 Wabash Ave, Terre Haute, IN 47803", "$$");
		
		mg.addRestaurant("M. Moggers Restaurant & Pub", new Point2D.Double(OFFSET + 115, OFFSET + 232),
						"908 Poplar St., Terre Haute, IN 47803", "$$");
		
		mg.addRestaurant("Stables Steakhouse", new Point2D.Double(OFFSET + 123, OFFSET + 240),
				"908 Poplar St., Terre Haute, IN 47803", "$$$");
		
		mg.addRestaurant("Cackleberries", new Point2D.Double(OFFSET + 100, OFFSET + 240),
				"303 S 7th St., Terre Haute, IN 47803", "$");
		
		mg.addRestaurant("J. Ford's Black Angus Steakhouse", new Point2D.Double(OFFSET + 85, OFFSET + 255),
				"502 S 3rd St., Terre Haute, IN 47807", "$$$");
		
		mg.addRestaurant("Taj Mahal Indian Cuisine", new Point2D.Double(OFFSET + 75, OFFSET + 285),
				"1349 S 3rd St., Terre Haute, IN 47802", "$$");
		
		mg.addRestaurant("Umi Grill and Sushi", new Point2D.Double(OFFSET + 85, OFFSET + 320),
				"2002 S 3rd St., Terre Haute, IN 47802", "$$$");
		
		mg.addRestaurant("Chava's Mexican Grill", new Point2D.Double(OFFSET + 100, OFFSET + 223),
				"669 Wabash Ave., Terre Haute, IN 47807", "$");
		
		mg.addRestaurant("J. Gumbo's", new Point2D.Double(OFFSET + 95, OFFSET + 223),
				"665 Wabash Ave., Terre Haute, IN 47807", "$");
		
		mg.addRestaurant("The Bierstube", new Point2D.Double(OFFSET + 160, OFFSET + 35),
				"1727 Lafayette Ave., Terre Haute, IN 47804", "$");
		
		mg.addRestaurant("Piloni's Italian Restaurant", new Point2D.Double(OFFSET + 170, OFFSET + 35),
				"1733 Lafayette Ave., Terre Haute, IN 47804", "$$");
		
		mg.addRestaurant("Texas Roadhouse", new Point2D.Double(OFFSET + 75, OFFSET + 365),
				"2941 S 3rd St., Terre Haute, IN 47802", "$$");
		
		mg.addRestaurant("Java Haute", new Point2D.Double(OFFSET + 257, OFFSET + 187),
				"3805 Wabash Ave., Terre Haute, IN 47803", "$");
		
		mg.addRestaurant("Baskin-Robbins Ice Cream", new Point2D.Double(OFFSET + 250, OFFSET + 190),
				"85 S Fruitridge Ave., Terre Haute, IN 47803", "$");
		
		mg.addRestaurant("Twiggy's One Headlight Pub", new Point2D.Double(OFFSET + 490, OFFSET + 125),
				"8567 Wabash Ave., Terre Haute, IN 47803", "$$");
		
		mg.addRestaurant("Grand Traverse Pie Company", new Point2D.Double(OFFSET + 85, OFFSET + 210),
				"75 N 3rd St Ave., Terre Haute, IN 47807", "$$");
		
		mg.addRestaurant("TGI Friday's", new Point2D.Double(OFFSET + 65, OFFSET + 430),
				"Honey Creek Mall, 3401 US-41, Terre Haute, IN 47802", "$$");
		
		mg.addRestaurant("Red Lobster", new Point2D.Double(OFFSET + 75, OFFSET + 440),
				"3407 Lafayette Ave., Terre Haute, IN 47802", "$$$");
		
		mg.addRestaurant("Sonka Irish Pub", new Point2D.Double(OFFSET + 150, OFFSET + 205),
				"1366 Wabash Ave., Terre Haute, IN 47807", "$$");
		}
}
