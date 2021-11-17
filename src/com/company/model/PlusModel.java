package com.company.model;

import com.company.util.ActionCalculator;
import com.company.util.CalculatorUtil;

public class PlusModel implements ActionCalculator {
    private final CalculatorModel calculatorModel;
    private final CalculatorUtil calculatorUtil;

    public PlusModel(CalculatorModel calculatorModel, CalculatorUtil calculatorUtil) {
        this.calculatorModel = calculatorModel;
        this.calculatorUtil = calculatorUtil;
    }

    @Override
    public void doAction() {
        if (calculatorUtil.validOperand(calculatorModel.getExpression())) {
            calculatorModel.setExpression(calculatorModel.getExpression() + "+");
        } else
            calculatorModel.setExpression(calculatorModel.getExpression() + "");
        calculatorModel.setTemp("");
    }
}