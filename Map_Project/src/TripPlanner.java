import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 * The top level (GUI) trip planner class
 *
 * @author owencb.
 *         Created Feb 21, 2016.
 */
public class TripPlanner extends JPanel {

	private Restaurant[] restaurantList;
	private JComboBox<String> startComboBox;
	private int comboBoxesCreated;
	private JComboBox<String> endComboBox;
	private TripPlannerFunctionality tpFunct;
	private LinkedList<Intersection> currentRoute;
	private ArrayList<RestaurantCheckBox> checkboxes;

	/**
	 * Constucts GUI trip planner
	 *
	 * @param mg
	 */
	public TripPlanner(MapGraph mg) {
		super();
		this.restaurantList = mg.getRestaurants().toArray(new Restaurant[0]);
		// Add some spacing around our panel
		this.setBorder(new EmptyBorder(30, 30, 30, 30));
		this.buildTripPlannerGUI();
		this.comboBoxesCreated = 0;
		this.tpFunct = new TripPlannerFunctionality(this);
		//this.checkboxes = new ArrayList<RestaurantCheckBox>();
	}
	
	/**
	 * @return the restaurants list
	 */
	public Restaurant[] getRestaurants(){
		return this.restaurantList;
	}
	
	/**
	 * @return get the restaurant selected as the start
	 */
	public Restaurant getStart(){
		int ind = this.startComboBox.getSelectedIndex();
		return this.restaurantList[ind];
	}
	
	/**
	 * @return get the restaurant selected as the end
	 */
	public Restaurant getEnd(){
		int ind = this.endComboBox.getSelectedIndex();
		return this.restaurantList[ind];
	}

	private void buildTripPlannerGUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel tripPlannerLabel = this.createTitleLabel();
		JPanel tripStartPanel = this.createDropDownPanel("Starting Point: ");
		JPanel tripEndPanel = this.createDropDownPanel("Destination:   ");
		JLabel optionalDestLabel = this.createOptionalDestinationLabel();
		JPanel optionalDestPanel = this.createOptionalDestinationPanel();
		JPanel buttonPanel = new JPanel();

		JButton shortest = new JButton("Get Shortest Path");
		this.addShortListener(shortest);
		buttonPanel.add(shortest);

		JButton fastest = new JButton("Get Quickest Path");
		this.addFastListener(fastest);
		buttonPanel.add(fastest);

