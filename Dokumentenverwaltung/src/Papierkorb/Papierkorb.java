package Papierkorb;

import java.io.Serializable;
import java.util.ArrayList;
import Datei.Datei;

/**
 * Klasse, welche den Papierkorb verwaltet
 */
public class Papierkorb implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static Papierkorb uniqueInstance = null;
	private ArrayList<Datei> papierkorb = new ArrayList<>();
	
	/**
	 * Konstruktor und Singleton
	 */
	private Papierkorb() {
	}
	public static Papierkorb getInstance() {
		if(uniqueInstance == null) uniqueInstance = new Papierkorb();
		return uniqueInstance;
	}
	
	/**
	 * Überprüfung, ob Paperkorb leer ist
	 * 
	 * @return true, wenn leer
	 */
	public boolean istLeer() {
		if (papierkorb.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Ausgabe aller Dateien im Detail, welche sich im Papierkorb befinden
	 */
	public void papierkorbAnzeigen() {
		for (Datei a:papierkorb) {
			a.anzeigeDateiDetail();
		}
	}

	/**
	 * Hinzufügen einer Datei zu dem Papierkorb
	 * wird nach dem Löschen einer Datei ausgeführt
	 * 
	 * @param datei Dateiobjekt, welches zu dem Papierkorb hinzugefügt wird
	 */
	public void hochladeDateiPapierkorb(Datei datei) {
		papierkorb.add(datei);
	}
	
	/**
	 * Wiederherstellen einer Datei aus dem Papierkorb
	 * 
	 * @param name Name der Datei, welche aus dem Papierkorb wiederhergestellt werden soll
	 * 
	 * @return Es wird ein Dateiobjekt, welches ebend aus dem Papierkorb gelöscht wurde, wieder zurückgeliefert
	 * 			(dies dient dem darauffolgenden Hinzufügen in die gesamtListe aller Dokumente)
	 */
	public Datei wiederherstelle (String name) {
		for (Datei a:papierkorb) {
			if (a.getName().equals(name)) {
				papierkorb.remove(a);
				return a;
			}
		}
		return null;
	}
	
	/**
	 * leeren des Papierkorbes
	 */
	public void leerePapierkorb() {
		papierkorb.clear();
	}
}
