package com.company;

public class Model {

    private final Viewer viewer;
    private String temp;
    private String expression;

    public Model(Viewer viewer) {
        this.viewer = viewer;
        temp = "";
        expression = "";
    }

    public void doAction(String command) {

        for (int i = 0; i < 10; i++) {
            if (command.equals(i + "")) {
                expression = expression + i;
                temp = temp + i;
            }
        }

        switch (command) {
            case "Point":
                if (validPoint(temp)) {
                    temp = temp + ".";
                    expression = expression + '.';
                } else {
                    expression = expression + "";
                    temp = temp + "";
                }
                break;

            case "Plus":
                if (validOperand(expression)) {
                    expression = expression + "+";
                } else expression = expression + "";
                temp = "";
                break;

            case "Subtraction":
                if (validOperand(expression)) {
                    expression = expression + "-";
                } else expression = expression + "";
                temp = "";
                break;

            case "Multiply":
                if (validOperand(expression)) {
                    expression = expression + "*";
                } else expression = expression + "";
                temp = "";
                break;

            case "Divide":
                if (validOperand(expression)) {
                    expression = expression + "/";
                } else expression = expression + "";
                temp = "";
                break;

            case "Open bracket":
                expression = expression + "(";
                temp = temp + "(";
                break;

            case "Close bracket":
                expression = expression + ")";
                temp = temp + ")";
                break;

            case "Clear":
                expression = "";
                temp = "";
                break;

            case "Delete":
                String deleteTemp = temp;
                String deleteExpression = expression;
                expression = "";
                temp = "";

                for (int i = 0; i < deleteTemp.length() - 1; i++) {
                    temp = temp + deleteTemp.charAt(i);
                }

                for (int i = 0; i < deleteExpression.length() - 1; i++) {
                    expression = expression + deleteExpression.charAt(i);
                }
                break;

            case "Equal":
                try {
                    if (!expression.isEmpty()) {
                        if (!validParentheses(expression)) throw new Exception();
                        String result = calculate(getReversePolishNotation(expression));
                        viewer.updateExpression(expression + "=" + result);
                        viewer.updateTemp(result);
                    } else viewer.updateTemp("");
                } catch (Exception exception) {
                    viewer.updateExpression("");
                    viewer.updateTemp("Error! Invalid expression!");
                }
                break;
        }

        if (!command.equals("Equal")) {
            viewer.updateExpression(expression);
            viewer.updateTemp(temp);
        }
    }

    // expr it's Expression. Example: 3+2-1
    public String getReversePolishNotation(String expr) {
        StringBuilder reversePolishNotation = new StringBuilder();
        MyStack<Character> stack = new MyStack<>(getCharStackSize(expr));

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
        MyStack<Double> stack = new MyStack<>(getNumbersStackSize(reversePolishNotation));

        for (int i = 0; i < reversePolishNotation.length(); i++) {
            if (reversePolishNotation.charAt(i) == ' ')
                continue;
            if (getPriority(reversePolishNotation.charAt(i)) == 0) {
                while (reversePolishNotation.charAt(i) != ' ' &&
                        getPriority(reversePolishNotation.charAt(i)) == 0) {

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

    private int getPriority(char symbol) {
        if (symbol == '*' || symbol == '/') return 3;
        else if (symbol == '+' || symbol == '-') return 2;
        else if (symbol == '(') return 1;
        else if (symbol == ')') return -1;
        else return 0;
    }

    private boolean validOperand(String expr) {
        if (expr.isEmpty()) return false;
        int last = expr.length() - 1;
        return expr.charAt(last) != '+'
                && expr.charAt(last) != '-'
                && expr.charAt(last) != '*'
                && expr.charAt(last) != '/'
                && expr.charAt(last) != '=';
    }

    private boolean validPoint(String number) {
        int pointCounter = 0;
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) == '.') {
                pointCounter = pointCounter + 1;
            }
        }
        return !number.isEmpty() && pointCounter < 1;
    }

    private boolean validParentheses(String expr) {
        int bracketsCount = 0;
        String brackets = "";

        for (int i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) == '(' || expr.charAt(i) == ')')
                brackets = brackets + expr.charAt(i);
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

    private boolean isThereNumberInsideBrackets(String expr) {
        for (int i = 0; i < expr.length() - 1; i++) {
            if (expr.charAt(i) == '(' && expr.charAt(i + 1) == ')')
                return false;
        }
        return true;
    }

    private int getCharStackSize(String expr) {
        int count = 0;
        for (int i = 0; i < expr.length(); i++) {
            if (getPriority(expr.charAt(i)) != 0) {
                count++;
            }
        }
        return count;
    }

    private int getNumbersStackSize(String expr) {
        int count = 0;
        for (int i = 0; i < expr.length(); i++) {
            if (getPriority(expr.charAt(i)) == 0) {
                count++;
            }
        }
        return count;
    }
}