import dfa.*;
import org.apache.commons.lang3.tuple.Pair;
import token.Token;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static token.Utils.isBlankChar;

public class Main {

    private static final List<DFA> dfaList = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/main/java/test.txt"));
        dfaList.add(IdentifierKeywordDFA.getSingleInstance());
        dfaList.add(NumberDFA.getSingleInstance());
        dfaList.add(SymbolDFA.getSingleInstance());
        dfaList.add(CommentDFA.getSingleInstance());
        dfaList.add(StringConstDFA.getSingleInstance());
        int lineNum = 0;
        while (scanner.hasNextLine()) {
            lineNum++;
            String line = scanner.nextLine();
            line = line.trim();
            int pos = 0;
            while (pos < line.length()) {
                boolean accept = false;
                for (DFA dfa: dfaList) {
                    Pair<Integer, Token> pair = dfa.walk(line, pos);
                    int nextPos = pair.getLeft();
                    Token token = pair.getRight();
//                System.out.println(nextPos);
                    if (token != null) {
                        System.out.println(token);
                        pos = nextPos;
                        accept = true;
                        break;
                    }
                }
                if (!accept) {
                    System.err.printf("Lexical error at Line %d: Cannot analyze at position %d%n", lineNum, pos + 1);
                    break;
                }
                while (pos < line.length() && isBlankChar(line.charAt(pos))) {
                    pos++;
                }
//            System.out.println("pos = " + pos);
            }
        }
    }
}
