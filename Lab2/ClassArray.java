import java.util.ArrayList;

/**
 * Created by Ekaterina Advolotkina on 26.10.2017.
 */
public class ClassArray<T> {
    private ArrayList<T> places;
    private T defaultValue;
    private int sizes = 20;
    public ClassArray(int sizes, T defVal)
    {
        defaultValue = defVal;
        places = new ArrayList<T>();
        for(int i = 0; i < sizes; i++)
        {
            places.add(defaultValue);
        }
    }

    public int plus(T fish)
    {
        for(int i = 0; i < places.size(); i++)
        {
            if (CheckFreePlace(i))
            {
                places.set(i,fish);
                return i;
            }
        }
        return -1;
    }

    public  T minus( int index)
    {
        if (!CheckFreePlace(index))
        {
            T fish = places.get(index);
            places.set(index,defaultValue);
            return fish;
        }
        return defaultValue;
    }

    private boolean CheckFreePlace(int index)
    {
        if(index < 0 || index > places.size())
        {
            return false;
        }
        if(places.get(index)== null)
        {
            return true;
        }
        if (places.get(index).equals(defaultValue))
        {
            return true;
        }
        return false;
    }

    public T getObject(int ind)
    {
        if(ind > -1 && ind < places.size())
        {
            return places.get(ind);
        }
        return defaultValue;
    }
}
