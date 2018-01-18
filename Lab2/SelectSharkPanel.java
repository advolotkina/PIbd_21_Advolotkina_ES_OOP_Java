/**
 * Created by Ekaterina Advolotkina on 16.01.2018.
 */
import java.awt.Color;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SelectSharkPanel extends JDialog {
    DropPanel panel = new DropPanel();
    private boolean r;

    public SelectSharkPanel(JFrame parent) {
        super(parent, true);
        getContentPane().setLayout(null);
        initialize();
    }

    public boolean Execute() {
        setVisible(true);
        return r;
    }

    public IAnimal GetShark() {
        return panel.GetShark();
    }

    private void initialize() {
        setBounds(100, 100, 503, 400);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        DragLabel sharkLabel = new DragLabel("Shark");
        sharkLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sharkLabel.setBounds(10, 26, 101, 35);
        sharkLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
        getContentPane().add(sharkLabel);

        DragLabel tigerSharkLabel = new DragLabel("TigerShark");
        tigerSharkLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tigerSharkLabel.setBounds(10, 72, 101, 35);
        tigerSharkLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
        getContentPane().add(tigerSharkLabel);
        panel.setBackground(Color.BLUE);

        panel.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.setBounds(170, 26, 300, 130);
        getContentPane().add(panel);

        DropLabel labelMainColor = new DropLabel("MainColor");
        labelMainColor.setHorizontalAlignment(SwingConstants.CENTER);
        labelMainColor.setLabelFor(panel);
        labelMainColor.setBorder(new LineBorder(new Color(0, 0, 0)));
        labelMainColor.setBounds(200, 163, 101, 35);
        getContentPane().add(labelMainColor);

        DropLabel labelDopColor = new DropLabel("DopColor");
        labelDopColor.setHorizontalAlignment(SwingConstants.CENTER);
        labelDopColor.setLabelFor(panel);
        labelDopColor.setBorder(new LineBorder(new Color(0, 0, 0)));
        labelDopColor.setBounds(351, 163, 101, 35);
        getContentPane().add(labelDopColor);

        DragPanel RED = new DragPanel();
        RED.setBackground(Color.RED);
        RED.setBounds(178, 234, 46, 33);
        getContentPane().add(RED);

        DragPanel YELLOW = new DragPanel();
        YELLOW.setBackground(Color.YELLOW);
        YELLOW.setBounds(178, 294, 46, 33);
        getContentPane().add(YELLOW);

        DragPanel MAGENTA = new DragPanel();
        MAGENTA.setBackground(Color.MAGENTA);
        MAGENTA.setBounds(250, 234, 46, 33);
        getContentPane().add(MAGENTA);

        DragPanel GREEN = new DragPanel();
        GREEN.setBackground(Color.GREEN);
        GREEN.setBounds(250, 294, 46, 33);
        getContentPane().add(GREEN);

        DragPanel BLUE = new DragPanel();
        BLUE.setBackground(Color.BLUE);
        BLUE.setBounds(322, 234, 46, 33);
        getContentPane().add(BLUE);

        DragPanel CYAN = new DragPanel();
        CYAN.setBackground(Color.CYAN);
        CYAN.setBounds(322, 294, 46, 33);
        getContentPane().add(CYAN);

        DragPanel ORANGE = new DragPanel();
        ORANGE.setBackground(Color.ORANGE);
        ORANGE.setBounds(388, 234, 46, 33);
        getContentPane().add(ORANGE);

        DragPanel PINK = new DragPanel();
        PINK.setBackground(Color.PINK);
        PINK.setBounds(388, 294, 46, 33);
        getContentPane().add(PINK);

        JButton buttonCancel = new JButton("Отмена");
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                r = false;
                dispose();
            }
        });
        buttonCancel.setBounds(10, 327, 121, 23);
        getContentPane().add(buttonCancel);

        JButton buttonAdd = new JButton("Добавить");
        buttonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                r = true;
                dispose();
            }
        });
        buttonAdd.setBounds(10, 293, 121, 23);
        getContentPane().add(buttonAdd);
    }
}
