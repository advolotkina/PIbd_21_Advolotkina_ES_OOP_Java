import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Ekaterina Advolotkina on 27.10.2017.
 */
public class Ocean {
    ClassArray<IAnimal> ocean;
    ArrayList<ClassArray<IAnimal>> oceanLevels;

    public int getCurrentLevel() {
        return currentLevel;
    }

    int currentLevel;

    int countPlaces = 20;

    int placeSizeWidth = 130;

    int placeSizeHeight = 100;


    public Ocean()
    {
        ocean = new ClassArray<IAnimal>(countPlaces, null);
    }

    public Ocean(int countLevels){
        oceanLevels = new ArrayList<ClassArray<IAnimal>>();
        for(int i = 0; i < countLevels;i++){
            ocean = new ClassArray<IAnimal>(countPlaces,null);
            oceanLevels.add(ocean);
        }
    }

    public void LevelUp(){
        if(currentLevel + 1 < oceanLevels.size()){
            currentLevel++;
        }
    }

    public void LevelDown(){
        if(currentLevel > 0){
            currentLevel--;
        }
    }

    public int PutFishInOcean(IAnimal fish)
    {
        return oceanLevels.get(currentLevel).plus(fish);
    }

    public IAnimal GetFishFromOcean(int n)
    {
        return oceanLevels.get(currentLevel).minus(n);
    }

    private void DrawOcean(Graphics g)
    {
        g.setColor(Color.CYAN);
        g.drawOval(0,0,550,550);
        g.setColor(Color.BLUE);
        g.fillRect(0,0,550,550);
        for (int i = 0; i < countPlaces; i++)
        {
            g.setColor(Color.CYAN);
            g.drawOval((int)i / 5 * placeSizeWidth + 5, i % 5 * placeSizeHeight,placeSizeWidth,placeSizeHeight);

        }
    }

    public void Draw(Graphics g, int width, int height)
    {
        DrawOcean(g);
        for(int i = 0; i < countPlaces; i++)
        {
            IAnimal fish = oceanLevels.get(currentLevel).getObject(i);

            if (fish != null)
            {
                fish.setPosition(15+(int)i/5*placeSizeWidth+5,i%5*placeSizeHeight+15);
                fish.draw(g);
            }
        }
    }
}
