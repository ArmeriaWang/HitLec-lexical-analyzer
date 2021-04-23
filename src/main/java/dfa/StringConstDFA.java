package dfa;

import token.Token;
import token.TokenType;

/**
 * 字符串常量DFA。单例模式。
 */
public class StringConstDFA extends DFA {

    private static final int stateNumber = 5;
    private static final StringConstDFA instance = new StringConstDFA();

    private StringConstDFA() {
        super(stateNumber);
        states[0] = new DFAState("0, start");
        states[1] = new DFAState("1, \"");
        states[2] = new DFAState("2 \"\"", "");
        startState = states[0];
        StringBuilder builder = new StringBuilder();
        for (int i = 32; i <= 126; i++) {
            if ((char) (i) != '"') {
                builder.append((char) i);
            }
        }
        String printableChars = builder.toString();
        states[0].addTransition('"', states[1]);
        states[1].addTransition(printableChars, states[1]);
        states[1].addTransition('"', states[2]);
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
        return new Token(TokenType.CONST_STRING, token.substring(1, token.length() - 1));
    }
}
