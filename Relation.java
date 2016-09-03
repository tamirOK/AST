/**
 * Created by nezumi on 9/1/16.
 */
public class Relation extends Logical {
    enum Opcode {
        LESS("<"),
        LESS_EQ("<="),
        GREATER(">"),
        GREATER_EQ(">="),
        EQUAL("="),
        NOT_EQUAL("/="),
        NONE("");

        private final String sign;

        Opcode(String sign) {
            this.sign = sign;
        }

        String getSign() {
            return sign;
        }
    }

    

    public Relation() {

    }

    public Relation(Opcode op, Term t1, Term t2) {
        double value1 = t1.getValue();
        double value2 = t2.getValue();
        booleanSet();
        switch(op.getSign()) {
            case ">=":
                booleanValue = (value1 >= value2);
                break;
            case "<=":
                booleanValue = (value1 <= value2);
                break;
            case ">":
                booleanValue = (value1 > value2);
                break;
            case "<":
                booleanValue = (value1 < value2);
                break;
            case "=":
                booleanValue = (value1 == value2);
                break;
            case "/=":
                booleanValue = (value1 != value2);
                break;
            default:
                System.out.println("Unexpected error in Relation ctr");
                break;
        }
    }


    public boolean getBooleanValue() {
        return booleanValue;
    }
}
