package main;

import dfa.*;
import org.apache.commons.lang3.tuple.Pair;
import token.Position;
import token.Token;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LexicalParser {

    private static final List<DFA> dfaList = new ArrayList<>();
    private static int lineNum;
    private static int columnNum;

    public static Position getCurrentPosition() {
        return new Position(lineNum, columnNum);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/main/java/test4report.c"));
        dfaList.add(IdentifierKeywordDFA.getSingleInstance());
        dfaList.add(NumberDFA.getSingleInstance());
        dfaList.add(SymbolDFA.getSingleInstance());
        dfaList.add(CommentDFA.getSingleInstance());
        dfaList.add(StringConstDFA.getSingleInstance());
        lineNum = 0;
        while (scanner.hasNextLine()) {
            lineNum++;
            String line = scanner.nextLine();
            line = line.trim();
            columnNum = 0;
            while (columnNum < line.length()) {
                boolean accept = false;
                for (DFA dfa: dfaList) {
                    Pair<Integer, Token> pair = dfa.walk(line, columnNum);
                    int nextPos = pair.getLeft();
                    Token token = pair.getRight();
//                System.out.println(nextPos);
                    if (token != null) {
                        System.out.println(token);
                        columnNum = nextPos;
                        accept = true;
                        break;
                    }
                }
                if (!accept) {
                    System.err.printf("Lexical error at Line %d: Cannot analyze at position %d%n", lineNum, columnNum + 1);
                    break;
                }
                while (columnNum < line.length() && Utils.isBlankChar(line.charAt(columnNum))) {
                    columnNum++;
                }
//            System.out.println("pos = " + pos);
            }
        }
    }
}
