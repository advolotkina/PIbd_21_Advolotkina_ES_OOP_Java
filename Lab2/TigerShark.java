import java.awt.*;
import java.util.Random;

/**
 * Created by Ekaterina Advolotkina on 12.10.2017.
 */
public class TigerShark extends Shark {
    public Color getDopColor() {
        return dopColor;
    }

    public void setDopColor(Color dopColor) {
        this.dopColor = dopColor;
    }

    private Color dopColor;
    private int humansKilled;

    public int getHumansKilled() {
        return humansKilled;
    }

    public void setHumansKilled(int humansKilled) {
        this.humansKilled = humansKilled;
    }


    public TigerShark(int maxNumberOfVictims, int hoursNeededToSleep, double weight, Color color,Color dopColor) {
        super(maxNumberOfVictims, hoursNeededToSleep, weight, color);
        setHumansKilled(humansKilled);
        setDopColor(dopColor);
    }

    protected void killHumans(int count)
    {
        for(int i = 0; i < count; i++)
        {
            Random rand = new Random();
            setHumansKilled(rand.nextInt(2)+getHumansKilled());
        }
    }

    @Override
    protected void drawShark(Graphics g)
    {
          super.drawShark(g);
          g.setColor(dopColor);
        for(int i = 0; i < 40; i = i + 5)
        {
            g.drawOval(startPosX + 20 + i, startPosY + 29, 3, 6);
        }
        for(int i = 0; i < 20; i = i + 5)
        {
            g.drawOval(startPosX + 29 + i, startPosY + 23, 3, 6);
        }
        for (int i = 0; i < 15; i = i + 5)
        {
            g.drawOval(startPosX + 3 + i, startPosY + 16+i, 3, 6);
        }
        for (int i = 0; i < 12; i = i + 5)
        {
            g.drawOval(startPosX + 4 + i, startPosY + 21 + i, 3, 6);
        }
        for (int i = 0; i < 9; i = i + 5)
        {
            g.drawOval(startPosX + 4 + i, startPosY + 27 + i, 3, 6);
        }
    }

    public TigerShark(String info)
    {
        super(info);
        String[] strs = info.split(";");
        if(strs.length == 6)
        {
            setMaxNumberOfVictims(Integer.parseInt(strs[0]));
            setHoursNeededToSleep(Integer.parseInt(strs[1]));
            setWeight(Double.parseDouble(strs[2]));
            setBodyPattern(Color.decode(strs[3]));
            setHumansKilled(Integer.parseInt(strs[4]));
            setDopColor(Color.decode(strs[5]));
        }
    }

    public String getInfo() {
        String hex = "#"+Integer.toHexString(getBodyPattern().getRGB()).substring(2);
        String hex2 = "#"+Integer.toHexString(getDopColor().getRGB()).substring(2);
        return getMaxNumberOfVictims() + ";" + getHoursNeededToSleep() + ";" + getWeight() + ";" + hex+ ";"+getHumansKilled()+";" + hex2;
    }
}
