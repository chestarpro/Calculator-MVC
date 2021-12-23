import java.util.HashMap;
import java.util.Map;

public class CalculatorModel {
    private final Viewer viewer;
    private final Numbers numbers;
    private final CalculatorUtil calculatorUtil;

    private String temp;
    private String command;
    private String expression;

    private Map<String, ActionCalculator> map;

    public CalculatorModel(Viewer viewer) {
        this.viewer = viewer;
        numbers = new Numbers(this);
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
            initMap("" + i, numbers);
        }

        initMap(CommandEnum.CLEAR.toString(), new Clear(this));
        initMap(CommandEnum.DELETE.toString(), new Delete(this));
        initMap(CommandEnum.OPEN_BRACKET.toString(), new OpenBracket(this));
        initMap(CommandEnum.PLUS.toString(), new Plus(this, calculatorUtil));
        initMap(CommandEnum.CLOSE_BRACKET.toString(), new CloseBracket(this));
        initMap(CommandEnum.POINT.toString(), new Point(this, calculatorUtil));
        initMap(CommandEnum.DIVIDE.toString(), new Divide(this, calculatorUtil));
        initMap(CommandEnum.MULTIPLY.toString(), new Multiply(this, calculatorUtil));
        initMap(CommandEnum.EQUAL.toString(), new Equal(this, viewer, calculatorUtil));
        initMap(CommandEnum.SUBTRACTION.toString(), new Subtraction(this, calculatorUtil));
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