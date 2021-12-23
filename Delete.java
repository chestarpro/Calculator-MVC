public class Delete implements ActionCalculator {
    private final CalculatorModel calculatorModel;

    public Delete(CalculatorModel calculatorModel) {
        this.calculatorModel = calculatorModel;
    }

    @Override
    public void doAction() {
        String deleteTemp = calculatorModel.getTemp();
        String deleteExpression = calculatorModel.getExpression();

        calculatorModel.setTemp("");
        calculatorModel.setExpression("");

        for (int i = 0; i < deleteTemp.length() - 1; i++)
            calculatorModel.setTemp(calculatorModel.getTemp() + deleteTemp.charAt(i));

        for (int i = 0; i < deleteExpression.length() - 1; i++)
            calculatorModel.setExpression(calculatorModel.getExpression() + deleteExpression.charAt(i));
    }
}