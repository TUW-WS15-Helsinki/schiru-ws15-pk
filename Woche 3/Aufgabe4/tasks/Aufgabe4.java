import java.util.ArrayDeque;
import java.util.Deque;

/*
    Aufgabenstellung zur Klasse Aufgabe4:

    Implementieren Sie eine statische Methode check, die einen String als Parameter hat und genau dann true zurückgibt,
    wenn der String einen wohlgeformten Ausdruck enthält. Ein Ausdruck ist dann wohlgeformt, wenn er nur aus
      - ganzen Zahlen (Folge von Ziffern),
      - Variablennamen (Folge von Buchstaben),
      - geklammerten Ausdrücken (durch zusammenpassende öffnende und schließende Klammern '(' oder '[' bzw. ')' oder ']'
        umschlossene Ausdrücke),
      - zwei durch einen Operator +, -, * oder / verbundene Ausdrücke
    besteht.

    Beispiele für wohlgeformte Ausdrücke: "", "a*[bc+12]", "a+(b)-c", "a+[a+8+(b+c)]/a"
    Beispiele für nicht korrekt geklammerte Ausdrücke: "[a)", "()", "a--[x]", "-a+b", "]["

    Verwenden Sie einen Stack zur Überprüfung der Klammerung: Durchlaufen Sie die Zeichen im String von vorne nach
    hinten und legen Sie jede öffnende Klammer, die Sie dabei finden, auf den Stack. Wenn Sie auf eine schließende
    Klammer stoßen, nehmen Sie das oberste Element vom Stack; bei korrekter Klammerung muss die schließende Klammer
    mit der Klammer vom Stack zusammenpassen. Bei korrekter Klammerung muss der Stack am Ende leer sein.

    Hinweis: Sie können als Stack z.B. ein Objekt des Typs Deque<String> verwenden.
    Bitte verwenden Sie dafür kein Array.

    Zusatzfragen:
    1. Was versteht man unter dem LIFO- und FIFO-Prinzip?
    2. Könnte man zur Lösung dieser Aufgabe statt Deque<String> auch Queue<String> verwenden?
    3. Wie könnte man diese Aufgabe auch mit einem Array statt einem Stack lösen?
       Welche Nachteile würden sich daraus ergeben?
*/
public class Aufgabe4 {

    private static boolean check(String toBeChecked) {
        String numbers = "0123456789";
        String letters = "abcdefghijklmnopqrstuvxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String operators = "+-*/";
        String openingParantheses = "([";
        String closingParantheses = ")]";

        Deque<Character> paranthesesStack = new ArrayDeque<>();

        boolean numberExpected = true;
        boolean letterExpected = true;
        boolean operatorExpected = false;

        char currentChar;
        int charIndex;
        // Iterate over toBeChecked characters
        for (int i = 0; i < toBeChecked.length(); i++) {
            currentChar = toBeChecked.charAt(i);

            // If an opening parantheses was found...
            if (openingParantheses.indexOf(currentChar) > -1) {
                // ... add to Stack
                paranthesesStack.offerLast(currentChar);
                continue;
            }

            // If closing parantheses was found...
            if ((charIndex = closingParantheses.indexOf(currentChar)) > -1) {
                // ... check if matching opening parentheses is on top of stack
                // A matching par. can be identified by having the same index as the closing one

                Character lastOpeningPar = paranthesesStack.pollLast();
                if(lastOpeningPar != null && openingParantheses.indexOf(lastOpeningPar) == charIndex) {
                    continue;
                } else {
                    return false;
                }
            }

            // If a number was found and numbers are currently allowed
            if (numbers.indexOf(currentChar) > -1 && numberExpected) {
                // A number can not be followed directly by a letter
                letterExpected = false;

                operatorExpected = true;
                continue;
            }

            // If a letter was found and letters are currently allowed
            if (letters.indexOf(currentChar) > -1 && letterExpected) {
                // A letter can not be followed directly by a number
                numberExpected = false;

                operatorExpected = true;
                continue;
            }

            // If an operator was found and operators are currently allowed
            if (operators.indexOf(currentChar) > -1 && operatorExpected) {
                // An operator is allowed to be followed by numbers or letters
                numberExpected = true;
                letterExpected = true;

                // An operator can not be followed direcetly by another operator
                operatorExpected = false;
                continue;
            }

            // This statement can only be reached, if no correct character has been found
            return false;
        }

        // In the end, all parantheses should be matched, resulting in an empty array
        // Additionally, operatorExpected should be true, as this means the last character was
        // a number or letter
        if (paranthesesStack.isEmpty() && operatorExpected == true) {
            return true;
        }

        return false;
    }

    // Just for testing ...
    public static void main(String[] args) {
        System.out.println(check("0-a+b"));
    }
}
