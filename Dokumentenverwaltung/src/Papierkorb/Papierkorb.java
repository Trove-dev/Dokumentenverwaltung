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
	 * �berpr�fung, ob Paperkorb leer ist
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
	 * Hinzuf�gen einer Datei zu dem Papierkorb
	 * wird nach dem L�schen einer Datei ausgef�hrt
	 * 
	 * @param datei Dateiobjekt, welches zu dem Papierkorb hinzugef�gt wird
	 */
	public void hochladeDateiPapierkorb(Datei datei) {
		papierkorb.add(datei);
	}
	
	/**
	 * Wiederherstellen einer Datei aus dem Papierkorb
	 * 
	 * @param name Name der Datei, welche aus dem Papierkorb wiederhergestellt werden soll
	 * 
	 * @return Es wird ein Dateiobjekt, welches ebend aus dem Papierkorb gel�scht wurde, wieder zur�ckgeliefert
	 * 			(dies dient dem darauffolgenden Hinzuf�gen in die gesamtListe aller Dokumente)
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
