package token;

import java.util.Objects;

/**
 * 词法分析单元token
 */
public class Token {

    private final TokenType tokenType;
    private final Object value;

    public Token(TokenType tokenType, Object value) {
        this.value = value;
        this.tokenType = tokenType;
    }

    public Object getValue() {
        return value;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    @Override
    public String toString() {
        return String.format("(%-13s,%s)", tokenType, value == null ? "" : value.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return tokenType == token.tokenType && value.equals(token.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokenType, value);
    }
}
