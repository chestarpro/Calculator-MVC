package com.company.model;

import com.company.util.ActionCalculator;

public class OpenBracketModel implements ActionCalculator {
    private final CalculatorModel calculatorModel;

    public OpenBracketModel(CalculatorModel calculatorModel) {
        this.calculatorModel = calculatorModel;
    }

    @Override
    public void doAction() {
        calculatorModel.setExpression(calculatorModel.getExpression() + "(");
        calculatorModel.setTemp(calculatorModel.getTemp() + "(");
    }
}
