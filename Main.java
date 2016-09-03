import java.util.Scanner;

/**
 * Created by nezumi on 9/1/16.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        Parser parser = new Parser(input);
        Expression expression = parser.parse();
//        expression.printExpression();
        if (expression.isBooleanResult())
            System.out.println(expression.getBoolean());
        else
            System.out.println(expression.getValue());

    }
}
