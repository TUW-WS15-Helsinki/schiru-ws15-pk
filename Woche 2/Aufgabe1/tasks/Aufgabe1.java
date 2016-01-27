/**********************************************************************************************************************

 AUFGABENBLATT 5 - Allgemeine Informationen

 Das Projekt Aufgabenblatt5 besteht aus fünf Aufgaben in den Klassen Aufgabe1 bis Aufgabe5, die Sie erweitern und in
 den Übungen vom 23.11. bis 27.11. präsentieren können müssen.

 Achten Sie bei der Implementierung von Aufgabe1 bis Aufgabe5 auf folgende Punkte:

 - Ihr Programm sollte kompilierbar und ausführbar sein.
 - Testen und kommentieren Sie Ihre Programme ausführlich.
 - Bei jeder Aufgabe finden Sie Zusatzfragen. Diese Zusatzfragen beziehen sich thematisch auf das erstellte Programm.
   Sie müssen diese Zusatzfragen in der Übung beantworten können.

 Abgabe: Die Abgabe erfolgt in TUWEL indem Sie bis Montag, den 23.11.2015 um 06:00 Ihre Kreuzerln des aktuellen
 Aufgabenblattes eintragen. Nur durch Ankreuzen können Sie auch Punkte bekommen. Allerdings müssen Sie
 angekreuzte Aufgaben auch vorzeigen können.

 ***********************************************************************************************************************/
/*
    Aufgabe1) Rekursion in Iteration ändern

    Stellen Sie fest, was die Methode 'rec' macht. Schreiben Sie eine statische Methode 'iter', die das Gleiche macht
    wie 'rec' (gleiches Input-Output-Verhalten), aber ohne Rekursion auskommt.

    Zusatzfragen:
    1. Was berechnet 'rec'? // Berechnet den Binomialkoeffizienten
    2. Warum ist es notwendig, negative Parameterwerte getrennt zu behandeln?
    3. Wozu dient jede einzelne Fallunterscheidung?
*/
public class Aufgabe1 {

    // What does rec compute?
    private static int rec(int x, int y) {
        if (x == y || y == 0) {
            return 1;
        }
        if (x < 0) {
            return rec(-x, y);
        }
        if (y < 0) {
            return rec(x, -y);
        }
        if (x < y) {
            return rec(y, x);
        }
        return rec(x - 1, y - 1) + rec(x - 1, y);
    }

    /*  2,1 + 1,1
        1,0 // 1

         1 + 1 = 2

         3,1 + 2,1
         2,0 // 1
         2,1 // 1 + 1

         1+1+1 = 3

     */

    // Does the same as rec, but is not recursive.
    private static long iter(int x, int y) {
        x = x < 0 ? -x : x;
        y = y < 0 ? -y : y;
        if (x < y) { int t = x; x = y; y = t; }

        return fakt(x) / (fakt(y) * fakt(x-y));
    }

    private static long fakt(int n) {
        long fakt = n > 1 ? n : 1;
        while(--n > 1) {
            fakt *= n;
        }
        return fakt;
    }

    // Just for testing ...
    public static void main(String[] args) {

        // Den Rumpf dieser Methode können Sie beliebig verändern.
        for (int i = 0; i <= 5; i++) {
            System.out.println("r" + rec(i, 5));
            System.out.println("i" + iter(i, 5));
        }
    }
}
