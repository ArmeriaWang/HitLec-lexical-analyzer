package dfa;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import token.Token;
import token.TokenType;
import static token.Utils.*;
import static token.TokenType.*;


/**
 * 数字DFA（可处理十进制数、浮点数、科学计数法、八进制和十六进制数）。单例模式。
 */
public class NumberDFA extends DFA {

    private static final int stateNumber = 10;
    private static final NumberDFA instance = new NumberDFA();
    private static final String commonLegalEndChars = "/=<>%+-*|&^~!;)]";

    private NumberDFA() {
        super(stateNumber);
        states[0] = new DFAState("0, start");
        states[1] = new DFAState("1, accept", CONST_INTEGER, commonLegalEndChars);
        states[2] = new DFAState("2, accept", CONST_FLOAT, commonLegalEndChars);
        states[3] = new DFAState("3");
        states[4] = new DFAState("4, accept", CONST_FLOAT, commonLegalEndChars);
        states[5] = new DFAState("5");
        states[6] = new DFAState("6, accept", CONST_INTEGER, commonLegalEndChars);
        states[7] = new DFAState("7");
        states[8] = new DFAState("8, accept", CONST_INTEGER, commonLegalEndChars);
        states[9] = new DFAState("9, accept", CONST_INTEGER, commonLegalEndChars);
        startState = states[0];
        states[0].addTransition("123456789", states[1]);
        states[0].addTransition("0", states[6]);
        states[1].addTransition(digits, states[1]);
        states[1].addTransition(".", states[2]);
        states[1].addTransition("eE", states[3]);
        states[2].addTransition(digits, states[2]);
        states[2].addTransition("eE", states[3]);
        states[3].addTransition(digits, states[4]);
        states[3].addTransition("+-", states[5]);
        states[4].addTransition(digits, states[4]);
        states[5].addTransition(digits, states[4]);
        states[6].addTransition("xX", states[7]);
        states[6].addTransition("01234567", states[9]);
        states[7].addTransition(digits + "abcdefABCDEF", states[8]);
        states[8].addTransition(digits + "abcdefABCDEF", states[8]);
        states[9].addTransition("01234567", states[9]);
    }

    public static NumberDFA getSingleInstance() {
        return instance;
    }

    @Override
    public boolean isLegalEndChar(char c, DFAState state) {
        return isBlankChar(c) || commonLegalEndChars.contains(String.valueOf(c));
    }

    @Override
    public Token analyze(DFAState state, String token) {
        if (state.getTokenType().equals(CONST_INTEGER)) {
            if (token.equals("0")) {
                return new Token(CONST_INTEGER, 0);
            }
            else if (!token.startsWith("0")) {
                return new Token(CONST_INTEGER, Integer.parseInt(token));
            }
            else if (token.startsWith("0x") || token.startsWith("0X")){
                return new Token(CONST_INTEGER, Integer.parseInt(token.substring(2), 16));
            }
            else {
                return new Token(CONST_INTEGER, Integer.parseInt(token.substring(1), 8));
            }
        }
        else {
            return new Token(CONST_FLOAT, Float.parseFloat(token));
        }

    }
}
