import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.HashMap;

/**
 * The KeyListener checks whether a key is pressed currently
 * 
 * @author Manuel Kienlein
 */
public class KeyListener  
{
    private static HashMap<String, Long> register = new HashMap<String, Long>();
    public static final int DELAY = 500;

    public static boolean isKeyTyped(String keyCode){
        if(Greenfoot.isKeyDown(keyCode)){
            if(register.containsKey(keyCode)){
                if(register.get(keyCode) + DELAY < System.currentTimeMillis()){
                    logTyped(keyCode);
                    return true;
                }
            }else{
                logTyped(keyCode);
                return true;
            }
        }
        return false;
    }

    private static void logTyped(String keyCode){
        register.put(keyCode, System.currentTimeMillis());
    }
}
