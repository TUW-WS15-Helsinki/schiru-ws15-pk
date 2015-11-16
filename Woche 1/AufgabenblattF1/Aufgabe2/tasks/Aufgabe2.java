import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

/*
    Aufgabe2) Verschachtelte Schleifen und Rekursion

    - Erweitern Sie die Klasse 'Aufgabe2' um eine statische Methode namens "drawNumDiamondIter(int h)", die einen Diamanten
    (Raute) mit Zahlen iterativ berechnet und ausgibt. Der übergebene Parameter "h" entspricht der Höhe des Diamanten (Raute).
    Ein h=9 führt zu folgender Ausgabe:

        1
       222
      33333
     4444444
    555555555
     4444444
      33333
       222
        1

    Testen Sie Ihre Methode mit weiteren Höhen für "h"! Der Rückgabetyp der Methode ist "void".

    - Implementieren Sie zusätzlich eine Methode "drawNumDiamondRec(int h)", die den Diamanten (Raute) rekursiv generieren
    soll. Sie können für die rekursive Implementierung zusätzliche Methoden anlegen.


    Zusatzfragen:
    1. Wie ist die Vorgangsweise abzuändern, wenn statt jedem Wert 1 der Buchstabe A, statt jedem Wert 2 der
    Buchstabe B, ... und statt jedem Wert 5 der Buchstabe E ausgegeben werden soll ?
    (Die Methode soll dann nur für h <= 17 funktionieren.)

    2. An welchen Stellen ist das Programm zu ändern, wenn Rauten der Form
        1
        2
       333
       444
      55555
       444
       333
        2
        1
    generiert werden sollen ?
    3. Welche Unterschiede konnten Sie zwischen der rekursiven und iterativen Implementierung feststellen?
*/
public class Aufgabe2 {

    // just for testing ...
    public static void main(String[] args) {
        drawNumDiamondIter(9);
        drawNumDiamondRec(9);
    }

    public static void drawNumDiamondRec(int h) {
        drawNumDiamondRec(h, 0, 1, 1);
    }

    public static void drawNumDiamondRec(int h, int row, int digitValue, int numDigits) {
        int spaces = (h - numDigits) / 2;

        String output = "";
        for (int col = 0; col < spaces + numDigits; col++) {
            if (col < spaces) {
                output += " ";
            }
            else {
                output += (digitValue + "");
            }
        }

        System.out.println(output);

        // Draw next row
        if(row + 1 < h) {
            boolean increase = row < h/2;
            numDigits += increase ? 2 : -2;
            digitValue += increase ? 1 : -1;
            drawNumDiamondRec(h, ++row, digitValue, numDigits);
        }
    }

    public static void drawNumDiamondIter(int h) {
        int numDigits = 1;
        int digitValue = 1;

        for (int row = 0; row < h; row++) {
            int spaces = (h - numDigits) / 2;

            String output = "";
            for (int col = 0; col < spaces + numDigits; col++) {
                if (col < spaces) {
                    output += " ";
                }
                else {
                    output += (digitValue + "");
                }
            }

            System.out.println(output);

            boolean increase = row < h/2;
            numDigits += increase ? 2 : -2;
            digitValue += increase ? 1 : -1;
        }
    }
}

