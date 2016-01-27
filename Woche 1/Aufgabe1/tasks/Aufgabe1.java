import java.util.Arrays;

/**********************************************************************************************************************

 AUFGABENBLATT F1 für fortgeschrittene Übungsgruppen - Allgemeine Informationen

 Das Projekt AufgabenblattF1 besteht aus fünf Aufgaben in den Klassen Aufgabe1 bis Aufgabe5, die Sie erweitern und in
 den Übungen vom 16.11. bis 20.11. präsentieren können müssen.

 Achten Sie bei der Implementierung von Aufgabe1-Aufgabe5 auf folgende Punkte:

 - Ihr Programm sollte kompilierbar und ausführbar sein.
 - Testen und kommentieren Sie Ihre Programme ausführlich.
 - Bei jeder Aufgabe finden Sie Zusatzfragen. Diese Zusatzfragen beziehen sich thematisch auf das erstellte Programm.
   Sie müssen diese Zusatzfragen in der Übung beantworten können.

 Abgabe: Die Abgabe erfolgt in TUWEL indem Sie bis Montag, den 16.11.2015 um 06:00 Ihre Kreuzerl des aktuellen
 Aufgabenblattes eintragen. Nur durch Ankreuzen können Sie auch Punkte bekommen. Allerdings müssen Sie
 angekreuzte Aufgaben auch vorzeigen können.

***********************************************************************************************************************/
/*
    Aufgabe1) Mehrfache Rekursion versus einfache Iteration

    Implementieren Sie in Aufgabe1 je eine iterative (iter) und eine rekursive (rec) statische Methode, die für eine
    ganze nicht-negative Zahl n die Zahl L(n) berechnet. L(n) ist definiert durch:

        L(0) = 1
        L(1) = 1
        L(n) = L(n - 1) + L(n - 2) + 1  wenn  n > 1.

    Rufen Sie in main die Methoden iter und rec mit allen Zahlen von 0 bis 50 auf und geben Sie die Ergebnisse aus.
    Ein Programmaufruf soll in weniger als einer Sekunde terminieren.

    Hinweis: Eine simple, nahe an die Definition angelehnte rekursive Implementierung kann sehr ineffizient werden.
    Suchen Sie daher nach einer effizienteren Implementierungsmöglichkeit, die jedoch ohne Schleifen auskommen muss.
    Sie können z.B. Hilfsmethoden (ohne Schleifen) und/oder ein Array verwenden.

    Zusatzfragen:
    1. Welche Vor- und Nachteile hat iter im Vergleich zu rec?
    2. Welcher elementare Typ ist als Ergebnistyp dieser Methoden geeignet? Warum nur dieser?
    3. Welche elementaren Typen sind als Parametertypen geeignet? Warum?
    4. Welche alternativen Implementierungstechniken würde es geben?
       Aufgrund welcher Tatsachen haben Sie die von Ihnen verwendete(n) Implementierungstechnik(en) gewählt?
*/
public class Aufgabe1 {

    // invokes iter as well as rec with all integers from 0 to 50 and prints the results
    // (without empty lines or other output)
    public static void main(String[] args) {
        for (int i = 0; i <= 50; i++) {
            System.out.println(iter(i));
            System.out.println(rec(i));
        }
    }

    public static long rec(int n, int i, long[] L) {
        L[i] = L[i - 1] + L[i - 2] + 1;

        if(i == n)
            return L[i];

        return rec(n, ++i, L);
    }

    public static long rec(int n) {
        if(n <= 1) {
            return 1;
        } else {
            long[] L = new long[n+1];
            L[0] = 1;
            L[1] = 1;
            return rec(n, 2, L); // n > 1, L(0) and L(1) are already predefined/prefilled
        }
    }

    public static long iter(int n) {
        if (n <= 1) {
            return 1;
        } else {
            long[] L = new long[n+1];
            L[0] = 1;
            L[1] = 1;

            // Calculate L(n)
            int i = 2; // n > 1, L(0) and L(1) are already predefined/prefilled
            do {
                L[i] = L[i - 1] + L[i - 2] + 1;
                i++;
            } while (i <= n);

            return L[n];
        }
    }
}
