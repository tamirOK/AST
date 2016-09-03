import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by nezumi on 9/1/16.
 */
public class Term {

    enum Opcode {
        PLUS('+'),
        MINUS('-');

        private final char sign;

        Opcode(char sign) {
            this.sign = sign;
        }

        public char getSign() {
            return sign;
        }
    }

    private ArrayList<Opcode> opcodes;
    private ArrayList<Factor> factors;
    private String term;
    private  static HashMap<Character, Opcode> opcodeHashMap = new HashMap<>();

    static {
        opcodeHashMap.put('+', Opcode.PLUS);
        opcodeHashMap.put('-', Opcode.MINUS);
    }

    public Term() {
        factors = new ArrayList<>();
        opcodes = new ArrayList<>();
    }

    public void addFactor(String factor) {
        factors.add(new Factor(factor));
    }

    public void addOperator(char operator) {
        opcodes.add(opcodeHashMap.get(operator));
    }

    public String getTerm() {
        return term;
    }

    public ArrayList<Factor> getFactors() {
        return factors;
    }

    public void printTerm() {
        if (factors.size() != opcodes.size() + 1) {
            System.out.println("Incorrect expression(Term)");
            System.exit(0);
        }
        for (int i = 0; i < factors.size(); i++) {
            factors.get(i).printFactor();
            if (i < opcodes.size())
                System.out.print(opcodes.get(i).getSign() + " ");
        }
    }

    public double calcTerm() {
        return ShuntingYard.calculate(term);
    }

    public Term(String term) {
        this.term = term;
        factors = new ArrayList<>();
        opcodes = new ArrayList<>();
    }
}
