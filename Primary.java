/**
 * Created by nezumi on 9/1/16.
 */
public class Primary extends Expression {
    private Integer integer = null;
    private Parenthesized paranthesized = null;

    public Primary() {

    }

    void printPrimary() {
        if (integer != null)
            System.out.print(integer.getValue() + " ");
        else if (paranthesized != null)
            paranthesized.printParanthesized();
        else {
            System.out.println("Error in printPrimary");
            System.exit(0);
        }
    }

    public Primary(String primary) {
        if (primary.charAt(0) == '(') {
            System.out.println(primary);
            paranthesized = new Parenthesized(primary.substring(1, primary.length() - 2));
        }
        else
            integer = new Integer(primary);
    }
}
