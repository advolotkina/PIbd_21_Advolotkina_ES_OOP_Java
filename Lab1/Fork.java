/**
 * Created by Ekaterina Advolotkina on 12.10.2017.
 */
public class Fork
{
    public void Break(Egg egg)
    {
        if (egg.isHaveShell())
        {
            egg.setHaveShell(false);
        }
    }

}
