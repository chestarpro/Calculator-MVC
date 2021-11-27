package com.company.controller;

import com.company.viewer.Viewer;
import com.company.model.CalculatorModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionController implements ActionListener {

    private final CalculatorModel calculatorModel;

    public ActionController(Viewer viewer) {
        calculatorModel = new CalculatorModel(viewer);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();
        calculatorModel.doAction(command);
    }
}