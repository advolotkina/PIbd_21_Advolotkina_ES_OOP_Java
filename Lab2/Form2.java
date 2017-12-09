import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Created by Ekaterina Advolotkina on 26.10.2017.
 */
public class Form2 extends JFrame {
    private JTextField textField1;
    private JButton sharkButton;
    private JButton tigersharkButton;
    private JButton getButton;
    private JPanel oceanPanel;
    private JPanel rootPanel;
    private JPanel getPanel;

    private JPanel panel;
    Ocean ocean;
    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        Draw();

    }


    public Form2(){
        setContentPane(rootPanel);
        setVisible(true);
        this.setSize(1000, 600);
        ocean = new Ocean();
        Draw();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        sharkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color newColor = JColorChooser.showDialog(null, "Выберите цвет",Color.gray);
                if (newColor == null) {
                    return;
                }
                    IAnimal fish = new Shark(10, 10, 300, newColor);
                    int place = ocean.PutFishInOcean(fish);

                    Draw();
                    JOptionPane.showMessageDialog(oceanPanel,"Ваше место: " + place);
            }
        });
        tigersharkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    Color newColor = JColorChooser.showDialog(null, "Выберите цвет",Color.gray);
                if (newColor == null) {
                    return;
                } else{
                    Color newColor2 = JColorChooser.showDialog(null, "Выберите цвет",Color.black);
                    if(newColor2 == null){
                        return;
                    } else{
                        IAnimal fish = new TigerShark(10, 10, 300, newColor, newColor2);
                        int place = ocean.PutFishInOcean(fish);
                        Draw();
                        JOptionPane.showMessageDialog(oceanPanel,"Ваше место: " + place);
                    }
                }
            }
        });
        getButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int index = Integer.parseInt(textField1.getText());
                    IAnimal fish = ocean.GetFishFromOcean(index);
                    if (fish != null)
                    {
                        Graphics gr = getPanel.getGraphics();
                        fish.setPosition(40, 20);
                        fish.draw(gr);
                        Draw();
                    }
                } catch (Exception ex){

                }
            }
        });

        oceanPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                paintComponents(oceanPanel.getGraphics());
            }
        });
    }
    private void Draw()
    {
        Graphics gr = oceanPanel.getGraphics();
        ocean.Draw(gr,oceanPanel.getWidth(),oceanPanel.getHeight());
    }

    public static void main(String[] args) {
        new Form2();
    }
}
