public class Clear implements ActionCalculator {
    private final CalculatorModel calculatorModel;

    public Clear(CalculatorModel calculatorModel) {
        this.calculatorModel = calculatorModel;
    }

    @Override
    public void doAction() {
        calculatorModel.setExpression("");
        calculatorModel.setTemp("");
    }
}
