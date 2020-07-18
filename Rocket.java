
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A rocket can be fired from an ambulance to destroy ladybugs
 * 
 * @author Manuel Kienlein
 */
public class Rocket extends Actor
{
    private int speed = 6;
    private int direction;
    private boolean autoFollow;
    
    public Rocket(int direction, boolean autoFollow)
    {
        this.direction = direction;
        this.autoFollow = autoFollow;
        turn(direction);
    }
    
    public void act() 
    {
        move(speed);
        
        if(autoFollow){
            Ladybug nearestLadybug = null;
            java.util.List ladybugs = getNeighbours(5000, true, Ladybug.class);
            if(ladybugs.size() > 0){
                nearestLadybug = (Ladybug)ladybugs.get(0);
            }
            if(nearestLadybug != null){
                turnTowards(nearestLadybug.getX(), nearestLadybug.getY());
            }
        }
        
        if(isAtEdge()){
            SpaceWorld w = (SpaceWorld)this.getWorld();
            w.despawnObject(this);
            return;
        }
        
        if(isTouching(Ladybug.class)){
            removeTouching(Ladybug.class);
            SpaceWorld w = (SpaceWorld)this.getWorld();
            w.checkForRespawn();
            w.despawnObject(this);
            return;
        }
    }    
}
