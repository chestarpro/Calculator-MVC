package com.company.util;

import com.company.model.CalculatorModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalculatorUtil {
    private final CalculatorModel calculatorModel;

    public CalculatorUtil(CalculatorModel calculatorModel) {
        this.calculatorModel = calculatorModel;
    }

    public String getReversePolishNotation(String expr) {
        StringBuilder reversePolishNotation = new StringBuilder();
        MyStack<Character> stack = new MyStack<>(getCharStackSize(calculatorModel.getExpression()));

        for (int i = 0; i < expr.length(); i++) {
            int priority = getPriority(expr.charAt(i));

            if (priority == 0) {
                reversePolishNotation.append(expr.charAt(i));
            }

            if (priority == 1) {
                stack.push(expr.charAt(i));
            }

            if (priority > 1) {
                reversePolishNotation.append(' ');
                while (stack.empty()) {
                    if (getPriority(stack.peek()) >= priority)
                        reversePolishNotation.append(stack.pop());
                    else break;
                }
                stack.push(expr.charAt(i));
            }

            if (priority == -1) {
                reversePolishNotation.append(' ');
                while (getPriority(stack.peek()) != 1) {
                    reversePolishNotation.append(stack.pop());
                }
                stack.pop();
            }
        }
        reversePolishNotation.append(' ');
        while (stack.empty())
            reversePolishNotation.append(stack.pop());

        return reversePolishNotation + "";
    }

    public String calculate(String reversePolishNotation) {
        StringBuilder operand = new StringBuilder();
        MyStack<Double> stack = new MyStack<>(getNumbersStackSize(calculatorModel.getExpression()));

        for (int i = 0; i < reversePolishNotation.length(); i++) {
            if (reversePolishNotation.charAt(i) == ' ') {
                continue;
            }

            if (getPriority(reversePolishNotation.charAt(i)) == 0) {
                while (reversePolishNotation.charAt(i) != ' '
                        && getPriority(reversePolishNotation.charAt(i)) == 0) {

                    operand.append(reversePolishNotation.charAt(i++));

                    if (i == reversePolishNotation.length())
                        break;
                }
                stack.push(Double.parseDouble(operand.toString()));
                operand = new StringBuilder();
            }

            if (getPriority(reversePolishNotation.charAt(i)) > 1) {
                double a = stack.pop();
                double b = stack.pop();

                switch (reversePolishNotation.charAt(i)) {
                    case '+' -> stack.push(b + a);
                    case '-' -> stack.push(b - a);
                    case '*' -> stack.push(b * a);
                    case '/' -> stack.push(b / a);
                }
            }
        }

        double res = stack.pop();

        return res % 1 == 0 ? (int) res + "" : res + "";
    }

    public int getPriority(char symbol) {
        if (symbol == '*' || symbol == '/') return 3;
        else if (symbol == '+' || symbol == '-') return 2;
        else if (symbol == '(') return 1;
        else if (symbol == ')') return -1;
        else if (symbol == ' ') return -2;
        else return 0;
    }

    public boolean validPoint(String number) {
        int pointCounter = 0;
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) == '.') {
                pointCounter = pointCounter + 1;
            }
        }
        return !number.isEmpty() && pointCounter < 1;
    }

    public boolean validOperand(String expr) {
        if (expr.isEmpty()) return false;
        int last = expr.length() - 1;
        return expr.charAt(last) != '+'
                && expr.charAt(last) != '-'
                && expr.charAt(last) != '*'
                && expr.charAt(last) != '/'
                && expr.charAt(last) != '=';
    }

    public boolean validParentheses(String expr) {
        int bracketsCount = 0;
        StringBuilder brackets = new StringBuilder();

        for (int i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) == '(' || expr.charAt(i) == ')')
                brackets.append(expr.charAt(i));
        }

        if (brackets.length() == 0) return true;
        else {
            for (int i = 0; i < brackets.length(); i++) {
                if (brackets.charAt(i) == '(') bracketsCount++;
                if (brackets.charAt(i) == ')') bracketsCount--;
                if (bracketsCount < 0) return false;
            }
        }

        return bracketsCount == 0 && isThereNumberInsideBrackets(expr);
    }

    public boolean isThereNumberInsideBrackets(String expr) {
        for (int i = 0; i < expr.length() - 1; i++) {
            if (expr.charAt(i) == '(' && expr.charAt(i + 1) == ')')
                return false;
        }
        return true;
    }

    public int getCharStackSize(String expr) {
        return expr.replaceAll("[^-*+/()]", "").length();
    }

    public int getNumbersStackSize(String expr) {
        String numberOnly = expr.replaceAll("[^0-9]", " ");
        List<String> list = new ArrayList<>(List.of(numberOnly.split(" ")));
        list.removeAll(Collections.singleton(""));
        return list.size();
    }
}