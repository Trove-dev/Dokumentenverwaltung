package Verlinkung;

import java.io.Serializable;

import Datei.Datei;

public class VerknuepfungVonDateien implements Serializable{
	
	private Datei listeDateien[];
	private static VerknuepfungVonDateien uniqueInstance = null;

	public VerknuepfungVonDateien() {
		
	}
	
	public void verbindeDatei(Datei dokument) {
		
	}
	
	public void entkoppleDatei(Datei dokument) {
			
	}

	public static VerknuepfungVonDateien getInstance() {
		if(uniqueInstance == null) uniqueInstance = new VerknuepfungVonDateien();
		return uniqueInstance;
	}
		
}
