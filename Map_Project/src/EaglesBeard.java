import java.awt.BorderLayout;
import java.awt.geom.Point2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This is Eagle's Beard Navigation System
 * This is a top level class
 * 
 * @author owencb. Created Feb 18, 2016.
 */
public final class EaglesBeard {

	static final int OFFSET = 20;
	private static MapGraph mapGraph;
	private MapComponent mapComp;
	private static TripPlanner tripPlanner;
	private SearchPanel searchPanel;
	private final int REPAINT_INTERVAL_MS = 250;

	/**
	 * The code in this constructor brings up the entire system
	 */
	public EaglesBeard() {
		JFrame frame = new JFrame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);

		EaglesBeard.mapGraph = new MapGraph();
		this.mapComp = new MapComponent(EaglesBeard.mapGraph);

		addRoads(EaglesBeard.mapGraph);
		addRestaurants(EaglesBeard.mapGraph);
		// be sure to test three way intersection

		this.tripPlanner = new TripPlanner(EaglesBeard.mapGraph);
		this.searchPanel = new SearchPanel(EaglesBeard.mapGraph);

		JPanel controlPanel = new JPanel();
		JButton randomRestSelect = new RandomRestaurantSelectButton(frame,
				EaglesBeard.mapGraph);

		controlPanel.add(randomRestSelect);
		frame.add(this.searchPanel, BorderLayout.LINE_START);
		frame.add(this.tripPlanner, BorderLayout.LINE_END);
		frame.add(this.mapComp, BorderLayout.CENTER);
		frame.add(controlPanel, BorderLayout.SOUTH);
		frame.setSize(1900, 800);
		
		Runnable repaintTimer = new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						Thread.sleep(EaglesBeard.this.REPAINT_INTERVAL_MS);
						EaglesBeard.this.mapComp.repaint();
					}
				} catch (InterruptedException exception) {
					// Stop when interrupted
				}
			}
		};
		new Thread(repaintTimer).start();
		
	}
	
	/**
	 * @return a reference to the map
	 */
	public static MapGraph getMapGraph(){
		return EaglesBeard.mapGraph;
	}
	
	/**
	 * @return a reference to the trip planner
	 */
	public static TripPlanner getTripPlanner(){
		return EaglesBeard.tripPlanner;
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
		mg.addRestaurant("Real Hacienda", new Point2D.Double(OFFSET + 340,
				OFFSET + 340), "2141 IN-46, Terre Haute, IN 47803", "$",
				"Mexican", 4.1);

		mg.addRestaurant("Rick's Smokehouse", new Point2D.Double(OFFSET + 225,
				OFFSET + 180), "3102 Wabash Ave, Terre Haute, IN 47803", "$$",
				"BBQ", 4.2);

		mg.addRestaurant("M. Moggers Restaurant & Pub", new Point2D.Double(
				OFFSET + 115, OFFSET + 232),
				"908 Poplar St., Terre Haute, IN 47803", "$$", "Pub", 3.8);

		mg.addRestaurant("Stables Steakhouse", new Point2D.Double(OFFSET + 123,
				OFFSET + 240), "908 Poplar St., Terre Haute, IN 47803", "$$$",
				"Steakhouse", 4.5);

		mg.addRestaurant("Cackleberries", new Point2D.Double(OFFSET + 100,
				OFFSET + 240), "303 S 7th St., Terre Haute, IN 47803", "$",
				"Breakfast", 3.9);

		mg.addRestaurant("J. Ford's Black Angus Steakhouse",
				new Point2D.Double(OFFSET + 85, OFFSET + 255),
				"502 S 3rd St., Terre Haute, IN 47807", "$$$", "Steakhouse",
				4.6);

		mg.addRestaurant("Taj Mahal Indian Cuisine", new Point2D.Double(
				OFFSET + 75, OFFSET + 285),
				"1349 S 3rd St., Terre Haute, IN 47802", "$$", "Indian", 4.3);

		mg.addRestaurant("Umi Grill and Sushi", new Point2D.Double(OFFSET + 85,
				OFFSET + 320), "2002 S 3rd St., Terre Haute, IN 47802", "$$$",
				"Sushi", 4.1);

		mg.addRestaurant("Chava's Mexican Grill", new Point2D.Double(
				OFFSET + 100, OFFSET + 223),
				"669 Wabash Ave., Terre Haute, IN 47807", "$", "Mexican", 3.6);

		mg.addRestaurant("J. Gumbo's", new Point2D.Double(OFFSET + 95,
				OFFSET + 223), "665 Wabash Ave., Terre Haute, IN 47807", "$",
				"Cajun", 4.0);

		mg.addRestaurant("The Bierstube", new Point2D.Double(OFFSET + 160,
				OFFSET + 35), "1727 Lafayette Ave., Terre Haute, IN 47804",
				"$", "German", 3.8);

		mg.addRestaurant("Piloni's Italian Restaurant", new Point2D.Double(
				OFFSET + 170, OFFSET + 35),
				"1733 Lafayette Ave., Terre Haute, IN 47804", "$$", "Italian",
				4.4);

		mg.addRestaurant("Texas Roadhouse", new Point2D.Double(OFFSET + 75,
				OFFSET + 365), "2941 S 3rd St., Terre Haute, IN 47802", "$$",
				"Steakhouse", 4.4);

		mg.addRestaurant("Java Haute", new Point2D.Double(OFFSET + 257,
				OFFSET + 187), "3805 Wabash Ave., Terre Haute, IN 47803", "$",
				"Cafe", 4.3);

		mg.addRestaurant("Baskin-Robbins Ice Cream", new Point2D.Double(
				OFFSET + 250, OFFSET + 190),
				"85 S Fruitridge Ave., Terre Haute, IN 47803", "$",
				"Ice Cream", 4.7);

		mg.addRestaurant("Twiggy's One Headlight Pub", new Point2D.Double(
				OFFSET + 490, OFFSET + 125),
				"8567 Wabash Ave., Terre Haute, IN 47803", "$$", "Pizza", 4.0);

		mg.addRestaurant("Grand Traverse Pie Company", new Point2D.Double(
				OFFSET + 85, OFFSET + 210),
				"75 N 3rd St Ave., Terre Haute, IN 47807", "$$", "Sandwiches",
				4.4);

		mg.addRestaurant("TGI Friday's", new Point2D.Double(OFFSET + 65,
				OFFSET + 430),
				"Honey Creek Mall, 3401 US-41, Terre Haute, IN 47802", "$$",
				"American", 3.6);

		mg.addRestaurant("Red Lobster", new Point2D.Double(OFFSET + 75,
				OFFSET + 440), "3407 Lafayette Ave., Terre Haute, IN 47802",
				"$$$", "Seafood", 4.0);

		mg.addRestaurant("Sonka Irish Pub", new Point2D.Double(OFFSET + 150,
				OFFSET + 205), "1366 Wabash Ave., Terre Haute, IN 47807", "$$",
				"Pub", 4.3);
	}

}
