package token;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 标识符表
 */
public class IdentifierTable {

    private static final Set<Token> identifierSet = new HashSet<>();

    /**
     * 添加标识符
     * @param token 标识符
     * @return 该标识符的hash值
     */
    public static int addIdentifier(Token token) {
        if (token.getTokenType() != TokenType.IDENTIFIER) {
            throw new IllegalArgumentException("Adding a non-identifier token to identifier table");
        }
        identifierSet.add(token);
        return token.hashCode();
    }

    public static boolean isIdentifier(Token token) {
        return identifierSet.contains(token);
    }

    public static List<Token> getIdentifierList() {
        return new ArrayList<>(identifierSet);
    }

}
