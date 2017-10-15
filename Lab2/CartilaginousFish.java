import java.awt.*;

/**
 * Created by Ekaterina Advolotkina on 12.10.2017.
 */
public abstract class CartilaginousFish implements IAnimal {
    protected int startPosX;
    protected int startPosY;

    protected int victims;
    protected int sleptHours;

    protected Color bodyPattern;
    protected double weight;
    protected int hoursNeededToSleep;
    protected int maxNumberOfVictims;

    public int getMaxNumberOfVictims() {
        return maxNumberOfVictims;
    }

    protected void setMaxNumberOfVictims(int maxNumberOfVictims) {
        this.maxNumberOfVictims = maxNumberOfVictims;
    }



    public int getHoursNeededToSleep() {
        return hoursNeededToSleep;
    }

    protected void setHoursNeededToSleep(int hoursNeededToSleep) {
        this.hoursNeededToSleep = hoursNeededToSleep;
    }



    public double getWeight() {
        return weight;
    }

    protected void setWeight(double weight) {
        this.weight = weight;
    }

    public Color getBodyPattern() {
        return bodyPattern;
    }

    protected void setBodyPattern(Color bodyPattern) {
        this.bodyPattern = bodyPattern;
    }

    public abstract void draw(Graphics g);

    public abstract void move(Graphics g);

    public void eat(int count)
    {
        hunt(count);
    }

    public void hunt(int count)
    {
        if (victims + count < getMaxNumberOfVictims())
        {
            victims = victims + count;
        } else if(victims + count == getMaxNumberOfVictims())
        {
            sleptHours = 0;
            victims = getMaxNumberOfVictims();
        }
    }

    public void setPosition(int x, int y)
    {
        startPosX = x;
        startPosY = y;
    }

    public void sleep(int count)
    {
        if(sleptHours + count < getHoursNeededToSleep())
        {
            sleptHours = sleptHours + count;
        } else if(sleptHours + count == getHoursNeededToSleep())
        {
            victims = 0;
        }
    }

    public void breath()
    {
        breathOxygen();
    }

    public void breathOxygen()
    {

    }

    public void makeNoise()
    {
        makeUltraSound();
    }

    public void makeUltraSound()
    {

    }
}
