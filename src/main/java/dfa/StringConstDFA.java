package dfa;

import token.Token;
import token.TokenType;

/**
 * 字符串常量DFA。单例模式。
 */
public class StringConstDFA extends DFA {

    private static final int stateNumber = 4;
    private static final StringConstDFA instance = new StringConstDFA();
    private static final String escapeChars = "abfnrtv\\'\"?0";

    private StringConstDFA() {
        super(stateNumber);
        states[0] = new DFAState("0, start");
        states[1] = new DFAState("1, \"");
        states[2] = new DFAState("2 \"\"", "");
        states[3] = new DFAState("3 \\");
        startState = states[0];
        StringBuilder builder = new StringBuilder();
        for (int i = 32; i <= 126; i++) {
            char c = (char) i;
            if (c != '"' && c != '\\') {
                builder.append((char) i);
            }
        }
        String printableChars = builder.toString();
        states[0].addTransition('"', states[1]);
        states[1].addTransition(printableChars, states[1]);
        states[1].addTransition('"', states[2]);
        states[1].addTransition('\\', states[3]);
        states[3].addTransition(escapeChars, states[1]);
    }

    public static StringConstDFA getSingleInstance() {
        return instance;
    }

    @Override
    public boolean isLegalEndChar(char c, DFAState state) {
        return true;
    }

    @Override
    public Token analyze(DFAState state, String token) {
        StringBuilder builder = new StringBuilder();
        boolean escaping = false;
        for (int i = 1; i < token.length() - 1; i++) {
            char c = token.charAt(i);
            if (escaping) {
                if (c == '"' || c == '\\') {
                    builder.append(c);
                }
                else {
                    builder.append("\\").append(c);
                }
                escaping = false;
            }
            else if (c == '\\') {
                escaping = true;
            }
            else {
                builder.append(c);
            }
        }
        return new Token(TokenType.CONST_STRING, builder.toString());
    }
}
