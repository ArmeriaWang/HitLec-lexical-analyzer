package token;

public class Position {

    private final int lineNum;
    private final int columnNum;

    public Position(int lineNum, int columnNum) {
        this.lineNum = lineNum;
        this.columnNum = columnNum;
    }

    public static Position parsePosition(String str) {
        if (str.length() < 2) {
            throw new IllegalArgumentException();
        }
        String[] numbers = str.substring(1, str.length() - 1).split(",");
        return new Position(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]));
    }

    public int getLineNum() {
        return lineNum;
    }

    public int getColumnNum() {
        return columnNum;
    }

    @Override
    public String toString() {
        return String.format("<%d, %d>", lineNum, columnNum);
    }
}
