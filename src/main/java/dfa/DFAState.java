package dfa;

import token.TokenType;

import java.util.HashMap;
import java.util.Map;

public class DFAState {

    private final String comment;
    private final boolean isAccept;
    private final TokenType tokenType;
    private final Map<Character, DFAState> walkMap = new HashMap<>();
    private final String legalEndChars;

    public DFAState(String comment) {
        this.comment = comment;
        this.isAccept = false;
        this.tokenType = TokenType.ERROR;
        this.legalEndChars = "";
    }

    public DFAState(String comment, String legalEndChars) {
        this.comment = comment;
        this.isAccept = true;
        this.tokenType = TokenType.UNDETERMINED;
        this.legalEndChars = legalEndChars;
    }

    public DFAState(String comment, TokenType tokenType, String legalEndChars) {
        this.comment = comment;
        this.isAccept = true;
        this.tokenType = tokenType;
        this.legalEndChars = legalEndChars;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public boolean isAccept() {
        return isAccept;
    }

    public boolean isLegalEndChar(char ch) {
        return legalEndChars.contains(String.valueOf(ch));
    }

    public String getComment() {
        return comment;
    }

    public void addTransition(char c, DFAState state) {
        if (walkMap.containsKey(c) && !walkMap.get(c).equals(state)) {
            throw new IllegalStateException("Duplicate DFA state transitions");
        }
        walkMap.put(c, state);
    }

    public void addTransition(String string, DFAState state) {
        for (int i = 0; i < string.length(); i++) {
            addTransition(string.charAt(i), state);
        }
    }

    public DFAState getNext(char c) {
        return walkMap.getOrDefault(c, null);
    }

    @Override
    public String toString() {
        return comment;
    }
}
