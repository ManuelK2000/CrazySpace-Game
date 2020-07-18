import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A toast is a message that will be displayed in the center of the screen
 * 
 * @author Manuel Kienlein
 */
public class Toast extends Actor
{
    private static String toastMessage = null;
    private GreenfootImage image = null;

    public Toast()
    {
        toastMessage = null;
    }

    public void act()
    {
        if (toastMessage != null){
            image = new GreenfootImage(toastMessage, 48, Color.WHITE, null);
        }
        setImage(image);
    }
    
    public static void setText(String message){
        toastMessage = message;
    }
}
