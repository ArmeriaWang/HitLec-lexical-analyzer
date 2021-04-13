package token;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IdentifierTable {

    private static final Set<String> identifierSet = new HashSet<>();

    public static int addIdentifier(String token) {
        identifierSet.add(token);
        return token.hashCode();
    }

    public static boolean isIdentifier(String token) {
        return identifierSet.contains(token);
    }

    public static List<String> getIdentifierList() {
        return new ArrayList<>(identifierSet);
    }

}
