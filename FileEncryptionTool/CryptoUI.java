package cryptov01;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class CryptoUI extends JFrame {

    private JTextField inputField;
    private JTextField outputField;
    private JTextField keyField;

    public CryptoUI() {

        setTitle("File Encryption Tool");
        setSize(500, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 3));

        // Input file
        add(new JLabel("Input File:"));
        inputField = new JTextField();
        add(inputField);
        JButton browseInput = new JButton("Browse");
        add(browseInput);

        // Output file
        add(new JLabel("Output File:"));
        outputField = new JTextField();
        add(outputField);
        JButton browseOutput = new JButton("Browse");
        add(browseOutput);

        // Key
        add(new JLabel("Key:"));
        keyField = new JTextField();
        add(keyField);
        add(new JLabel(""));

        // Buttons
        JButton encryptBtn = new JButton("Encrypt");
        JButton decryptBtn = new JButton("Decrypt");

        add(encryptBtn);
        add(decryptBtn);

        // Browse input
        browseInput.addActionListener(e -> chooseFile(inputField));

        // Browse output
        browseOutput.addActionListener(e -> chooseFile(outputField));

        // Encrypt button
        encryptBtn.addActionListener((ActionEvent e) -> process(true));

        // Decrypt button
        decryptBtn.addActionListener((ActionEvent e) -> process(false));

        setVisible(true);
    }

    private void chooseFile(JTextField field) {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);

        if (option == JFileChooser.APPROVE_OPTION) {
            field.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }

    private void process(boolean encryptMode) {
        String inputPath = inputField.getText();
        String outputPath = outputField.getText();
        String key = keyField.getText();

        if (inputPath.isEmpty() || outputPath.isEmpty() || key.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fill all fields!");
            return;
        }

        try {
            String content = readFile(inputPath);
            String result;

            if (encryptMode) {
                result = Cryptologic.encrypt(content, key);
            } else {
                result = Cryptologic.decrypt(content, key);
            }

            writeFile(outputPath, result);

            JOptionPane.showMessageDialog(this, "Done!");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private String readFile(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        StringBuilder content = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }

        reader.close();
        return content.toString();
    }

    private void writeFile(String path, String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write(content);
        writer.close();
    }

    public static void main(String[] args) {
        new CryptoUI();
    }
}