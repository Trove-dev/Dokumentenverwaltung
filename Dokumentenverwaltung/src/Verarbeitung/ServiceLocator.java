package Verarbeitung;

import java.io.Serializable;
import Datei.DateienContainer;
import Datei.DateienContainerInterface;
import Nutzer.NutzerContainer;
import Nutzer.NutzerContainerInterface;
import Papierkorb.Papierkorb;
import Tag.TagsContainer;
import Tag.TagsContainerInterface;
import db.PersistenzDB;
import db.PersistenzIF;

/**
 * Klasse zur Lokalisierung der Container und Persistenz
 */
public class ServiceLocator implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static ServiceLocator uniqueInstance = null;
	
	private DateienContainerInterface dc;
	private TagsContainerInterface tc; 
	private Papierkorb pk;
	private NutzerContainerInterface nc;
	private PersistenzIF p;
	
	/**
	 * Konstuktor
	 */
	public ServiceLocator() {
		
		dc = DateienContainer.getInstance();
		tc = TagsContainer.getInstance();
		pk = Papierkorb.getInstance();
		nc = NutzerContainer.getInstance();
		p = PersistenzDB.getInstance();
	}
	
	/**
	 * Singleton
	 */
	public static ServiceLocator getInstance() {
		if (uniqueInstance == null) uniqueInstance = new ServiceLocator();
		return uniqueInstance;
	}

	public DateienContainerInterface getDateienContainer() {
		return dc;
	}

	public TagsContainerInterface getTagsContainer() {
		return tc;
	}

	public Papierkorb getPapierkorb() {
		return pk;
	}

	public NutzerContainerInterface getNutzerContainer() {
		return nc;
	}
	
	/**
	 * Speicherung aller Containerklassen
	 * 
	 * @param dateiname Name der Datei, welche am Ende auf dem PC gespeichert wird
	 * @param sl Daten, die gespeichert werden
	 */
	public void speicherAlleContainer(String dateiname, ServiceLocator sl) {
		p.speicher(dateiname, sl);
	}
	
	/**
	 * Auslesen aus der gespeicherten Datei
	 * 
	 * @param dateiname Name der Datei, die ausgelesen werden soll
	 * 
	 * @return den ausgelesenen ServiceLocator mit den gespeicherten Daten
	 */
	public ServiceLocator ladeAlleContainer(String dateiname) {
		ServiceLocator sl = p.lade(dateiname);
		return sl;
	}

	
	
	

}
