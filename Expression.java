/**
 * Created by nezumi on 9/1/16.
 */
public class Expression {
    protected int value;
    protected boolean booleanValue, booleanValueSet = false;

    public boolean isBooleanResult() {
        return booleanValueSet;
    }

    public int getValue() {
        return value;
    }

    public boolean getBoolean() {
        return booleanValue;
    }

    protected void booleanSet() {
        booleanValueSet = true;
    }
}
