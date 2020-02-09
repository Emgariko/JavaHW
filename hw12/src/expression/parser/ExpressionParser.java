package expression.parser;

import expression.*;
import expression.TripleExpression;

public class ExpressionParser extends BaseParser implements Parser  {
    private TokenType curToken;
    private int tokenValue;
    private String tokenName;

    @Override
    public CommonExpression parse(String expression) {
        this.source = new StringSource(expression);
        nextChar();
        parseToken(true);
        return parseTerm(false);
    }

    private CommonExpression parseTerm(boolean get) {
        CommonExpression left = parseMultiplier(get);
        while (true) {
            switch (curToken) {
                case ADD :
                    left = new Add((CommonExpression) left, (CommonExpression) parseMultiplier(true));
                    break;
                case SUB :
                    left = new Subtract((CommonExpression) left, (CommonExpression) parseMultiplier(true));
                    break;
                default:
                    return left;
            }
        }
    }

    private CommonExpression parseMultiplier(boolean get) {
        CommonExpression left = parseElement(get);
        while (true) {
            switch (curToken) {
                case MUL :
                    left = new Multiply((CommonExpression) left, (CommonExpression) parseElement(true));
                    break;
                case DIV :
                    left = new Divide((CommonExpression) left, (CommonExpression) parseElement(true));
                    break;
                default:
                    return left;
            }
        }
    }

    private CommonExpression parseElement(boolean get) {
        if (get) {
            parseToken(get);
        }
        CommonExpression res;
        TokenType token = curToken;
        switch (token) {
            case NUMBER:
                res = new Const(tokenValue);
                parseToken(false);
                return res;
            case VAR:
                res = new Variable(tokenName);
                parseToken(false);
                return res;
            case SUB:
                res = new Negative((CommonExpression) parseElement(true));
                return res;
            case LB:
                res = parseTerm(true);
                parseToken(false);
                return res;
        }
        return null;
    }

    private TokenType   parseToken(boolean get) {
        skipWhitespace();
        if (test('-')) {
            if (get && between('0', '9')) {
                curToken = TokenType.NUMBER;
                tokenValue = parseValue(true);
                return curToken;
            } else {
                return curToken = TokenType.SUB;
            }
        } else if (between('0', '9')) {
            curToken = TokenType.NUMBER;
            tokenValue = parseValue(false);
            return curToken;
        } else if (between('a', 'z')) {
            tokenName = parseName();
            return curToken = TokenType.VAR;
        /*} else if (test('\0')) {
            return curToken = TokenType.END;
        */ } else if (test('+')) {
            return curToken = TokenType.ADD;
        } else if (test('-')) {
            return curToken = TokenType.SUB;
        } else if (test('*')) {
            return curToken = TokenType.MUL;
        } else if (test('/')) {
            return curToken = TokenType.DIV;
        } else if (test('(')) {
            return curToken = TokenType.LB;
        } else if (test(')')) {
            return curToken = TokenType.RB;
        }
        return null;
    }

    private String parseName() {
        StringBuilder res = new StringBuilder();
        while (between('a', 'z')) {
            res.append(ch);
            nextChar();
        }
        return res.toString();
    }

    private int parseValue(boolean minus) {
        StringBuilder res = new StringBuilder();
        if (minus) {
            res.append('-');
        }
        while (between('0', '9')) {
            res.append(ch);
            nextChar();
        }
        return Integer.valueOf(res.toString());
    }

    private void skipWhitespace() {
        while (test(' ') || test('\r') || test('\n') || test('\t')) {
            // skip
        }
    }

}
