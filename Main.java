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
        CalculationResult result = expression.calcExpression();
        if (result.isBoolean())
            System.out.println(result.getBoolean());
        else
            System.out.println(result.getInteger());
    }
}
