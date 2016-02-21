import java.util.ArrayList;
import java.util.Iterator;
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

	public LinkedList<Intersection> shortestDistance(ArrayList<Restaurant> rests) {
		Restaurant startRes = this.tp.getStart();
		Restaurant endRes = this.tp.getEnd();
		Intersection startI = EaglesBeard.getMapGraph()
				.calculateClosestIntersection(startRes.getLocation());
		Intersection endI = EaglesBeard.getMapGraph()
				.calculateClosestIntersection(endRes.getLocation());

		LinkedList<Intersection> totalRoute = new LinkedList<Intersection>();
		Intersection lastI = startI;
		while (rests.size() > 0) {
			Intersection closest = EaglesBeard.getMapGraph()
					.calculateClosestIntersection(rests.get(0).getLocation());
			double smallestDistance = closest.location.distance(lastI.location);
			Restaurant next = rests.get(0);
			// determine the intersection to go to next
			for (Restaurant r : rests) {
				Intersection i = EaglesBeard.getMapGraph()
						.calculateClosestIntersection(r.getLocation());
				double distance = i.location.distance(lastI.location);
				if(distance < smallestDistance){
					closest = i;
					smallestDistance = distance;
					next = r;
				}
			}
			rests.remove(next);
			LinkedList<Intersection> toAdd = EaglesBeard.getMapGraph().shortestPath_distance(lastI,closest);
			Iterator<Intersection> it = toAdd.iterator();
			while(it.hasNext())
				totalRoute.addLast(it.next());
			lastI = closest;
			

		}
		LinkedList<Intersection> lastLeg = EaglesBeard.getMapGraph().shortestPath_distance(lastI, endI);
		Iterator<Intersection> it1 = lastLeg.iterator();
		while(it1.hasNext())
			totalRoute.addLast(it1.next());
		return totalRoute;
	}

	public LinkedList<Intersection> shortestTime(ArrayList<Restaurant> rests) {

		Restaurant startRes = this.tp.getStart();
		Restaurant endRes = this.tp.getEnd();
		Intersection startI = EaglesBeard.getMapGraph()
				.calculateClosestIntersection(startRes.getLocation());
		Intersection endI = EaglesBeard.getMapGraph()
				.calculateClosestIntersection(endRes.getLocation());

		LinkedList<Intersection> totalRoute = new LinkedList<Intersection>();
		Intersection lastI = startI;
		while (rests.size() > 0) {
			Intersection closest = EaglesBeard.getMapGraph()
					.calculateClosestIntersection(rests.get(0).getLocation());
			double smallestDistance = closest.location.distance(lastI.location);
			Restaurant next = rests.get(0);
			// determine the intersection to go to next
			for (Restaurant r : rests) {
				Intersection i = EaglesBeard.getMapGraph()
						.calculateClosestIntersection(r.getLocation());
				double distance = i.location.distance(lastI.location);
				if(distance < smallestDistance){
					closest = i;
					smallestDistance = distance;
					next = r;
				}
			}
			rests.remove(next);
			LinkedList<Intersection> toAdd = EaglesBeard.getMapGraph().shortestPath_time(lastI,closest);
			Iterator<Intersection> it = toAdd.iterator();
			while(it.hasNext())
				totalRoute.addLast(it.next());
			lastI = closest;
			

		}
		LinkedList<Intersection> lastLeg = EaglesBeard.getMapGraph().shortestPath_time(lastI, endI);
		Iterator<Intersection> it1 = lastLeg.iterator();
		while(it1.hasNext())
			totalRoute.addLast(it1.next());
		return totalRoute;
		
	}
}
