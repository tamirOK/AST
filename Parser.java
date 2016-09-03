import java.util.ArrayList;

/**
 * Created by nezumi on 9/1/16.
 */



public class Parser {
    private String input;

    public Parser(String s) {
        input = s;
        input = input.replaceAll("\\s+", "");
    }

    public Expression parse() {
        return parseLogical();
    }

    private Expression parseLogical() {
        Expression result = new Expression();
        Logical logical = new Logical();
        StringBuilder relation = new StringBuilder();


        for (int i = 0; i < input.length(); i++) {
            if (isLogical(input.charAt(i))) {
                logical.addLogicalOperator(input.charAt(i));
                logical.addRelation(relation.toString());
                relation.setLength(0);
                continue;
            }
            relation.append(input.charAt(i));
        }
        if (relation.length() > 0)
            logical.addRelation(relation.toString());

        parseRelations(logical);
        result.setLogical(logical);
        return result;
    }


    private boolean isLogical(char ch) {
        return (ch == '^') || (ch == '|') || (ch == '&');
    }

    private boolean isComparisonOperator(char ch) {
        return (ch == '>') || (ch == '<') || (ch == '=') || (ch == '!');
    }

    private void parseRelations(Logical logical) {

        for (int i = 0; i < logical.getRelationsSize(); i++) {
            String relation = logical.getRelationItem(i).getRelation();
            Relation relationItem = logical.getRelationItem(i);
            StringBuilder term = new StringBuilder();
            StringBuilder operator = new StringBuilder();
            boolean setFirst = false;

            for (int j = 0; j < relation.length(); j++) {
                if (isComparisonOperator(relation.charAt(j))) {
                    operator.append(relation.charAt(j));
                } else {
                    if (operator.length() > 0) {
                        relationItem.setFirstTerm(term.toString());
                        term.setLength(0);
                        setFirst = true;
                        relationItem.setOpCode(operator.toString());
                        operator.setLength(0);
                    }
                    term.append(relation.charAt(j));
                }
            }
            if (!setFirst) {
                relationItem.setFirstTerm(term.toString());
                relationItem.setOpCode("");
            } else {
                relationItem.setSecondTerm(term.toString());
            }

            parseTerm(relationItem.getFirstTerm());
            parseTerm(relationItem.getSecondTerm());
        }

    }

    private void parseTerm(Term term) {
        if (term == null)
            return;

        String tempTerm = term.getTerm();
        StringBuilder factor = new StringBuilder();

        for (int i = 0; i < tempTerm.length(); i++) {
            if (tempTerm.charAt(i) == '+' || tempTerm.charAt(i) == '-') {
                term.addFactor(factor.toString());
                term.addOperator(tempTerm.charAt(i));
                factor.setLength(0);
            } else {
                factor.append(tempTerm.charAt(i));
            }
        }
        if (factor.length() > 0)
            term.addFactor(factor.toString());

        ArrayList<Factor> factors = term.getFactors();

        for (int i = 0; i < factors.size(); i++)
            parseFactor(factors.get(i));
    }

    private void parseFactor(Factor factor) {
        StringBuilder primary = new StringBuilder();
        String currentFactor = factor.getFactor();

        for (int i = 0; i < currentFactor.length(); i++) {
            char currentChar = currentFactor.charAt(i);
            if (currentChar == '*' || currentChar == '/') {
                factor.addPrimary(primary.toString());
                factor.addOperator(currentChar);
                primary.setLength(0);
            } else {
                primary.append(currentChar);
            }
        }
        if (primary.length() > 0)
            factor.addPrimary(primary.toString());
    }

}
