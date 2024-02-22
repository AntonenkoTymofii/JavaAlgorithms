import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private final Algorithm1 algorithm1Wind;
    private final Algorithm2 algorithm2Wind;
    private final Algorithm3 algorithm3Wind;
    public MainWindow() {
        super("Лабораторна №1");
        super.setBounds(500, 200, 350, 150);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        algorithm1Wind = new Algorithm1(this);
        algorithm2Wind = new Algorithm2(this);
        algorithm3Wind = new Algorithm3(this);

        Container wind = super.getContentPane();
        wind.setLayout(new GridLayout(1, 1, 3, 2));

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Алгоритми");
        JMenuItem alg1 = new JMenuItem("Алгоритм 1");
        JMenuItem alg2 = new JMenuItem("Алгоритм 2");
        JMenuItem alg3 = new JMenuItem("Алгоритм 3");

        menu.add(alg1);
        menu.add(alg2);
        menu.add(alg3);
        menuBar.add(menu);
        super.setJMenuBar(menuBar);

        alg1.addActionListener(e -> {
            algorithm1Wind.setVisible(true);
        });

        alg2.addActionListener(e -> {
            algorithm2Wind.setVisible(true);
        });

        alg3.addActionListener(e -> {
            algorithm3Wind.setVisible(true);
        });




    }
}
