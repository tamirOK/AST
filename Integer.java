/**
 * Created by nezumi on 9/1/16.
 */
public class Integer  extends Primary {
    private double value;

    public Integer(String integer) {
        value = Long.parseLong(integer);
    }

    public double getValue() {
        return value;
    }
}
