public class OpenBracket implements ActionCalculator {
    private final CalculatorModel calculatorModel;

    public OpenBracket(CalculatorModel calculatorModel) {
        this.calculatorModel = calculatorModel;
    }

    @Override
    public void doAction() {
        calculatorModel.setExpression(calculatorModel.getExpression() + "(");
        calculatorModel.setTemp(calculatorModel.getTemp() + "(");
    }
}
