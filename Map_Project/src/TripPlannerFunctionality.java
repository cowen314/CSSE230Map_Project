import java.util.LinkedList;

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
	//TODO: make this return a route instead of a linked list
	public LinkedList<Intersection> shortestDistance(){
		Restaurant startRes = this.tp.getStart();
		Restaurant endRes = this.tp.getEnd();
		Intersection startI = EaglesBeard.getMapGraph().calculateClosestIntersection(startRes.getLocation());
		Intersection endI = EaglesBeard.getMapGraph().calculateClosestIntersection(endRes.getLocation());
		
		double begin = startI.getLocation().distance(startRes.getLocation());
		double end = endI.getLocation().distance(endRes.getLocation());
		
		Route shortest = new Route();
		
		return EaglesBeard.getMapGraph().shortestPath_distance(startI,endI);
	}
	
	public LinkedList<Intersection> shortestTime(){
		Restaurant startRes = this.tp.getStart();
		Restaurant endRes = this.tp.getEnd();
		Intersection startI = EaglesBeard.getMapGraph().calculateClosestIntersection(startRes.getLocation());
		Intersection endI = EaglesBeard.getMapGraph().calculateClosestIntersection(endRes.getLocation());
		
		double begin = startI.getLocation().distance(startRes.getLocation());
		double end = endI.getLocation().distance(endRes.getLocation());
		
		Route shortest = new Route();
		
		return EaglesBeard.getMapGraph().shortestPath_time(startI,endI);
	}
}
