import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;
import info.gridworld.actor.Critter;

public class FlyingBug extends Critter {
 
  // public Jumper()
  // {
  //   setColor(Color.PINK);
  // }
  
  // public Jumper(Color JumperColor)
  // {
  //   setColor(JumperColor);
  // }
  public void act()
  {
    if (canJump()) {
      jump();
 } else {
  removeSelfFromGrid();
  }
  }
    
    public void turn()
    {
      setDirection(getDirection() + Location.HALF_RIGHT);
    }

  public void jump()
  {
    Grid<Actor> gr = getGrid();
    if (gr == null)
      return;
    Location loc = getLocation();
    Location next = loc.getAdjacentLocation(getDirection());
    Location twoAway = next.getAdjacentLocation(getDirection());
    Location threeAway = twoAway.getAdjacentLocation(getDirection());
    Location fourAway = threeAway.getAdjacentLocation(getDirection());
    if (gr.isValid(fourAway)) {
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