/**
 * Created by Ekaterina Advolotkina on 12.10.2017.
 */
public class Pan
{
    private Oil[] oil;

    private Egg[] eggs;

    private Salt salt;

    private Pepper pepper;

    private boolean state;

    private boolean readyToGo;

    public boolean isReadyToGo() {
        return Check();
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void Init2(int countOil)
    {
        oil = new Oil[countOil];
    }

    public void Init(int countEggs)
    {
        eggs = new Egg[countEggs];
    }

    public void AddOil(Oil o)
    {
        for (int i = 0; i < oil.length; ++i)
        {
            if (oil[i] == null)
            {
                oil[i] = o;
                return;
            }
        }
    }

    public void AddSalt(Salt s)
    {
        salt = s;
    }

    public void AddPepper(Pepper p)
    {
        pepper = p;
    }

    public void AddEgg(Egg egg)
    {
        for (int i = 0; i < eggs.length; ++i)
        {
            if (eggs[i] == null)
            {
                eggs[i] = egg;
                return;
            }
        }
    }

    private boolean Check()
    {
        if (oil.length == 0)
        {
            return false;
        }

        if (eggs == null)
        {
            return false;
        }
        if (eggs.length == 0)
        {
            return false;
        }

        for (int i = 0; i < eggs.length; ++i)
        {
            if (eggs[i] == null)
            {
                return false;
            }
        }
        return true;
    }

    public void GetHeat()
    {
        if (!Check())
        {
            return;
        }
        if (oil.length > 0)
        {
            if (oil[0].getTemperature() < 100)
            {
                for (int i = 0; i < oil.length; ++i)
                {
                    oil[i].GetHeat();
                }
                boolean flag = false;
                switch (oil[0].getTemperature())
                {
                    case 20: flag = true; break;
                    case 40: flag = true; break;
                    case 60: flag = true; break;
                    case 80: flag = true; break;
                    case 100: flag = true; break;
                }
                if (flag)
                {
                    for (int i = 0; i < eggs.length; ++i)
                    {
                        eggs[i].GetHeat();
                    }
                }
            }
            else
            {
                for (int i = 0; i < eggs.length; ++i)
                {
                    eggs[i].GetHeat();
                }
            }
        }
    }

    public boolean IsReady()
    {
        for (int i = 0; i < oil.length; ++i)
        {
            if (oil[i].getTemperature() < 100)
            {
                return false;
            }
        }
        for (int i = 0; i < eggs.length; ++i)
        {
            if (eggs[i].getHas_ready() < 10)
            {
                return false;
            }
        }
        return true;
    }

    public Egg[] GetEggs()
    {
        return eggs;
    }
}
