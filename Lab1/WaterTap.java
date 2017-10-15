/**
 * Created by Ekaterina Advolotkina on 12.10.2017.
 */
public class WaterTap
{
    private boolean state;

    public WaterTap() {
        this.state = false;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }



    public void Wash(Egg egg)
    {
        if (isState())
        {
            egg.setDirty(0);
        }
    }
}