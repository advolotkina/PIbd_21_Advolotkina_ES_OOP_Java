/**
 * Created by Ekaterina Advolotkina on 12.10.2017.
 */
public class Stove
{
    private Pan pan;
    private boolean state;

    public Pan getPan() {
        return pan;
    }

    public void setPan(Pan pan) {
        this.pan = pan;
    }
    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void Cook()
    {
        if (isState())
        {
            while (!pan.IsReady())
            {
                pan.GetHeat();
            }
        }
    }
}
