package papierkorb;

import java.io.Serializable;

import Datei.Datei;

public class Papierkorb implements Serializable{
	
	private String name;
	private double groesse;
	private static Papierkorb uniqueInstance = null;
	
	public Papierkorb() {
		
	}
	
	public void wiederherstelle(Datei datei) {
		
	}
	
	public void leerePapierkorb() {
		
	}

	public static Papierkorb getInstance() {
		if(uniqueInstance == null) uniqueInstance = new Papierkorb();
		return uniqueInstance;
	}

	
}
