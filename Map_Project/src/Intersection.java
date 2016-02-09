/**
 * TODO Put here a description of what this class does.
 * 
 * @author owencb. Created Feb 8, 2016.
 */
public class Intersection extends MapNode {

	/**
	 * TODO Put here a description of what this constructor does.
	 * 
	 * @param name
	 */
	public Intersection(String name) {
		super(name);
		// TODO Auto-generated constructor stub.
	}

	public Intersection(String name, RoadSegment rs1, RoadSegment rs2,
			RoadSegment rs3, RoadSegment rs4) {
		super(name,rs1,rs2,rs3,rs4);
	}

}
