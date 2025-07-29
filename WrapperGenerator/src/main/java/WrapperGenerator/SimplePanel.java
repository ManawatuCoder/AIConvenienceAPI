package WrapperGenerator;

import javax.swing.*;

public class SimplePanel extends JPanel {
    public int modelChoice = 0;//gpt-4.1 default
    private JTextField textField;
    JButton button;

    public SimplePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JRadioButton optionA = new JRadioButton("GPT-4.1");
        JRadioButton optionB = new JRadioButton("gpt-4.1-mini");
        JRadioButton optionC = new JRadioButton("model-router");
        JRadioButton optionD = new JRadioButton("o4-mini");

        optionA.setSelected(true);

        optionA.addActionListener(e -> modelChoice = 0);
        optionB.addActionListener(e -> modelChoice = 1);
        optionC.addActionListener(e -> modelChoice = 2);
        optionD.addActionListener(e -> modelChoice = 3);


        ButtonGroup group = new ButtonGroup();
        group.add(optionA);
        group.add(optionB);
        group.add(optionC);
        group.add(optionD);

        textField = new JTextField();

        textField.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, 410));

        // Add button
        button = new JButton("Submit");

        // Add components to this panel
        add(optionA);
        add(optionB);
        add(optionC);
        add(optionD);
        add(Box.createVerticalStrut(10));
        add(textField);
        add(Box.createVerticalStrut(10));
        add(button);
    }

    void setTextField(String text){
        textField.setText(text);
    }

    public JButton getSubmitButton() {
        return button;
    }

}
