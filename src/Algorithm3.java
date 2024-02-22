import javax.swing.*;
import java.awt.*;
import java.io.*;


public class Algorithm3 extends JDialog {
    private final JTextField[] enterVariables;

    private final JLabel result;
    public Algorithm3(Frame owner) {
        super(owner, "Алгоритм 3", true);
        super.setBounds(500, 200, 350, 150);
        Container wind = super.getContentPane();
        wind.setLayout(new GridLayout(4, 2, 3, 2));

        JLabel labelN = new JLabel("Введіть n:");
        JLabel labelD = new JLabel("Введіть d:");
        result = new JLabel("Відповідь: ");

        enterVariables = new JTextField[2];
        for (int i = 0; i < enterVariables.length; i++) {
            enterVariables[i] = new JTextField();
        }

        JButton takeResult = new JButton("Вирішити");
        JButton enterFile = new JButton("Записати в файл");
        JButton readFile = new JButton("Зчитати з файла");

        wind.add(labelN);
        wind.add(enterVariables[0]);
        wind.add(labelD);
        wind.add(enterVariables[1]);
        wind.add(result);
        wind.add(takeResult);
        wind.add(enterFile);
        wind.add(readFile);

        takeResult.addActionListener(e -> {
            result.setText("Відповідь: " + realizationFunc(convertAndDisplayResult(enterVariables[0].getText()),
                    convertAndDisplayResult(enterVariables[1].getText())));
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

    private double realizationFunc (double n, double d) {
        double result = 1/n;
        for (int i = 0; i < 25; i++){
            n+=d;
            result += 1/(n+d);
        }
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
                for (int i = 0; i < 3; i++) {
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
