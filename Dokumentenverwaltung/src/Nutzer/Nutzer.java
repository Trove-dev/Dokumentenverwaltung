package Nutzer;

import java.io.Serializable;

/**
 * Klasse, welche Nutzerobjekte anlegt
 */
public class Nutzer implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name;
	private Rechte rechte;
	private String nameVollstaendig;
	
	/**
	 * Konstuktor für das Anlegen von Nutzern
	 * 
	 * @param name
	 * @param rechte
	 * @param nameVollstaendig
	 */
	public Nutzer(String name, Rechte rechte, String nameVollstaendig) {
		this.name = name;
		this.rechte = rechte;
		this.nameVollstaendig = nameVollstaendig;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Rechte getRechte() {
		return rechte;
	}

	public void setRechte(Rechte rechte) {
		this.rechte = rechte;
	}

	public String getNameVollstaendig() {
		return nameVollstaendig;
	}

	public void setNameVollstaendig(String nameVollstaendig) {
		this.nameVollstaendig = nameVollstaendig;
	}
	
	/**
	 * Ausgabe von Nutzer in der Konsole
	 */
	public void printNutzer() {
		System.out.print("Username: " + name + "; das Recht : " + rechte + "; Ihr Name: " + nameVollstaendig+"\n");
	}

}
