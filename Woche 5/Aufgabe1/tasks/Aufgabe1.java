import java.util.Arrays;

/*******************************************************************************

 AUFGABENBLATT F5 - Allgemeine Informationen

 Das Projekt AufgabenblattF5 besteht aus fünf Aufgaben in je einer Klasse, die
 Sie erweitern und in den Übungen vom präsentieren können müssen.

 ********************************** HINWEIS ************************************
 Übungswoche F5 findet aufgrund Laborverfügbarkeiten über zwei Wochen statt.
 D.h. alle Dienstag-, Mittwoch- und Freitag-Gruppen absolvieren die Übung
 in der Woche von 11.01-15.01 in ihrem jeweiligen Slot.
 Alle Montag- und Donnerstag-Gruppen absolvieren die Übung in der Woche von
 18.01-22.01 in ihrem jeweiligen Slot.
 *******************************************************************************

 Achten Sie bei der Implementierung der Klassen auf folgende Punkte:

 - Einige Aufgabenstellungen sind in eine Geschichte eingebettet und auf einer
 höheren Abstraktionsebene beschrieben als in bisherigen Übungen. Rechnen Sie
 daher mit einem höheren Aufwand zum Verstehen der Aufgaben.
 - Ihr Programm sollte kompilierbar und ausführbar sein.
 - Testen und kommentieren Sie Ihre Programme ausführlich.
 - Bei jeder Aufgabe finden Sie Zusatzfragen. Diese Zusatzfragen beziehen sich
 thematisch auf das erstellte Programm. Sie müssen diese Zusatzfragen in der
 Übung beantworten können.

 Abgabe: Die Abgabe erfolgt in TUWEL indem Sie bis Montag, den 11.01.2016 um
 06:00 Ihre Kreuzerl des aktuellen Aufgabenblattes eintragen. Nur durch
 Ankreuzen können Sie auch Punkte bekommen. Allerdings müssen Sie angekreuzte
 Aufgaben auch vorzeigen können.

 ******************************************************************************/
/*
    Aufgabe1) Sortieren & Suchen

    Implementieren Sie in der gegebenen Klasse Aufgabe1 folgende statische
    Methoden:

    - sort:      Diese Methode soll den Sortieralgorithmus "QuickSort"
                 implementieren. Sie müssen den Sortieralgorithmus selbst
                 ausimplementieren und dürfen keinen entsprechenden Aufruf aus
                 der Java-API verwenden.
    - binSearch: Dieser Methode wird ein sortiertes Array übergeben. Zusätzlich
                 erhält die Methode einen Wert nach dem gesucht werden soll. Es
                 soll eine binäre Suche implementiert werden, die true
                 zurückliefert falls das Element enthalten ist, ansonsten false.
    - sortAlternative:
        + Methode beginnt mit dem Sortiervorgang an der Position i=1 und
          vergleicht in jedem Schritt den Wert an dieser Position mit seinem
          Vorgänger (i und i-1)
        + Sind die zwei Werte in der richtigen Reihenfolge aufsteigend sortiert,
          dann wird die Position um eins erhöht.
        + Sind die zwei Werte nicht in der richtigen Reihenfolge, dann werden
          sie vertauscht. Die Position wird um eins erniedrigt, falls i>1,
          ansonsten wird die Position um eins erhöht.
        + Der Algorithmus terminiert, wenn man an der letzten Position im Array
          ankommt und die letzten beiden Werte im Array richtig sortiert sind.

    Zusatzfragen:
    1. Welche API-Aufrufe bietet Java für das Sortieren von Arrays an?
    2. Welcher Sortieralgorithmus wird in Java (1.8) für das Sortieren von
       Arrays verwendet?
    3. Warum ist die Wahl des Pivot-Elements entscheidend für die Performance
       des Quicksort Algorithmus?
    4. Warum muss das Array für die binäre Suche sortiert sein?
    5. Wie geht man vor wenn man in einem absteigend sortierten Array die
       Binärsuche anwenden will?

*/
public class Aufgabe1 {

    // quicksort
    public static void sort(int[] array) {
        quickSort(array, 0, array.length-1);
    }

    public static void quickSort(int[] array, int leftPos, int rightPos) {
        if (leftPos >= rightPos) return;

        int pivotIndex = rightPos; // use last element as pivot

        int insertPos = leftPos;
        for (int i = insertPos; i < rightPos; i++) {
            // make sure all elements with value
            // lower than pivot are on the left
            if (array[i] < array[pivotIndex]) {
                swapElements(array, insertPos, i);
                insertPos++;
            }
        }

        // after all smaller elements have been moved to the left
        // put pivot between smaller and larger elements
        swapElements(array, insertPos, pivotIndex);

        // Sort left side of pivot
        quickSort(array, leftPos, insertPos-1);

        // Sort right side of pivot
        quickSort(array, insertPos+1, rightPos);
    }

    public static void swapElements(int[] arr, int indexA, int indexB) {
        int temp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = temp;
    }

    // array is assumed to be sorted
    public static boolean binSearch(int[] array, int elem) {
        while (array.length > 1) {
            int centerIndex = (array.length-1)/2;
            int centerElem = array[centerIndex];

            if (elem == centerElem) {
                return true;
            } else if (elem > centerElem) {
                // Continue searching in right part of array
                array = Arrays.copyOfRange(array, centerIndex + 1, array.length);
            } else if (centerElem > elem) {
                // Continue searching in left part of array
                array = Arrays.copyOfRange(array, 0, centerIndex);
            }
        }

        if (array.length == 1 && array[0] == elem) return true;

        return false;
    }

    // just for testing ...
    public static void main(String[] args) {
        sort(new int[]{9, 9, 5, 7, 3, 7, 3, 5, 3});

        System.out.println(binSearch(new int[]{1}, 1));
    }
}