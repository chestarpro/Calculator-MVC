package com.company;

import javax.swing.*;
import java.awt.*;

public class Viewer {

    private final JTextField textFieldTemp;
    private final JTextField textFieldExpression;

    public Viewer() {
        Controller controller = new Controller(this);

        Font expressionFont = new Font("Courier", Font.ITALIC, 10);
        Font globalFont = new Font("Courier", Font.ITALIC, 25);

        textFieldExpression = new JTextField();
        textFieldExpression.setBounds(20, 30, 300, 30);
        textFieldExpression.setFont(expressionFont);
        textFieldExpression.setForeground(Color.BLUE);
        textFieldExpression.setEditable(false);

        textFieldTemp = new JTextField();
        textFieldTemp.setBounds(20, 58, 300, 50);
        textFieldTemp.setFont(globalFont);
        textFieldTemp.setEditable(false);


        JButton addButton = new JButton("+");
        addButton.setActionCommand("Plus");

        JButton subButton = new JButton("-");
        subButton.setActionCommand("Subtraction");

        JButton mulButton = new JButton("*");
        mulButton.setActionCommand("Multiply");

        JButton divButton = new JButton("/");
        divButton.setActionCommand("Divide");

        JButton pointButton = new JButton(".");
        pointButton.setActionCommand("Point");

        JButton equButton = new JButton("=");
        equButton.setActionCommand("Equal");

        JButton opnBracketButton = new JButton("(");
        opnBracketButton.setActionCommand("Open bracket");

        JButton clsBracketButton = new JButton(")");
        clsBracketButton.setActionCommand("Close bracket");

        JButton clrButton = new JButton("C");
        clrButton.setActionCommand("Clear");

        JButton delButton = new JButton("del");
        delButton.setActionCommand("Delete");

        JButton[] numberButtons = new JButton[10];
        JButton[] functionButtons = new JButton[10];

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = pointButton;
        functionButtons[5] = equButton;
        functionButtons[6] = opnBracketButton;
        functionButtons[7] = clsBracketButton;
        functionButtons[8] = delButton;
        functionButtons[9] = clrButton;

        for (int i = 0; i < 10; i++) {
            functionButtons[i].addActionListener(controller);
            functionButtons[i].setFont(globalFont);
            functionButtons[i].setFocusable(false);

            numberButtons[i] = new JButton(i + "");
            numberButtons[i].addActionListener(controller);
            numberButtons[i].setFont(globalFont);
            numberButtons[i].setFocusable(false);
        }

        JPanel panel = new JPanel();
        panel.setBounds(20, 120, 300, 300);
        panel.setLayout(new GridLayout(5, 4, 5, 5));

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(opnBracketButton);
        panel.add(numberButtons[0]);
        panel.add(clsBracketButton);
        panel.add(divButton);
        panel.add(pointButton);
        panel.add(equButton);
        panel.add(delButton);
        panel.add(clrButton);

        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 480);
        frame.setLayout(null);
        frame.add(panel);
        frame.add(textFieldTemp);
        frame.add(textFieldExpression);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void updateTemp(String text) {
        textFieldTemp.setText(text);
    }

    public void updateExpression(String text) {
        textFieldExpression.setText(text);
    }
}