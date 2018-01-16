
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ekaterina Advolotkina on 26.10.2017.
 */
public class ClassArray<T> {
    private Map<Integer,T> places;
    private T defaultValue;
    private int maxCount;
    public ClassArray(int sizes, T defVal)
    {
        defaultValue = defVal;
        places = new HashMap<Integer, T>();
        maxCount = sizes;
    }

    public int plus(T fish)
    {
        if(places.size()== maxCount){
            return -1;
        }
        for(int i = 0; i < places.size(); i++)
        {
            if (CheckFreePlace(i))
            {
                places.put(i,fish);
                return i;
            }
        }
        places.put(places.size(),fish);
        return places.size()-1;
    }

    public  T minus(int index)
    {
        if(places.containsKey(index)){
            T fish = places.get(index);
            places.remove(index);
            return fish;
        }
        return defaultValue;
    }

    private boolean CheckFreePlace(int index)
    {
        return !places.containsKey(index);
    }

    public T getObject(int ind)
    {
        if(places.containsKey(ind)){
            return places.get(ind);
        }
        return defaultValue;
    }

}
