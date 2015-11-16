/**
 * Created by thomas on 16/11/15.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(giveMeWeirdTriangles(5));
    }

    static String[] triangle;

    public static String giveMeWeirdTriangles(int n) {
        if (triangle == null) triangle = new String[n]; // Lazily init triangle

        generateTriangle(n, 0);
        repeatLines(n, 0);

        return concatTriangle(0);
    }

    public static String concatTriangle(int i) {
        return i == triangle.length ? "" : triangle[i] + "\n" + concatTriangle(i + 1);
    }

    public static void repeatLines(int n, int row)  {
        if (row == n) {
           return;
        }

        String invertedCurrentLine = invertLine(triangle[row], 0);

        String otherLine = triangle[n-1-row];
        String invertedOtherLine = invertLine(otherLine, 0);

        repeatLines(n, row+1);

        triangle[row] = triangle[row] + " " + invertedOtherLine +" "+ otherLine +" "+ invertedCurrentLine;

    }

    // pos is 1 based
    public static String invertLine(String s, int pos) {
        return (pos == s.length()) ? "" : s.charAt(s.length()-1-pos) + invertLine(s, pos+1);
    }

    public static void generateTriangle(int n, int row) {
        if(row == n) {
            return;
        }

        int spaces = row;
        int chars = n-spaces;

        String line = generateLine(spaces, chars);
        triangle[row] = line;

        generateTriangle(n, row+1);
    }

    public static String generateLine(int spaces, int chars) {
        if (spaces > 0) {
            return " " + generateLine(spaces - 1, chars);
        } else if (chars > 0) {
            return "*" + generateLine(0, chars-1);
        }

        return "";
    }

}

