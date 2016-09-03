import java.util.HashMap;

/**
 * Created by nezumi on 9/1/16.
 */
public class Factor extends Term {


    enum Opcode {
        MULT('*'),
        DIV('/'),
        NONE(' ');

        private final char sign;

        Opcode(char sign) {
            this.sign = sign;
        }

        public char getSign() {
            return sign;
        }
    }

    private static HashMap<Character, Factor.Opcode> opcodeHashMap = new HashMap<>();

    static {
        opcodeHashMap.put('*', Opcode.MULT);
        opcodeHashMap.put('/', Opcode.DIV);
    }


    public Factor() { }

    public Factor(Opcode op, Primary p1, Primary p2) {
        int value1 = p1.getValue();
        int value2 = p2.getValue();

        if (op.getSign() == '*')
            value = value1 * value2;
        else if (op.getSign() == '/')
            value = value1 / value2;
    }

    public Factor(Opcode op, Factor f, Primary p) {
        int value1 = f.getValue();
        int value2 = p.getValue();

        if (op.getSign() == '*')
            value = value1 * value2;
        else if (op.getSign() == '/')
            value = value1 / value2;
    }


    public int getValue() {
        return value;
    }
}
