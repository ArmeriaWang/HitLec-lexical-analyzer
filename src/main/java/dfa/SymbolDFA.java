package dfa;

import token.Token;

import static token.TokenType.*;
import static main.Utils.*;


/**
 * 各类符号DFA。单例模式。
 */
public class SymbolDFA extends DFA {

    private static final int stateNumber = 30;
    private static final SymbolDFA instance = new SymbolDFA();

    private SymbolDFA() {
        super(stateNumber);
        states[0] = new DFAState("0, start");
        states[1] = new DFAState("1, accept, {", BEGIN, "/+-*!&;.({}_'\"" + digits + letters);
        states[2] = new DFAState("2, accept, }", END, "/+-*!;.({}_'\"" + digits + letters);
        states[3] = new DFAState("3, accept, ,", COMMA, "/+-*!;&(_'\"" + digits + letters);
        states[4] = new DFAState("4, accept, ;", SEMICOLON, "/+-*&!;.({}_'\"" + digits + letters);
        states[5] = new DFAState("5, accept, .", DOT, "/_" + letters);
        states[6] = new DFAState("6, accept, [", SQUARE_LEFT, "/+-!*&.(_]" + digits + letters);
        states[7] = new DFAState("7, accept, ]", SQUARE_RIGHT, "/=<>+-*%|&^~!;,.)[");
        states[8] = new DFAState("8, accept, (", ROUND_LEFT, "/+-*&!~()_'\"" + digits + letters);
        states[9] = new DFAState("9, accept, )", ROUND_RIGHT, "/=<>+-*%|&^~!;,{])");

        final String operatorLegalChars = "/+-*~!(_&" + digits + letters;
        states[10] = new DFAState("10, accept, ==", EQUAL, operatorLegalChars);
        states[11] = new DFAState("11, accept, >", GREATER, operatorLegalChars);
        states[12] = new DFAState("12, accept, >=", GREATER_EQUAL, operatorLegalChars);
        states[13] = new DFAState("13, accept, <", LESS, operatorLegalChars);
        states[14] = new DFAState("14, accept, <=", LESS_EQUAL, operatorLegalChars);
        states[15] = new DFAState("15, accept, !=", NOT_EQUAL, operatorLegalChars);

        states[16] = new DFAState("16, accept, +", PLUS, operatorLegalChars);
        states[17] = new DFAState("17, accept, -", MINUS, operatorLegalChars);
        states[18] = new DFAState("18, accept, *", STAR, operatorLegalChars);
        states[19] = new DFAState("19, accept, /", DIVIDE, operatorLegalChars);
        states[20] = new DFAState("20, accept, %", MOD, operatorLegalChars);
        states[21] = new DFAState("21, accept, &&", LOGIC_AND, operatorLegalChars);
        states[22] = new DFAState("22, accept, ||", LOGIC_OR, operatorLegalChars);
        states[23] = new DFAState("23, accept, !", LOGIC_NOT, operatorLegalChars);
        states[24] = new DFAState("24, accept, &", AMPERSAND, operatorLegalChars);
        states[25] = new DFAState("25, accept, |", BIT_OR, operatorLegalChars);
        states[26] = new DFAState("26, accept, ~", BIT_NOT, operatorLegalChars);
        states[27] = new DFAState("27, accept, ^", BIT_XOR, operatorLegalChars);

        states[28] = new DFAState("28, accept, =", ASSIGNMENT, operatorLegalChars);

        startState = states[0];
        states[0].addTransition("{", states[1]);
        states[0].addTransition("}", states[2]);
        states[0].addTransition(",", states[3]);
        states[0].addTransition(";", states[4]);
        states[0].addTransition(".", states[5]);
        states[0].addTransition("[", states[6]);
        states[0].addTransition("]", states[7]);
        states[0].addTransition("(", states[8]);
        states[0].addTransition(")", states[9]);

        states[0].addTransition("=", states[28]);
        states[0].addTransition(">", states[11]);
        states[0].addTransition("<", states[13]);
        states[0].addTransition("!", states[23]);

        states[28].addTransition("=", states[10]);
        states[23].addTransition("=", states[15]);
        states[11].addTransition("=", states[12]);
        states[13].addTransition("=", states[14]);

        states[0].addTransition("+", states[16]);
        states[0].addTransition("-", states[17]);
        states[0].addTransition("*", states[18]);
        states[0].addTransition("/", states[19]);
        states[0].addTransition("%", states[20]);
        states[0].addTransition("&", states[24]);
        states[0].addTransition("|", states[25]);
        states[0].addTransition("~", states[26]);
        states[0].addTransition("^", states[27]);
        states[24].addTransition("&", states[21]);
        states[25].addTransition("|", states[22]);
    }

    public static SymbolDFA getSingleInstance() {
        return instance;
    }

    @Override
    public boolean isLegalEndChar(char c, DFAState state) {
        return isBlankChar(c) || state.isLegalEndChar(c);
    }

    @Override
    public Token analyze(DFAState state, String token) {
        return new Token(state.getTokenType(), null);
    }
}
