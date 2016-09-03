/**
 * Created by nezumi on 9/1/16.
 */
public class Expression {
    private String expression;
    private Logical logical;

    public void printExpression() {
        logical.printLogical();
    }

    public Expression() {

    }

    public CalculationResult calcExpression() {
        return logical.calcLogical();
    }

    public Expression(String expression) {
        this.expression = expression;
    }

    public void setLogical(Logical logical) {
        this.logical = logical;
    }

    private String getExpression() {
        return expression;
    }


}
