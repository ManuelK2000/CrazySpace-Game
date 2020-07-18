import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the world (a space environment)
 * 
 * @author Manuel Kienlein
 */
public class SpaceWorld extends World
{
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public SpaceWorld()
    {    
        // Create a new world with 1200x800 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1);
        Game.worldWidth = 1200;
        Game.worldHeight = 800;
        
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Game.reset();
        
        Ambulance amb = new Ambulance();
        int mX = getWidth()/2;
        int mY = getHeight()/2;
        spawnActor(amb, mX, mY);
        
        Toast toast = new Toast();
        toast.setText(null);
        addObject(toast, getWidth()/2, getHeight()/2);
        
        spawnLadybugs(3);
        spawnWorms(5);
    }
    
    public void spawnActor(Actor actor, int x, int y){
        addObject(actor, x, y);
    }
    
    public void spawnLadybugs(int amount){
        for(int i=0; i < amount; i++){
            Ladybug lb = new Ladybug();
            if(Greenfoot.getRandomNumber(10)<2) lb.setAggressive(false);
            int x = Greenfoot.getRandomNumber(20+getWidth()-40);
            int y = Greenfoot.getRandomNumber(20+getHeight()-40);
            spawnActor(lb, x, y);
        }
    }
    
    public void spawnWorms(int z)
    {
        for (int i=0;i<z;i++){
            Worm w= new Worm(Greenfoot.getRandomNumber(4)+1);
            int x=Greenfoot.getRandomNumber(20+getWidth()-40);
            int y=Greenfoot.getRandomNumber(20+getHeight()-40);
            addObject(w,x,y);
        }
    }
    
    public void checkForRespawn()
    {
        SpaceWorld w = this;
        java.util.List l=w.getObjects(Worm.class);
        if(l.isEmpty()){
            w.spawnWorms(12);
        }

        java.util.List l2=w.getObjects(Ladybug.class);
        if(l2.isEmpty())
        {
            w.spawnLadybugs(5);
        }
    }
    
    public void despawnObject(Actor actor)
    {
        if(!containsObject(actor.getClass(), actor)){
            return;
        }
        removeObject(actor);
    }
    
    private boolean containsObject(Class className, Object actor)
    {
        java.util.List objects = getObjects(className);
        if(objects.contains(actor)){
            return true;
        }
        return false;
    }
}
