package dfa;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import token.IdentifierTable;
import token.Token;
import token.TokenType;

import java.util.HashMap;
import java.util.Map;

import static token.TokenType.*;
import static token.Utils.*;


public class IdentifierKeywordDFA extends DFA {

    private static final int stateNumber = 2;
    private static final IdentifierKeywordDFA instance = new IdentifierKeywordDFA();
    private static final String commonLegalEndChars = "=<>+-%*/|&^~!;.()[]{";

    private static final Map<String, TokenType> token2attribute = new HashMap<>() {{
        put("do", KW_DO);
        put("while", KW_WHILE);
        put("for", KW_FOR);
        put("if", KW_IF);
        put("else", KW_ELSE);
        put("return", KW_RETURN);
        put("int", DT_INTEGER);
        put("float", DT_FLOAT);
        put("bool", DT_BOOLEAN);
        put("struct", DT_STRUCT);
    }};

    private IdentifierKeywordDFA() {
        super(stateNumber);
        states[0] = new DFAState("0, start");
        states[1] = new DFAState("1, accept", commonLegalEndChars);
        startState = states[0];
        states[0].addTransition("_" + letters, states[1]);
        states[1].addTransition("_" + letters + digits, states[1]);
    }

    public static IdentifierKeywordDFA getSingleInstance() {
        return instance;
    }

    @Override
    public boolean isLegalEndChar(char ch, DFAState state) {
        return isBlankChar(ch) || commonLegalEndChars.contains(String.valueOf(ch));
    }

    @Override
    public Token analyze(DFAState state, String token) {
        if (token2attribute.containsKey(token.toLowerCase())) {
            return new Token(token2attribute.get(token.toLowerCase()), null);
        }
        else {
            IdentifierTable.addIdentifier(token);
            return new Token(TokenType.IDENTIFIER, token);
        }
    }

}
