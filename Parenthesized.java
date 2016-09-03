/**
 * Created by nezumi on 9/1/16.
 */
public class Parenthesized extends Primary {
    private Parser parser;

    public Parenthesized(String parameter) {
        parser = new Parser(parameter);
    }

    public void printParanthesized() {
        parser.parse().printExpression();
    }
}
