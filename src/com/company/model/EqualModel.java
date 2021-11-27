package com.company.model;

import com.company.util.ActionCalculator;
import com.company.util.CalculatorUtil;
import com.company.viewer.Viewer;

public class EqualModel implements ActionCalculator {
    private final CalculatorModel calculatorModel;
    private final Viewer viewer;
    private final CalculatorUtil calculatorUtil;

    public EqualModel(CalculatorModel calculatorModel, Viewer viewer, CalculatorUtil calculatorUtil) {
        this.calculatorModel = calculatorModel;
        this.viewer = viewer;
        this.calculatorUtil = calculatorUtil;
    }

    @Override
    public void doAction() {
        try {
            if (!calculatorModel.getExpression().isEmpty()) {
                if (!calculatorUtil.validParentheses(calculatorModel.getExpression())) throw new Exception();
                String rpn = calculatorUtil.getReversePolishNotation(calculatorModel.getExpression());
                String result = calculatorUtil.calculate(rpn);
                viewer.updateExpression(calculatorModel.getExpression() + "=" + result);
                viewer.updateTemp(result);
            } else viewer.updateTemp("");
        } catch (Exception exception) {
            viewer.updateExpression("");
            viewer.updateTemp("Error! Invalid expression!");
        }
    }
}