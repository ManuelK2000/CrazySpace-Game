import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The ambulance is the vehicle, that is controlled by the player
 * 
 * @author Manuel Kienlein 
 */
public class Ambulance extends Actor
{
    protected int speed = 3;
    protected boolean god = false;
    protected boolean autopilot = false;
    
    private long ap_lastRocket = 0;
    
    public Ambulance()
    {
        super();
    }
    
    public void act() 
    {
        move(speed);
        lookForWorms();
        checkKeys();
        displayScore();
        drawWaypoints();
        autopilot();
    }
    
    
    private void checkKeys()
    {
        if(Greenfoot.isKeyDown("left")){
            turn(-5);
        }
        if(Greenfoot.isKeyDown("right")){
            turn(5);
        }
        
        if(KeyListener.isKeyTyped("space")){
            fireRocket(false);
        }
        if(KeyListener.isKeyTyped("G")) god = !god;
        if(KeyListener.isKeyTyped("+")) speed++;
        if(KeyListener.isKeyTyped("-")) speed--; 
        if(KeyListener.isKeyTyped("A")) autopilot = !autopilot;
        if(KeyListener.isKeyTyped("R")){
            for(int i=0; i < 36; i++){
                turn(10);
                fireRocket(false);
            }
        }
    }
    
    private void lookForWorms()
    {
        if(!isTouching(Worm.class)) return;
        if(Game.gameStopped) return;
        
        Worm worm = (Worm)getOneIntersectingObject(Worm.class);
        int size = worm.getSize();
        Game.score += size;
        if(speed<3) speed++;
        removeTouching(Worm.class);
        
        if(Game.score >= Game.MAX_SCORE){
            System.out.println("ERROR");
            Game.gameOver();
        }
        
        Greenfoot.playSound("slurp.wav");
        SpaceWorld w = (SpaceWorld)this.getWorld();
        w.checkForRespawn();
    }
    
    private void displayScore(){
        World w = getWorld();
        GreenfootImage background = w.getBackground();

        background.setColor(Color.WHITE);
        background.fillRect(0, 0, 210, 100);
        background.setColor(Color.BLACK);
        background.setFont(new Font("Consolas", false, false, 20));
        int c = 20;
        background.drawString("Score: "+Game.score+" / "+Game.MAX_SCORE, 10, c);
        c+=20;
        background.drawString("God: "+String.valueOf(god)+"  [G]", 10, c);
        c+=20;
        background.drawString("Speed: "+speed+"  [+/-]", 10, c);
        c+=20;
        background.drawString("AP: "+String.valueOf(autopilot)+"  [A]", 10, c);
        c+=20;
    }
    
    private void fireRocket(boolean autoFollow)
    {
        Rocket rocket = new Rocket(this.getRotation(), autoFollow);
        ((SpaceWorld)this.getWorld()).spawnActor(rocket, this.getX(), this.getY());
    }
    
    private void autopilot(){

        if(!autopilot) return;

        int x = getX();
        int y = getY();

        boolean faceWorms = true;
        boolean fireRockets = true;
        boolean escapeLadybugs = true;

        Ladybug nearestLadybug = null;
        Worm nearestWorm = null;

        java.util.List ladybugs = getNeighbours(5000, true, Ladybug.class);
        if(ladybugs.size() > 0){
            nearestLadybug = (Ladybug)ladybugs.get(0);
        }
        
        java.util.List worms = getNeighbours(5000, true, Worm.class);
        if(worms.size() > 0){
            nearestWorm = (Worm)worms.get(0);
        }

        if(nearestWorm != null && faceWorms){
            turnTowards(nearestWorm.getX(), nearestWorm.getY());
        }
        
        if(nearestLadybug != null){

            int nearestLadybug_d_x = getX()-nearestLadybug.getX();
            int nearestLadybug_d_y = getY()-nearestLadybug.getY();
            int nearestLadybugDistance = (int)Math.sqrt((int)(Math.pow(nearestLadybug_d_x, 2) + Math.pow(nearestLadybug_d_y, 2)));

            if(nearestLadybugDistance < 100){
                turnTowards(nearestLadybug.getX(), nearestLadybug.getY());
                turn(180);
            }

            if(fireRockets){
                if(ap_lastRocket+500 > System.currentTimeMillis()) return;
                ap_lastRocket = System.currentTimeMillis();
                turnTowards(nearestLadybug.getX(), nearestLadybug.getY());
                fireRocket(true);
            }

        }
    }
    
    private long lastWaypoint;
    private void drawWaypoints(){
        if(Game.score < Game.MAX_SCORE/2) return;
        if(lastWaypoint + 100 > System.currentTimeMillis()) return;
        lastWaypoint = System.currentTimeMillis();
        GreenfootImage bg = getWorld().getBackground();
        bg.setColor(Color.GREEN);
        bg.drawOval(getX()-2, getY()-2, 4, 4);
    }
    
    public boolean isGod()
    {
        return god;
    }
    
    public void setGod(boolean god)
    {
        this.god = god;
    }
}
