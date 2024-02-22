import javax.swing.*;
import java.awt.*;
import java.io.*;

import static java.lang.Math.pow;

public class Algorithm1 extends JDialog {

    private final JTextField[] enterVariables;

    private final JLabel result;

    public Algorithm1(Frame owner) {
        super(owner, "Алгоритм 1", true);
        super.setBounds(350, 200, 550, 150);
        Container wind = super.getContentPane();
        wind.setLayout(new GridLayout(3, 4, 3, 2));

        JLabel labelA = new JLabel("Введіть a:");
        JLabel labelB = new JLabel("Введіть b:");
        JLabel labelC = new JLabel("Введіть c:");
        JLabel labelD = new JLabel("Введіть d:");
        result = new JLabel("Відповідь: ");

        enterVariables = new JTextField[4];
        for (int i = 0; i < enterVariables.length; i++) {
            enterVariables[i] = new JTextField();
        }

        JButton takeResult = new JButton("Вирішити");
        JButton enterFile = new JButton("Записати в файл");
        JButton readFile = new JButton("Зчитати з файла");

        wind.add(labelA);
        wind.add(enterVariables[0]);
        wind.add(labelB);
        wind.add(enterVariables[1]);
        wind.add(labelC);
        wind.add(enterVariables[2]);
        wind.add(labelD);
        wind.add(enterVariables[3]);
        wind.add(result);
        wind.add(takeResult);
        wind.add(enterFile);
        wind.add(readFile);

        takeResult.addActionListener(e -> {
            result.setText("Відповідь: " + realizationFunc(convertAndDisplayResult(enterVariables[0].getText()),
                    convertAndDisplayResult(enterVariables[1].getText()),
                    convertAndDisplayResult(enterVariables[2].getText()),
                    convertAndDisplayResult(enterVariables[3].getText())));
        });

        enterFile.addActionListener(e -> {
            saveToFile();
        });

        readFile.addActionListener(e -> {
            loadFromFile();
        });
    }

    private double convertAndDisplayResult(String field) {
        try {
            return Double.parseDouble(field);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Помилка: Введіть коректне число");
            return 0;
        }
    }

    private double realizationFunc (double a, double b, double c, double d) {
        double result;
        double res1 = pow((a + (b/c)), d);
        double res2 = pow((d + (b/c)), a);
        result = res1 + res2;
        return result;
    }

    private void saveToFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) {
                for (JTextField variable : enterVariables) {
                    String text = variable.getText();
                    writer.write(text);
                    writer.newLine();
                }
                JOptionPane.showMessageDialog(this, "Дані були успішно збережені у файл" + selectedFile);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Помилка при збереженні у файл.");
                e.printStackTrace();
            }
        }
    }
    private void loadFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                for (int i = 0; i < 4; i++) {
                    String line = reader.readLine();
                    if (line != null) {
                        enterVariables[i].setText(line);
                    } else {
                        JOptionPane.showMessageDialog(this, "Недостатньо рядків у файлі.");
                        break;
                    }
                }
                JOptionPane.showMessageDialog(this, "Дані були успішно завантажені з файлу.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Помилка при зчитуванні з файлу.");
                e.printStackTrace();
            }
        }
    }
}
