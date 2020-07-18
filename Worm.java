import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The worm is a entity that scores points if you run over it in an ambulance
 * 
 * @author Manuel Kienlein
 */
public class Worm extends Actor
{
    private int size;
    
    public Worm(int size)
    {
        setSize(size);
    }
    
    private void setSize(int size){
        if(size > 5 || size < 1) return;
        this.size = size;

        GreenfootImage image = this.getImage();
        image.scale(size*10+10, size*10+10);
    }

    public int getSize(){
        return size;
    }
}
