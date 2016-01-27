/*
    Aufgabe4) Rekursion und Termination

    Die Methoden f, g, h, p und q sind vorgegeben. Rufen Sie in main jede dieser Methoden mit allen Argumenten im
    Bereich von -100 bis 100 (in aufsteigender Reihenfolge) auf und geben sie die Ergebnisse aus, wenn die Aufrufe mit
    Argumenten terminieren. Aufrufe, die nicht terminieren oder einen Überlauf produzieren, sind auszulassen.
    Vermeiden Sie Exceptions.

    Hinweis: Für diese Aufgabe ist es besonders wichtig, die Zusatzfragen beantworten zu können.

    Zusatzfragen:
    1. Nennen Sie für jeden nicht terminierenden Aufruf von f, g, h, p und q einen Grund für die Endlosrekursion
       (im Hinblick auf Fundiertheit und Fortschritt).
    2. Beschreiben Sie überblicksartig, was die Methoden f, g, h, p und q berechnen.
    3. Bedeutet ein StackOverflowError immer, dass eine Endlosrekursion vorhanden ist?
    4. Bedeutet kein StackOverflowError immer, dass ein richtiges Ergebnis geliefert wird?
    5. [optional] Ist die Aufgabe überhaupt lösbar (wegen der Unentscheidbarkeit des Halteproblems der Turing-Maschine)?
*/
public class Aufgabe4 {

    // 10, -10, -7, -4, -1, 2, 5, 8, 10, 11, 13, 14, 16, 17, 19, 20, ...
    // Nicht terminierend: alles < 10
    // Grund für endlosrekursion: x-3 erreicht nie -10 oder 10
    // Rückgabewert/Berechnung: Wie oft muss man x um -3 verringern, um x*x = 100 zu erhalten
    private static int f(int x) {
        return x * x == 100 ? 0 : f(x - 3) + 1;
    }

    // Nicht terminierend: alles > -100
    // Terminiert nur für x <= -100, weil alle weiteren Aktionen die Zahl näher zu 0 bringen
    // Berechnet Rekursionstiefe
    private static int g(int x) {
        return x <= -100 ? 0 : g(x / 2 - 2) + 1;
    }

    // Terminiert, wenn x = 100 oder x = +-9
    // 100 = x * x + 19
    // 81 = x^2
    // x = sqrt(81)
    // x = +-9
    private static int h(int x) {
        return x == 100 ? 0 : h(x * x + 19) + 1;
    }

    // Terminiert, wenn x = 0, dividiert so lange durch 2 bis x = 0
    // Funktioniert nur, wenn x = negativae Zweierpotenz oder x = Zweierpotenz - 1
    private static int p(int x) {
        return x == 0 ? 0 : x % 2 == 1 ? p(x / 2) + 2 : p(- x - 1) + 1;
    }

    // Terminiert bei 0, 11 und allen Vielfachen von 11,
    // in allen anderen fällen entsteht ein Überlauf
    // Rückgabewert ist die Anzahl der Rekursiven Aufrufe
    // je nach x kommt es ab 30-32 rekursiven Aufrufen zu einem Überlauf
    //    da bei jedem Aufruf von q x*2 gerechnet wird und es bei 2^31 zum Überlauf kommt
    private static int q(int x) {
        return x % 11 == 0 ? 0 : q(x * 2) + 1;
    }

    // to be implemented by you
    public static void main(String[] args) {
        // function f
//        for (int i = -100; i <= 100; i++) {
//            try {
//                int recCalls = f(i);
//                int endValue = i - (3 * recCalls);
//                System.out.println("x: " + i + ", recCalls: " + recCalls + ", end value: "+ endValue);
//            } catch (Error e) { }
//        }

        // function g
//        for (int i = -100; i <= 100; i++) {
//            try {
//                int recCalls = g(i);
//                System.out.println("x: " + i + ", recCalls: " + recCalls);
//            } catch (Error e) { }
//        }

        // function h
//        for (int i = -100; i <= 100; i++) {
//            try {
//                int recCalls = h(i);
//                System.out.println("x: " + i + ", recCalls: " + recCalls);
//            } catch (Error e) { }
//        }

//         function p
//        for (int i = -100; i <= 100; i++) {
//            try {
//                int result = p(i);
//                System.out.println("x: " + i + ", result: " + result);
//            } catch (Error e) {
////                System.out.println("NOT " + i);
//            }
//        }

        for (int i = -100; i <= 100; i++) {
            try {
                int result = q(i);
                if (result == 0)
                    System.out.println("x: " + i + ", result: " + result);
            } catch (Error e) {}
        }
    }
}
