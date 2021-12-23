public class Divide implements ActionCalculator {
    private final CalculatorModel calculatorModel;
    private final CalculatorUtil calculatorUtil;

    public Divide(CalculatorModel calculatorModel, CalculatorUtil calculatorUtil) {
        this.calculatorModel = calculatorModel;
        this.calculatorUtil = calculatorUtil;
    }

    @Override
    public void doAction() {
        if (calculatorUtil.validOperand(calculatorModel.getExpression())) {
            calculatorModel.setExpression(calculatorModel.getExpression() + "/");
        } else
            calculatorModel.setExpression(calculatorModel.getExpression() + "");
        calculatorModel.setTemp("");
    }
}
