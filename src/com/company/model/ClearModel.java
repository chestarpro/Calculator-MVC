package com.company.model;

import com.company.util.ActionCalculator;

public class ClearModel implements ActionCalculator {
    private final CalculatorModel calculatorModel;

    public ClearModel(CalculatorModel calculatorModel) {
        this.calculatorModel = calculatorModel;
    }

    @Override
    public void doAction() {
        calculatorModel.setExpression("");
        calculatorModel.setTemp("");
    }
}
