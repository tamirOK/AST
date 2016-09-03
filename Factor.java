import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by nezumi on 9/1/16.
 */
public class Factor extends Term {

    enum Opcode {
        MULT('*'),
        DIV('/');

        private final char sign;

        Opcode(char sign) {
            this.sign = sign;
        }

        public char getSign() {
            return sign;
        }
    }

    private static HashMap<Character, Factor.Opcode> opcodeHashMap = new HashMap<>();
    private ArrayList<Primary> primaries;
    private ArrayList<Opcode> opcodes;
    private String factor;

    static {
        opcodeHashMap.put('*', Opcode.MULT);
        opcodeHashMap.put('/', Opcode.DIV);
    }

    Factor(String factor) {
        this.factor = factor;
        primaries = new ArrayList<>();
        opcodes = new ArrayList<>();
    }


    public void printFactor() {
        if (primaries.size() != opcodes.size() + 1) {
            System.out.println("Incorrect expression(Factor)");
            System.exit(0);
        }
        for (int i = 0; i < primaries.size(); i++) {
            primaries.get(i).printPrimary();
            if (i < opcodes.size())
                System.out.print(opcodes.get(i).getSign() + " ");
        }
    }

    public void addPrimary(String primary) {
        primaries.add(new Primary(primary));
    }

    public void addOperator(char operator) {
        opcodes.add(opcodeHashMap.get(operator));
    }

    public String getFactor() {
        return factor;
    }

}
