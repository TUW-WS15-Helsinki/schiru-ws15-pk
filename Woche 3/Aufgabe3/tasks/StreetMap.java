import java.util.*;

/*
    Aufgabenstellung zur Klasse StreetMap:

    Um die Suche nach einer Zieladresse zu erleichtern, haben die Fahrer in Karlis Fahrradbotendienst einen einfachen
    elektronischen Stadtplan bei sich: Nach Eingabe einer Adresse erscheinen der Name des entsprehenden Distrikts und
    die GPS-Koordinaten. Objekte der Klasse StreetMap stellen einen wesentlichen Teil des Stadtplans dar.
    Vervollständigen Sie die Implementierung, so wie in den Kommentaren beschrieben.

    Bitte verwenden Sie in der Implementierung von StreetMap KEIN Array, sondern nur Objekte von Typen wie Queue, Deque
    und Map (neben MapData - siehe unten).

    Zusatzfragen:
    1. Wieso ist die zusätzliche Klasse MapData sinnvoll?
    2. Wie werden die Daten in MapData zugreifbar? Geht das auch, wenn die Objektvariablen private sind?
    3. Wofür ist Queue besser geeignet, wofür Map?
    4. Warum ist Double mit großem Anfangsbuchstaben geschrieben (nicht double)?
    5. Sollte MapData auch die Adresse enthalten? Warum?
*/

public class StreetMap {

    private TreeMap<String, MapData> data = new TreeMap<>();

    // TODO: Object variables shall be declared here.

    // The parameters specify the initial contents of a street map:
    //     addr:  addresses that can be found in the street map
    //     distr: names of the districts of corresponding addresses
    //     lon:   longitudes in the GPS coordinates
    //     lat:   latitude in the GPS coordinates
    // All queues in the parameters are of the same size.
    // All entries at the same position (1st, 2nd, 3rd, ...) belong together.
    public StreetMap(Queue<String> addr, Queue<String> distr, Queue<Double> lon, Queue<Double> lat) {
        while(addr.peek() != null) {
            MapData entry = new MapData(distr.poll(), lat.poll(), lon.poll());
            data.put(addr.poll(), entry);
        }
    }

    // Returns all data (district and GPS coordinates) for address addr.
    // Returns null if no data can be found for this address.
    public MapData find(String addr) {
        return data.containsKey(addr) ? data.get(addr) : null;
    }

    // Returns all data (district and GPS coordinates) for all known address that begin with addrPart.
    public Queue<MapData> findAll(String addrPart) {
        Queue<MapData> result = new LinkedList<>();

        Iterator<String> addrIterator = data.keySet().iterator();
        while(addrIterator.hasNext()) {
            String addr = addrIterator.next();
            if (addr.startsWith(addrPart))
                result.offer(find(addr));
        }
        return result;
    }

    // Returns true if (and only if) address addr is in district distr.
    public boolean inDistrict(String addr, String distr) {
        MapData result = find(addr);

        return result != null && result.district.equals(distr);
    }

    // Adds a new address addr to the street map, where newData are the data to be associated with this address.
    // However, if the address already exists, the old data are replaced with newData.
    // true is returned if the address was replaced, false if a new address was added.
    public boolean newData(String addr, MapData newData) {
        boolean replace = data.containsKey(addr);
        data.put(addr, newData);
        return replace;
    }

    // To test the implementation several objects of StreetMap (each with several addresses) are created,
    // all methods are called, and data associated with found addresses are printed.
    public static void main(String[] args) {
        Queue<String> addr = new LinkedList<>();
        Queue<Double> lat = new LinkedList<>();
        Queue<Double> lon = new LinkedList<>();
        Queue<String> district = new LinkedList<>();

        // Favoriten, Taubstummengasse
        addr.offer("Taubstummengasse");
        lat.offer(10.0);
        lon.offer(10.0);
        district.offer("Favoriten");

        // Favoriten, Keplerplatz
        addr.offer("Keplerplatz");
        lat.offer(10.1);
        lon.offer(10.1);
        district.offer("Favoriten");

        // Favoriten, Otto-Probst-Platz
        addr.offer("Otto-Probst-Platz");
        lat.offer(10.1);
        lon.offer(10.1);
        district.offer("Favoriten");

        // Hietzing, OWS
        addr.offer("Otto-Wagner-Spital");
        lat.offer(14.0);
        lon.offer(14.0);
        district.offer("Hietzing");

        // Ottakring, Yeppenplatz
        addr.offer("Yppenplatz");
        lat.offer(16.0);
        lon.offer(16.0);
        district.offer("Ottakring");

        // Ottakring, Ottakringer Straße
        addr.offer("Ottakringer Straße");
        lat.offer(16.1);
        lon.offer(16.1);
        district.offer("Ottakring");

        StreetMap map = new StreetMap(addr, district, lon, lat);
        map.find("Keplerplatz");
        map.findAll("Ott");
        map.inDistrict("Keplerplatz", "Favoriten");
        map.inDistrict("Keplerplatz", "Ottakring");

        map.newData("Stephansplatz", new MapData("Innere Stadt", 1.0, 1.0));
        map.newData("Keplerplatz", new MapData("Neuhaus", 5.0, 5.0));
    }
}

// Objects of MapData hold all data (district and GPS coordinates) associated with an address in the street map.
// It is necessary to get access to the data in objects of this type.
class MapData {
    public String district;
    public double lat;
    public double lon;

    public MapData (String district, double lat, double lon) {
        this.district = district;
        this.lat = lat;
        this.lon = lon;
    }

}
