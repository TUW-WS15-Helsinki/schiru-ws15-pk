/*
    Aufgabe5) Vervollständigung von Methoden

    Vervollständigen Sie die Methoden, sodass sie sich den Kommentaren entsprechend verhalten. Verändern Sie dabei nur
    Ausdrücke, die in einem Kommentar mit TODO: gekennzeichnet sind. Lassen Sie andere Teile der Klasse unverändert.
    Von dieser Einschränkung ausgenommen ist nur die Methode main, die Sie beliebig zum Testen verwenden können.

    Zusatzfragen:
    1. Wie lautet jeweils die Abbruchbedingung?
    2. In welchen Fällen (in dieser Aufgabe) werden Kurzschlussoperatoren benötigt?
    3. Warum wird in isPrime eine Hilfsmethode benötigt?
    4. Warum liefert Java keine Fehlermeldung, wenn mehrere Methoden denselben Namen haben?
*/
public class Aufgabe5 {

    // Returns true if (and only if) n is a prime number;  n > 1 is assumed.
    private static boolean isPrime(long n) {
        return isPrime(n, (n-1 /* TODO: modify expression if necessary */));
        // See the method isPrime below.
    }

    // 4 is Prime
    // 4/3 % 0 == 0 => false
    // 4/2 % 0 == 0 => true
    // 4/3 FALSE
    // is Prime => FALSE

    // Helper method for isPrime ...
    private static boolean isPrime(long n, long m) {
        return ((m) == (1 /* TODO: modify expression if necessary */))
                || (((n % m) != (0 /* TODO: modify expression if necessary */)) // wenn n%m==0 ==> false, wenn n%m!=0 ==> true
                && isPrime((n /* TODO: modify expression if necessary */), m - 1));
    }

    // Prints the largest prime number smaller than or equal to n;  n > 1 is assumed.
    private static void printPrime(long n) {
        if (isPrime((n /* TODO: modify expression if necessary */))) {
            System.out.println((n/* TODO: modify expression if necessary */));
        } else {
            printPrime((n - 1 /* TODO: modify expression if necessary */));
        }
    }


    // Returns the sum of all even numbers in the range from x to y (including x and y).
    private static int sum(int x, int y) {
        if (x > y) {
            return (0 /* TODO: modify expression if necessary */);
        }
        return (x % 2 != 0 ? 0 : x /* TODO: modify expression if necessary */) + sum((x + 1 /* TODO: modify expression if necessary */), y);
    }


    // Returns x * y without applying the operator *;  y >= 0 is assumed.
    private static int prod(int x, int y) {
        if (y == 0 /* TODO: modify expression if necessary */) {
            return (0 /* TODO: modify expression if necessary */);
        }
        return (x /* TODO: modify expression if necessary */)
                + prod((x /* TODO: modify expression if necessary */),
                (y - 1 /* TODO: modify expression if necessary */));
    }


    // just for testing ...
    public static void main(String[] args) {
        // Den Rumpf dieser Methode können Sie beliebig verändern.

        printPrime(4);
        printPrime(129);
        printPrime(3456);
//
//        System.out.println(sum(-2, 2));
//
//        System.out.println(sum(5, 5));
//        System.out.println(sum(5, 7));
//        System.out.println(sum(6, 5));
//        System.out.println(sum(-6, 5));
//
//        System.out.println(prod(1, 5));
//
//        System.out.println(prod(2, 5));
//        System.out.println(prod(2, 1));
//        System.out.println(prod(2, 2));
//        System.out.println(prod(-2, 3));

    }
}

