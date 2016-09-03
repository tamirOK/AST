/**
 * Created by nezumi on 9/1/16.
 */
public class Integer  extends Primary {
    private int value = 0;

    public Integer() {}

    public Integer(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
