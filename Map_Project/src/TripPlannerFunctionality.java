/**
 * Provides the functionality behind the Trip Planner
 * 
 * @author owencb. Created Feb 18, 2016.
 */
public final class TripPlannerFunctionality {
	private TripPlanner tp;

	@SuppressWarnings("javadoc")
	public TripPlannerFunctionality(TripPlanner tp) {
		this.tp = tp;
	}

	public Route shortestDistance(){
		Restaurant startRes = this.tp.getStart();
		Restaurant endRes = this.tp.getEnd();
		Intersection startI = EaglesBeard.getMapGraph().calculateClosestIntersection(startRes.getLocation());
		Intersection endI = EaglesBeard.getMapGraph().calculateClosestIntersection(endRes.getLocation());
		
		//Route shortest = new Route();
		
		
		//EaglesBeard.getMapGraph().shortestPath_distance(this.tp.);
		return null;
	}
}
