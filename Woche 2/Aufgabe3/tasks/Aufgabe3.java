import java.util.Arrays;

/*
    Aufgabe3) Eindimensionales Array

    Implementieren Sie folgende statische Methoden:

      - 'createArray' gibt ein neues Array zurück, dessen Länge gleich dem Argument der Methode ist. Das Array
        enthält Zufallszahlen größer oder gleich 0.0 und kleiner 100.0 (des diesen Literalen entsprechenden Typs).
        Zur Erzeugung von Zufallszahlen können Sie Math.random() verwenden:
        http://docs.oracle.com/javase/8/docs/api/java/lang/Math.html#random

      - 'printArray' hat zwei Parameter, gibt aber kein Ergebnis zurück. Der erste Parameter A ist ein Array, das z.B.
        durch 'createArray' erzeugt wurde. Der zweite Parameter N ist eine ganze Zahl mit N > 0. 'printArray' zerlegt
        den Wertebereich [0.0, 100.0) in N gleich große Teilbereiche (z.B. für N gleich 4 in [0.0, 25.0), [25.0, 50.0),
        [50.0, 75.0) und [75.0, 100.0) ) und zählt die Zahlen im Array A, die in jeden dieser Wertebereiche fallen.
        Schließlich gibt 'printArray' für jeden dieser Teilbereiche eine Zeile aus, welche die Anzahl der Zahlen im
        entsprechenden Teilbereich enthält. Beispielsweise gibt 'printArray' für ein A der Länge 5 mit den Zahlen
        10.0, 60.0, 20.0, 80.0 und 70.0 und N gleich 4 folgende Zeilen aus:

            2
            0
            2
            1

        Hinweise: Verwenden Sie zum Zählen der Zahlen in den Teilbereichen am besten ein weiteres Array. Am einfachsten
        ist es, die Zuordnung einer Zahl zu einem Teilbereich in die Berechnung des Indexes für dieses Array einzubauen.
        A und N dienen zur Beschreibung der Methode. In Ihrer Implementierung können die Parameter anders heißen.

      - 'diffArray' hat ein Array (wie von 'createArray' zurückgegeben) als Parameter und gibt nichts zurück. Jeder
        Wert im Array wird durch die positive Differenz zwischen diesem Wert und dem Durchschnittswert aller
        Elemente im Array ersetzt.

    Zusatzfragen:
    1. Warum kann man in 'printArray' for-each-Schleifen (also Schleifen der Form for(... : ...) ...) verwenden,
       in 'createArray' und 'diffArray' aber nicht?
        --> Weil man in der foreach-schleife eine Kopie des jew. Elementes erstellt und somit nicht das Element
               direkt verändern kann
    2. Warum ist es möglich, dass 'diffArray' kein Ergebnis zurückgibt, die Auswirkungen der Methode aber dennoch
       sichtbar werden?
        --> Call by reference.
    3. Woran könnte man feststellen, ob die Lösung richtig ist, obwohl jeder Testlauf andere Ergebnisse liefert?
    4. Wie wirken sich wiederholte Anwendungen von 'diffArray' auf die Verteilung der Werte im Array aus
       (entsprechend der Ausgabe von 'printArray')?
       --> Da die Differenz zum Durchschnitt immer geringer wird, befinden sich mit jedem Aufruf von diffArray
            immer mehr Elemente in den Partitionen näher zu 0
*/
public class Aufgabe3 {

    // Hier sollten die benötigten Methoden stehen.

    // Returns an array with n elements.
    // Each element contains a random value between 0.0 and 100.0
    public static double[] createArray(int n) {
        double[] arr = new double[n];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Math.random() * 100.0;
        }

        return arr;
    }

    // Divides the array arr into n partitions between 0.0 and 100.0
    // and prints how many of arr's values are in each partition
    public static void printArray(double[]arr, int n) {
        int[] partitions = new int[n];
        double partitionSteps = 100.0/n;

        // Calculate how many elements are included in every partition
        int belongingPartition;
        for (double d : arr) {
            belongingPartition = (int) Math.ceil(d/partitionSteps) - 1;
            partitions[belongingPartition] += 1;
        }

        // Display results
        for(int partition : partitions) {
            System.out.println(partition);
        }
    }

    // Calculates the difference of each element in the array
    // to the arithmetic average of all values and replaces the
    // value with the calculated value
    public static void diffArray(double[] arr) {
        double average = calculateAverageArrayValue(arr);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Math.abs(arr[i] - average);
        }
    }

    public static double calculateAverageArrayValue(double[] arr) {
        double sum = 0;
        for (double d : arr) {
            sum += d;
        }
        return sum / arr.length;
    }

    // Just for testing ...
    public static void main(String[] args) {
        double[] arr = createArray(8);
        System.out.println(Arrays.toString(arr));
        printArray(arr, 4);

        for (int i = 0; i < 4; i++) {
            diffArray(arr);
            System.out.println(Arrays.toString(arr));
            printArray(arr, 4);
        }
    }
}
