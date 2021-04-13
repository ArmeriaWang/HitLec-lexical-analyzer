package dfa;

import org.apache.commons.lang3.tuple.Pair;
import token.TokenType;

public class StringConstDFA extends DFA {

    private static final int stateNumber = 5;

    protected StringConstDFA() {
        super(stateNumber);
    }

    @Override
    public boolean isLegalEndChar(char ch, DFAState state) {
        return false;
    }

    @Override
    public Pair<TokenType, Object> analyze(DFAState state, String token) {
        return null;
    }
}
