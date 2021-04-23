package dfa;

import token.TokenType;

import java.util.HashMap;
import java.util.Map;

/**
 * DFA状态
 */
public class DFAState {

    private final String comment;
    private final boolean isAccept;
    private final TokenType tokenType;
    private final Map<Character, DFAState> walkMap = new HashMap<>();
    private final String legalEndChars;

    /**
     * 构造一个DFA非接受状态
     * @param comment 状态注释
     */
    public DFAState(String comment) {
        this.comment = comment;
        this.isAccept = false;
        this.tokenType = TokenType.ERROR;
        this.legalEndChars = "";
    }

    /**
     * 构造一个DFA接受状态，token类型定义为{@code TokenType.UNDETERMINED}
     * @param comment 状态注释
     * @param legalEndChars 合法结束字符集
     */
    public DFAState(String comment, String legalEndChars) {
        this.comment = comment;
        this.isAccept = true;
        this.tokenType = TokenType.UNDETERMINED;
        this.legalEndChars = legalEndChars;
    }

    /**
     * 构造一个DFA接受状态
     * @param comment 状态注释
     * @param tokenType token类型
     * @param legalEndChars 合法结束字符集
     */
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

    /**
     * 添加转移边
     * @param c 输入字符
     * @param state 目标状态
     */
    public void addTransition(char c, DFAState state) {
        if (walkMap.containsKey(c) && !walkMap.get(c).equals(state)) {
            throw new IllegalStateException("Duplicate DFA state transitions");
        }
        walkMap.put(c, state);
    }

    /**
     * 添加转移边。输入给定字符串中的任意一个字符，都会转移到目标状态
     * @param string 输入字符集
     * @param state 目标状态
     */
    public void addTransition(String string, DFAState state) {
        for (int i = 0; i < string.length(); i++) {
            addTransition(string.charAt(i), state);
        }
    }

    /**
     * 获取下一个状态
     * @param c 输入字符
     * @return 当前状态输入{@code c}之后达到的状态。如果未定义，返回null
     */
    public DFAState getNext(char c) {
        return walkMap.getOrDefault(c, null);
    }

    @Override
    public String toString() {
        return comment;
    }
}
