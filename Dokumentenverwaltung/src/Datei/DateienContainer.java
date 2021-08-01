package Datei;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import Tag.Tag;
import UI.HilfUI;

/**
 * Der DateinenContainer dient zur Verwaltung von Dateien
 * z.B. Hochladen oder Entfernen von Dateien
 * Die Dateien werden in einer ArrayList<Datei> abgespeichert
 * 
 */
public class DateienContainer implements DateienContainerInterface, Serializable{

	private static final long serialVersionUID = 1L;
	private static DateienContainer uniqueInstance = null;
	private ArrayList<Datei> dateienListe = new ArrayList<>();
	
	/**
	 * Konstruktor und Singleton
	 */
	private DateienContainer() {
	}
	public static DateienContainer getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new DateienContainer();
		}
		return uniqueInstance;
	}
	
	/**
	 * Methode zum hochladen von neuen Dateien
	 * Dabei wird ein neues Objekt vom Typ Datei erzeugt und in die ArrayList dateinenListe hinzugefügt
	 * Eception, um Abstürze bei korrupten/nicht vorhandenen Dateien zu verhindern
	 * 
	 * BasicFileAttributes:		liest die Metadaten von Daten aus
	 * FileOwnerAttributeView:	kann den Ersteller von einer Datei auslesen
	 * 
	 * @param file der Dateipfad zu der Datei
	 * @param name Name der zu speicherden Datei
	 * 
	 * @return Erfolgreich oder Fehlgeschlagen
	 */
	public boolean hochladeDatei(Path file, String name) {
		try {
			BasicFileAttributes a = Files.readAttributes(file, BasicFileAttributes.class);
			FileOwnerAttributeView b = Files.getFileAttributeView(file, FileOwnerAttributeView.class);
			
			if (a.isDirectory() == false) {
				String extension = "";
				int t = name.lastIndexOf(".");
				extension = name.substring(t);
				
				if (dublikatFinden(file) == true) {
					System.out.println("\nDiese Datei wurde bereits hinzugefügt!");
					return false;
				}
				else {
					String tmpOwner = b.getOwner().toString();
					String tmpCreationTime = HilfUI.fileTime(a.creationTime());
					String tmpLastModiefiedTime = HilfUI.fileTime(a.lastModifiedTime());
					String tmpfile = file.toString();
					Datei tmp = new Datei(name, tmpOwner, tmpCreationTime, tmpLastModiefiedTime, extension ,a.size(), tmpfile);
					dateienListe.add(tmp);
					return true;
				}
			}
			else {
				System.out.println("\nEs handelt sich bei " + name + " um ein Ordner!");
				return false;
			}
		}
		catch(Exception e) {
			System.out.println("\nDatei konnte nicht geladen werden! (Nicht vorhanden oder korrupt)\n Fehlercode: " + e);
			return false;
		}
	}
	
	/**
	 * Lädt ein schon vorhandenes Objekt vom Typ Datei hoch und fügt es zur ArrayList hinzu
	 * Wird verwendet, um ein wiederherstellen aus dem Papierkorb zu ermöglichen
	 * 
	 * @param datei Datei, welches wieder in die dateienListe hinzugefügt werden soll
	 * 
	 * @return Erfolgreich oder Fehlgeschlagen
	 */
	public boolean hochladeObjekt(Datei datei) {
		try {
			if (dublikatFinden(Paths.get(datei.getDateiPfad())) == true) {
				System.out.println("\nDiese Datei wurde bereits hinzugefügt!");
				return false;
			}
			else {
				Datei uploadDatei = new Datei(datei.getName(), datei.getErsteller(), datei.getErstellungsDatum(), datei.getDatumVonletzterAenderung(), datei.getDateiPfad(), datei.getGroesse(), datei.getName());
				uploadDatei.setKommentar(datei.getKommentar());
				uploadDatei.setTags(datei.getTags());
				uploadDatei.setVerknuepfung(datei.getVerknuepfung());
				dateienListe.add(uploadDatei);
				return true;
			}
		}
		catch(Exception e) {
			System.out.println("Datei konnte nicht geladen werden! \nFehlercode: " + e);
			return false;
		}
	}
	
	/**
	 * Ausgabe aller Dateien in der Konsole
	 */
	public void zeigeAlleDateienDetails() {
		for(Datei a:dateienListe) {
			a.anzeigeDateiDetail();
		}
		System.out.println();
	}
	
	/**
	 * Gibt die Liste mit allen Dateiobjekten aus
	 * 
	 * @return Liste mit allesn Dateiobjekten wird zurückgegeben
	 */
	public ArrayList<Datei> getAlleDateien(){
		if (dateienListe.isEmpty() == true) {
			return null;
		}
		else {
			return dateienListe;
		}
	}
	
	/**
	 * Dient beim Hochladen zum Untersuchen, ob die Datei bereits im System gespeichert wurde
	 * dabei werden die Dateipfade verglichen
	 * 
	 * @param file Eingabedateipfad
	 * 
	 * @return handelt sich bei file um ein Dublikat?
	 */
	private boolean dublikatFinden(Path file) {
		for (Datei a:dateienListe) {
			if (a.getDateiPfad().equals(file.toString())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Dublikate werden entfernt. Es werden alle Elemente a in die results ArrayList geschrieben, 
	 * wenn diese nicht dort bereits vorhanden sind
	 * 
	 * @param inputList ausgefüllte Liste mit möglichen Dublikaten
	 * 
	 * @return Liste, aus der die Dubliakte entfernt wurden
	 */
	private ArrayList<Datei> dublikatEntfernen(ArrayList<Datei> inputList) {
		ArrayList <Datei> results = new ArrayList<>();
		for (Datei a : inputList) {
			if (!results.contains(a)) {
				results.add(a);
			}
		}
		return results;
	}
	
	/**
	 * Suchfunktion, welche es ermöglicht nach Dateien zu suchen, welche gemeinsame Tags enthalten
	 * Es können mehrere Tags angegeben werden
	 * 
	 * @param tagsNames Liste mit den Tags, nach welchen gesucht werden soll
	 * 
	 */
	@Override
	public void sucheDateiTags(ArrayList<String> tagsNames) {                
		ArrayList<Datei> tmp = new ArrayList<>();		
		for (Datei d:dateienListe) {
			boolean[]gefunden = new boolean[tagsNames.size()];
			boolean isTrue = true;
			if(d.getTags() != null) {
				for(Tag t:d.getTags()) {
					for(int i = 0; i < tagsNames.size(); i++) {
						if(t.getKey().compareTo(tagsNames.get(i)) == 0) {
							gefunden[i] = true;
							break;
						}
					}
				}
			}
			for(boolean b:gefunden) {
				if(b == false) {
					isTrue = false;
					break;
				}
			}
			if(isTrue) {
				tmp.add(d);
			}
		}
		if(!tmp.isEmpty()) {
			String tags = "";
			for(String t:tagsNames) tags = tags + t + "  ";
			System.out.println("\nDiese Dateien wurden gefunden mit Tag(s): " + tags +"\n");
			Collections.sort(tmp);
			for(Datei dat:tmp) {
				dat.anzeigeDateiDetail();
			}
		}
		else System.out.println("Es wurde keine Dateien gefunden\n");
	}
	
	/**
	 * Überprüfen nach einer Datei mit spezifierten Namen
	 * 
	 * @param name Name, nach dem gesucht werden soll
	 * 
	 * @return Dateiobjekt, welches zu dem Namen name gepasst hat
	 */
	public Datei checkFile(String name) {
		for (Datei a:dateienListe) {
			if(a.getName().compareTo(name) == 0) {
				return a;
			}
		}
		return null;
	}
	
	/**
	 * Suchfunktion, welche nach Namen, Tags, Dateityp, verlinketen Dateien und Kommentaren sucht
	 * Dann wird die neue ArrayList von Dublikaten befreit und ausgegeben
	 * 
	 * @param suchWort Wort, nach dem gesucht werden soll
	 */
	public void sucheDatei(String suchWort) {
		ArrayList <Datei> tmp = new ArrayList<>();
		for (Datei a:dateienListe) {				//Suche nach Namen
			if (a.getName().contains(suchWort)) {
				tmp.add(a);
			}
		}
		for (Datei b:dateienListe) {	//Suche nach Tag
			if(b.getTags() != null) {
				for (Tag t:b.getTags()) {
					if (t.getKey().contains(suchWort)) {
						tmp.add(b);
					}
				}
			}
		}
		for (Datei c:dateienListe) {				//Suche nach Dateitypen
			if (c.getFormat().contains(suchWort)) {
				tmp.add(c);
			}
		}
		for(Datei d:dateienListe) {                //Suche nach verlinkten Dateien
			HashSet<Datei> hs = d.getVerknuepfung();
			if(hs != null && !hs.isEmpty()) {
				for(Datei e:hs)
					if(e.getName().contains(suchWort)) {
						tmp.add(d);
					}
			}
		}
		for(Datei f:dateienListe) {			//Suche nach Kommentaren
			if(f.getKommentar() != null && f.getKommentar().contains(suchWort))
				tmp.add(f);
		}
		ArrayList <Datei> results = dublikatEntfernen(tmp);
		if (results.isEmpty() == false) {		//Ausgabe
			Collections.sort(results);
			for (Datei x:results) {
				x.anzeigeDateiDetail();
			}
		}
		else {
			System.out.println("\nEs konnten keine Dateien mit dem Suchwort " + suchWort + " gefunden werden!");
		}
	}
}
