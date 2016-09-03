import java.util.HashMap;

/**
 * Created by nezumi on 9/1/16.
 */
public class Relation extends Expression {
    enum Opcode {
        LESS("<"),
        LESS_EQ("<="),
        GREATER(">"),
        GREATER_EQ(">="),
        EQUAL("="),
        NOT_EQUAL("!="),
        NONE("");

        private final String sign;

        Opcode(String sign) {
            this.sign = sign;
        }

        String getSign() {
            return sign;
        }
    }

    private String relation;
    private Opcode op;
    private Term left, right;
    private static HashMap<String, Opcode> opcodeHashMap = new HashMap<>();

    static {
        opcodeHashMap.put(">", Opcode.GREATER);
        opcodeHashMap.put("<", Opcode.LESS);
        opcodeHashMap.put(">=", Opcode.GREATER_EQ);
        opcodeHashMap.put("<=", Opcode.LESS_EQ);
        opcodeHashMap.put("=", Opcode.EQUAL);
        opcodeHashMap.put("!=", Opcode.NOT_EQUAL);
        opcodeHashMap.put("", Opcode.NONE);
    }

    public Relation(String relation) {
        this.relation = relation;
        left = right = null;
        op = Opcode.NONE;
    }

    public void printRelation() {
        if (left == null) {
            System.out.println("Left null");
        }
        if (left == null || (op != Opcode.NONE && right == null)) {
            System.out.println("Incorrect expression (Relation)");
            System.exit(0);
        }

        left.printTerm();
        if (op != null)
            System.out.print(op.getSign() + " ");
        if (right != null)
            right.printTerm();
    }

    public void setFirstTerm(String term) {
        left = new Term(term);
    }

    public void setSecondTerm(String term) {
        right = new Term(term);
    }

    public CalculationResult calcRelation() {
        if (op != Opcode.NONE) {
            double leftValue = left.calcTerm();
            double rightValue = right.calcTerm();
            String compare = op.getSign();

            if (compare.equals(">"))
                return new CalculationResult(leftValue > rightValue);
            if (compare.equals(">="))
                return new CalculationResult(leftValue >= rightValue);
            if (compare.equals("<"))
                return new CalculationResult(leftValue < rightValue);
            if (compare.equals("<="))
                return new CalculationResult(leftValue <= rightValue);
            if (compare.equals("="))
                return new CalculationResult(leftValue == rightValue);
            if (compare.equals("!="))
                return new CalculationResult(leftValue != rightValue);
        } else {
            return new CalculationResult(left.calcTerm());
        }
        return null;
    }

    public Term getFirstTerm() {
        return left;
    }

    public Term getSecondTerm() {
        return right;
    }

    public String getRelation() {
        return relation;
    }

    public void setOpCode(String opCode) {
        op = opcodeHashMap.get(opCode);
    }
}
