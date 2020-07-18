import greenfoot.*;

/**
 * This class contains variables and controls the game
 * 
 * @author Manuel Kienlein
 */
public class Game  
{
    public static final int MAX_SCORE = 100;
    public static int score;
    
    public static int worldWidth;
    public static int worldHeight;
    
    public static boolean gameStopped = false;
    
    public static void gameOver(){        
        Greenfoot.playSound("fanfare.wav");
        
        int centerX = worldWidth/2;
        int centerY = worldHeight/2;
        Toast.setText("GAME OVER");

        Thread thread = new Thread(){
                public void run(){
                    try{Thread.sleep(4000);}catch(Exception e){}
                    Greenfoot.stop();
                }
            };
        gameStopped = true;
        thread.start();
    }
    
    public static void reset()
    {
        Game.gameStopped = false;
        Game.score = 0;
    }
}
