package token;

public class Token {

    private final String token;
    private final TokenType tokenType;

    public Token(String token, TokenType tokenType) {
        this.token = token;
        this.tokenType = tokenType;
    }

    public String getToken() {
        return token;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

}
