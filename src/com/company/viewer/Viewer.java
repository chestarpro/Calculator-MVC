package com.company.viewer;

import com.company.controller.ActionController;
import com.company.util.CommandEnum;

import javax.swing.*;
import java.awt.*;

public class Viewer {

    private JTextField textFieldTemp;
    private JTextField textFieldExpression;

    public Viewer() {
        ActionController actionController = new ActionController(this);

        JFrame frame = new JFrame("Calculator MVC");
        frame.setLayout(null);
        frame.setSize(350, 450);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("./icons/calculator.png"));

        Font globalFont = new Font("Courier", Font.ITALIC, 25);

        frame.add(initPanel(actionController, globalFont));
        frame.add(initTempTextField(globalFont));
        frame.add(initExpressionTextField());

        frame.setVisible(true);
    }

    public void updateTemp(String text) {
        textFieldTemp.setText(text);
    }

    public void updateExpression(String text) {
        textFieldExpression.setText(text);
    }

    private JPanel initPanel(ActionController actionController, Font globalFont) {
        JButton[] numberButtons = new JButton[10];
        JButton[] functionButtons = new JButton[10];

        functionButtons[0] = createButton("+", CommandEnum.PLUS);
        functionButtons[1] = createButton("-", CommandEnum.SUBTRACTION);
        functionButtons[2] = createButton("*", CommandEnum.MULTIPLY);
        functionButtons[3] = createButton("/", CommandEnum.DIVIDE);
        functionButtons[4] = createButton(".", CommandEnum.POINT);
        functionButtons[5] = createButton("=", CommandEnum.EQUAL);
        functionButtons[6] = createButton("(", CommandEnum.OPEN_BRACKET);
        functionButtons[7] = createButton(")", CommandEnum.CLOSE_BRACKET);
        functionButtons[8] = createButton("C", CommandEnum.CLEAR);

        JButton deleteButton = new JButton(new ImageIcon("./icons/delete.png"));
        deleteButton.setActionCommand(CommandEnum.DELETE.toString());

        functionButtons[9] = deleteButton;

        for (int i = 0; i < 10; i++) {
            functionButtons[i].addActionListener(actionController);
            functionButtons[i].setFont(globalFont);
            functionButtons[i].setFocusable(false);

            numberButtons[i] = new JButton(i + "");
            numberButtons[i].addActionListener(actionController);
            numberButtons[i].setFont(globalFont);
            numberButtons[i].setFocusable(false);
        }

        JPanel panel = new JPanel();
        panel.setBounds(10, 100, 315, 300);
        panel.setLayout(new GridLayout(5, 4, 5, 5));

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(functionButtons[0]);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(functionButtons[1]);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(functionButtons[2]);
        panel.add(functionButtons[6]);
        panel.add(numberButtons[0]);
        panel.add(functionButtons[7]);
        panel.add(functionButtons[3]);
        panel.add(functionButtons[4]);
        panel.add(functionButtons[5]);
        panel.add(functionButtons[9]);
        panel.add(functionButtons[8]);

        return panel;
    }

    private JTextField initExpressionTextField() {
        textFieldExpression = new JTextField();
        textFieldExpression.setBounds(10, 9, 315, 30);
        textFieldExpression.setFont(new Font("Courier", Font.ITALIC, 10));
        textFieldExpression.setForeground(Color.BLUE);
        textFieldExpression.setEditable(false);

        return textFieldExpression;
    }

    private JTextField initTempTextField(Font font) {
        textFieldTemp = new JTextField();
        textFieldTemp.setBounds(10, 38, 315, 45);
        textFieldTemp.setFont(font);
        textFieldTemp.setEditable(false);

        return textFieldTemp;
    }

    private JButton createButton(String operand, CommandEnum command) {
        JButton button = new JButton(operand);
        button.setActionCommand(command.toString());

        return button;
    }
}