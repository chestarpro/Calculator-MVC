package com.company.model;

import com.company.util.ActionCalculator;

public class CloseBracketModel implements ActionCalculator {
    private final CalculatorModel calculatorModel;

    public CloseBracketModel(CalculatorModel calculatorModel) {
        this.calculatorModel = calculatorModel;
    }

    @Override
    public void doAction() {
        calculatorModel.setExpression(calculatorModel.getExpression() + ")");
        calculatorModel.setTemp(calculatorModel.getTemp() + ")");
    }
}