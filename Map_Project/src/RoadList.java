import java.util.ArrayList;


/**
 * TODO Put here a description of what this class does.
 *
 * @author owencb.
 *         Created Feb 9, 2016.
 * @param <E>
 */
public class RoadList<E> extends ArrayList<E> {
	private String roadName;
	public void changeName(String name){
		this.roadName = name;
	}
	public String getName(){
		return this.roadName;
	}
	
}
