package Papierkorb;

import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.Iterator;

import Datei.Datei;
import UI.HilfUI;

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
		papierkorb.clear();
	}

	public static Papierkorb getInstance() {
		if(uniqueInstance == null) uniqueInstance = new Papierkorb();
		return uniqueInstance;
	}

	
}
