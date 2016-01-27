import java.util.ArrayDeque;
import java.util.Deque;

/*
    Aufgabenstellung zur Klasse District:

    Karlis Fahrradbotendienst hat die Stadt in verschiedene Distrikte (engl. District) eingeteilt. Pro Distrikt
    gibt es eine Sammelstelle, in der sich Fahrer treffen und auf Aufträge warten. Führt ein Auftrag von einem
    Distrikt in einen anderen, kommt der Fahrer nach Erledigung des Auftrags zur Sammelstelle des Distrikts, in dem
    die Fahrt endet. Dort wartet der Fahrer auf einen Auftrag. Fahrer erhalten Aufträge in der Reihenfolge, in der
    Sie in der Sammelstelle eingetroffen sind. Manchmal kommt es zu ungleichen Auslastungen. Dann muss ein Fahrer, der
    als nächster eine Fahrt zu absolvieren hätte, in einen anderen Distrikt wechseln. Damit es zu keinen
    Ungerechtigkeiten kommt, wird ein solcher Fahrer gleich nach Eintreffen ganz nach vorne gereiht.

    Simulieren Sie diese Vorgehensweise in der Klasse District:
    Ein Objekt der Klasse erhält den Namen des Distrikts über einen Konstruktor. Es wird eine Uhr mitgeführt.
    Folgende Methoden werden benötigt:

    - tick:   Diese parameterlose Methode schaltet die Uhr um zehn Minuten weiter. Die Uhr startet mit 08:00 Uhr
              (Betriebsbeginn) und stoppt mit 18:00 Uhr (kann danach nicht mehr erhöht werden). Ausgaben der
              Uhrzeit erfolgen im Format hh:mm (je zwei Ziffern für Stunden und Minuten, getrennt durch :).

    - arrive: Ein Fahrer (Name als String-Parameter übergeben) trifft in der Sammelstelle des Distrikts ein.
              Eine Ausgabe wird gemacht: "Um <Uhrzeit> ist <Name des Fahrers> in <Name des Distrikts> eingetroffen."

    - help:   Ein Fahrer (Name als String-Parameter übergeben) trifft in der Sammelstelle des Distrikts ein, weil er
              von einem anderen Distrikt hierher gewechselt hat um auszuhelfen. Es wird dieselbe Ausgabe gemacht wie
              bei arrive.

    - job:    Ein Auftrag ist eingetroffen. Der Distrikt des Ziels der Fahrt wird als Parameter übergeben. Wenn kein
              Fahrer verfügbar ist, wird als Ergebnis false zurückgegeben, andernfalls true. Eine Ausgabe wird
              gemacht, entweder: "Um <Uhrzeit> Auftrag ohne Fahrer in <Name des Distrikts>."
              oder: "Um <Uhrzeit> fährt <Name des Fahrers> von <Name des Distrikts> nach <Name des Distrikts>."
              Dieselbe Methode wird verwendet, wenn ein Fahrer in einen anderen Distrikt wechseln soll.

    Verwenden Sie in District KEIN Array, sondern ein Objekt vom Typ Queue bzw. Deque.

    Schreiben Sie in District eine Methode main, die ein Gesamtszenario simuliert. Es sollen mehrere Distrikte erzeugt
    und mehrere Fahrer zwischen ihnen hin und her geschickt werden. Auch tick ist regelmäßig aufzurufen. In den
    entstehenden Bildschirmausgaben sollen alle wesentlichen unterschiedlichen Fälle sichtbar werden.

    Zusatzfragen:
    1. Was unterscheidet Queue von Deque?
    2. Was versteht man unter dem FIFO- bzw. LIFO-Prinzip?
    3. Welche dieser Methoden sind static, welche nicht? Warum ist das so?
*/
public class District {

    // Static
    public static int clock = 480; // Minutes since midnight, workday starts at 8 AM

    public static String getTimeOfDay() {
        int hours = District.clock / 60;
        int minutes = District.clock % 60;
        return (hours < 10 ? "0" : "") + hours  + ":" + (minutes < 10 ? "0" : "") + minutes;
    }

    public static void tick() {
        // Increase clock by 10 minutes if current time's prior to 18 o'clock
        District.clock += (District.clock / 60) < 18 ? +10 : 0;
    }

    // Member
    public String districtName;
    private Deque<String> availableDrivers = new ArrayDeque<>();
//    private Queue<String> availableJobs = new LinkedList<>(); // Hmmm, seems like jobs don't have to be saved if there's no driver available

    public District (String districtName) {
        this.districtName = districtName;
    }

    public void arrive(String driverName) {
        availableDrivers.add(driverName);

        log(String.format("Um %1$s ist %2$s in %3$s eingetroffen.", District.getTimeOfDay(), driverName, districtName));
    }

    public boolean job(District destination) {
        // If no driver is available
        if (availableDrivers.isEmpty()) {
//            availableJobs.add(destination.districtName);
            log(String.format("Um %1$s Auftrag ohne Fahrer in %2$s.", District.getTimeOfDay(), districtName));
            return false;
        } else {
            // Send available driver to destination
            log(String.format("Um %1$s fährt %2$s von %3$s nach %4$s.", District.getTimeOfDay(), availableDrivers.pop(), districtName, destination.districtName));
            return true;
        }
    }

    public void help(String driverName) {
        availableDrivers.addFirst(driverName);

        log(String.format("Um %1$s ist %2$s in %3$s eingetroffen.", District.getTimeOfDay(), driverName, districtName));

    }

    public void log(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        District favoriten = new District("Favoriten");
        District liesing = new District("Liesing");
        District ottakring = new District("Ottakring");

        ottakring.arrive("Peter");
        favoriten.arrive("Lissi");
        liesing.arrive("Karli");

        District.tick();

        favoriten.arrive("Paul"); // Paul always arrives late.

        District.tick();
        District.tick();

        ottakring.job(liesing);

        District.tick();

        favoriten.job(ottakring);

        District.tick();
        District.tick();

        liesing.arrive("Peter");
        liesing.job(ottakring);
        ottakring.job(liesing);

        District.tick();

        ottakring.arrive("Lissi");

        District.tick();

        ottakring.help("Karli");

        District.tick();

        ottakring.job(favoriten);

        District.tick();
        District.tick();
        District.tick();

        ottakring.job(favoriten);

        District.tick();

        favoriten.arrive("Karli");

        District.tick();

        favoriten.job(ottakring);

        // Lissi and Paul are still out for delivery
    }

}
