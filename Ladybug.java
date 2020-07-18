import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Ladybugs are enemies of the ambulance in the space.
 * But there is a 20 percent chance that they are peaceful.
 * 
 * @author Manuel Kienlein
 */
public class Ladybug extends Actor
{
    private boolean aggressive;
    private int speed = 3;
    
    public Ladybug()
    {
        setAggressive(true);
    }
    
    public void act() 
    {
        randomTurn();
        lookForAmbulance();
    }
    
    private void randomTurn()
    {
        move(speed);
        int percent;
        percent=Greenfoot.getRandomNumber(100);
        if(percent<30)
        {
            int winkel;
            winkel=Greenfoot.getRandomNumber(31);
            winkel=winkel-15;
            turn(winkel);
        }
        if(this.isAtEdge())
        {
            turn(15);
        } 
    }

    private void lookForAmbulance()
    {
        if(!aggressive) return;
        if(isTouching(Ambulance.class))
        {
            Ambulance[] ambulanceArray = getIntersectingObjects(Ambulance.class).toArray(new Ambulance[getIntersectingObjects(Ambulance.class).size()]);
            if(ambulanceArray[0].isGod()) return;
            removeTouching(Ambulance.class);
            Greenfoot.playSound("au.wav");
            Game.gameOver();
        }
    }
    
    private boolean isAggressive(){
        return aggressive;
    }

    public void setAggressive(boolean aggressive){
        this.aggressive = aggressive;

        if(!aggressive){
            this.setImage(new GreenfootImage("ladybug1.png"));
        }else{
            this.setImage(new GreenfootImage("ladybug_02.png"));
        }
    }
}
