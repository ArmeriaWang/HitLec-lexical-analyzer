package dfa;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import token.Token;

/**
 * 有限状态自动机DFA类
 */
public abstract class DFA {

    protected DFAState startState;
    protected final int stateNumber;
    protected final DFAState[] states;

    /**
     * 创建一个DFA实例
     * @param stateNumber 状态数
     */
    protected DFA(int stateNumber) {
        this.stateNumber = stateNumber;
        states = new DFAState[stateNumber];
    }

    /**
     * 输入字符串，模拟DFA转移。贪心地尽量往后拓展。
     * 从字符串{@code line}的第{@code startPos}个字符开始输入，DFA从初始状态开始转移，在所达到的最远位置处分割为一个token。
     * @param line 输入字符串
     * @param startPos 输入开始位置
     * @return 结束位置和token的二元组
     */
    public Pair<Integer, Token> walk(String line, int startPos) {
        if (startPos >= line.length()) {
            throw new IllegalArgumentException("The value of startPos exceeds the length of line");
        }
        DFAState curState = startState;
        int curPos = startPos;
        while (curPos < line.length()) {
            DFAState nextState = curState.getNext(line.charAt(curPos));
            if (nextState == null) {
                if (isLegalEndChar(line.charAt(curPos), curState) && curState.isAccept()) {
                    return new ImmutablePair<>(curPos, analyze(curState, line.substring(startPos, curPos)));
                }
                else {
                    return new ImmutablePair<>(curPos, null);
                }
            }
            curState = nextState;
//            System.out.println(nextState.getComment());
            curPos++;
        }
        if (curState.isAccept()) {
            return new ImmutablePair<>(curPos, analyze(curState, line.substring(startPos, curPos)));
        }
        else {
            return new ImmutablePair<>(curPos, null);
        }
    }

    /**
     * 判断字符是否是状态的合法结束输入
     * @param c 输入字符
     * @param state 状态
     * @return 字符是状态的合法结束输入时，true；否则false
     */
    public abstract boolean isLegalEndChar(char c, DFAState state);

    /**
     * 根据自动机接受状态和token字符串分析token类型和值
     * @param state 最后接受该token的状态
     * @param token token字符串
     * @return Token实例，包括token类别和token值
     */
    public abstract Token analyze(DFAState state, String token);

}
