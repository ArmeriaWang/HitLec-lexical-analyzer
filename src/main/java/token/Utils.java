package token;

import java.util.*;


/**
 * 辅助方法和参数
 */
public class Utils {

    private static final List<String> keywordList = Arrays.asList(
            "do", "while", "for", "if", "else",
            "int", "float", "bool", "string", "struct"
    );

    public static final String digits = "0123456789";
    public static final String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";


    public static boolean isKeyword(String token) {
        return keywordList.contains(token.toLowerCase());
    }

    public static boolean isBlankChar(char c) {
        return " \r\n\t".contains(String.valueOf(c));
    }
}
