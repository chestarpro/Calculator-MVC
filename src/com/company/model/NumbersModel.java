package com.company.model;

import com.company.util.ActionCalculator;

public class NumbersModel implements ActionCalculator {

    private final CalculatorModel calculatorModel;

    public NumbersModel(CalculatorModel calculatorModel) {
        this.calculatorModel = calculatorModel;
    }

    @Override
    public void doAction() {
        calculatorModel.setExpression(calculatorModel.getExpression() + calculatorModel.getCommand());
        calculatorModel.setTemp(calculatorModel.getTemp() + calculatorModel.getCommand());
    }
}