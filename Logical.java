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



    public Logical() {}

    public Logical(Opcode op, Relation r1, Relation r2) {
        boolean rel1 = r1.getBooleanValue();
        boolean rel2 = r2.getBooleanValue();

        booleanSet();

        switch (op) {
            case AND:
                booleanValue = (rel1 && rel2);
                break;
            case OR:
                booleanValue = (rel1 || rel2);
                break;
            case XOR:
                booleanValue = (rel1 ^ rel2);
                break;
            default:
                System.out.println("Unexpected booleanValue in Logical ctr");
                break;
        }
    }

    public void addRelation(Opcode op, Relation r) {
        boolean rel = r.getBooleanValue();

        switch (op) {
            case AND:
                booleanValue = (booleanValue && rel);
                break;
            case OR:
                booleanValue = (booleanValue || rel);
                break;
            case XOR:
                booleanValue = (booleanValue ^ rel);
                break;
            default:
                System.out.println("Unexpected booleanValue in Logical addRelation");
                break;
        }
    }
}
