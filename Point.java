public class Point implements ActionCalculator {
    private final CalculatorModel calculatorModel;
    private final CalculatorUtil calculatorUtil;

    public Point(CalculatorModel calculatorModel, CalculatorUtil calculatorUtil) {
        this.calculatorModel = calculatorModel;
        this.calculatorUtil = calculatorUtil;
    }


    @Override
    public void doAction() {
        if (calculatorUtil.validPoint(calculatorModel.getTemp())) {
            calculatorModel.setTemp(calculatorModel.getTemp() + ".");
            calculatorModel.setExpression(calculatorModel.getExpression() + ".");
        } else {
            calculatorModel.setTemp(calculatorModel.getTemp() + "");
            calculatorModel.setExpression(calculatorModel.getExpression() + "");
        }
    }
}