/**
 * Created by nezumi on 9/3/16.
 */
public class CalculationResult {
    private boolean isInteger;
    private double value;
    private boolean booleanValue;

    public CalculationResult(boolean value) {
        booleanValue = value;
        isInteger = false;
    }

    public CalculationResult(double value) {
        this.value = value;
        isInteger = true;
    }

    public boolean isBoolean() {
        return !isInteger;
    }

    public boolean isInteger() {
        return isInteger;
    }

    public boolean getBoolean() {
        return booleanValue;
    }

    public double getInteger() {
        return value;
    }
}
