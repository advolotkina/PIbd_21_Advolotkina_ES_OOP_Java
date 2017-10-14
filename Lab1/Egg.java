/**
 * Created by Ekaterina Advolotkina on 12.10.2017.
 */
public class Egg
{
    private int has_ready = 0;
    private int dirty = 10;
    private boolean haveShell;

    public boolean isHaveShell() {
        return haveShell;
    }

    public void setHaveShell(boolean haveShell) {
        this.haveShell = haveShell;
    }

    public int getHas_ready() {
        return has_ready;
    }

    public int getDirty() {
        return dirty;
    }

    public void setDirty(int value) {
        if (value > -1 && value < 11) dirty = value;
        this.dirty = value;
    }

    public void GetHeat()
    {
        if (has_ready < 10)
        {
            has_ready++;
        }
    }
}
