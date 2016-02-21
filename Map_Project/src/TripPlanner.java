import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class TripPlanner extends JPanel {

	private Restaurant[] restaurantList;
	private JComboBox<String> startComboBox;
	private int comboBoxesCreated;
	private JComboBox<String> endComboBox;
	private TripPlannerFunctionality tpFunct;

	public TripPlanner(MapGraph mg) {
		super();
		this.restaurantList = mg.getRestaurants().toArray(new Restaurant[0]);
		// Add some spacing around our panel
		this.setBorder(new EmptyBorder(30, 30, 30, 30));
		this.buildTripPlannerGUI();
		this.comboBoxesCreated = 0;
		this.tpFunct = new TripPlannerFunctionality(this);

	}
	
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
		restaurantNames[counter] = "Rose-Hulman Institute of Technology";

		// TODO: clean this up
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
		toReturn.setLayout(new GridLayout(numRows, 1, 10, 0));
		for (Restaurant r : this.restaurantList) {
			JCheckBox checkBox = new JCheckBox(r.getName());
			toReturn.add(checkBox);
		}
		toReturn.add(new JCheckBox("Rose-Hulman"));
		toReturn.setAlignmentX(CENTER_ALIGNMENT);
		return toReturn;
	}

	private void addShortListener(JButton shortestPathButton) {
		shortestPathButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				System.out.println("Calculating Shortest Trip");
				System.out.println(TripPlanner.this.tpFunct.shortestDistance());
			}
		});
	}

	private void addFastListener(JButton fastestPathButton) {
		fastestPathButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				System.out.println("Calculating Fastest Trip");
				System.out.println(TripPlanner.this.tpFunct.shortestTime());
			}
		});
	}
}