		this.add(tripPlannerLabel);
		this.add(tripStartPanel);
		this.add(tripEndPanel);
		this.add(optionalDestLabel);
		this.add(optionalDestPanel);
		this.add(buttonPanel);
	}

	/**
	 * Creates a JLabel that says "Trip Planner"
	 * */
	private JLabel createTitleLabel() {
		JLabel TPLabel = new JLabel("Trip Planner");

		// Create compound border with Padding and a Blue outline
		EmptyBorder spacingBorder = new EmptyBorder(10, 10, 10, 10);
		Border blueLineBorder = BorderFactory.createLineBorder(Color.blue);
		Border compoundBorder = BorderFactory.createCompoundBorder(
				blueLineBorder, spacingBorder);
		TPLabel.setBorder(compoundBorder);

		// Set font
		TPLabel.setFont(new Font("Batang", Font.PLAIN, 36));

		// Color the Background and text appropriately
		TPLabel.setForeground(Color.blue);
		TPLabel.setBackground(Color.white);
		TPLabel.setOpaque(true);

		TPLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		return TPLabel;
	}

	private JComboBox<String> createDropDown() {
		String[] restaurantNames = new String[this.restaurantList.length + 1];

		int counter = 0;
		for (Restaurant r : this.restaurantList) {
			restaurantNames[counter] = r.getName();
			counter++;
		}
		//restaurantNames[counter] = "Rose-Hulman Institute of Technology";

		JComboBox<String> comboBox = new JComboBox<String>(restaurantNames);
		comboBox.setSelectedIndex(counter);
		if (this.comboBoxesCreated == 0) {
			this.startComboBox = new JComboBox<String>(restaurantNames);
			this.comboBoxesCreated++;
			return this.startComboBox;
		}
		if (this.comboBoxesCreated == 1) {
			this.endComboBox = new JComboBox<String>(restaurantNames);
			this.comboBoxesCreated++;
			return this.endComboBox;
		}
		this.comboBoxesCreated++;
		System.out.println("Warning: too many combo boxes created");
		return null;

	}

	private JPanel createDropDownPanel(String labelStr) {
		JPanel toReturn = new JPanel();
		// Make Label
		JLabel label = new JLabel(labelStr);

		// Make ComboBox
		JComboBox<String> cb = this.createDropDown();
		if (labelStr.charAt(0) == 'S') {
			// Set default selection to RHIT if we are making a start box
			// RHIT is last entry in list.
			cb.setSelectedIndex(cb.getItemCount() - 1);
		}

		cb.setSelectedIndex(0);

		// Add them to the new Panel
		toReturn.add(label);
		toReturn.add(cb);

		return toReturn;
	}

	private JLabel createOptionalDestinationLabel() {
		JLabel optionalDestLabel = new JLabel("Optional Destinations:");

		// Create padding around label
		EmptyBorder spacingBorder = new EmptyBorder(10, 10, 10, 10);
		optionalDestLabel.setBorder(spacingBorder);

		// Set font
		optionalDestLabel.setFont(new Font("Batang", Font.PLAIN, 18));

		optionalDestLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		return optionalDestLabel;
	}

	private JPanel createOptionalDestinationPanel() {
		JPanel toReturn = new JPanel();
		int numRows = (this.restaurantList.length / 2);
		this.checkboxes = new ArrayList<RestaurantCheckBox>();
		toReturn.setLayout(new GridLayout(numRows, 1, 10, 0));
		for (Restaurant r : this.restaurantList) {
			RestaurantCheckBox checkBox = new RestaurantCheckBox(r);
			this.checkboxes.add(checkBox);
			toReturn.add(checkBox);
		}
		//toReturn.add(new JCheckBox("Rose-Hulman"));
		toReturn.setAlignmentX(CENTER_ALIGNMENT);
		return toReturn;
	}
	
	private class RestaurantCheckBox extends JCheckBox {
		private Restaurant restaurant;
		/**
		 * constructs check box with attached restaurant name
		 */
		public RestaurantCheckBox(Restaurant restaurant) {
			super(restaurant.getName());
			this.restaurant = restaurant;
		}
	}

	private void addShortListener(JButton shortestPathButton) {
		shortestPathButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				ArrayList<Restaurant> rests = new ArrayList<Restaurant>();
				for(RestaurantCheckBox rcb : TripPlanner.this.checkboxes) {
					if(rcb.isSelected())
						rests.add(rcb.restaurant);
				}
				System.out.println("Calculating Shortest Trip");
				TripPlanner.this.currentRoute = TripPlanner.this.tpFunct.shortestDistance(rests);
				System.out.println(TripPlanner.this.currentRoute);
			}
		});
	}

	private void addFastListener(JButton fastestPathButton) {
		fastestPathButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				//ADDED THIS
				ArrayList<Restaurant> rests = new ArrayList<Restaurant>();
				for(RestaurantCheckBox rcb : TripPlanner.this.checkboxes) {
					if(rcb.isSelected())
						rests.add(rcb.restaurant);
				}
				
				System.out.println("Calculating Fastest Trip");
				TripPlanner.this.currentRoute = TripPlanner.this.tpFunct.shortestTime(rests);
				System.out.println(TripPlanner.this.currentRoute);
			}
		});
	}

	/**
	 * @return the current route
	 */
	public LinkedList<Intersection> getCurrentRoute() {
		return this.currentRoute;
	}
	
	public boolean isOnRoute(Restaurant r){
		if(this.checkboxes==null)
			return false;
		for(RestaurantCheckBox rcb : this.checkboxes){
			if(rcb.isSelected()&&r==rcb.restaurant)
				return true;
		}
		return false;
	}
	
}
