import java.awt.*;
import java.util.Random;

/**
 * Created by Ekaterina Advolotkina on 12.10.2017.
 */
public class Shark extends CartilaginousFish {

    public Shark(int maxNumberOfVictims, int hoursNeededToSleep, double weight, Color color)
    {
        this.setMaxNumberOfVictims(maxNumberOfVictims);
        this.setHoursNeededToSleep(hoursNeededToSleep);
        this.setWeight(weight);
        this.setBodyPattern(color);
        this.sleptHours = 0;
        this.victims = 0;
        Random rand = new Random();
        this.setPosition(rand.nextInt(30),rand.nextInt(100));
    }
    @Override
    public void draw(Graphics g) {
        drawShark(g);
    }
    protected void drawShark(Graphics g)
    {
        g.setColor(getBodyPattern());

        Polygon p = new Polygon();
        p.addPoint(startPosX,startPosY+11);
        p.addPoint(startPosX, startPosY + 11);
        p.addPoint(startPosX + 19, startPosY + 28);
        p.addPoint(startPosX + 41, startPosY + 21);
        p.addPoint(startPosX + 52, startPosY + 26);
        p.addPoint(startPosX + 52, startPosY + 8);
        p.addPoint(startPosX + 65, startPosY + 26);
        p.addPoint(startPosX + 78, startPosY + 23);
        p.addPoint(startPosX + 96, startPosY + 31);
        p.addPoint(startPosX + 86, startPosY + 39);
        p.addPoint(startPosX + 68, startPosY + 42);
        p.addPoint(startPosX + 57, startPosY + 57);
        p.addPoint(startPosX + 57, startPosY + 42);
        p.addPoint(startPosX + 44, startPosY + 44);
        p.addPoint(startPosX + 18, startPosY + 39);
        p.addPoint(startPosX, startPosY + 57);
        p.addPoint(startPosX + 5, startPosY + 34);
        g.fillPolygon(p);

        g.setColor(Color.white);

        g.drawLine(startPosX + 65, startPosY + 29, startPosX + 62, startPosY + 30);
        g.drawLine( startPosX + 62, startPosY + 30, startPosX + 65, startPosY + 35);
        g.drawLine( startPosX + 69, startPosY + 29, startPosX + 66, startPosY + 30);
        g.drawLine( startPosX + 66, startPosY + 30, startPosX + 69, startPosY + 35);
        g.drawLine( startPosX + 73, startPosY + 29, startPosX + 70, startPosY + 30);
        g.drawLine( startPosX + 70, startPosY + 30, startPosX + 73, startPosY + 35);
        g.drawOval( startPosX + 81, startPosY + 29, 4, 3);
        g.drawLine( startPosX + 78, startPosY + 36, startPosX + 86, startPosY + 39);

    }

    @Override
    public void move(Graphics g)
    {
        startPosX = startPosX + (int)Math.abs(2*getHoursNeededToSleep() - 0.5*getWeight());
        Random rand = new Random();
        startPosY = startPosY + 10*rand.nextInt(4);
        draw(g);
    }



    @Override
    public double getWeight() {
        return super.getWeight();
    }

    @Override
    protected void setWeight(double value) {
        if(value > 100 && value < 300)
        {
             super.setWeight(value);
        }else
        {
            super.setWeight(150);
        }
    }

    @Override
    public int getHoursNeededToSleep() {
        return super.getHoursNeededToSleep();
    }

    @Override
    protected void setHoursNeededToSleep(int value) {
        if(value > 0 && value < 10)
        {
            super.setHoursNeededToSleep(value);
        }
        else
        {
            super.setHoursNeededToSleep(8);
        }
    }

    @Override
    public int getMaxNumberOfVictims() {
        return super.getMaxNumberOfVictims();
    }

    @Override
    protected void setMaxNumberOfVictims(int value) {
        if(value > 0 && value < 10)
        {
            super.setMaxNumberOfVictims(value);
        } else
        {
            super.setMaxNumberOfVictims(8);
        }
    }

    @Override
    public void setBodyPattern(Color bodyPattern) {
        super.setBodyPattern(bodyPattern);
    }

    public Shark(String info)
    {
        String[] strs = info.split(";");
        if(strs.length == 4)
        {
            setMaxNumberOfVictims(Integer.parseInt(strs[0]));
            setHoursNeededToSleep(Integer.parseInt(strs[1]));
            setWeight(Double.parseDouble(strs[2]));
            setBodyPattern(Color.decode(strs[3]));
        }
        this.sleptHours = 0;
        this.victims = 0;
    }

    @Override
    public String getInfo() {
        String hex = "#"+Integer.toHexString(getBodyPattern().getRGB()).substring(2);
        return getMaxNumberOfVictims() + ";" + getHoursNeededToSleep() + ";" + getWeight() + ";" + hex;
    }
}
