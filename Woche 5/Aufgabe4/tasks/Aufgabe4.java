/*
    Aufgabe4) toString, equals, hashCode implementieren

    Implementieren Sie die Klasse Person mit folgenden Eigenschaften:

    - Vorname
    - Nachname
    - Geschlecht
    - Alter (als Zahl)
    - Sozialversicherungsnummer (als vierstellige Zahl)
    - Liste aller Kinder dieser Person (Es kann beliebig viele Kinder,
      aber auch gar kein Kind geben.)

    Person soll keine Klasse sondern ein Interface sein. Nennen Sie das
    Interface PersonIF und verwenden dieses in der Klasse Person.
    Abgesehen von der Liste der Kinder sollen alle Eigenschaften im Konstruktor
    festgelegt werden. Vermeiden Sie unnötige "setter"-Methoden!

    Um ein Kind hinzufügen zu können, implementieren Sie die Methode:

      void neuesKind(PersonIF p);

    Stellen Sie sicher, dass die Ausgabe eines Objekts vom Typ Person
    mittels System.out.println(person) folgende Beschreibung dieser Person
    liefert (Implementierung von toString()):

    # <Vorname> <Nachname>, <Geschlecht>, <Alter> Jahre, Svnr: <SV-Nummer>

    Hat eine Person Kinder, so werden auch rekursiv alle Kinder dieser
    Person (sowie auch alle darunterliegenden Hierarchieebenen) bei der
    Ausgabe berücksichtigt. Jede Ebene wird mit einem Leerzeichen
    eingerückt, z.B:

    # <Vorname> <Nachname>, <Geschlecht>, <Alter> Jahre, Svnr: <SV-Nummer>
     # <Vorname Kind1> <Nachname Kind1>, <Geschlecht Kind1>, <Alter Kind1> Jahre, Svnr: <SV-Nummer Kind1>
     # <Vorname Kind2> <Nachname Kind2>, <Geschlecht Kind2>, <Alter Kind2> Jahre, Svnr: <SV-Nummer Kind2>
      # <Vorname Kind1 von Kind2> <Nachname Kind1 von Kind2>, <Geschlecht Kind1 von Kind2>, <Alter Kind1 von Kind2> Jahre, Svnr: <SV-Nummer Kind1 von Kind2>

    Implementieren Sie createHubert() so, dass die zurückgelieferte Person
    folgende Ausgabe hat:

    # Hubert Maier, maennlich, 88 Jahre, Svnr: 1234
     # Julia Maier, weiblich, 54 Jahre, Svnr: 1111
      # Thomas Maier, maennlich, 22 Jahre, Svnr: 2222
      # Gernot Maier, maennlich, 24 Jahre, Svnr: 3333
     # Gernot Mueller, maennlich, 40 Jahre, Svnr: 1115
      # Roman Mueller, maennlich, 12 Jahre, Svnr: 1116
      # Sophie Mueller, weiblich, 14 Jahre, Svnr: 1117

    createJulia() soll innerhalb von createHubert() verwendet werden
    und createThomas() innerhalb von createJulia(). Es dürfen weitere
    Hilfsmethoden verwendet werden.

    Stellen Sie außerdem sicher, dass Kinder jünger als Eltern sind und das auch
    andere ungültige Werte abgewiesen werden (Alter<0, Svnr<0 oder > 9999).

    Innerhalb der Liste der Kinder einer Person, darf es keine zwei
    gleichen Objekte vom Typ Person geben. Zwei Personen sind gleich, wenn
    ihr Vor- und Nachname, sowie ihre Sozialversicherungsnummer gleich ist.

    Achten Sie auch darauf, dass hashCode() und equals() korrekt funktionieren
    und alle Modifier korrekt sind.

    Kommentieren Sie Ihr Programm ausreichend!

    Zusatzfragen:
    1. Wie verhält sich das Programm wenn es keine eigene Implementierung
       von equals gibt?
    2. Warum sollte man zur textuellen Beschreibung einer Klasse toString
       verwenden und keine Methode mit einem anderen Namen implementieren?
    3. Warum wird hashCode benötigt und wie kann der Rückgabewert dieser
       Methode interpretiert werden?
    4. Warum eignet sich hashCode() nicht für Vergleiche von Objekten?
    5. Inwiefern können Namen und Kommentare altern?
*/

