
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

/**
 * Created by Ekaterina Advolotkina on 26.10.2017.
 */
public class ClassArray<T> implements Comparable<ClassArray<T>>, Iterable<T>, Iterator<T> {
    private Map<Integer,T> places;
    private T defaultValue;
    private int maxCount;
    private int currentIndex = -1;

    public ClassArray(int sizes, T defVal)
    {
        defaultValue = defVal;
        places = new HashMap<Integer, T>();
        maxCount = sizes;
    }

    public int plus(T fish) throws OceanOverFlowException {
        if(places.size()== maxCount){
            throw new OceanOverFlowException();
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

    public  T minus(int index) throws OceanIndexOutOfRangeException {
        if(places.containsKey(index)){
            T fish = places.get(index);
            places.remove(index);
            return fish;
        }
        throw new OceanIndexOutOfRangeException();
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

    @Override
    public int compareTo(ClassArray<T> o) {
        if (places.size() > o.places.size()) {
            return -1;
        } else if (places.size() < o.places.size()) {
            return 1;
        } else {
            Integer[] thisKeys = this.places.keySet().toArray(new Integer[places.size()]);
            Integer[] otherKeys = o.places.keySet().toArray(new Integer[o.places.size()]);
            for (int i = 0; i < this.places.size(); i++) {
                if (this.places.get(thisKeys[i]).getClass().equals(Shark.class)
                        && o.places.get(otherKeys[i]).getClass().equals(TigerShark.class)) {
                    return 1;
                }
                if (this.places.get(thisKeys[i]).getClass().equals(TigerShark.class)
                        && o.places.get(otherKeys[i]).getClass().equals(Shark.class)) {
                    return -1;
                }
                if (this.places.get(thisKeys[i]).getClass().equals(Shark.class)
                        && o.places.get(otherKeys[i]).getClass().equals(Shark.class)) {
                    return ((Shark) this.places.get(thisKeys[i]))
                            .compareTo((Shark) o.places.get(otherKeys[i]));
                }
                if (this.places.get(thisKeys[i]).getClass().equals(TigerShark.class)
                        && o.places.get(otherKeys[i]).getClass().equals(TigerShark.class)) {
                    return ((TigerShark) this.places.get(thisKeys[i]))
                            .compareTo((TigerShark) o.places.get(otherKeys[i]));
                }
            }
        }
        return 0;
    }

    @Override
    public Iterator<T> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        if (currentIndex + 1 >= places.size()) {
            currentIndex = -1;
            return false;
        }
        currentIndex++;
        return true;
    }

    @Override
    public T next() {
        return places.get(currentIndex);
    }
}
