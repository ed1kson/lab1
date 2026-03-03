import javax.swing.*;

import myUtility.MyReflector;

import java.awt.*;
import java.awt.event.*;

public class MySwingApp {
    public static void main(String[] args) { 
        JFrame f = new JFrame("My Reflector");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setBackground(Color.LIGHT_GRAY);
        f.setSize(400, 300);
        f.setLayout(new BorderLayout(10, 5));

        JPanel upperPanel = new JPanel(new BorderLayout(10, 5));
        f.add(upperPanel, BorderLayout.NORTH);

        JLabel label = new JLabel("Введіть повне ім'я класу: ");
        JTextField inputField = new JTextField();

        upperPanel.add(label, BorderLayout.WEST);
        upperPanel.add(inputField, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        f.add(buttonPanel, BorderLayout.SOUTH);
        
        JTextArea text = new JTextArea("damn that's crazy");
        JScrollPane scrollPane = new JScrollPane(text);
        f.add(scrollPane, BorderLayout.CENTER);
        
        JButton analizeButton = new JButton("Аналіз");
        JButton clearButton = new JButton("Очистити");
        JButton finishButton = new JButton("Завершити");


        analizeButton.addActionListener(new analize(inputField, text));
        clearButton.addActionListener( e -> {
            text.setText("");
        });
        finishButton.addActionListener( e -> {
            System.exit(0);
        });

        buttonPanel.add(analizeButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(finishButton);

        f.setVisible(true);
    }
}

class analize implements ActionListener {
    private JTextField inputField;
    private JTextArea textbox;
    
    analize(JTextField iF, JTextArea textbox) {
        inputField = iF;
        this.textbox = textbox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = MyReflector.getClassInfo(inputField.getText());
        if ( str == null ) {
            textbox.setText("NOT FOUND");
        } else {
            textbox.setText(str);
        }
    }
}