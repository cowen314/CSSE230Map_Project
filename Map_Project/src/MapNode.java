import java.awt.image.BufferedImage;


/**
 * TODO Put here a description of what this class does.
 *
 * @author owencb.
 *         Created Feb 3, 2016.
 */
public abstract class MapNode {
	
	//we'll end up changing these most likely
	final private int HEIGHT = 10;
	final private int WIDTH = 10;
	
	/**
	 * lookupName must be unique. It will be used as a key in the lookup table.
	 * There may be a better way to do this.
	 */
	protected String lookupName;
	
	//this will be the icon displayed on the map
	private BufferedImage icon;
	
	public MapNode(String name){
		this.lookupName = name;
	}
	
	
}
