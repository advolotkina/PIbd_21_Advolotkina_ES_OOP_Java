import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ekaterina Advolotkina on 12.10.2017.
 */
public class Form1 extends JFrame {
    private JPanel rootPanel;
    private JSpinner numericUpDownOil;
    private JSpinner numericUpDownPepper;
    private JSpinner numericUpDownSalt;
    private JSpinner numericUpDownEggs;
    private JRadioButton radioButtonOpen;
    private JRadioButton radioButtonClose;
    private JButton buttonWash;
    private JCheckBox checkBoxOn;
    private JButton buttonAddPan;
    private JButton buttonCook;
    private JButton buttonGetPan;
    private JButton buttonBreakEggs;
    private JButton buttonAddOil;
    private JButton buttonAddEggs;
    private JButton buttonAddSalt;
    private JButton buttonAddPepper;
    private JButton buttonGetEggs;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JPanel panel6;

    private Egg[] eggs;

    private Salt salt;

    private Pepper pepper;

    private WaterTap waterTap;

    private Fork fork;

    private Pan pan;

    private Stove stove;

    private Oil[] oil;

    public Form1() {
        waterTap = new WaterTap();
        fork = new Fork();
        pan = new Pan();
        stove = new Stove();
        setContentPane(rootPanel);
        //setLayout(new GridLayout(2, 3));
        this.setSize(1200, 1000);
        setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        radioButtonOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radioButtonOpen.isSelected()) {
                    waterTap.setState(true);
                    radioButtonClose.setSelected(false);
                }
            }
        });
        radioButtonClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radioButtonClose.isSelected()) {
                    waterTap.setState(false);
                    radioButtonOpen.setSelected(false);
                }
            }
        });
        buttonWash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if ((int) numericUpDownEggs.getValue() > 0) {
                    if (!waterTap.isState()) {
                        JOptionPane.showMessageDialog(null, "Кран закрыт, как мыть? Ошибка логики");
                        return;
                    }
                    eggs = new Egg[(int) (numericUpDownEggs.getValue())];
                    pan.Init((int) (numericUpDownEggs.getValue()));
                    for (int i = 0; i < eggs.length; ++i) {
                        eggs[i] = new Egg();
                    }
                    for (int i = 0; i < eggs.length; ++i) {
                        waterTap.Wash(eggs[i]);
                    }
                    numericUpDownEggs.setEnabled(false);
                    radioButtonClose.setSelected(true);
                    radioButtonOpen.setSelected(false);
                    JOptionPane.showMessageDialog(null, "Скорлупу помыли, можно разбивать");
                } else {
                    JOptionPane.showMessageDialog(null, "Яиц то нет, что мыть?");
                }
            }
        });
        buttonBreakEggs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (eggs == null) {
                    JOptionPane.showMessageDialog(null, "Яиц то нет, что разбивать? Ошибка логики");
                    return;
                }
                if (eggs.length == 0) {
                    JOptionPane.showMessageDialog(null, "Яиц то нет, что разбивать? Ошибка логики");
                    return;
                }
                for (int i = 0; i < eggs.length; ++i) {
                    if (eggs[i].getDirty() > 0) {
                        JOptionPane.showMessageDialog(null, "Скорлупа грязная!!! Помыть бы ее сначала, а уж потом чистить. Ошибка логики");
                        return;
                    }
                }


                for (int i = 0; i < eggs.length; ++i) {
                    fork.Break(eggs[i]);
                }
                buttonAddEggs.setEnabled(true);
                JOptionPane.showMessageDialog(null, "Скорлупу разбили, можно жарить");
            }
        });
        buttonAddSalt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salt = new Salt();
                salt.setCount((int) numericUpDownSalt.getValue());
                if (salt.getCount() > 0) {
                    pan.AddSalt(salt);
                    JOptionPane.showMessageDialog(null, "Соль добавили");
                } else {
                    JOptionPane.showMessageDialog(null, "Соли же нет, что добавлять? Ошибка логики");
                }
            }
        });
        buttonAddPepper.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pepper = new Pepper();
                pepper.setCount((int) numericUpDownPepper.getValue());
                if (pepper.getCount() > 0) {
                    pan.AddPepper(pepper);
                    JOptionPane.showMessageDialog(null, "Перец добавили");
                } else {
                    JOptionPane.showMessageDialog(null, "Перца же нет, что добавлять? Ошибка логики");
                }
            }
        });
        buttonAddOil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int oilCount = (int) (numericUpDownOil.getValue());
                if (oilCount <= 0) {
                    JOptionPane.showMessageDialog(null, "Масла нет, что заливать? Ошибка логики");
                    return;
                }
                oil = new Oil[(int) (numericUpDownOil.getValue())];
                pan.Init2(oilCount);
                for (int i = 0; i < oil.length; ++i) {
                    oil[i] = new Oil();
                }
                for (int i = 0; i < oil.length; ++i) {
                    pan.AddOil(oil[i]);
                }
                buttonAddSalt.setEnabled(true);
                buttonAddPepper.setEnabled(true);

                JOptionPane.showMessageDialog(null, "Масло залили");
            }
        });
        buttonAddEggs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (eggs == null) {
                    JOptionPane.showMessageDialog(null, "Яиц то нет, что жарить собрались? Ошибка логики");
                    return;
                }
                if (eggs.length == 0) {
                    JOptionPane.showMessageDialog(null, "Яиц то нет, что жарить собрались? Ошибка логики");
                    return;
                }
                if (oil == null) {
                    JOptionPane.showMessageDialog(null, "Следует сначала добавить масло. Ошибка логики");
                    return;
                }
                if (oil.length <= 0) {
                    JOptionPane.showMessageDialog(null, "Следует сначала добавить масло. Ошибка логики");
                    return;
                }

                if (!stove.isState() || !pan.isState()) {
                    JOptionPane.showMessageDialog(null, "Следует сначала раскалить масло. Ошибка логики");
                    return;
                }


                for (int i = 0; i < eggs.length; ++i) {
                    if (eggs[i].getDirty() > 0) {
                        JOptionPane.showMessageDialog(null, "Скорлупа грязная!!! Какой ее варить, а ну мыть ее быстро!!! Ошибка логики");
                        return;
                    }
                    if (eggs[i].isHaveShell()) {
                        JOptionPane.showMessageDialog(null, "Для начала следует очистить яйца от скорлупы. Ошибка логики");
                        return;
                    }
                }
                for (int i = 0; i < eggs.length; ++i) {
                    pan.AddEgg(eggs[i]);
                }
                buttonAddSalt.setEnabled(true);
                buttonAddPepper.setEnabled(true);
                JOptionPane.showMessageDialog(null, "Яйца добавили");
            }
        });
        buttonAddPan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stove.setPan(pan);
                buttonCook.setEnabled(true);
                pan.setState(true);
                JOptionPane.showMessageDialog(null, "Сковорода на плите");
            }
        });
        checkBoxOn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stove.setState(checkBoxOn.isSelected());
            }
        });
        buttonCook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!pan.isReadyToGo()) {
                    JOptionPane.showMessageDialog(null, "У нас не все готово к жарке! Ошибка логики");
                    return;
                }
                if (!stove.isState()) {
                    JOptionPane.showMessageDialog(null, "Жарить собрались энергией космоса или все же включим плиту? Ошибка логики");
                    return;
                }
                stove.Cook();
                if (stove.getPan().IsReady()) {
                    buttonGetPan.setEnabled(true);
                    buttonGetEggs.setEnabled(true);
                    checkBoxOn.setSelected(false);
                    JOptionPane.showMessageDialog(null, "Готова!");
                } else {
                    JOptionPane.showMessageDialog(null, "Что-то пошло не так, яичница не пожарилась. Ошибка логики");
                    return;
                }
            }
        });
        buttonGetPan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Убрали с плиты");
            }
        });
        buttonGetEggs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eggs = pan.GetEggs();
                JOptionPane.showMessageDialog(null, "Мы сделали это! Приятного аппетита!");
            }
        });
    }

    public static void main(String[] args) {
        new Form1();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 4, new Insets(0, 0, 0, 0), -1, -1));
        rootPanel.setBackground(new Color(-9543));
        panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 9, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-9543));
        panel1.setEnabled(false);
        rootPanel.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(221, 24), null, 0, false));
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font(panel1.getFont().getName(), panel1.getFont().getStyle(), 16)));
        final JLabel label1 = new JLabel();
        label1.setFont(new Font("Minsk", label1.getFont().getStyle(), 24));
        label1.setText("Ингредиенты");
        panel1.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 9, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setBackground(new Color(-2578));
        label2.setFont(new Font(label2.getFont().getName(), label2.getFont().getStyle(), 20));
        label2.setText("Яйца");
        panel1.add(label2, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 7, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setBackground(new Color(-2578));
        label3.setFont(new Font(label3.getFont().getName(), label3.getFont().getStyle(), 20));
        label3.setText("Масло");
        panel1.add(label3, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setBackground(new Color(-2578));
        label4.setFont(new Font(label4.getFont().getName(), label4.getFont().getStyle(), 20));
        label4.setText("Соль");
        panel1.add(label4, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setBackground(new Color(-2578));
        label5.setFont(new Font(label5.getFont().getName(), label5.getFont().getStyle(), 20));
        label5.setText("Перец");
        panel1.add(label5, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        numericUpDownEggs = new JSpinner();
        numericUpDownEggs.setFont(new Font(numericUpDownEggs.getFont().getName(), numericUpDownEggs.getFont().getStyle(), 16));
        panel1.add(numericUpDownEggs, new com.intellij.uiDesigner.core.GridConstraints(4, 7, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        numericUpDownOil = new JSpinner();
        numericUpDownOil.setFont(new Font(numericUpDownOil.getFont().getName(), numericUpDownOil.getFont().getStyle(), 16));
        panel1.add(numericUpDownOil, new com.intellij.uiDesigner.core.GridConstraints(1, 7, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        numericUpDownPepper = new JSpinner();
        numericUpDownPepper.setFont(new Font(numericUpDownPepper.getFont().getName(), numericUpDownPepper.getFont().getStyle(), 16));
        panel1.add(numericUpDownPepper, new com.intellij.uiDesigner.core.GridConstraints(2, 7, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        numericUpDownSalt = new JSpinner();
        numericUpDownSalt.setFont(new Font(numericUpDownSalt.getFont().getName(), numericUpDownSalt.getFont().getStyle(), 16));
        panel1.add(numericUpDownSalt, new com.intellij.uiDesigner.core.GridConstraints(3, 7, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel2.setBackground(new Color(-9543));
        rootPanel.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(221, 26), null, 0, false));
        panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null));
        final JLabel label6 = new JLabel();
        label6.setFont(new Font("Minsk", label6.getFont().getStyle(), 24));
        label6.setText("Плита");
        panel2.add(label6, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel2.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        panel2.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        checkBoxOn = new JCheckBox();
        checkBoxOn.setBackground(new Color(-2578));
        checkBoxOn.setFont(new Font(checkBoxOn.getFont().getName(), checkBoxOn.getFont().getStyle(), 20));
        checkBoxOn.setText("Вкл");
        panel2.add(checkBoxOn, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonAddPan = new JButton();
        buttonAddPan.setBackground(new Color(-2578));
        buttonAddPan.setFont(new Font(buttonAddPan.getFont().getName(), buttonAddPan.getFont().getStyle(), 20));
        buttonAddPan.setText("Поставить сковороду");
        panel2.add(buttonAddPan, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonCook = new JButton();
        buttonCook.setBackground(new Color(-2578));
        buttonCook.setEnabled(false);
        buttonCook.setFont(new Font(buttonCook.getFont().getName(), buttonCook.getFont().getStyle(), 20));
        buttonCook.setText("Готовить");
        panel2.add(buttonCook, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonGetPan = new JButton();
        buttonGetPan.setBackground(new Color(-2578));
        buttonGetPan.setEnabled(false);
        buttonGetPan.setFont(new Font(buttonGetPan.getFont().getName(), buttonGetPan.getFont().getStyle(), 20));
        buttonGetPan.setText("Убрать сковороду");
        panel2.add(buttonGetPan, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel3.setBackground(new Color(-9543));
        rootPanel.add(panel3, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(112, 24), null, 0, false));
        panel3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null));
        final JLabel label7 = new JLabel();
        label7.setFont(new Font("Minsk", label7.getFont().getStyle(), 24));
        label7.setText("Кран");
        panel3.add(label7, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        panel3.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        radioButtonOpen = new JRadioButton();
        radioButtonOpen.setBackground(new Color(-2578));
        radioButtonOpen.setFont(new Font(radioButtonOpen.getFont().getName(), radioButtonOpen.getFont().getStyle(), 20));
        radioButtonOpen.setText("Открыт");
        panel3.add(radioButtonOpen, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButtonClose = new JRadioButton();
        radioButtonClose.setBackground(new Color(-2578));
        radioButtonClose.setFont(new Font(radioButtonClose.getFont().getName(), radioButtonClose.getFont().getStyle(), 20));
        radioButtonClose.setSelected(true);
        radioButtonClose.setText("Закрыт");
        panel3.add(radioButtonClose, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonWash = new JButton();
        buttonWash.setBackground(new Color(-2578));
        buttonWash.setFont(new Font(buttonWash.getFont().getName(), buttonWash.getFont().getStyle(), 20));
        buttonWash.setText("Помыть скорлупу");
        panel3.add(buttonWash, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel4 = new JPanel();
        panel4.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel4.setBackground(new Color(-9543));
        rootPanel.add(panel4, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 3, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        panel4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null));
        final JLabel label8 = new JLabel();
        label8.setFont(new Font("Minsk", label8.getFont().getStyle(), 24));
        label8.setText("Сковорода");
        panel4.add(label8, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        panel4.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        buttonAddOil = new JButton();
        buttonAddOil.setFont(new Font(buttonAddOil.getFont().getName(), buttonAddOil.getFont().getStyle(), 20));
        buttonAddOil.setText("Залить масло");
        panel4.add(buttonAddOil, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonAddEggs = new JButton();
        buttonAddEggs.setEnabled(true);
        buttonAddEggs.setFont(new Font(buttonAddEggs.getFont().getName(), buttonAddEggs.getFont().getStyle(), 20));
        buttonAddEggs.setText("Добавить яйца");
        panel4.add(buttonAddEggs, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonAddSalt = new JButton();
        buttonAddSalt.setEnabled(false);
        buttonAddSalt.setFont(new Font(buttonAddSalt.getFont().getName(), buttonAddSalt.getFont().getStyle(), 20));
        buttonAddSalt.setText("Добавить соль");
        panel4.add(buttonAddSalt, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonAddPepper = new JButton();
        buttonAddPepper.setEnabled(false);
        buttonAddPepper.setFont(new Font(buttonAddPepper.getFont().getName(), buttonAddPepper.getFont().getStyle(), 20));
        buttonAddPepper.setText("Добавить перец");
        panel4.add(buttonAddPepper, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonGetEggs = new JButton();
        buttonGetEggs.setEnabled(false);
        buttonGetEggs.setFont(new Font(buttonGetEggs.getFont().getName(), buttonGetEggs.getFont().getStyle(), 20));
        buttonGetEggs.setText("Получить яичницу");
        panel4.add(buttonGetEggs, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel5 = new JPanel();
        panel5.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel5.setBackground(new Color(-9543));
        rootPanel.add(panel5, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel5.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null));
        final JLabel label9 = new JLabel();
        label9.setIcon(new ImageIcon(getClass().getResource("/pictureBox1.Image.png")));
        label9.setText("");
        panel5.add(label9, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel6 = new JPanel();
        panel6.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel6.setBackground(new Color(-9543));
        rootPanel.add(panel6, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setFont(new Font("Minsk", label10.getFont().getStyle(), 24));
        label10.setText("Вилка");
        panel6.add(label10, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        panel6.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer6 = new com.intellij.uiDesigner.core.Spacer();
        panel6.add(spacer6, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        buttonBreakEggs = new JButton();
        buttonBreakEggs.setBackground(new Color(-2578));
        buttonBreakEggs.setFont(new Font(buttonBreakEggs.getFont().getName(), buttonBreakEggs.getFont().getStyle(), 20));
        buttonBreakEggs.setText("Разбить скорлупу");
        panel6.add(buttonBreakEggs, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}
