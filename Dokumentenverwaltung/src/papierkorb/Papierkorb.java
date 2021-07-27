package papierkorb;

import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;

import Datei.Datei;

public class Papierkorb implements Serializable{

	private static Papierkorb uniqueInstance = null;
	private ArrayList<Datei> papierkorb = new ArrayList<>();
	
	private Papierkorb() {
	}
	
	public boolean istLeer() {
		if (papierkorb.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void papierkorbAnzeigen() {
		for (Datei a:papierkorb) {
			a.anzeigeDateiDetail();
		}
	}

	public void hochladeDateiPapierkorb(Datei datei) {
		papierkorb.add(datei);
	}
	
	public Datei wiederherstelle (String name) {
		for (Datei a:papierkorb) {
			if (a.getName().equals(name)) {
				papierkorb.remove(a);
				return a;
			}
		}
		return null;
	}
	
	public void leerePapierkorb() {
		for (Datei a:papierkorb) {
			System.out.println(a.getName() + " wurde gelöscht!");
			papierkorb.remove(a);
		}
	}

	public static Papierkorb getInstance() {
		if(uniqueInstance == null) uniqueInstance = new Papierkorb();
		return uniqueInstance;
	}

	
}
