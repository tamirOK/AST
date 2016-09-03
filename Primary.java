/**
 * Created by nezumi on 9/1/16.
 */
public class Primary extends Factor {
    private Integer integer = null;
    private Parenthesized paranthesized = null;

    public Primary() { }

    public Primary(int value) {
        integer = new Integer(value);
    }

    public int getValue() {
        return integer.getValue();
    }
}