import java.util.HashSet;

class Person implements PersonIF {

    private String vorname;
    private String nachname;
    private String geschlecht;
    private int alter;
    private int svnr;
    private HashSet<PersonIF> kinder;

    public Person(String vorname, String nachname, String geschlecht, int alter, int svnr) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.geschlecht = geschlecht;
        this.alter = alter;
        this.svnr = svnr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (svnr != person.svnr) return false;
        if (!vorname.equals(person.vorname)) return false;
        if (!nachname.equals(person.nachname)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = vorname.hashCode();
        result = 31 * result + nachname.hashCode();
        result = 31 * result + geschlecht.hashCode();
        result = 31 * result + alter;
        result = 31 * result + svnr;
        result = 31 * result + (kinder != null ? kinder.hashCode() : 0);
        return result;
    }

    @Override
    public void neuesKind(PersonIF p) {
        if (kinder == null) kinder = new HashSet<>();

        if (p instanceof Person) {
            Person kind = (Person)p;

            if (kind.alter < this.alter && kind.alter >= 0 && kind.svnr >= 0 && kind.svnr <= 9999)
                kinder.add(p);
        }
    }

    @Override
    public String toString() {
        return toString(0);
    }

    private String createStringIndent(int indent) {
        String output = "";
        for (int i = 0; i < indent; i++) {
            output += " ";
        }
        return output;
    }
    public String toString(int indent) {
        // # <Vorname> <Nachname>, <Geschlecht>, <Alter> Jahre, Svnr: <SV-Nummer>
        String output = "";
        output += "# " + vorname + ' ' + nachname + ", " + geschlecht + ", " + alter + " Jahre, " + "Svnr: " + svnr;

        if (kinder != null && kinder.size() > 0) {
            indent++;
            for(PersonIF kind : kinder) {
                output += '\n' + createStringIndent(indent);
                output += kind.toString(indent);
            }
        }

        return output;
    }
}
public class Aufgabe4 {

    public static PersonIF createHubert() {
        Person hubert = new Person("Hubert", "Maier", "maennlich", 88, 1234);
        hubert.neuesKind(createJulia());
        hubert.neuesKind(createGernot());

        return hubert;
    }

    public static PersonIF createJulia() {
        Person julia = new Person("Julia", "Maier", "weiblich", 54, 1111);
        julia.neuesKind(createThomas());
        julia.neuesKind(new Person("Gernot", "Maier", "maennlich", 24, 3333));

        return julia;
    }

    public static PersonIF createGernot() {
        Person gernot = new Person("Gernot", "Mueller", "maennlich", 40, 1115);
        gernot.neuesKind(new Person("Roman", "Mueller", "maennlich", 12, 1116));
        gernot.neuesKind(new Person("Sophie", "Mueller", "weiblich", 14, 1117));

        return gernot;
    }

    public static PersonIF createThomas() {
        return new Person("Thomas", "Maier", "maennlich", 22, 2222);
    }

    // just for testing ...
    public static void main(String[] args) {
//        # Hubert Maier, maennlich, 88 Jahre, Svnr: 1234
//           # Julia Maier, weiblich, 54 Jahre, Svnr: 1111
//              # Thomas Maier, maennlich, 22 Jahre, Svnr: 2222
//              # Gernot Maier, maennlich, 24 Jahre, Svnr: 3333
//          # Gernot Mueller, maennlich, 40 Jahre, Svnr: 1115
//              # Roman Mueller, maennlich, 12 Jahre, Svnr: 1116
//              # Sophie Mueller, weiblich, 14 Jahre, Svnr: 1117

        System.out.println(createHubert());
    }
}