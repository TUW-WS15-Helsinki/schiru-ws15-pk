/*
    Aufgabe3) Rekursive Datenstrukturen und Interface

    Ein ListItem ist ein Gegenstand mit einer bestimmten Größe, der einen
    weiteren Gegenstand als "Nachfolger" hat. Das kann zum Beispiel ein
    Gegenstand sein, den man auf einen anderen Gegenstand (den Nachfolger in
    einem Stapel) legen kann, oder ein Gegenstand in dem man einen weiteren
    Gegenstand aufbewahren kann. Die Klasse Box repräsentiert eine Box mit einer
    bestimmten Größe und Farbe. In jeder Box kann wieder eine Box aufbewahrt
    werden, die wiederum eine Box enthalten kann, etc.. Eine Box-Referenz steht
    also im allgemeinen für ein Boxenset (die äußerste Box und alle enthaltenen
    Boxen).

    Im untenstehenden Programmgerüst sollen folgende Methoden vervollständigt
    werden:
    - countColors:        gibt eine Häufigkeitstabelle in Form einer Map zurück.
                          Für jede in dem Boxenset vorkommende Farbe (Schlüssel)
                          wird die Häufigkeit (Wert) gespeichert.
    - hasColor:           liefert true dann und nur dann, wenn es im Boxenset
                          eine Box mit der angegebene Farbe gibt.
    - isWellNested:       liefert true dann und nur dann, wenn jede Box im
                          Boxenset nur kleinere Boxen enthält.
    - isSortedDescending: eine static Methode, die dann und nur dann true
                          liefert, wenn jeder Gegenstand in der durch den
                          Startknoten angegebenen Liste von Gegenständen nur
                          kleinere Nachfolger hat.
    - averageSizeReduction: liefert die das durchschnittliche Größenverhältnis
                            (double-Wert) zwischen zwei aufeinanderfolgenden
                            Boxen im Boxenset zurückgibt. Dieser Wert soll 0.0
                            sein, wenn es nur eine Box gibt. Schreiben Sie diese
                            Methode sowohl als Objekt-Methode in der Klasse Box
                            als auch als static Methode
                            Aufgabe5.averageSizeReduction. Die Objekt-Methode
                            soll ohne Verwendung von
                            Aufgabe3.averageSizeReduction implementiert werden.
                            Die Methode kann - muss aber nicht - ohne Verwendung
                            von Schleifen programmiert werden.

    Zusatzfragen:
    1. Welche Änderungen wären sinnvoll, wenn man in einer Box anstelle einer
       Box ein Item oder ListItem aufbewahren will?
    2. Warum kann man für die Implementierung von "isWellNested" die Methode
       "isSortedDescending" nutzen? Wäre das umgekehrt auch möglich?
    3. Wie ist der Ansatz, um die Methode Aufgabe3.averageSizeReduction so zu
       implementieren, dass sie ohne Schleife auskommt? (Hinweis: zusätzliche
       Methoden, die wieder ohne Schleife auskommen, sind erlaubt).

*/
import java.util.HashMap;

class Box implements ListItem {

    private String color;
    private int size;
    private Box content;

    //constructor: initializes this box with color and size, this box has no
    //content
    public Box(String color, int size) {
        this.color = color;
        this.size = size;
        this.content = null;
    }

    //constructor: initializes this box with color, size and content
    public Box(String color, int size, Box content) {
        this(color, size);
        this.content = content;
    }

    //returns a map with frequencies for each color occurring in the set of
    //boxes
    public HashMap<String, Integer> countColors() {

        HashMap<String, Integer> colors = null;

        if (this.content == null) {
            colors = new HashMap<>();
            colors.put(this.color, 1);
        }
        else {
            colors = content.countColors();
            colors.put(this.color, (colors.containsKey(this.color) ? colors.get(this.color) + 1 : 1) );
        }
        return colors;
    }

    //returns true if at least one of the boxes in the set has the specified
    //color
    public boolean hasColor(String color) {
        return this.countColors().containsKey(color);
    }

    //returns the size of this box
    public int getSize() {
        return size;
    }

    //returns the box contained in this box
    public Box getNext() {
        return content;
    }

    //true if and only if every box (this box and all nested boxes) contains
    //only smaller boxes
    public boolean isWellNested() {
        return this.content == null || this.getSize() > content.getSize() && content.isWellNested();
    }

    public double averageSizeReduction() {
        if (this.getNext() == null) {
            return 0d;
        }
        else {
            double average = this.getNext().averageSizeReduction();
            if (average > 0d) {
                return (this.getSize() - this.getNext().getSize())/2 + average/2;
            }
            else {
                return this.getSize() - this.getNext().getSize();
            }
        }
    }

    //returns a readable representation of the box with all contained boxes.
    public String toString() {
        return "[" + this.color + " " + this.size + " " + this.content + "]";
    }
}

//An item with a specific size
interface Item {
    public int getSize();
}

//An item with a specific size that is part of a list of items. It therefore
//also has reference to the next item.
interface ListItem extends Item {
    //should be declared or overridden by every subclass (incl. abstract
    //subclasses)
    public ListItem getNext();
}

public class Aufgabe3 {

    //returns true if every item in the specified list of items has only
    //successors with smaller size, false otherwise (recursive check)
    public static boolean isSortedDescending(ListItem list) {
        if (list == null || list.getNext() == null) {
            return true;
        }
        else {
            return list.getSize() > list.getNext().getSize() && isSortedDescending(list.getNext());
        }
    }

    public static double averageSizeReduction (ListItem list) {

        if (list == null || list.getNext() == null) {
            return 0d;
        }
        else {
            double average = averageSizeReduction(list.getNext());
            if (average > 0d) {
                return (list.getSize() - list.getNext().getSize())/2 + average/2;
            }
            else {
                return list.getSize() - list.getNext().getSize();
            }
        }
    }

    // just for testing ...
    public static void main(String[] args) {
        Box box = new Box("red", 10, new Box("blue", 9, new Box("red", 8, new Box("grey", 7))));
        System.out.println(box.countColors());
        System.out.println(box.hasColor("grey"));
        System.out.println(box.hasColor("black"));
        System.out.println(box.isWellNested());
        System.out.println(isSortedDescending(box));
        System.out.println(averageSizeReduction(box));
        System.out.println(box.averageSizeReduction());
    }
}