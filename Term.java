/**
 * Created by nezumi on 9/1/16.
 */
public class Term extends Relation {

    enum Opcode {
        PLUS('+'),
        MINUS('-'),
        NONE(' ');

        private final char sign;

        Opcode(char sign) {
            this.sign = sign;
        }

        public char getSign() {
            return sign;
        }
    }


    public Term() {}

    public Term(Opcode op, Factor f1, Factor f2) {
        int value1 = f1.getValue();
        int value2 = f2.getValue();

        if (op.getSign() == '+')
            value = value1 + value2;
        if (op.getSign() == '-')
            value = value1 - value2;
    }

    public void addFactor(Opcode op, Factor f) {
        if (op.getSign() == '+')
            value += f.getValue();
        if (op.getSign() == '-')
            value -= f.getValue();
    }

    public int getValue() {
        return value;
    }

}
