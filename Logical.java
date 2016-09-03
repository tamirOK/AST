import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by nezumi on 9/1/16.
 */
public class Logical extends Expression {
    enum Opcode {
        AND('&'),
        OR('|'),
        XOR('^'),
        NONE(' ');

        private final char sign;

        Opcode(char sign) {
            this.sign = sign;
        }

        char getSign() {
            return sign;
        }
    }

    private static HashMap<Character, Opcode> opcodeHashMap;
    private ArrayList<Opcode> opcodes = new ArrayList<>();
    private ArrayList<Relation> relations = new ArrayList<>();

    public void printLogical() {
        if (relations.size() != opcodes.size() + 1) {
            System.out.println("Incorrect expression");
            System.exit(0);
        }
        for (int i = 0; i < relations.size(); i++) {
            relations.get(i).printRelation();
            if (i < opcodes.size())
                System.out.print(opcodes.get(i).getSign() + " ");
        }
    }

    static {
        opcodeHashMap = new HashMap<>();
        opcodeHashMap.put('|', Opcode.OR);
        opcodeHashMap.put('^', Opcode.XOR);
        opcodeHashMap.put('&', Opcode.AND);
    }

    public Logical() {

    }

    public void addRelation(String relation) {
        relations.add(new Relation(relation));
    }

    public void addLogicalOperator(Character operator) {
        opcodes.add(opcodeHashMap.get(operator));
    }

    public int getRelationsSize() {
        return relations.size();
    }

    public Relation getRelationItem(int position) {
        return relations.get(position);
    }

    public CalculationResult calcLogical() {
        if (opcodes.isEmpty())
            return relations.get(0).calcRelation();
        boolean result = relations.get(0).calcRelation().getBoolean();
        for (int i = 0; i < opcodes.size(); i++) {
            boolean temp = relations.get(i + 1).calcRelation().getBoolean();
            char currentSign = opcodes.get(i).getSign();

            if (currentSign == '|')
                result |= temp;
            if (currentSign == '^')
                result ^= temp;
            if (currentSign == '&')
                result &= temp;
        }
        return new CalculationResult(result);
    }
}
