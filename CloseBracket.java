public class CloseBracket implements ActionCalculator {
    private final CalculatorModel calculatorModel;

    public CloseBracket(CalculatorModel calculatorModel) {
        this.calculatorModel = calculatorModel;
    }

    @Override
    public void doAction() {
        calculatorModel.setExpression(calculatorModel.getExpression() + ")");
        calculatorModel.setTemp(calculatorModel.getTemp() + ")");
    }
}