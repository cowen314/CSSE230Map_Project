import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 * 
 * This class creates and handles the search panel portion of the project
 * (everything left of the map)
 * 
 * @author heshelhj. Created Feb 17, 2016.
 */
public class SearchPanel extends JPanel {

	private static final int NUM_VISIBLE_ROWS_IN_LIST = 10;
	private static final Color PRIMARY_THEME_COLOR = Color.BLUE;
	private static final Color SECONDARY_THEME_COLOR = Color.WHITE;

	private static final String ABC_SORT_BTN_TEXT = "Sort Alphabetically";
	private static final String RATING_SORT_BTN_TEXT = "Sort by Rating";

	private boolean sortByRating;
	private Comparator<Restaurant> ratingComparator;

	private static Restaurant[] restaurantList;
	private static List<Restaurant> restaurantListForSort;
	private static ArrayList<String> rl;
	private String selectedPrice;
	private String selectedType;
	private DefaultListModel<String> listModel;

	public SearchPanel(MapGraph mg) {
		super();
		this.sortByRating = false;
		this.ratingComparator = new RatingSortComparator();
		Collection<Restaurant> allRests = mg.getRestaurants();
		restaurantList = allRests.toArray(new Restaurant[0]);
		restaurantListForSort = Arrays.asList(restaurantList);
		// Add some spacing around our panel
		selectedPrice = "ALL PRICES";
		selectedType = "ALL TYPES";
		this.setBorder(new EmptyBorder(30, 30, 30, 30));
		rl = getRestaurantList(restaurantListForSort);
		this.listModel = null;
		this.buildSearchPanel();

	}

	/**
	 * 
	 * Builds the search panel
	 * 
	 */
	private void buildSearchPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JLabel titleBar = this.createTitleLabel();

		JLabel subTitle = new JLabel("View Restaurants in Price Range:");
		subTitle.setFont(new Font("Batang", Font.PLAIN, 18));
		subTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel listPanel = this.createListPanel();
		JPanel priceBox = this.pricePanelComboBox();
		JPanel typeBox = this.typePanelComboBox();

