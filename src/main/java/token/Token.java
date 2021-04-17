package token;

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
}
