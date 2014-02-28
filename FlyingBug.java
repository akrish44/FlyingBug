import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;
import info.gridworld.actor.Critter;
import java.util.ArrayList;

public class FlyingBug extends Critter {
 
  public void act()
  {
  	// selectMoveLocation();
    if (canJump()) {
      jump();
 } else {
  removeSelfFromGrid();
  }
  }

  // public Location selectMoveLocation(ArrayList<Location> locs) {
  // 	int n = locs.size();
  // 	if (n ==0) {
  // 		return getLocation();
  // 	}
  // 	int r = (int) (Math.random()*n);

  // 	return locs.get(r);
  // }
    
   public int getRandomDirection() {
   	int turns = (int) (Math.random() * 8);
   return Location.NORTH + turns*Location.HALF_RIGHT;

   }
    

  public void jump()
  {
  	// ArrayList<Location> locs = new ArrayList<Location>();
    Grid<Actor> gr = getGrid();
    if (gr == null)
      return;
    Location loc = getLocation();
    Location next = loc.getAdjacentLocation(getRandomDirection());
    Location twoAway = next.getAdjacentLocation(getRandomDirection());
    Location threeAway = twoAway.getAdjacentLocation(getRandomDirection());
    Location fourAway = threeAway.getAdjacentLocation(getRandomDirection());
    if (gr.isValid(fourAway)) {
    	// locs.add(fourAway);
    	// selectMoveLocation(fourAway);
      moveTo(fourAway);
    } else {
      removeSelfFromGrid();
    }
  }

  public boolean canJump() {
    Grid<Actor> gr = getGrid();
    if (gr == null)
      return false;
    Location loc = getLocation();
    Location next = loc.getAdjacentLocation(getDirection());
    if (!gr.isValid(next))
      return false;
    Actor neighbor = gr.get(next);
    if (!((neighbor == null) || (neighbor instanceof Flower)
                             || (neighbor instanceof Rock)))
      return false;
    Location fourAway = next.getAdjacentLocation(getDirection());
    if (!gr.isValid(fourAway))
      return false;
    neighbor = gr.get(fourAway);
    return (neighbor == null) || (neighbor instanceof Flower);



  }


  }
