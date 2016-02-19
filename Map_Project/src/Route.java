import java.util.Iterator;
import java.util.LinkedList;


/**
 * Essentially a collection of road segments and nodes
 * With some methods for formatting for display
 *
 * @author owencb.
 *         Created Feb 18, 2016.
 */
public class Route {
	private LinkedList<Object> routeObjects;
	private double additionalDistance;
	public Route(){
		this.routeObjects = new LinkedList<Object>();
	}
	
	public void addDistance(double distance){
		this.additionalDistance+=distance;
	}
	
	@Override
	public String toString() {
		Iterator it = this.routeObjects.iterator();
		if(!it.hasNext())
			return "";
		String out = "Head to";
		while(it.hasNext()){
			//TODO: finish this
		}
		return out;
	}
}
