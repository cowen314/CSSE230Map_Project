import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RandomRestaurantSelectButton extends JButton {

	private static final String BUTTON_TEXT = "Random Restaurant Selector";
	private JFrame frame;
	private MapGraph mapGraph;

	public RandomRestaurantSelectButton(JFrame frame, MapGraph mg) {
		super(BUTTON_TEXT);
		this.frame = frame;
		this.mapGraph = mg;

		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				pullUpSelectWindow();
			}
		});
	}

	private void pullUpSelectWindow() {
		Object[] options = { "$", "$$", "$$$", "Surprise Me!" };
		int initPriceAmount = JOptionPane.showOptionDialog(this.frame,
				"How much money would you like to spend?",
				"Culinary Decision Assistant",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, options, options[3]);
		// Have to add 1 because our price fields are 1-based
		int adjustedPriceAmount = initPriceAmount + 1;
		Restaurant[] restaurantList = this.mapGraph.getRestaurants().toArray(
				new Restaurant[0]);

		Restaurant chosenRestaurant = null;
		if (adjustedPriceAmount == 4) {
			chosenRestaurant = restaurantList[(int) (Math.random() * restaurantList.length)];
		} else {
			while (true) {
				// Not worried about infinite loops. We have restaurants
				// at all 3 price points. Loop until we find one at that
				// priceLevel
				int idx = (int) (Math.random() * restaurantList.length);
				chosenRestaurant = restaurantList[idx];
				if (chosenRestaurant.getPriceLevel() == adjustedPriceAmount) {
					break;
				}
			}
		}
		// For now, just print random restaurant's name to the console
		System.out.println(chosenRestaurant.getName());

	}
}