		this.add(titleBar);
		this.add(subTitle);
		this.add(priceBox);
		this.add(typeBox);
		this.add(listPanel);

	}

	/**
	 * Creates a JLabel that says "Trip Planner"
	 * */
	private JLabel createTitleLabel() {
		JLabel TPLabel = new JLabel("Restaurant List");

		// Create compound border with Padding and a Blue outline
		EmptyBorder spacingBorder = new EmptyBorder(10, 10, 10, 10);
		Border blueLineBorder = BorderFactory
				.createLineBorder(PRIMARY_THEME_COLOR);
		Border compoundBorder = BorderFactory.createCompoundBorder(
				blueLineBorder, spacingBorder);
		TPLabel.setBorder(compoundBorder);

		// Set font
		TPLabel.setFont(new Font("Batang", Font.PLAIN, 36));

		// Color the Background and text appropriately
		TPLabel.setForeground(PRIMARY_THEME_COLOR);
		TPLabel.setBackground(SECONDARY_THEME_COLOR);
		TPLabel.setOpaque(true);

		TPLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		return TPLabel;
	}

	/**
	 * 
	 * Sets up the combo box
	 * 
	 * @return
	 */
	private JPanel pricePanelComboBox() {
		JPanel pPanel = new JPanel();
		String[] priceOptions = new String[4];
		priceOptions[0] = "ALL PRICES";
		priceOptions[1] = "$";
		priceOptions[2] = "$$";
		priceOptions[3] = "$$$";

		JComboBox<String> priceRangeBox = new JComboBox<String>(priceOptions);
		priceRangeBox.addActionListener(getList);
		pPanel.add(priceRangeBox);
		return pPanel;
	}

	private JPanel typePanelComboBox() {
		JPanel tPanel = new JPanel();
		String[] tOptions = new String[17];
		tOptions[0] = "ALL TYPES";
		tOptions[1] = "Mexican";
		tOptions[2] = "BBQ";
		tOptions[3] = "Steakhouse";
		tOptions[4] = "Pub";
		tOptions[5] = "Breakfast";
		tOptions[6] = "Sushi";
		tOptions[7] = "Indian";
		tOptions[8] = "Cajun";
		tOptions[9] = "German";
		tOptions[10] = "Italian";
		tOptions[11] = "Cafe";
		tOptions[12] = "Ice Cream";
		tOptions[13] = "Pizza";
		tOptions[14] = "Seafood";
		tOptions[15] = "Sandwiches";
		tOptions[16] = "American";
		JComboBox<String> typeBox = new JComboBox<String>(tOptions);
		typeBox.addActionListener(getList);
		tPanel.add(typeBox);
		return tPanel;
	}

	/**
	 * Action listener for the combo box
	 */
	ActionListener getList = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// This Listener will only be given to JComboBoxes
			JComboBox<String> c = (JComboBox) e.getSource();
			String Code = (String) c.getSelectedItem();
			// System.out.println(priceCode);
			if (Code.charAt(0) == '$' || Code.equals("ALL PRICES")) {
				selectedPrice = Code;
				updatepriceP(Code);
			} else {
				selectedType = Code;
				updateType(Code);
			}
			refreshView();
		}

	};

	/**
	 * 
	 * Updates the restaurant array for a given price type
	 * 
	 * @param s
	 */
	private void updatepriceP(String s) {
		ArrayList<String> pc = new ArrayList<String>();
		List<Restaurant> restaurantListFS = new ArrayList<Restaurant>();
		for (Restaurant r : restaurantList) {
			if ((r.getPriceLevel() == s.length() || selectedPrice
					.equals("ALL PRICES"))
					&& (r.getType().equals(selectedType) || selectedType
							.equals("ALL TYPES"))) {
				pc.add(r.getName());
				restaurantListFS.add(r);
				// System.out.println(r.getName());
			}
		}
		rl = pc;
		restaurantListForSort = restaurantListFS;
	}

	protected void updateType(String code) {
		ArrayList<String> tl = new ArrayList<String>();
		List<Restaurant> restaurantListFS = new ArrayList<Restaurant>();
		// if (code.equals("ALL TYPES")) {
		// rl = this.getRestaurantList(restaurantList);
		// return;
		// }
		for (Restaurant r : restaurantList) {
			if ((r.getType().equals(code) || selectedType.equals("ALL TYPES"))
					&& (r.getPriceLevel() == selectedPrice.length() || selectedPrice
							.equals("ALL PRICES"))) {
				tl.add(r.getName());
				restaurantListFS.add(r);
			}
		}
		rl = tl;
		restaurantListForSort = restaurantListFS;
	}

	/**
	 * 
	 * Refreshes the view on a selection of price
	 * 
	 */
	protected void refreshView() {
		if (this.sortByRating) {
			this.sortByRating();
		} else {
			this.alphabetize();
		}
		this.revalidate();
		rl = this.getRestaurantList(restaurantListForSort);
	}

	/**
	 * 
	 * Gets the restaurant list for the search panel
	 * 
	 * @param restaurantListForSort2
	 * @return
	 */
	private ArrayList<String> getRestaurantList(List<Restaurant> restaurantListForSort2) {
		ArrayList<String> restNames = new ArrayList<String>();
		for (Restaurant r : restaurantListForSort2) {
			restNames.add(r.getName());
		}
		return restNames;
	}

	/**
	 * 
	 * Puts the restaurants in alphabetical order and adds them to the list
	 * 
	 * @return
	 */
	private void alphabetize() {
		Collections.sort(rl);
		this.listModel.clear();
		this.addRestaurantsToListModel();
	}
	
	/**
	 * 
	 * Puts the restaurants in alphabetical order and adds them to the list
	 * 
	 * @return
	 */
	private void sortByRating() {
		Collections.sort(restaurantListForSort, this.ratingComparator);
		ArrayList<String> temp = new ArrayList<String>();
		for(Restaurant r : restaurantListForSort) {
			temp.add(r.getName());
		}
		this.listModel.clear();
		rl = temp;
		this.addRestaurantsToListModel();
	}
	

	private JPanel createListPanel() {
		JPanel toReturn = new JPanel();
		
		// Create DefaultListModel to manage add/remove
		this.listModel = new DefaultListModel<String>();
		this.addRestaurantsToListModel(); // Initialization
		// Create JList using the DefaultListModel
		JList<String> list = new JList<String>(listModel);
		list.setVisibleRowCount(NUM_VISIBLE_ROWS_IN_LIST);
		// Put the JList in a JScrollPane and fix the size
		JScrollPane listScrollPane = new JScrollPane(list);
		listScrollPane.setPreferredSize(new Dimension(220, NUM_VISIBLE_ROWS_IN_LIST * 15));

		// Create the panel and buttons that will allow sorting of the
		// list by alphabetical order or by rating
		JPanel sortButtonsPanel = new JPanel();
		sortButtonsPanel.setLayout(new BoxLayout(sortButtonsPanel,
				BoxLayout.Y_AXIS));
		// Creat Sort Button Group
		ButtonGroup sortBtnGroup = new ButtonGroup();
		JRadioButton ABCSortBtn = new JRadioButton(ABC_SORT_BTN_TEXT, true);
		ABCSortBtn.addActionListener(this.sortListener());
		JRadioButton RatingSortBtn = new JRadioButton(RATING_SORT_BTN_TEXT,
				false);
		RatingSortBtn.addActionListener(this.sortListener());		
		sortBtnGroup.add(ABCSortBtn);
		sortBtnGroup.add(RatingSortBtn);
		// Add buttons to panel
		sortButtonsPanel.add(ABCSortBtn);
		sortButtonsPanel.add(RatingSortBtn);

		toReturn.add(listScrollPane);
		toReturn.add(sortButtonsPanel);
		
		// Since the Sort Alphabetically button will be selected on project
		// startup, we should show the list initially alphabetized.
		this.alphabetize();

		return toReturn;
	}

	private ActionListener sortListener() {
		ActionListener toReturn = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// This Listener will only be given to JRadioButtons
				JRadioButton btn = (JRadioButton) e.getSource();
				String btnLabel = btn.getText();
				if (btnLabel.equals(ABC_SORT_BTN_TEXT)) {
					sortByRating = false;
				} else if (btnLabel.equals(RATING_SORT_BTN_TEXT)) {
					sortByRating = true;
				}				
				refreshView();
			}
		};
		return toReturn;
	}

	private void addRestaurantsToListModel() {
		for (String restaurantName : rl) {
			this.listModel.addElement(restaurantName);
		}
	}
	
	private class RatingSortComparator implements Comparator<Restaurant> {

		@Override
		public int compare(Restaurant r1, Restaurant r2) {
			Double rating1 = r1.getRating();
			Double rating2 = r2.getRating();
			int comp = rating2.compareTo(rating1);
			if (comp == 0) {
				// If ratings are equal, sort alphabetically
				comp = r1.getName().compareTo(r2.getName());
			}
			return comp;
		}
		
	}

}
