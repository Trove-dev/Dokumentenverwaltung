package db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import Verarbeitung.ServiceLocator;

/**
 * Klasse, welche das persistente Speichern der Software ermöglicht
 */
public class PersistenzDB implements PersistenzIF, Serializable{
	
	private static final long serialVersionUID = 1L;
	private static PersistenzDB uniqueInstance = null;
	
	/**
	 * Konstruktor und Singleton
	 */
	private PersistenzDB() {}
	public static PersistenzDB getInstance() {
		if (uniqueInstance == null) uniqueInstance = new PersistenzDB();
		return uniqueInstance;
	}

	/**
	 * Speichern der Daten in einer Datei
	 * 
	 * @param dateiname Dateiname, in dem die Daten gespeichert werden
	 * @param sl In diesem befinden sich die ganzen Daten, welche abgespeichert werden
	 */
	public void speicher(String dateiname, ServiceLocator sl) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(dateiname));
			out.writeObject(sl);
			out.close();
		} catch (IOException e) {
			System.out.println("Fehler beim Speichern aufgetreten!");
			e.printStackTrace();
			System.exit(0);
		}
	}

	/**
	 * Auslesen der gespeicherten Daten
	 * Beim fehlerhaften Laden wird die Fehlermeldung ausgegeben und das System beedent
	 * 
	 * @param dateiname Dateiname, aus dem Ausgelesen werden soll
	 * 
	 * @return 	Beim erfolgreichen Laden von Daten wird der ServiceLocator zurückgegeben 
	 * 			Bei nicht vorhandener Datei, oder leerer Datei wird "null" zurückgegeben, damit eine neue Datei angelegt werden kann
	 */
	public ServiceLocator lade(String dateiname) {
		ServiceLocator sl = null;
		File datei = new File(dateiname);
		if(!datei.exists()) {
			System.out.println("File existiert nicht...");
			return null;
		}else if(datei.length() == 0) {
			System.out.println("File ist leer, es ist nichts zu laden...");
			return null;
		}		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(dateiname));
			sl = (ServiceLocator) in.readObject();
			in.close();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Fehler beim Laden aufgetreten!");
			e.printStackTrace();
			System.exit(0);
		}
		return sl;
	}

}
