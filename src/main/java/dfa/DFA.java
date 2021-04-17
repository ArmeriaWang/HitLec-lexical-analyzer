package dfa;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import token.Token;

public abstract class DFA {

    protected DFAState startState;
    protected final int stateNumber;
    protected final DFAState[] states;

    protected DFA(int stateNumber) {
        this.stateNumber = stateNumber;
        states = new DFAState[stateNumber];
    }

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

    public abstract boolean isLegalEndChar(char ch, DFAState state);

    public abstract Token analyze(DFAState state, String token);

}
