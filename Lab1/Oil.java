/**
 * Created by Ekaterina Advolotkina on 12.10.2017.
 */
public class Oil
{


    private int temperature = 0;
    private double count;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    private boolean state;

    public int getTemperature() {
        return temperature;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public void GetHeat()
    {
        if (temperature < 100)
        {
            temperature++;
        }
    }

    public Oil GetOil()
    {
        if (isState())
        {
            return new Oil();
        }
        else
        {
            return null;
        }
    }
}
