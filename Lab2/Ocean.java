import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public boolean SaveData(String filename) {
        File file = new File(filename);
        if (file.exists()) {
            file.delete();
        }
        try (FileOutputStream fileStream = new FileOutputStream(file)) {
            try (BufferedOutputStream bs = new BufferedOutputStream(fileStream)) {
                String s = "CountLeveles:" + oceanLevels.size() + System.lineSeparator();

                ByteArrayOutputStream bos = new ByteArrayOutputStream();

                for (int i = 0; i < s.length(); i++) {
                    bos.write(s.charAt(i));
                }

                byte[] info = bos.toByteArray();
                fileStream.write(info, 0, info.length);

                for (ClassArray<IAnimal> level : oceanLevels) {
                    bos = new ByteArrayOutputStream();
                    s = "Level" + System.lineSeparator();

                    for (int i = 0; i < s.length(); i++) {
                        bos.write(s.charAt(i));
                    }
                    info = bos.toByteArray();
                    fileStream.write(info, 0, info.length);

                    for (int i = 0; i < countPlaces; i++) {
                        IAnimal shark = level.getObject(i);

                        if (shark != null) {
                            bos = new ByteArrayOutputStream();
                            String sharkInfoStr = shark.getClass().getName() + ":" + shark.getInfo()
                                    + System.lineSeparator();
                            for (int j = 0; j < sharkInfoStr.length(); j++) {
                                bos.write(sharkInfoStr.charAt(j));
                            }
                            info = bos.toByteArray();
                            fileStream.write(info, 0, info.length);
                        }
                    }
                }
            }
            fileStream.close();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public boolean LoadData(String fileName)  {
        File file = new File(fileName);
        if (!file.exists()) {
            return false;
        }
        try (FileInputStream fileStream = new FileInputStream(fileName)) {
            String s = "";
            try (BufferedInputStream bs = new BufferedInputStream(fileStream)) {

                Path path = Paths.get(file.getAbsolutePath());
                byte[] b = new byte[fileStream.available()];
                b = Files.readAllBytes(path);

                ByteArrayInputStream bos = new ByteArrayInputStream(b);
                String value = new String(b, StandardCharsets.UTF_8);

                while (bos.read(b, 0, b.length) > 0) {
                    s += value;
                }

                s = s.replace("\r", "");
                String[] strs = s.split("\n");
                if (strs[0].contains("CountLeveles")) {
                    if (ocean != null) {
                        oceanLevels.clear();
                    }
                    oceanLevels = new ArrayList<ClassArray<IAnimal>>();
                } else
                    return false;

                int counter = -1;
                for (int i = 0; i < strs.length; i++) {
                    if (strs[i].startsWith("Level")) {
                        counter++;
                        oceanLevels.add(new ClassArray<IAnimal>(countPlaces, null));
                    } else if (strs[i].startsWith("Shark")) {
                        IAnimal shark = new Shark(strs[i].split(":")[1]);
                        int number = oceanLevels.get(counter).plus(shark);
                        if (number == -1) {
                            return false;
                        }
                    } else if (strs[i].startsWith("TigerShark")) {
                        IAnimal shark = new TigerShark(strs[i].split(":")[1]);
                        int number = oceanLevels.get(counter).plus(shark);
                        if (number == -1) {
                            return false;
                        }
                    }
                }
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
}
