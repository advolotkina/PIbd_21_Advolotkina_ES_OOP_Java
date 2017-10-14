import java.awt.*;

/**
 * Created by Ekaterina Advolotkina on 12.10.2017.
 */
public interface IAnimal {
    void move(Graphics g);

    void draw(Graphics g);

    void setPosition(int x, int y);

    void eat(int count);

    void sleep(int count);

    void breath();

    void makeNoise();
}
