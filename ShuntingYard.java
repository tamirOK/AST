/**
 * Created by nezumi on 9/3/16.
 */
import java.lang.*;
import java.util.HashMap;

/**
 * Created by nezumi on 8/30/16.
 */
public class ShuntingYard {

    static HashMap<Character, java.lang.Integer> order;

    static void init() {
        /*
            To compare order of two operations
         */
        order = new HashMap<>();
        order.put('+', 1);
        order.put('-', 1);
        order.put('*', 2);
        order.put('/', 2);
    }

    static boolean isOperator(char ch) {
        return (ch == '+') || (ch == '-') || (ch == '*') || (ch == '/');
    }

    static boolean left(char ch) {
        return (ch == '+') || (ch == '-') || (ch == '*') || (ch == '/');
    }

    static boolean right(char ch) {
        return (ch == '^') || (ch == '+') || (ch == '*');
    }

    static boolean less(char x, char y, boolean equal) {
        if (equal)
            return order.get(x) <= order.get(y);
        else
            return order.get(x) < order.get(y);
    }

    /*
        Shunting yard algorithm
        returns queue with postfix order
     */
    static LinkedQueue<Character> process(String expression) {
        LinkedStack<Character> operators = new LinkedStack<>();
        LinkedQueue<Character> output = new LinkedQueue<>();
        StringBuilder result = new StringBuilder();
        boolean previousNumber = false;

        init();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (Character.isDigit(ch) || ch == '.') {
                output.enqueue(ch);
                previousNumber = true;
                continue;
            }

            if (previousNumber) {    // for working correctly with numbers of length >= 2
                output.enqueue(' ');
                previousNumber = false;
            }

            if (isOperator(ch)) {
                while (!operators.isEmpty() && isOperator(operators.top()) &&
                        ((left(ch) && less(ch, operators.top(), true)) || (right(ch) && less(ch, operators.top(), false)))) {
                    output.enqueue(operators.pop());
                }
                operators.push(ch);
                continue;
            }

            if (ch == '(') {
                operators.push(ch);
                continue;
            }

            if (ch == ')') {
                while (operators.top() != '(')
                    output.enqueue(operators.pop());
                operators.pop();
                continue;
            }
        }

        if (previousNumber)
            output.enqueue(' ');

        while (!operators.isEmpty())
            output.enqueue(operators.pop());

        return output;
    }

    static double calculate(LinkedQueue<Character> output) {
        StringBuilder num = new StringBuilder();
        LinkedStack<Double> numbers = new LinkedStack<>();

        while (!output.isEmpty()) {
            char ch = output.dequeue();
            if (ch == ' ') {
                numbers.push(Double.parseDouble(num.toString()));
                num.setLength(0);
                continue;
            }

            if (Character.isDigit(ch) || ch == '.') {
                num.append(ch);
                continue;
            }
            if (isOperator(ch)) {
                double first = numbers.pop(), second = numbers.pop();
                if (ch == '+')
                    numbers.push(first + second);
                else if (ch == '-')
                    numbers.push(second - first);
                else if (ch == '*')
                    numbers.push(first * second);
                else if (ch == '/')
                    numbers.push(second / first);
                continue;
            }
        }
        return numbers.get(0);
    }

    public static double calculate(String input) {
        input = input.replaceAll("\\s+", "");
        LinkedQueue<Character> output = process(input);
        return calculate(output);
    }
}

