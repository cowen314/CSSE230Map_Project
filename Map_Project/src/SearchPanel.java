import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * 
 * This class creates and handles the search panel portion of the project
 * (everything left of the map)
 *
 * @author heshelhj. Created Feb 17, 2016.
 */
public class SearchPanel extends JPanel {

	private static Restaurant[] restaurantList;
	private static ArrayList<String> rl;
	private MapGraph mapGraph;
	private JPanel j;

	public SearchPanel(MapGraph mg) {
		super();
		this.restaurantList = mg.getRestaurants().toArray(new Restaurant[0]);
		// Add some spacing around our panel
		this.setBorder(new EmptyBorder(30, 30, 30, 30));
		this.rl = getRestaurantList(restaurantList);
		this.j = this.alphabetize();
		this.buildSearchPanel();

	}
	/**
	 * 
	 *Builds the search panel
	 *
	 */
	private void buildSearchPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JLabel r = new JLabel("Restaurant List");
		r.setFont(new Font("Batang", Font.PLAIN, 36));
		JLabel r2 = new JLabel("View Restaurants in Price Range:");
		r2.setFont(new Font("Batang", Font.PLAIN, 18));

//		j = this.alphabetize();
		JPanel pp = this.pricePanelComboBox();

		this.add(r);
		this.add(r2);
		this.add(pp);
		this.add(j);

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
		priceOptions[0] = "ALL";
		priceOptions[1] = "$";
		priceOptions[2] = "$$";
		priceOptions[3] = "$$$";
	

		JComboBox priceRangeBox = new JComboBox(priceOptions);
		priceRangeBox.addActionListener(getList);
		pPanel.add(priceRangeBox);
		return pPanel;
	}
	/**
	 * Action listener for the combo box
	 */
	ActionListener getList = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox c = (JComboBox) e.getSource();
			String priceCode = (String) c.getSelectedItem();
//			System.out.println(priceCode);
			updatepriceP(priceCode);
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
		ArrayList pc = new ArrayList(); 
		if(s.equals("ALL")){
			rl = this.getRestaurantList(restaurantList);
			return;
		}
		for (Restaurant r : this.restaurantList) {
			if (r.getPriceLevel() == s.length()) {
				JLabel rest = new JLabel(r.getName());
				pc.add(r.getName());
//				System.out.println(r.getName());

			}
		}
		rl = pc;
	}
	
	/**
	 * 
	 * Refreshes the view on a selection of price
	 *
	 */
	protected void refreshView() {
		this.remove(j);
		j = this.alphabetize();
		this.add(j);
		this.revalidate();
		rl = this.getRestaurantList(restaurantList);
	}
	/**
	 * 
	 *Creates the drop down price selection menu
	 *
	 * @return
	 */
	private JComboBox<String> dropDown() {
		String[] priceOptions = new String[4];
		priceOptions[0] = "$";
		priceOptions[1] = "$$";
		priceOptions[2] = "$$$";
		priceOptions[3] = "$$$$";
		return new JComboBox<String>(priceOptions);
	}

	/**
	 * 
	 * Gets the restaurant list for the search panel
	 *
	 * @param restList
	 * @return
	 */
	private ArrayList getRestaurantList(Restaurant[] restList) {
		ArrayList<String> restNames = new ArrayList<String>();
		for (int i = 0; i < restList.length; i++) {
			restNames.add(restList[i].getName());
		}
		return restNames;
	}

	/**
	 * 
	 * Puts the restaurants in alphabetical order
	 *
	 * @return
	 */
	private static JPanel alphabetize() {
		Collections.sort(rl);
		JPanel toReturn = new JPanel();
		toReturn.setLayout(new BoxLayout(toReturn, BoxLayout.Y_AXIS));
		JList list = new JList();
		for (int i = 0; i < rl.size() - 1; i++) {
			JLabel rb = new JLabel(rl.get(i));
			toReturn.add(rb);
		}
		return toReturn;
	}

}
