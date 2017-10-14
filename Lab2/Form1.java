import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

/**
 * Created by Ekaterina Advolotkina on 13.10.2017.
 */
public class Form1 extends JFrame{
    private JTextField textBox1;
    private JTextField texBox2;
    private JTextField textBox3;
    private JButton button1;
    private JButton button2;
    private JButton sharkButton;
    private JButton tigerSharkButton;
    private JButton moveButton;
    private JPanel rootPanel;

    private JPanel panel;
    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        inter.draw(g);
    }

    Color color;
    Color dopcColor;
    int maxNumberOfVictims;
    int hoursNeededToSleep;
    int weight;

    private IAnimal inter;
    public Form1(){
        color = Color.gray;
        dopcColor = Color.black;
        maxNumberOfVictims = 10;
        hoursNeededToSleep = 10;
        weight = 300;
        setContentPane(rootPanel);
        this.setSize(1000, 1000);
        setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        sharkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkFields())
                {
                    inter = new Shark(maxNumberOfVictims, hoursNeededToSleep, weight, color);
                    Graphics gr = panel.getGraphics();
                    gr.clearRect(0,0,panel.getWidth(),panel.getHeight());
                    inter.draw(gr);
                }
            }
        });
        tigerSharkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkFields())
                {
                    inter = new TigerShark(maxNumberOfVictims, hoursNeededToSleep, weight, color, dopcColor);
                    Graphics gr = panel.getGraphics();
                    gr.clearRect(0,0,panel.getWidth(),panel.getHeight());
                    inter.draw(gr);
                }
            }
        });
        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inter != null)
                {
                    Graphics gr = panel.getGraphics();
                    gr.clearRect(0,0,panel.getWidth(),panel.getHeight());
                    inter.move(gr);
                }
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Color initialBackground = button1.getBackground();
                Color newColor = JColorChooser.showDialog(null, "JColorChooser Sample", initialBackground);
                if (newColor == null) {
                    return;
                }
                color = newColor;
                button1.setBackground(newColor);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color initialBackground = button2.getBackground();
                Color newColor = JColorChooser.showDialog(null, "JColorChooser Sample", initialBackground);
                if (newColor == null) {
                    return;
                }
                dopcColor = newColor;
                button2.setBackground(newColor);
            }
        });
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                paintComponents(panel.getGraphics());
            }
        });
    }
    private boolean checkFields()
    {
        try {
            Integer.parseInt(textBox1.getText());
            Integer.parseInt(texBox2.getText());
            Integer.parseInt(textBox3.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        new Form1();
    }
}
