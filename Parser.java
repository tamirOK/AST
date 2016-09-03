/**
 * Created by nezumi on 9/1/16.
 */



public class Parser {
    private String input;
    private int position;

    public Parser(String s) {
        input = s;
        position = 0;
        input = input.replaceAll("\\s+", "");
    }

    public Expression parse() {
        return parseLogical();
    }

    private Expression parseLogical() {
        Relation result = parseRelation();
        Logical logical = null;
        while (true) {
            Logical.Opcode op = parseLogOperator();
            if (op != Logical.Opcode.NONE) {
                Relation right = parseRelation();
                if (logical == null)
                    logical = new Logical(op, result, right);
                else
                    logical.addRelation(op, right);
                continue;
            }
            break;
        }
        if (logical == null)
            return result;
        return logical;
    }

    private Relation parseRelation() {
        Term result = parseTerm();
        Relation relation = null;
        while (true) {
            Relation.Opcode op = parseCompareOperator();
            if (op != Relation.Opcode.NONE) {
                Term right = parseTerm();
                if (relation == null)
                    relation = new Relation(op, result, right);
                else
                    System.out.println("Error in parseRelation");
                continue;
            }
            break;
        }
        if (relation == null)
            return result;
        return relation;
    }

    private Term parseTerm() {
        Factor result = parseFactor();
        Term term = null;
        while (true) {
            Term.Opcode op = parseTermOperator();
            if (op != Term.Opcode.NONE) {
                Factor right = parseFactor();
                if (term == null)
                    term = new Term(op, result, right);
                else
                    term.addFactor(op, right);
                continue;
            }
            break;
        }
        if (term == null)
            return result;
        return term;
    }

    private Factor parseFactor() {
        Primary result = parsePrimary();
        Factor factor = null;
        while (true) {
            Factor.Opcode op = parseFactorOperator();
            if (op != Factor.Opcode.NONE) {
                Primary right = parsePrimary();
                if (factor == null)
                    factor = new Factor(op, result, right);
                else
                    factor = new Factor(op, factor, right);
                continue;
            }
            break;
        }
        if (factor == null)
            return result;
        return factor;
    }

    private Primary parsePrimary() {
        if (nextChar() == null)
            return null;
        Primary result = null;
        if (Character.isDigit(nextChar()))
            result = parseInteger();
        else if (nextChar() == '(') {
            getNextChar();
            Expression expression = parse();
            getNextChar();
            return new Primary(expression.getValue());
        }
        return result;
    }

    private Integer parseInteger() {
        Integer result = new Integer();
        while (nextChar() != null && Character.isDigit(nextChar())) {
            result.setValue(result.getValue() * 10 + (getNextChar() - '0'));
        }
        return result;
    }

    private Term.Opcode parseTermOperator() {
        Character op = nextChar();
        if (op == null || (op != '+' && op != '-'))
            return Term.Opcode.NONE;
        op = getNextChar();
        if (op == '+')
            return Term.Opcode.PLUS;
        if (op == '-')
            return Term.Opcode.MINUS;
        System.out.println("Unexpected value in parseTermOperator");
        return null;
    }

    private Relation.Opcode parseCompareOperator() {
        StringBuilder sign = new StringBuilder();
        Character op = nextChar();
        if (op == null || (op != '>' && op != '<' && op != '=' && op != '/'))
            return Relation.Opcode.NONE;
        op = getNextChar();
//        if (op == '<' || op == '>' || op == '=' || op == '/')
        sign.append(op);
        op = nextChar();
        if (op != null && op == '=') {
            op = getNextChar();
            sign.append(op);
        }
        String finalSign = sign.toString();
        if (finalSign.equals("="))
            return Relation.Opcode.EQUAL;
        if (finalSign.equals("/="))
            return Relation.Opcode.NOT_EQUAL;
        if (finalSign.equals(">"))
            return Relation.Opcode.GREATER;
        if (finalSign.equals("<"))
            return Relation.Opcode.LESS;
        if (finalSign.equals("<="))
            return Relation.Opcode.LESS_EQ;
        if (finalSign.equals(">="))
            return Relation.Opcode.GREATER_EQ;
        System.out.println("Unexpected value in parseCompareOperator");
        return null;
    }

    private Factor.Opcode parseFactorOperator() {
        Character op = nextChar();
        if (op == null || (op != '/' && op != '*'))
            return Factor.Opcode.NONE;
        op = getNextChar();
        Character nextChar = nextChar();
        if (nextChar != null && nextChar == '=') {
            position--;
            return Factor.Opcode.NONE;
        }
        if (op == '/')
            return Factor.Opcode.DIV;
        if (op == '*')
            return Factor.Opcode.MULT;

        System.out.println("Unexpected value in parseFactorOperator");
        return null;
    }

    private Logical.Opcode parseLogOperator() {
        Character op = nextChar();
        if (op == null || (op != '|' && op != '^' && op != '&'))
            return Logical.Opcode.NONE;
        op = getNextChar();
        if (op == '|')
            return Logical.Opcode.OR;
        if (op == '^')
            return Logical.Opcode.XOR;
        if (op == '&')
            return Logical.Opcode.AND;
        System.out.println("Unexpected value in parseLogOperator");
        return null;
    }

    private Character getNextChar() {
        if (position == input.length())
            return null;
        return input.charAt(position++);
    }

    private Character nextChar() {
        if (position == input.length())
            return null;
        return input.charAt(position);
    }
}
