import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Ekaterina Advolotkina on 26.10.2017.
 */
public class Form2 extends JFrame {
    private JTextField textField1;
    private JButton sharkButton;
    private JButton getButton;
    private JPanel oceanPanel;
    private JPanel rootPanel;
    private JPanel getPanel;
    private JList list1;
    private JButton buttonDown;
    private JButton buttonUp;
    private JButton sortButton;
    private JFrame frame;
    SelectSharkPanel dialog;
    Logger log;


    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        Draw();

    }

    Ocean ocean;

    public Form2(){
        log = Logger.getLogger(Form2.class.getName());
        FileHandler fh = null;
        try {
            fh = new FileHandler("C:\\users\\cube\\documents\\mylogs.txt");
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.addHandler(fh);
        frame = new JFrame();
        frame.setBounds(100, 100, 900, 518);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        setContentPane(rootPanel);
        setVisible(true);
        this.setSize(1000, 600);
        ocean = new Ocean(5);
        DefaultListModel listModel = new DefaultListModel();
            for (int i = 1; i < 6; i++) {

                listModel.addElement("Уровень " + i);
            }
            list1.setModel(listModel);
        list1.setSelectedIndex(ocean.getCurrentLevel());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Draw();

        sharkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog = new SelectSharkPanel(frame);
                log.log(Level.INFO, "Открыта форма добавления акулы");
                if (dialog.Execute()) {

                    IAnimal shark = dialog.GetShark();
                    int place = 0;
                    try {
                        if(shark!=null) {
                            place = ocean.PutFishInOcean(shark);
                            JOptionPane.showMessageDialog(null, "Место акулы: " + (place + 1), "",
                                    JOptionPane.INFORMATION_MESSAGE);
                            log.log(Level.INFO, "Добавлена акула на место:" + place);
                        }
                    } catch (OceanOverFlowException e1) {
                            JOptionPane.showMessageDialog(null, e1.getMessage(), "Ошибка переполнения",
                                    JOptionPane.ERROR_MESSAGE);
                    }
                        Draw();
                }
            }
        });
        getButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    try {
                    int index = Integer.parseInt(textField1.getText());
                    IAnimal fish = ocean.GetFishFromOcean(index);
                    Graphics gr = getPanel.getGraphics();
                    fish.setPosition(40, 20);
                    fish.draw(gr);
                    Draw();
                    log.log(Level.INFO, "Забрали акулу с места номер " + index);
                    } catch (OceanIndexOutOfRangeException ex){
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Неверный номер",
                                JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Общая ошибка", JOptionPane.ERROR_MESSAGE);
                    }
            }
        });

        oceanPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                paintComponents(oceanPanel.getGraphics());
            }
        });
        buttonDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ocean.LevelDown();
                list1.setSelectedIndex(ocean.getCurrentLevel());
                Draw();
                log.log(Level.INFO, "Переход на уровень ниже. Текущий уровень: " + (ocean.getCurrentLevel()+1));
            }
        });
        buttonUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ocean.LevelUp();
                list1.setSelectedIndex(ocean.getCurrentLevel());
                Draw();
                log.log(Level.INFO, "Переход на уровень выше. Текущий уровень: " + (ocean.getCurrentLevel()+1));

            }
        });

        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        JMenu menu = new JMenu("Файл");
        menuBar.add(menu);

        JMenuItem menuItemOpen = new JMenuItem("Загрузить");
        menuItemOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("txt file", "txt", "text");
                fileChooser.setFileFilter(filter);

                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        if (ocean.LoadData(file.getAbsolutePath())) {
                            JOptionPane.showMessageDialog(frame, "Загрузили", "", JOptionPane.INFORMATION_MESSAGE);
                            log.log(Level.INFO, "Загрузили файл, находящийся по адресу: " + file.getAbsolutePath());
                        } else {
                            JOptionPane.showMessageDialog(frame, "Что-то пошло не так. Не загрузили.", "", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (OceanOverFlowException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка переполнения",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    Draw();
                }
            }
        });
        menu.add(menuItemOpen);

        JMenuItem menuItemSave = new JMenuItem("Сохранить");
        menuItemSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("txt file", "txt", "text");
                fileChooser.setFileFilter(filter);

                if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    String path = file.getAbsolutePath();

                    if (!file.getAbsolutePath().endsWith(".txt")) {
                        path += ".txt";
                    }
                    if (ocean.SaveData(path)) {
                        JOptionPane.showMessageDialog(frame, "Сохранили", "", JOptionPane.INFORMATION_MESSAGE);
                        log.log(Level.INFO, "Сохранили данные по адресу: " + file.getAbsolutePath());
                        return;
                    } else {
                        JOptionPane.showMessageDialog(frame, "Что-то пошло не так. Не сохранили", "", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }
        });
        menu.add(menuItemSave);

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ocean.Sort();
                Draw();
            }
        });
    }
    private void Draw()
    {
        if(list1.getSelectedIndex() > -1) {
            Graphics gr = oceanPanel.getGraphics();
            ocean.Draw(gr, oceanPanel.getWidth(), oceanPanel.getHeight());
        }
    }

    public static void main(String[] args) {
        new Form2();
    }
}
