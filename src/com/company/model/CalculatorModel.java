package com.company.model;

import com.company.util.CalculatorUtil;
import com.company.util.CommandEnum;
import com.company.viewer.Viewer;
import com.company.util.ActionCalculator;

import java.util.HashMap;
import java.util.Map;

public class CalculatorModel {

    private final Viewer viewer;
    private final NumbersModel numbersModel;
    private final CalculatorUtil calculatorUtil;

    private String temp;
    private String command;
    private String expression;

    private Map<String, ActionCalculator> map;

    public CalculatorModel(Viewer viewer) {
        this.viewer = viewer;
        numbersModel = new NumbersModel(this);
        calculatorUtil = new CalculatorUtil(this);
        temp = "";
        expression = "";
        initMap();
    }

    public void doAction(String command) {
        this.command = command;

        if (map.containsKey(command)) {
            map.get(command).doAction();
        }

        if (!command.equals(CommandEnum.EQUAL.toString())) {
            viewer.updateExpression(expression);
            viewer.updateTemp(temp);
        }
    }

    private void initMap() {
        if (map == null) map = new HashMap<>();

        for (int i = 0; i <= 9; i++) {
            initMap("" + i, numbersModel);
        }

        initMap(CommandEnum.CLEAR.toString(), new ClearModel(this));
        initMap(CommandEnum.DELETE.toString(), new DeleteModel(this));
        initMap(CommandEnum.OPEN_BRACKET.toString(), new OpenBracketModel(this));
        initMap(CommandEnum.PLUS.toString(), new PlusModel(this, calculatorUtil));
        initMap(CommandEnum.CLOSE_BRACKET.toString(), new CloseBracketModel(this));
        initMap(CommandEnum.POINT.toString(), new PointModel(this, calculatorUtil));
        initMap(CommandEnum.DIVIDE.toString(), new DivideModel(this, calculatorUtil));
        initMap(CommandEnum.MULTIPLY.toString(), new MultiplyModel(this, calculatorUtil));
        initMap(CommandEnum.EQUAL.toString(), new EqualModel(this, viewer, calculatorUtil));
        initMap(CommandEnum.SUBTRACTION.toString(), new SubtractionModel(this, calculatorUtil));
    }

    private void initMap(String command, ActionCalculator actionCalculator) {
        if (map != null) map.put(command, actionCalculator);
    }

    public String getTemp() {
        return temp;
    }

    public String getExpression() {
        return expression;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getCommand() {
        return command;
    }
}