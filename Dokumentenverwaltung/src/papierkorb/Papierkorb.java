package papierkorb;

import Datei.Datei;

public class Papierkorb {
	
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
