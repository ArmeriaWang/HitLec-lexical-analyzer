package dfa;

import token.Token;
import token.TokenType;

public class CommentDFA extends DFA {

    private static final int stateNumber = 5;
    private static final CommentDFA instance = new CommentDFA();

    public static CommentDFA getSingleInstance() {
        return instance;
    }

    private CommentDFA() {
        super(stateNumber);
        states[0] = new DFAState("0, start");
        states[1] = new DFAState("1, /");
        states[2] = new DFAState("2, /*");
        states[3] = new DFAState("3, /* *");
        states[4] = new DFAState("4, /* */", TokenType.COMMENT, "");
        startState = states[0];
        states[0].addTransition("/", states[1]);
        states[1].addTransition("*", states[2]);
        for (int i = 1; i < 256; i++) {
            char ch = (char) i;
            if (ch != '*') {
                states[2].addTransition(ch, states[2]);
            }
            if (ch != '/') {
                states[3].addTransition(ch, states[2]);
            }
        }
        states[2].addTransition("*", states[3]);
        states[3].addTransition("/", states[4]);
    }

    @Override
    public boolean isLegalEndChar(char c, DFAState state) {
        return true;
    }

    @Override
    public Token analyze(DFAState state, String token) {
        return new Token(TokenType.COMMENT, token.substring(2, token.length() - 2));
    }
}
