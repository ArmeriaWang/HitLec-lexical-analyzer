package dfa;

import token.IdentifierTable;
import token.Token;
import token.TokenType;

import java.util.HashMap;
import java.util.Map;

import static token.TokenType.*;
import static token.Utils.*;


/**
 * 标识符和关键字DFA。单例模式。
 */
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
        put("string", DT_STRING);
        put("true", CONST_BOOLEAN);
        put("false", CONST_BOOLEAN);
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
    public boolean isLegalEndChar(char c, DFAState state) {
        return isBlankChar(c) || commonLegalEndChars.contains(String.valueOf(c));
    }

    /**
     * 根据自动机接受状态和token字符串分析token类型和值。
     * 若是标识符Identifier，则加入{@code IdentifierTable}中
     * @param state 最后接受该token的状态
     * @param token token字符串
     * @return Token实例，包括token类别和token值
     */
    @Override
    public Token analyze(DFAState state, String token) {
        if (token2attribute.containsKey(token.toLowerCase())) {
            return new Token(token2attribute.get(token.toLowerCase()), null);
        }
        else {
            Token identifier = new Token(TokenType.IDENTIFIER, token);
            IdentifierTable.addIdentifier(identifier);
            return identifier;
        }
    }

}
