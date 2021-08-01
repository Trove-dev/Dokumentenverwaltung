package Nutzer;

import java.util.ArrayList;	
import java.io.Serializable;

/**
 * Der Nutzercontainer dient zur Verwaltung von Nutzern
 * Alle Nutzer werden in der ArrayList <Nutzer> gespeichert
 */
public class NutzerContainer implements NutzerContainerInterface, Serializable{
	
	private static final long serialVersionUID = 1L;
	private static NutzerContainer uniqueInstance = null;
	private ArrayList <Nutzer> listeNutzer = new ArrayList<Nutzer>();
	
	/**
	 * Konstruktor und Singleton
	 */
	private NutzerContainer() {
	}
	public static NutzerContainer getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new NutzerContainer();
		}
		return uniqueInstance;
	}
	
	/**
	 * Anlegen eines neuen Nutzers
	 * 
	 * @param name zur Überprüfung, ob der Nutzer bereits existiert
	 * @param rechte zum Festlegen der Rechte
	 * @param nameVollstaendig zum Festlegen des vollständigen Namens
	 * 
	 * @return Es wird der erstellte Nutzer zurückgegeben
	 */
	public Nutzer addNeuenNutzer(String name, Rechte rechte, String nameVollstaendig) {
		Nutzer n = null;
		if(sucheNutzer(name) == null && rechte != null) {
			n = new Nutzer(name, rechte, nameVollstaendig);
			listeNutzer.add(n);
		}
		return n;
	}

	/**
	 * Löschen eines Nutzers
	 * es wird anhand des Usernamens gelöscht
	 * 
	 * @param name Zum Suche nach dem Nutzer
	 */
	public void loescheNutzer(String name) {
		Nutzer delNutzer = sucheNutzer(name);
		listeNutzer.remove(delNutzer);
	}
	
	/**
	 * Nutzer Name bearbeiten
	 * 
	 * @param user
	 * @param nameNew
	 */
	public void bearbeiteNutzerName(Nutzer user, String nameNew) {
		user.setName(nameNew);
	}
	
	/**
	 * Nutzer Rechte bearbeiten
	 * 
	 * @param user
	 * @param rechteNew
	 */
	public void bearbeiteNutzerRechte(Nutzer user, Rechte rechteNew) {
		user.setRechte(rechteNew);
	}
	
	/**
	 * Nutzer vollständiger Name bearbeiten
	 * 
	 * @param user
	 * @param nameVollstaendigNew
	 */
	public void bearbeiteNutzerNameVollstandig(Nutzer user, String nameVollstaendigNew) {
		user.setNameVollstaendig(nameVollstaendigNew);
	}
	
	/**
	 * Nutzer suchen in der gesamten Liste aller Nutzer
	 * 
	 * @param name Es wird ein name übergeben, anhand dessen gesucht wird
	 */
	public Nutzer sucheNutzer(String name) {
		for(Nutzer user:listeNutzer) 
			if(user.getName().compareTo(name) == 0)
				return user;
		return null;		
	}
	
	
	public ArrayList<Nutzer> getListeNutzer() {
		return listeNutzer;
	}

	/**
	 * Ausgabe aller Nutzer in der Konsole
	 */
	public void printNutzerList() {
		for(Nutzer user:listeNutzer) 
			System.out.println("Name: " + user.getName() + "; Rechte : " + user.getRechte()+ "; vollständiger Name : " + user.getNameVollstaendig() + "\n");
		
	}
}
