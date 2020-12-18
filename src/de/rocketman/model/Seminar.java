package de.rocketman.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Seminar extends Schulung {

    protected static final int TAGESSATZ = 500;
    protected IntegerProperty anzahlTage;

    /**
     * Einfacher Konstruktor ohne Parameter, Werte muessen per set...() geschrieben werden
     */
    public Seminar() {
        this(0, "", "", 0); // mit 0 und "" initialisieren
    }

    /**
     * Konstruktur zum Erzeugen eines Shculung Objekts mit Nummer, Ort und Thema
     * @param nummer Die Nummer der Schulung
     * @param ort Die Stadt, in der die Schulung stattfindet
     * @param thema Das Thema der Schulung
     */
    public Seminar(int nummer, String ort, String thema, int anzahlTage) {
        super(nummer, ort, thema);
        this.anzahlTage = new SimpleIntegerProperty(anzahlTage);
    }

    /**
     *  Setzt die Anzahl der Tage auf den uebergebenen Wert.
     * @param anzahlTage Die Anzahl der Seminartage
     */
    public void setAnzahlTage(int anzahlTage) {
        this.anzahlTage.set(anzahlTage);
    }

    /**
     * Gibt die Anzahl der Tage zurueck.
     * @return Die Anzahl der Seminartage
     */
    public int getAnzahlTage() {
        return anzahlTage.get();
    }

    /**
     * Gibt die Anzahl der Tage als Property zurueck
     * @return Die Anzahl der Seminartage
     */
    public IntegerProperty anzahlTageProperty() {
        return  anzahlTage;
    }

    /**
     * Berechnet den Preis p.P. des Simars als Produkt aus Anzahl Tage und Tagessatz.
     * @return Der Preis p.P. des Seminars.
     */
    @Override
    public int getPreisProPerson() {
        // Kostem = #Tage x Tagessatz
        return anzahlTage.get() * TAGESSATZ;
    }

    /**
     * Gibt eine Textrepraesentation des Objekts zurueck.
     * @return Die Texpraesentation des Objekts.
     */
    @Override
    public  String toString() {
        return "Seminar: Nummer: " + nummer + ", Ort: " + ort
                + ", Thema: " + thema + ", Anzahl Tage: " + anzahlTage;
    }
}
