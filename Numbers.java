public class Numbers implements ActionCalculator {
    private final CalculatorModel calculatorModel;

    public Numbers(CalculatorModel calculatorModel) {
        this.calculatorModel = calculatorModel;
    }

    @Override
    public void doAction() {
        calculatorModel.setExpression(calculatorModel.getExpression() + calculatorModel.getCommand());
        calculatorModel.setTemp(calculatorModel.getTemp() + calculatorModel.getCommand());
    }
}